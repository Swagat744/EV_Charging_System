// src/main/java/dao/ChargingSessionDao.java
package dao;

import model.ChargingSession;
import util.Db;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ChargingSessionDao {
    
    public void create(ChargingSession session) throws Exception {
        String sql = "INSERT INTO ChargingSession (Session_ID, Vehicle_ID, Start_Time, End_Time, Energy_Consumed, Cost, Station_ID) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = Db.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, session.getSessionId());
            ps.setInt(2, session.getVehicleId());
            ps.setTimestamp(3, Timestamp.valueOf(session.getStartTime()));
            if (session.getEndTime() != null) {
                ps.setTimestamp(4, Timestamp.valueOf(session.getEndTime()));
            } else {
                ps.setNull(4, java.sql.Types.TIMESTAMP);
            }
            ps.setDouble(5, session.getEnergyConsumed());
            ps.setDouble(6, session.getCost());
            ps.setInt(7, session.getStationId());
            ps.executeUpdate();
        }
    }
    
    public List<ChargingSession> findAll() throws Exception {
        List<ChargingSession> list = new ArrayList<>();
        String sql = "SELECT * FROM ChargingSession";
        try (Connection conn = Db.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Timestamp startTs = rs.getTimestamp("Start_Time");
                Timestamp endTs = rs.getTimestamp("End_Time");
                LocalDateTime startTime = startTs != null ? startTs.toLocalDateTime() : null;
                LocalDateTime endTime = endTs != null ? endTs.toLocalDateTime() : null;
                
                ChargingSession s = new ChargingSession(
                    rs.getInt("Session_ID"),
                    rs.getInt("Vehicle_ID"),
                    startTime,
                    endTime,
                    rs.getDouble("Energy_Consumed"),
                    rs.getDouble("Cost"),
                    rs.getInt("Station_ID")
                );
                list.add(s);
            }
        }
        return list;
    }
    
    public void update(ChargingSession session) throws Exception {
        String sql = "UPDATE ChargingSession SET Vehicle_ID = ?, Start_Time = ?, End_Time = ?, Energy_Consumed = ?, Cost = ?, Station_ID = ? WHERE Session_ID = ?";
        try (Connection conn = Db.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, session.getVehicleId());
            ps.setTimestamp(2, Timestamp.valueOf(session.getStartTime()));
            if (session.getEndTime() != null) {
                ps.setTimestamp(3, Timestamp.valueOf(session.getEndTime()));
            } else {
                ps.setNull(3, java.sql.Types.TIMESTAMP);
            }
            ps.setDouble(4, session.getEnergyConsumed());
            ps.setDouble(5, session.getCost());
            ps.setInt(6, session.getStationId());
            ps.setInt(7, session.getSessionId());
            ps.executeUpdate();
        }
    }
    
    public void delete(int sessionId) throws Exception {
        String sql = "DELETE FROM ChargingSession WHERE Session_ID = ?";
        try (Connection conn = Db.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, sessionId);
            ps.executeUpdate();
        }
    }
}