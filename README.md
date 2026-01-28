# 🚆 IRCTC Backend – Train Booking System (Spring Boot)

A **real-world IRCTC-like backend system** built using **Spring Boot, JPA/Hibernate, MySQL**, and **JWT-based authentication**.
This project simulates how actual railway ticket booking platforms work — from train search to seat booking and payment handling.

---

## 📌 Features Overview

### 👤 User Features

* User Registration & Login (JWT-based)
* Search trains between source & destination
* View train routes & schedules
* Check seat availability (AC / Sleeper / General)
* Book tickets with multiple passengers
* View booking history
* Cancel bookings

### 🛠️ Admin Features

* Manage Stations (CRUD)
* Manage Trains (CRUD)
* Configure Train Routes
* Add Train Schedules (date-wise)
* Configure Seats & Fare per Coach

---

## 🧱 Tech Stack

| Layer                 | Technology                  |
| --------------------- | --------------------------- |
| Backend               | Spring Boot 3.x             |
| ORM                   | Spring Data JPA (Hibernate) |
| Database              | MySQL                       |
| Security              | Spring Security + JWT       |
| Build Tool            | Maven                       |
| Boilerplate Reduction | Lombok                      |

---

## 🗂️ Project Architecture

```
irctc-backend
│
├── controller
│   ├── admin
│   └── user
│
├── service
│
├── repository
│
├── entity
│
├── dto
│
├── security
│
└── config
```

---

## 🗃️ Database Design (Core Tables)

* users
* stations
* trains
* train_route
* train_schedule
* train_seats
* bookings
* booking_passengers
* payments

✔ Fully normalized schema
✔ Real-world relational mappings

---

## 🔐 Authentication Flow

1. User registers → credentials stored securely
2. User logs in → JWT token generated
3. JWT token required for protected APIs

---

## 🔍 Train Search Logic (Core Feature)

Search trains using:

```
GET /api/search-trains
```

### Parameters

* sourceCode (e.g., NDLS)
* destinationCode (e.g., BSB)
* journeyDate (YYYY-MM-DD)

### Returns

* Train details
* Departure & arrival time
* Coach-wise seat availability
* Fare per coach

✔ Ensures correct route direction
✔ Checks date-wise availability

---

## 🎫 Booking Flow

1. User selects train & coach
2. Enters passenger details
3. Seats are locked using transaction
4. Booking created
5. Payment processed
6. Booking confirmed

⚠️ Seat consistency maintained using `@Transactional`

---

## 📡 API Endpoints

### 🔐 Auth APIs

```
POST /api/auth/register
POST /api/auth/login
```

### 🔍 User APIs

```
GET  /api/search-trains
GET  /api/trains/{trainId}/details
GET  /api/availability
POST /api/bookings
GET  /api/users/{userId}/bookings
DELETE /api/bookings/{bookingId}
```

### 🛠️ Admin APIs

```
POST   /admin/stations
GET    /admin/stations
PUT    /admin/stations/{id}
DELETE /admin/stations/{id}

POST   /admin/trains
POST   /admin/trains/{trainId}/routes
POST   /admin/trains/{trainId}/schedules
POST   /admin/schedules/{scheduleId}/seats
```

---

## 🧪 Dummy Data

Project includes:

* Preloaded stations (NDLS, CNB, LKO, BSB)
* Rajdhani Express with full route
* Multiple schedules & coach configurations

Seeded via:

* SQL scripts **OR**
* `CommandLineRunner`

---

## ▶️ How to Run the Project

### 1️⃣ Clone Repository

```bash
git clone https://github.com/your-username/irctc-backend.git
```

### 2️⃣ Configure Database

Update `application.yml` or `application.properties`

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/irctc
spring.datasource.username=root
spring.datasource.password=your_password
```

### 3️⃣ Run Application

```bash
mvn spring-boot:run
```

---

## 📈 Future Enhancements

* Live Train Status
* Seat Preference (Lower / Upper)
* Dynamic Pricing
* Ticket PDF Generation
* Refund Policy Engine
* Caching (Redis)

---

## 🎯 Learning Outcomes

* Real-world database modeling
* Complex SQL & JPA queries
* Transaction management
* Scalable backend architecture
* Industry-grade REST API design

---

## 👨‍💻 Author

**Gourav Giri**
Backend Developer | Java | Spring Boot

---

⭐ If you find this project useful, don’t forget to star the repository!
