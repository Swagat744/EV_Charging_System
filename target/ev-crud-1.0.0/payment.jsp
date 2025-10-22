<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Payments</title>
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
        <h1>Payments</h1>
        
        <c:if test="${not empty error}">
            <div class="alert alert-error">${error}</div>
        </c:if>

        <div class="form-section">
            <h2>Add Payment</h2>
            <form method="POST" action="payments">
                <div class="form-group">
                    <label>Payment ID</label>
                    <input type="number" name="paymentId" required>
                </div>
                <div class="form-group">
                    <label>Amount ($)</label>
                    <input type="number" step="0.01" name="amount" required>
                </div>
                <div class="form-group">
                    <label>Payment Date</label>
                    <input type="datetime-local" name="paymentDate" required>
                </div>
                <div class="form-group">
                    <label>Payment Mode</label>
                    <select name="paymentMode" required>
                        <option value="">-- Select Mode --</option>
                        <option value="Credit Card">Credit Card</option>
                        <option value="Debit Card">Debit Card</option>
                        <option value="Digital Wallet">Digital Wallet</option>
                        <option value="Bank Transfer">Bank Transfer</option>
                    </select>
                </div>
                <div class="form-group">
                    <label>Status</label>
                    <select name="status" required>
                        <option value="">-- Select Status --</option>
                        <option value="Pending">Pending</option>
                        <option value="Completed">Completed</option>
                        <option value="Failed">Failed</option>
                    </select>
                </div>
                <div class="form-group">
                    <label>Session ID (optional)</label>
                    <input type="number" name="sessionId">
                </div>
                <div class="form-group">
                    <label>Station ID (optional)</label>
                    <input type="number" name="stationId">
                </div>
                <button type="submit" name="action" value="add">Add Payment</button>
            </form>
        </div>

        <h2>Payment List</h2>
        <table>
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Amount ($)</th>
                    <th>Date</th>
                    <th>Mode</th>
                    <th>Status</th>
                    <th>Session ID</th>
                    <th>Station ID</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="payment" items="${list}">
                    <tr>
                        <td>${payment.paymentId}</td>
                        <td>${payment.amount}</td>
                        <td>${payment.paymentDate}</td>
                        <td>${payment.paymentMode}</td>
                        <td>${payment.status}</td>
                        <td>${payment.sessionId}</td>
                        <td>${payment.stationId}</td>
                        <td>
                            <div class="btn-group">
                                <form method="POST" action="payments" style="display:inline;">
                                    <input type="hidden" name="action" value="delete">
                                    <input type="hidden" name="paymentId" value="${payment.paymentId}">
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