// src/main/java/model/Vehicle.java
package model;

public class Vehicle {
    private int vehicleId;
    private String vehicleNo;
    private String vehicleType;
    private String vehicleModel;
    private int batteryCapacity;
    private int customerId;
    
    public Vehicle() {}
    
    public Vehicle(int vehicleId, String vehicleNo, String vehicleType, 
                   String vehicleModel, int batteryCapacity, int customerId) {
        this.vehicleId = vehicleId;
        this.vehicleNo = vehicleNo;
        this.vehicleType = vehicleType;
        this.vehicleModel = vehicleModel;
        this.batteryCapacity = batteryCapacity;
        this.customerId = customerId;
    }
    
    public int getVehicleId() {
        return vehicleId;
    }
    
    public void setVehicleId(int vehicleId) {
        this.vehicleId = vehicleId;
    }
    
    public String getVehicleNo() {
        return vehicleNo;
    }
    
    public void setVehicleNo(String vehicleNo) {
        this.vehicleNo = vehicleNo;
    }
    
    public String getVehicleType() {
        return vehicleType;
    }
    
    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }
    
    public String getVehicleModel() {
        return vehicleModel;
    }
    
    public void setVehicleModel(String vehicleModel) {
        this.vehicleModel = vehicleModel;
    }
    
    public int getBatteryCapacity() {
        return batteryCapacity;
    }
    
    public void setBatteryCapacity(int batteryCapacity) {
        this.batteryCapacity = batteryCapacity;
    }
    
    public int getCustomerId() {
        return customerId;
    }
    
    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }
}