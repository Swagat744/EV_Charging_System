// src/main/java/servlet/PaymentServlet.java
package servlet;

import dao.PaymentDao;
import model.Payment;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@WebServlet("/payments")
public class PaymentServlet extends HttpServlet {
    private PaymentDao dao = new PaymentDao();
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        try {
            List<Payment> list = dao.findAll();
            request.setAttribute("list", list);
        } catch (Exception e) {
            request.setAttribute("error", e.getMessage());
        }
        request.getRequestDispatcher("/payments.jsp").forward(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        try {
            String action = request.getParameter("action");
            
            if ("add".equals(action)) {
                int id = Integer.parseInt(request.getParameter("paymentId"));
                double amount = parseDouble(request.getParameter("amount"));
                LocalDateTime paymentDate = parseDate(request.getParameter("paymentDate"));
                String paymentMode = request.getParameter("paymentMode");
                String status = request.getParameter("status");
                int sessionId = parseIntOrNull(request.getParameter("sessionId"));
                int stationId = parseIntOrNull(request.getParameter("stationId"));
                
                Payment p = new Payment(id, amount, paymentDate, paymentMode, status, sessionId, stationId);
                dao.create(p);
            } else if ("update".equals(action)) {
                int id = Integer.parseInt(request.getParameter("paymentId"));
                double amount = parseDouble(request.getParameter("amount"));
                LocalDateTime paymentDate = parseDate(request.getParameter("paymentDate"));
                String paymentMode = request.getParameter("paymentMode");
                String status = request.getParameter("status");
                int sessionId = parseIntOrNull(request.getParameter("sessionId"));
                int stationId = parseIntOrNull(request.getParameter("stationId"));
                
                Payment p = new Payment(id, amount, paymentDate, paymentMode, status, sessionId, stationId);
                dao.update(p);
            } else if ("delete".equals(action)) {
                int id = Integer.parseInt(request.getParameter("paymentId"));
                dao.delete(id);
            }
        } catch (Exception e) {
            request.setAttribute("error", e.getMessage());
        }
        doGet(request, response);
    }
    
    private LocalDateTime parseDate(String dateStr) {
        if (dateStr == null || dateStr.isEmpty()) {
            return null;
        }
        return LocalDateTime.parse(dateStr.replace(" ", "T"));
    }
    
    private double parseDouble(String val) {
        if (val == null || val.isEmpty()) {
            return 0.0;
        }
        return Double.parseDouble(val);
    }
    
    private int parseIntOrNull(String val) {
        if (val == null || val.isEmpty()) {
            return 0;
        }
        return Integer.parseInt(val);
    }
}