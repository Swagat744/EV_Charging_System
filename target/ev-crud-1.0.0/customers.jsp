<!-- src/main/webapp/customers.jsp -->
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Customers</title>
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
        <h1>Customers</h1>
        
        <c:if test="${not empty error}">
            <div class="alert alert-error">${error}</div>
        </c:if>

        <div class="form-section">
            <h2>Add Customer</h2>
            <form method="POST" action="customers">
                <div class="form-group">
                    <label>Customer ID</label>
                    <input type="number" name="customerId" required>
                </div>
                <div class="form-group">
                    <label>Name</label>
                    <input type="text" name="name" required>
                </div>
                <div class="form-group">
                    <label>Email</label>
                    <input type="email" name="email" required>
                </div>
                <div class="form-group">
                    <label>Phone</label>
                    <input type="text" name="phone" required>
                </div>
                <button type="submit" name="action" value="add">Add Customer</button>
            </form>
        </div>

        <h2>Customer List</h2>
        <table>
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Name</th>
                    <th>Email</th>
                    <th>Phone</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="customer" items="${list}">
                    <tr>
                        <td>${customer.customerId}</td>
                        <td>${customer.name}</td>
                        <td>${customer.email}</td>
                        <td>${customer.phone}</td>
                        <td>
                            <div class="btn-group">
                                <form method="GET" style="display:inline;">
                                    <input type="hidden" name="customerId" value="${customer.customerId}">
                                    <button type="submit" class="btn-edit">Edit</button>
                                </form>
                                <form method="POST" action="customers" style="display:inline;">
                                    <input type="hidden" name="action" value="delete">
                                    <input type="hidden" name="customerId" value="${customer.customerId}">
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