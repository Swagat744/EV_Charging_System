<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Charging Stations</title>
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
        <h1>Charging Stations</h1>
        
        <c:if test="${not empty error}">
            <div class="alert alert-error">${error}</div>
        </c:if>

        <div class="form-section">
            <h2>Add Station</h2>
            <form method="POST" action="stations">
                <div class="form-group">
                    <label>Station ID</label>
                    <input type="number" name="stationId" required>
                </div>
                <div class="form-group">
                    <label>Manager Name</label>
                    <input type="text" name="managerName" required>
                </div>
                <div class="form-group">
                    <label>Working Hours</label>
                    <input type="text" name="workingHour" placeholder="e.g. 9AM-6PM" required>
                </div>
                <div class="form-group">
                    <label>Capacity (slots)</label>
                    <input type="number" name="capacity" required>
                </div>
                <div class="form-group">
                    <label>Location</label>
                    <input type="text" name="location" required>
                </div>
                <button type="submit" name="action" value="add">Add Station</button>
            </form>
        </div>

        <h2>Station List</h2>
        <table>
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Manager</th>
                    <th>Hours</th>
                    <th>Capacity</th>
                    <th>Location</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="station" items="${list}">
                    <tr>
                        <td>${station.stationId}</td>
                        <td>${station.managerName}</td>
                        <td>${station.workingHour}</td>
                        <td>${station.capacity}</td>
                        <td>${station.location}</td>
                        <td>
                            <div class="btn-group">
                                <form method="POST" action="stations" style="display:inline;">
                                    <input type="hidden" name="action" value="delete">
                                    <input type="hidden" name="stationId" value="${station.stationId}">
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