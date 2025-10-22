// src/main/java/servlet/ChargingSessionServlet.java
package servlet;

import dao.ChargingSessionDao;
import model.ChargingSession;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@WebServlet("/sessions")
public class ChargingSessionServlet extends HttpServlet {
    private ChargingSessionDao dao = new ChargingSessionDao();
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        try {
            List<ChargingSession> list = dao.findAll();
            request.setAttribute("list", list);
        } catch (Exception e) {
            request.setAttribute("error", e.getMessage());
        }
        request.getRequestDispatcher("/sessions.jsp").forward(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        try {
            String action = request.getParameter("action");
            
            if ("add".equals(action)) {
                int id = Integer.parseInt(request.getParameter("sessionId"));
                int vehicleId = Integer.parseInt(request.getParameter("vehicleId"));
                LocalDateTime startTime = parseDate(request.getParameter("startTime"));
                LocalDateTime endTime = parseDate(request.getParameter("endTime"));
                double energyConsumed = parseDouble(request.getParameter("energyConsumed"));
                double cost = parseDouble(request.getParameter("cost"));
                int stationId = Integer.parseInt(request.getParameter("stationId"));
                
                ChargingSession s = new ChargingSession(id, vehicleId, startTime, endTime, energyConsumed, cost, stationId);
                dao.create(s);
            } else if ("update".equals(action)) {
                int id = Integer.parseInt(request.getParameter("sessionId"));
                int vehicleId = Integer.parseInt(request.getParameter("vehicleId"));
                LocalDateTime startTime = parseDate(request.getParameter("startTime"));
                LocalDateTime endTime = parseDate(request.getParameter("endTime"));
                double energyConsumed = parseDouble(request.getParameter("energyConsumed"));
                double cost = parseDouble(request.getParameter("cost"));
                int stationId = Integer.parseInt(request.getParameter("stationId"));
                
                ChargingSession s = new ChargingSession(id, vehicleId, startTime, endTime, energyConsumed, cost, stationId);
                dao.update(s);
            } else if ("delete".equals(action)) {
                int id = Integer.parseInt(request.getParameter("sessionId"));
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
}