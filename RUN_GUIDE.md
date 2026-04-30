# 🚀 JDBC + Spring Boot Practical Guide (Set 2)

This guide explains how to run and demonstrate the three questions from Set 2.

---

## 📌 Q1: Flight Ticket Booking (JDBC)
**Goal:** Demonstrate JDBC Transaction Management (Commit/Rollback).

### 1. Database Setup
Run this command in your terminal to create the database and tables:
```bash
mysql -u root -proot123 < "Set2_Q1_JDBC_FlightBooking\setup.sql"
```
*(Or manually run the SQL in MySQL Workbench)*

### 2. How to Run
```bash
cd "Set2_Q1_JDBC_FlightBooking"
mvn exec:java
```

### 3. What to show the Examiner:
- Enter a **Flight ID** (1 or 2).
- Enter **Passenger Name** and **Seats**.
- Show the console output: "Booking Successful!"
- **Viva Tip:** Explain `con.setAutoCommit(false)` and why `commit()` is called only after both the update and insert are successful.

---

## 📌 Q2: Spring Boot Register API
**Goal:** Create a REST API that receives data via `@RequestParam`.

### 1. How to Run
```bash
cd "Set2_Q2_SpringBoot_Register"
mvn spring-boot:run
```
*Wait until you see `Started DemoApplication`.*

### 2. How to Test
Open your browser or use Postman and visit:
```text
http://localhost:8080/register?name=John&email=john@gmail.com
```

### 3. What to show the Examiner:
- The browser showing the message: `User Registered Successfully! Name: John Email: john@gmail.com`
- **Viva Tip:** Difference between `@RequestParam` (from URL) and `@RequestBody` (from JSON body).

---

## 📌 Q3: Spring Boot + MySQL Student JPA
**Goal:** Auto-create a database table using Hibernate/JPA.

### 1. How to Run
```bash
cd "Set2_Q3_SpringBoot_StudentJPA"
mvn spring-boot:run
```
*Wait for Hibernate logs to show `alter table students` or `create table students`.*

### 2. How to Verify
Open MySQL and run:
```sql
USE studentdb;
DESC students;
```

### 3. What to show the Examiner:
- Show that you **did not** write a `CREATE TABLE` script; Spring Boot created it automatically based on your `@Entity` class.
- **Viva Tip:** Explain `spring.jpa.hibernate.ddl-auto=update` and why it's useful during development.

---

## 🛠️ General Troubleshooting
- **Port 8080 already in use?**
  Run this in PowerShell to kill the process:
  ```powershell
  Stop-Process -Id (Get-NetTCPConnection -LocalPort 8080).OwningProcess -Force
  ```
- **MySQL Connection Error?**
  Check your `application.properties` and ensure the password is `root123`.
