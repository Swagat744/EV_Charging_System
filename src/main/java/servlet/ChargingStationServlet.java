// src/main/java/servlet/ChargingStationServlet.java
package servlet;

import dao.ChargingStationDao;
import model.ChargingStation;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/stations")
public class ChargingStationServlet extends HttpServlet {
    private ChargingStationDao dao = new ChargingStationDao();
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        try {
            List<ChargingStation> list = dao.findAll();
            request.setAttribute("list", list);
        } catch (Exception e) {
            request.setAttribute("error", e.getMessage());
        }
        request.getRequestDispatcher("/stations.jsp").forward(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        try {
            String action = request.getParameter("action");
            
            if ("add".equals(action)) {
                int id = Integer.parseInt(request.getParameter("stationId"));
                String managerName = request.getParameter("managerName");
                String workingHour = request.getParameter("workingHour");
                int capacity = Integer.parseInt(request.getParameter("capacity"));
                String location = request.getParameter("location");
                
                ChargingStation s = new ChargingStation(id, managerName, workingHour, capacity, location);
                dao.create(s);
            } else if ("update".equals(action)) {
                int id = Integer.parseInt(request.getParameter("stationId"));
                String managerName = request.getParameter("managerName");
                String workingHour = request.getParameter("workingHour");
                int capacity = Integer.parseInt(request.getParameter("capacity"));
                String location = request.getParameter("location");
                
                ChargingStation s = new ChargingStation(id, managerName, workingHour, capacity, location);
                dao.update(s);
            } else if ("delete".equals(action)) {
                int id = Integer.parseInt(request.getParameter("stationId"));
                dao.delete(id);
            }
        } catch (Exception e) {
            request.setAttribute("error", e.getMessage());
        }
        doGet(request, response);
    }
}