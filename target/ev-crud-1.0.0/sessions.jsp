
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Charging Sessions</title>
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
        <h1>Charging Sessions</h1>
        
        <c:if test="${not empty error}">
            <div class="alert alert-error">${error}</div>
        </c:if>

        <div class="form-section">
            <h2>Add Session</h2>
            <form method="POST" action="sessions">
                <div class="form-group">
                    <label>Session ID</label>
                    <input type="number" name="sessionId" required>
                </div>
                <div class="form-group">
                    <label>Vehicle ID</label>
                    <input type="number" name="vehicleId" required>
                </div>
                <div class="form-group">
                    <label>Start Time</label>
                    <input type="datetime-local" name="startTime" required>
                </div>
                <div class="form-group">
                    <label>End Time</label>
                    <input type="datetime-local" name="endTime">
                </div>
                <div class="form-group">
                    <label>Energy Consumed (kWh)</label>
                    <input type="number" step="0.01" name="energyConsumed" required>
                </div>
                <div class="form-group">
                    <label>Cost ($)</label>
                    <input type="number" step="0.01" name="cost" required>
                </div>
                <div class="form-group">
                    <label>Station ID</label>
                    <input type="number" name="stationId" required>
                </div>
                <button type="submit" name="action" value="add">Add Session</button>
            </form>
        </div>

        <h2>Session List</h2>
        <table>
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Vehicle ID</th>
                    <th>Start Time</th>
                    <th>End Time</th>
                    <th>Energy (kWh)</th>
                    <th>Cost ($)</th>
                    <th>Station ID</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="session" items="${list}">
                    <tr>
                        <td>${session.sessionId}</td>
                        <td>${session.vehicleId}</td>
                        <td>${session.startTime}</td>
                        <td>${session.endTime}</td>
                        <td>${session.energyConsumed}</td>
                        <td>${session.cost}</td>
                        <td>${session.stationId}</td>
                        <td>
                            <div class="btn-group">
                                <form method="POST" action="sessions" style="display:inline;">
                                    <input type="hidden" name="action" value="delete">
                                    <input type="hidden" name="sessionId" value="${session.sessionId}">
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