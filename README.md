# ⚡ EV Charging Station Management System

A comprehensive web-based CRUD application for managing Electric Vehicle (EV) charging stations, built with Java EE, Servlets, JSP, and MySQL.

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white)
![MySQL](https://img.shields.io/badge/MySQL-005C84?style=for-the-badge&logo=mysql&logoColor=white)
![Apache Tomcat](https://img.shields.io/badge/Apache%20Tomcat-F8DC75?style=for-the-badge&logo=apache-tomcat&logoColor=black)
![Maven](https://img.shields.io/badge/Maven-C71A36?style=for-the-badge&logo=apache-maven&logoColor=white)

## 📋 Table of Contents

- [Overview](#overview)
- [Features](#features)
- [Technology Stack](#technology-stack)
- [Database Schema](#database-schema)
- [Prerequisites](#prerequisites)
- [Installation & Setup](#installation--setup)
- [Usage](#usage)
- [Project Structure](#project-structure)
- [Screenshots](#screenshots)
- [Contributing](#contributing)
- [License](#license)
- [Contact](#contact)

## 🎯 Overview

The EV Charging Station Management System is a full-stack web application designed to efficiently manage electric vehicle charging infrastructure. It provides a centralized platform for tracking customers, vehicles, charging stations, charging sessions, and payment transactions.

### Problem Statement

With the rapid growth of electric vehicle adoption, there's a critical need for:
- Efficient management of multiple charging stations
- Real-time tracking of charging sessions
- Automated billing and payment processing
- Centralized customer and vehicle data management

This system addresses these challenges by providing an intuitive web interface with complete CRUD (Create, Read, Update, Delete) operations for all entities.

## ✨ Features

### Core Functionalities

- **Customer Management**
  - Register and manage customer profiles
  - Track customer information (name, email, phone)
  - Link multiple vehicles to customers

- **Vehicle Management**
  - Register electric vehicles with detailed specifications
  - Track vehicle type, model, and battery capacity
  - Associate vehicles with customer accounts

- **Charging Station Management**
  - Create and manage multiple charging stations
  - Track station capacity, location, and working hours
  - Monitor station manager information

- **Charging Session Tracking**
  - Record charging sessions with start/end timestamps
  - Calculate energy consumption and costs
  - Link sessions to vehicles and stations

- **Payment Management**
  - Process and track payment transactions
  - Support multiple payment modes (Credit Card, Debit Card, Digital Wallet, etc.)
  - Monitor payment status (Pending, Completed, Failed)

### Technical Features

- ✅ Full CRUD operations for all entities
- ✅ RESTful servlet-based architecture
- ✅ Prepared statements to prevent SQL injection
- ✅ Foreign key constraints for data integrity
- ✅ Responsive dark-themed UI
- ✅ Form validation and error handling
- ✅ Session management

## 🛠️ Technology Stack

| Component | Technology |
|-----------|-----------|
| **Backend** | Java EE 8, Servlets 4.0, JDBC |
| **Frontend** | JSP, HTML5, CSS3, JSTL |
| **Database** | MySQL 8.0 |
| **Server** | Apache Tomcat 9.0 |
| **Build Tool** | Apache Maven 3.9+ |
| **Java Version** | JDK 1.8+ |

## 🗄️ Database Schema

### Entity Relationship Diagram

```
Customer (1) ──────< Vehicle (N)
                        │
                        │
                        ▼
ChargingStation (1) ──────< ChargingSession (N)
       │                        │
       │                        │
       └──────────────┬─────────┘
                      ▼
                  Payment (N)
```

### Tables

**Customer**
- Customer_ID (PK)
- Name
- Email (Unique)
- Phone

**Vehicle**
- Vehicle_ID (PK)
- Vehicle_No (Unique)
- Vehicle_Type
- Vehicle_Model
- Battery_Capacity
- Customer_ID (FK)

**ChargingStation**
- Station_ID (PK)
- Manager_Name
- Working_Hour
- Capacity
- Location

**ChargingSession**
- Session_ID (PK)
- Vehicle_ID (FK)
- Station_ID (FK)
- Start_Time
- End_Time
- Energy_Consumed
- Cost

**Payment**
- Payment_ID (PK)
- Session_ID (FK)
- Station_ID (FK)
- Amount
- Payment_Date
- Payment_Mode
- Status

## 📦 Prerequisites

Before you begin, ensure you have the following installed:

- **Java Development Kit (JDK)** 1.8 or higher
- **Apache Maven** 3.6+
- **MySQL Server** 8.0+
- **Apache Tomcat** 9.0
- **Git** (for cloning the repository)

## 🚀 Installation & Setup

### 1. Clone the Repository

```bash
git clone https://github.com/Swagat744/EV_Charging_System.git
```

### 2. Set Up MySQL Database

```sql
-- Create database
CREATE DATABASE IF NOT EXISTS ev_charging;
USE ev_charging;

-- Create tables
CREATE TABLE Customer (
    Customer_ID INT PRIMARY KEY,
    Name VARCHAR(100) NOT NULL,
    Email VARCHAR(100) UNIQUE NOT NULL,
    Phone VARCHAR(15)
);

CREATE TABLE ChargingStation (
    Station_ID INT PRIMARY KEY,
    Manager_Name VARCHAR(100),
    Working_Hour VARCHAR(50),
    Capacity INT,
    Location VARCHAR(150)
);

CREATE TABLE Vehicle (
    Vehicle_ID INT PRIMARY KEY,
    Vehicle_No VARCHAR(20) UNIQUE NOT NULL,
    Vehicle_Type VARCHAR(30),
    Vehicle_Model VARCHAR(50),
    Battery_Capacity INT,
    Customer_ID INT,
    FOREIGN KEY (Customer_ID) REFERENCES Customer(Customer_ID) ON DELETE CASCADE
);

CREATE TABLE ChargingSession (
    Session_ID INT PRIMARY KEY,
    Vehicle_ID INT,
    Start_Time DATETIME,
    End_Time DATETIME,
    Energy_Consumed DECIMAL(10,2),
    Cost DECIMAL(10,2),
    Station_ID INT,
    FOREIGN KEY (Vehicle_ID) REFERENCES Vehicle(Vehicle_ID) ON DELETE CASCADE,
    FOREIGN KEY (Station_ID) REFERENCES ChargingStation(Station_ID) ON DELETE CASCADE
);

CREATE TABLE Payment (
    Payment_ID INT PRIMARY KEY,
    Amount DECIMAL(10,2),
    Payment_Date DATETIME,
    Payment_Mode VARCHAR(30),
    Status VARCHAR(30),
    Session_ID INT,
    Station_ID INT,
    FOREIGN KEY (Session_ID) REFERENCES ChargingSession(Session_ID) ON DELETE CASCADE,
    FOREIGN KEY (Station_ID) REFERENCES ChargingStation(Station_ID) ON DELETE CASCADE
);
```

### 3. Configure Database Connection

Edit `src/main/resources/db.properties`:

```properties
db.url=jdbc:mysql://localhost:3306/ev_charging?useSSL=false&serverTimezone=UTC
db.user=root
db.pass=your_password_here
```

### 4. Build the Project

```bash
mvn clean package
```

### 5. Deploy to Tomcat

**Option 1: Manual Deployment**
```bash
cp target/ev-crud-1.0.0.war $TOMCAT_HOME/webapps/
```

**Option 2: Using Maven Tomcat Plugin**
```bash
mvn tomcat7:deploy
```

### 6. Start Tomcat Server

**Linux/Mac:**
```bash
$TOMCAT_HOME/bin/startup.sh
```

**Windows:**
```cmd
%TOMCAT_HOME%\bin\startup.bat
```

### 7. Access the Application

Open your browser and navigate to:
```
http://localhost:8080/ev-crud-1.0.0/
```

## 📖 Usage

### Access Different Modules

- **Home Page:** `http://localhost:8080/ev-crud-1.0.0/`
- **Customers:** `http://localhost:8080/ev-crud-1.0.0/customers`
- **Vehicles:** `http://localhost:8080/ev-crud-1.0.0/vehicles`
- **Stations:** `http://localhost:8080/ev-crud-1.0.0/stations`
- **Sessions:** `http://localhost:8080/ev-crud-1.0.0/sessions`
- **Payments:** `http://localhost:8080/ev-crud-1.0.0/payments`

### Data Entry Flow

For proper functionality, follow this sequence:

1. **Add Customers** first
2. **Add Charging Stations**
3. **Add Vehicles** (linked to customers)
4. **Add Charging Sessions** (linked to vehicles and stations)
5. **Add Payments** (linked to sessions)

### Example Usage

```
1. Create Customer (ID: 1, Name: "John Doe")
2. Create Station (ID: 1, Location: "Downtown")
3. Create Vehicle (ID: 1, Customer_ID: 1)
4. Create Session (ID: 1, Vehicle_ID: 1, Station_ID: 1)
5. Create Payment (ID: 1, Session_ID: 1, Station_ID: 1)
```

## 📁 Project Structure

```
ev-crud/
├── pom.xml
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   ├── dao/
│   │   │   │   ├── CustomerDao.java
│   │   │   │   ├── VehicleDao.java
│   │   │   │   ├── ChargingStationDao.java
│   │   │   │   ├── ChargingSessionDao.java
│   │   │   │   └── PaymentDao.java
│   │   │   ├── model/
│   │   │   │   ├── Customer.java
│   │   │   │   ├── Vehicle.java
│   │   │   │   ├── ChargingStation.java
│   │   │   │   ├── ChargingSession.java
│   │   │   │   └── Payment.java
│   │   │   ├── servlet/
│   │   │   │   ├── CustomerServlet.java
│   │   │   │   ├── VehicleServlet.java
│   │   │   │   ├── ChargingStationServlet.java
│   │   │   │   ├── ChargingSessionServlet.java
│   │   │   │   └── PaymentServlet.java
│   │   │   └── util/
│   │   │       └── Db.java
│   │   ├── resources/
│   │   │   └── db.properties
│   │   └── webapp/
│   │       ├── WEB-INF/
│   │       │   └── web.xml
│   │       ├── assets/
│   │       │   └── css/
│   │       │       └── app.css
│   │       ├── index.jsp
│   │       ├── customers.jsp
│   │       ├── vehicles.jsp
│   │       ├── stations.jsp
│   │       ├── sessions.jsp
│   │       └── payments.jsp
│   └── test/
└── target/
    └── ev-crud-1.0.0.war
```

## 📸 Screenshots

### Home Page
<img width="1755" height="865" alt="Screenshot 2025-10-18 221221" src="https://github.com/user-attachments/assets/64b05758-8f9d-4aa7-8294-2c0cf3625ef2" />

### Customer Management
<img width="1215" height="882" alt="Screenshot 2025-10-18 221421" src="https://github.com/user-attachments/assets/5b557c60-872e-41d6-a4b4-130646f1b4b8" />

### Vehicle Management
<img width="987" height="878" alt="Screenshot 2025-10-18 221617" src="https://github.com/user-attachments/assets/321b40a7-e9b0-4702-b1bf-7857afe9d563" />

### Charging Sessions
<img width="991" height="840" alt="Screenshot 2025-10-18 223329" src="https://github.com/user-attachments/assets/7f5e672e-b013-4b65-9bbb-57930053af79" />

### Payment Management
<img width="1001" height="825" alt="Screenshot 2025-10-18 223430" src="https://github.com/user-attachments/assets/c64dddec-b08e-4be6-8089-1cada08dd9ca" />

## 🤝 Contributing

Contributions are welcome! Please follow these steps:

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## 📝 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## 👨‍💻 Author

**Your Name**
- GitHub: https://github.com/Swagat744
- LinkedIn: https://www.linkedin.com/in/swagatpatil-it/
- Email: swagatnpatil744@gmail.com

## 🙏 Acknowledgments

- Thanks to the Java EE community for excellent documentation
- Apache Software Foundation for Tomcat and Maven
- MySQL for the robust database system

## 📞 Contact

For any queries or suggestions, please reach out:

- Create an issue in this repository
- Email: swagatnpatil744@gmail.com

---

**⭐ If you found this project helpful, please give it a star!**

---
