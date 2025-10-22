// src/main/java/servlet/VehicleServlet.java
package servlet;

import dao.VehicleDao;
import model.Vehicle;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/vehicles")
public class VehicleServlet extends HttpServlet {
    private VehicleDao dao = new VehicleDao();
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        try {
            List<Vehicle> list = dao.findAll();
            request.setAttribute("list", list);
        } catch (Exception e) {
            request.setAttribute("error", e.getMessage());
        }
        request.getRequestDispatcher("/vehicles.jsp").forward(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        try {
            String action = request.getParameter("action");
            
            if ("add".equals(action)) {
                int id = Integer.parseInt(request.getParameter("vehicleId"));
                String vehicleNo = request.getParameter("vehicleNo");
                String vehicleType = request.getParameter("vehicleType");
                String vehicleModel = request.getParameter("vehicleModel");
                int batteryCapacity = Integer.parseInt(request.getParameter("batteryCapacity"));
                int customerId = Integer.parseInt(request.getParameter("customerId"));
                
                Vehicle v = new Vehicle(id, vehicleNo, vehicleType, vehicleModel, batteryCapacity, customerId);
                dao.create(v);
            } else if ("update".equals(action)) {
                int id = Integer.parseInt(request.getParameter("vehicleId"));
                String vehicleNo = request.getParameter("vehicleNo");
                String vehicleType = request.getParameter("vehicleType");
                String vehicleModel = request.getParameter("vehicleModel");
                int batteryCapacity = Integer.parseInt(request.getParameter("batteryCapacity"));
                int customerId = Integer.parseInt(request.getParameter("customerId"));
                
                Vehicle v = new Vehicle(id, vehicleNo, vehicleType, vehicleModel, batteryCapacity, customerId);
                dao.update(v);
            } else if ("delete".equals(action)) {
                int id = Integer.parseInt(request.getParameter("vehicleId"));
                dao.delete(id);
            }
        } catch (Exception e) {
            request.setAttribute("error", e.getMessage());
        }
        doGet(request, response);
    }
}