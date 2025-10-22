// src/main/java/servlet/CustomerServlet.java
package servlet;

import dao.CustomerDao;
import model.Customer;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/customers")
public class CustomerServlet extends HttpServlet {
    private CustomerDao dao = new CustomerDao();
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        try {
            List<Customer> list = dao.findAll();
            request.setAttribute("list", list);
        } catch (Exception e) {
            request.setAttribute("error", e.getMessage());
        }
        request.getRequestDispatcher("/customers.jsp").forward(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        try {
            String action = request.getParameter("action");
            
            if ("add".equals(action)) {
                int id = Integer.parseInt(request.getParameter("customerId"));
                String name = request.getParameter("name");
                String email = request.getParameter("email");
                String phone = request.getParameter("phone");
                
                Customer c = new Customer(id, name, email, phone);
                dao.create(c);
            } else if ("update".equals(action)) {
                int id = Integer.parseInt(request.getParameter("customerId"));
                String name = request.getParameter("name");
                String email = request.getParameter("email");
                String phone = request.getParameter("phone");
                
                Customer c = new Customer(id, name, email, phone);
                dao.update(c);
            } else if ("delete".equals(action)) {
                int id = Integer.parseInt(request.getParameter("customerId"));
                dao.delete(id);
            }
        } catch (Exception e) {
            request.setAttribute("error", e.getMessage());
        }
        doGet(request, response);
    }
}