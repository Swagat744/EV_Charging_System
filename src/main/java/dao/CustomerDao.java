// src/main/java/dao/CustomerDao.java
package dao;

import model.Customer;
import util.Db;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CustomerDao {
    
    public void create(Customer customer) throws Exception {
        String sql = "INSERT INTO Customer (Customer_ID, Name, Email, Phone) VALUES (?, ?, ?, ?)";
        try (Connection conn = Db.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, customer.getCustomerId());
            ps.setString(2, customer.getName());
            ps.setString(3, customer.getEmail());
            ps.setString(4, customer.getPhone());
            ps.executeUpdate();
        }
    }
    
    public List<Customer> findAll() throws Exception {
        List<Customer> list = new ArrayList<>();
        String sql = "SELECT * FROM Customer";
        try (Connection conn = Db.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Customer c = new Customer(
                    rs.getInt("Customer_ID"),
                    rs.getString("Name"),
                    rs.getString("Email"),
                    rs.getString("Phone")
                );
                list.add(c);
            }
        }
        return list;
    }
    
    public void update(Customer customer) throws Exception {
        String sql = "UPDATE Customer SET Name = ?, Email = ?, Phone = ? WHERE Customer_ID = ?";
        try (Connection conn = Db.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, customer.getName());
            ps.setString(2, customer.getEmail());
            ps.setString(3, customer.getPhone());
            ps.setInt(4, customer.getCustomerId());
            ps.executeUpdate();
        }
    }
    
    public void delete(int customerId) throws Exception {
        String sql = "DELETE FROM Customer WHERE Customer_ID = ?";
        try (Connection conn = Db.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, customerId);
            ps.executeUpdate();
        }
    }
}