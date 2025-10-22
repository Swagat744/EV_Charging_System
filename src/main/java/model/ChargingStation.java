// src/main/java/model/ChargingStation.java
package model;

public class ChargingStation {
    private int stationId;
    private String managerName;
    private String workingHour;
    private int capacity;
    private String location;
    
    public ChargingStation() {}
    
    public ChargingStation(int stationId, String managerName, String workingHour,
                          int capacity, String location) {
        this.stationId = stationId;
        this.managerName = managerName;
        this.workingHour = workingHour;
        this.capacity = capacity;
        this.location = location;
    }
    
    public int getStationId() {
        return stationId;
    }
    
    public void setStationId(int stationId) {
        this.stationId = stationId;
    }
    
    public String getManagerName() {
        return managerName;
    }
    
    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }
    
    public String getWorkingHour() {
        return workingHour;
    }
    
    public void setWorkingHour(String workingHour) {
        this.workingHour = workingHour;
    }
    
    public int getCapacity() {
        return capacity;
    }
    
    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
    
    public String getLocation() {
        return location;
    }
    
    public void setLocation(String location) {
        this.location = location;
    }
}