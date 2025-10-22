// src/main/java/dao/VehicleDao.java
package dao;

import model.Vehicle;
import util.Db;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class VehicleDao {
    
    public void create(Vehicle vehicle) throws Exception {
        String sql = "INSERT INTO Vehicle (Vehicle_ID, Vehicle_No, Vehicle_Type, Vehicle_Model, Battery_Capacity, Customer_ID) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = Db.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, vehicle.getVehicleId());
            ps.setString(2, vehicle.getVehicleNo());
            ps.setString(3, vehicle.getVehicleType());
            ps.setString(4, vehicle.getVehicleModel());
            ps.setInt(5, vehicle.getBatteryCapacity());
            ps.setInt(6, vehicle.getCustomerId());
            ps.executeUpdate();
        }
    }
    
    public List<Vehicle> findAll() throws Exception {
        List<Vehicle> list = new ArrayList<>();
        String sql = "SELECT * FROM Vehicle";
        try (Connection conn = Db.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Vehicle v = new Vehicle(
                    rs.getInt("Vehicle_ID"),
                    rs.getString("Vehicle_No"),
                    rs.getString("Vehicle_Type"),
                    rs.getString("Vehicle_Model"),
                    rs.getInt("Battery_Capacity"),
                    rs.getInt("Customer_ID")
                );
                list.add(v);
            }
        }
        return list;
    }
    
    public void update(Vehicle vehicle) throws Exception {
        String sql = "UPDATE Vehicle SET Vehicle_No = ?, Vehicle_Type = ?, Vehicle_Model = ?, Battery_Capacity = ?, Customer_ID = ? WHERE Vehicle_ID = ?";
        try (Connection conn = Db.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, vehicle.getVehicleNo());
            ps.setString(2, vehicle.getVehicleType());
            ps.setString(3, vehicle.getVehicleModel());
            ps.setInt(4, vehicle.getBatteryCapacity());
            ps.setInt(5, vehicle.getCustomerId());
            ps.setInt(6, vehicle.getVehicleId());
            ps.executeUpdate();
        }
    }
    
    public void delete(int vehicleId) throws Exception {
        String sql = "DELETE FROM Vehicle WHERE Vehicle_ID = ?";
        try (Connection conn = Db.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, vehicleId);
            ps.executeUpdate();
        }
    }
}