// src/main/java/model/ChargingSession.java
package model;

import java.time.LocalDateTime;

public class ChargingSession {
    private int sessionId;
    private int vehicleId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private double energyConsumed;
    private double cost;
    private int stationId;
    
    public ChargingSession() {}
    
    public ChargingSession(int sessionId, int vehicleId, LocalDateTime startTime,
                          LocalDateTime endTime, double energyConsumed, double cost, int stationId) {
        this.sessionId = sessionId;
        this.vehicleId = vehicleId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.energyConsumed = energyConsumed;
        this.cost = cost;
        this.stationId = stationId;
    }
    
    public int getSessionId() {
        return sessionId;
    }
    
    public void setSessionId(int sessionId) {
        this.sessionId = sessionId;
    }
    
    public int getVehicleId() {
        return vehicleId;
    }
    
    public void setVehicleId(int vehicleId) {
        this.vehicleId = vehicleId;
    }
    
    public LocalDateTime getStartTime() {
        return startTime;
    }
    
    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }
    
    public LocalDateTime getEndTime() {
        return endTime;
    }
    
    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }
    
    public double getEnergyConsumed() {
        return energyConsumed;
    }
    
    public void setEnergyConsumed(double energyConsumed) {
        this.energyConsumed = energyConsumed;
    }
    
    public double getCost() {
        return cost;
    }
    
    public void setCost(double cost) {
        this.cost = cost;
    }
    
    public int getStationId() {
        return stationId;
    }
    
    public void setStationId(int stationId) {
        this.stationId = stationId;
    }
}