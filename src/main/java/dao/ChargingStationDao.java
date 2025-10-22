// src/main/java/dao/ChargingStationDao.java
package dao;

import model.ChargingStation;
import util.Db;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ChargingStationDao {
    
    public void create(ChargingStation station) throws Exception {
        String sql = "INSERT INTO ChargingStation (Station_ID, Manager_Name, Working_Hour, Capacity, Location) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = Db.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, station.getStationId());
            ps.setString(2, station.getManagerName());
            ps.setString(3, station.getWorkingHour());
            ps.setInt(4, station.getCapacity());
            ps.setString(5, station.getLocation());
            ps.executeUpdate();
        }
    }
    
    public List<ChargingStation> findAll() throws Exception {
        List<ChargingStation> list = new ArrayList<>();
        String sql = "SELECT * FROM ChargingStation";
        try (Connection conn = Db.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ChargingStation s = new ChargingStation(
                    rs.getInt("Station_ID"),
                    rs.getString("Manager_Name"),
                    rs.getString("Working_Hour"),
                    rs.getInt("Capacity"),
                    rs.getString("Location")
                );
                list.add(s);
            }
        }
        return list;
    }
    
    public void update(ChargingStation station) throws Exception {
        String sql = "UPDATE ChargingStation SET Manager_Name = ?, Working_Hour = ?, Capacity = ?, Location = ? WHERE Station_ID = ?";
        try (Connection conn = Db.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, station.getManagerName());
            ps.setString(2, station.getWorkingHour());
            ps.setInt(3, station.getCapacity());
            ps.setString(4, station.getLocation());
            ps.setInt(5, station.getStationId());
            ps.executeUpdate();
        }
    }
    
    public void delete(int stationId) throws Exception {
        String sql = "DELETE FROM ChargingStation WHERE Station_ID = ?";
        try (Connection conn = Db.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, stationId);
            ps.executeUpdate();
        }
    }
}