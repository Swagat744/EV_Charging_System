<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Vehicles</title>
    <link rel="stylesheet" href="assets/css/app.css">
</head>
<body>
    <header>
        <nav>
            <div class="logo">⚡ EV Charging</div>
            <ul>
                <li><a href="index.jsp">Home</a></li>
                <li><a href="customers">Customers</a></li>
                <li><a href="vehicles">Vehicles</a></li>
                <li><a href="stations">Stations</a></li>
                <li><a href="sessions">Sessions</a></li>
                <li><a href="payments">Payments</a></li>
            </ul>
        </nav>
    </header>

    <div class="container">
        <h1>Vehicles</h1>
        
        <c:if test="${not empty error}">
            <div class="alert alert-error">${error}</div>
        </c:if>

        <div class="form-section">
            <h2>Add Vehicle</h2>
            <form method="POST" action="vehicles">
                <div class="form-group">
                    <label>Vehicle ID</label>
                    <input type="number" name="vehicleId" required>
                </div>
                <div class="form-group">
                    <label>Vehicle Number</label>
                    <input type="text" name="vehicleNo" required>
                </div>
                <div class="form-group">
                    <label>Vehicle Type</label>
                    <input type="text" name="vehicleType" required>
                </div>
                <div class="form-group">
                    <label>Vehicle Model</label>
                    <input type="text" name="vehicleModel" required>
                </div>
                <div class="form-group">
                    <label>Battery Capacity (kWh)</label>
                    <input type="number" name="batteryCapacity" required>
                </div>
                <div class="form-group">
                    <label>Customer ID</label>
                    <input type="number" name="customerId" required>
                </div>
                <button type="submit" name="action" value="add">Add Vehicle</button>
            </form>
        </div>

        <h2>Vehicle List</h2>
        <table>
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Vehicle No</th>
                    <th>Type</th>
                    <th>Model</th>
                    <th>Battery (kWh)</th>
                    <th>Customer ID</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="vehicle" items="${list}">
                    <tr>
                        <td>${vehicle.vehicleId}</td>
                        <td>${vehicle.vehicleNo}</td>
                        <td>${vehicle.vehicleType}</td>
                        <td>${vehicle.vehicleModel}</td>
                        <td>${vehicle.batteryCapacity}</td>
                        <td>${vehicle.customerId}</td>
                        <td>
                            <div class="btn-group">
                                <form method="POST" action="vehicles" style="display:inline;">
                                    <input type="hidden" name="action" value="delete">
                                    <input type="hidden" name="vehicleId" value="${vehicle.vehicleId}">
                                    <button type="submit" class="btn-delete" onclick="return confirm('Delete?')">Delete</button>
                                </form>
                            </div>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</body>
</html>