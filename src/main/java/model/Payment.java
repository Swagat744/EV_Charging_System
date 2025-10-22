// src/main/java/model/Payment.java
package model;

import java.time.LocalDateTime;

public class Payment {
    private int paymentId;
    private double amount;
    private LocalDateTime paymentDate;
    private String paymentMode;
    private String status;
    private int sessionId;
    private int stationId;
    
    public Payment() {}
    
    public Payment(int paymentId, double amount, LocalDateTime paymentDate,
                  String paymentMode, String status, int sessionId, int stationId) {
        this.paymentId = paymentId;
        this.amount = amount;
        this.paymentDate = paymentDate;
        this.paymentMode = paymentMode;
        this.status = status;
        this.sessionId = sessionId;
        this.stationId = stationId;
    }
    
    public int getPaymentId() {
        return paymentId;
    }
    
    public void setPaymentId(int paymentId) {
        this.paymentId = paymentId;
    }
    
    public double getAmount() {
        return amount;
    }
    
    public void setAmount(double amount) {
        this.amount = amount;
    }
    
    public LocalDateTime getPaymentDate() {
        return paymentDate;
    }
    
    public void setPaymentDate(LocalDateTime paymentDate) {
        this.paymentDate = paymentDate;
    }
    
    public String getPaymentMode() {
        return paymentMode;
    }
    
    public void setPaymentMode(String paymentMode) {
        this.paymentMode = paymentMode;
    }
    
    public String getStatus() {
        return status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    
    public int getSessionId() {
        return sessionId;
    }
    
    public void setSessionId(int sessionId) {
        this.sessionId = sessionId;
    }
    
    public int getStationId() {
        return stationId;
    }
    
    public void setStationId(int stationId) {
        this.stationId = stationId;
    }
}