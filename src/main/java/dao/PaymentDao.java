// src/main/java/dao/PaymentDao.java
package dao;

import model.Payment;
import util.Db;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class PaymentDao {
    
    public void create(Payment payment) throws Exception {
        String sql = "INSERT INTO Payment (Payment_ID, Amount, Payment_Date, Payment_Mode, Status, Session_ID, Station_ID) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = Db.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, payment.getPaymentId());
            ps.setDouble(2, payment.getAmount());
            ps.setTimestamp(3, Timestamp.valueOf(payment.getPaymentDate()));
            ps.setString(4, payment.getPaymentMode());
            ps.setString(5, payment.getStatus());
            ps.setInt(6, payment.getSessionId());
            ps.setInt(7, payment.getStationId());
            ps.executeUpdate();
        }
    }
    
    public List<Payment> findAll() throws Exception {
        List<Payment> list = new ArrayList<>();
        String sql = "SELECT * FROM Payment";
        try (Connection conn = Db.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Timestamp payTs = rs.getTimestamp("Payment_Date");
                LocalDateTime paymentDate = payTs != null ? payTs.toLocalDateTime() : null;
                
                Payment p = new Payment(
                    rs.getInt("Payment_ID"),
                    rs.getDouble("Amount"),
                    paymentDate,
                    rs.getString("Payment_Mode"),
                    rs.getString("Status"),
                    rs.getInt("Session_ID"),
                    rs.getInt("Station_ID")
                );
                list.add(p);
            }
        }
        return list;
    }
    
    public void update(Payment payment) throws Exception {
        String sql = "UPDATE Payment SET Amount = ?, Payment_Date = ?, Payment_Mode = ?, Status = ?, Session_ID = ?, Station_ID = ? WHERE Payment_ID = ?";
        try (Connection conn = Db.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setDouble(1, payment.getAmount());
            ps.setTimestamp(2, Timestamp.valueOf(payment.getPaymentDate()));
            ps.setString(3, payment.getPaymentMode());
            ps.setString(4, payment.getStatus());
            ps.setInt(5, payment.getSessionId());
            ps.setInt(6, payment.getStationId());
            ps.setInt(7, payment.getPaymentId());
            ps.executeUpdate();
        }
    }
    
    public void delete(int paymentId) throws Exception {
        String sql = "DELETE FROM Payment WHERE Payment_ID = ?";
        try (Connection conn = Db.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, paymentId);
            ps.executeUpdate();
        }
    }
}