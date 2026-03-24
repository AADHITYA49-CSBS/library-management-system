# 📚 Library Management System (Full Stack)

A full-stack Library Management System built using **Spring Boot** and **React**, secured with **JWT Authentication**.

---

## 🚀 Features

### 🔐 Authentication

* User Registration & Login
* JWT-based authentication
* Secure API access using Bearer Token

### 📚 Book Management

* Add new books
* View all books
* Track available copies

### 📖 Borrow System

* Borrow books (stock validated)
* View borrowed books (per user)
* Return books

### 🔒 Security

* No userId passed in APIs
* User extracted securely from JWT token
* Protected endpoints using Spring Security

---

## 🧱 Tech Stack

### Backend

* Java
* Spring Boot
* Spring Security
* JWT
* MySQL

### Frontend

* React (Vite)
* Axios
* REST API Integration

---

## 📂 Project Structure

```
library-management-system/
│
├── management/      # Spring Boot Backend
├── frontend/        # React Frontend
```

---

## ⚙️ Setup Instructions

### 🔧 Backend Setup

```bash
cd management
mvn spring-boot:run
```

Backend runs on:

```
http://localhost:8080
```

---

### 🎨 Frontend Setup

```bash
cd frontend
npm install
npm run dev
```

Frontend runs on:

```
http://localhost:5173
```

---

## 🔄 Application Flow

```
Register → Login → Receive JWT Token → 
Store Token → Access Protected APIs → 
Borrow / Return Books
```

---

## 🔑 API Overview

### Auth

* `POST /api/auth/register`
* `POST /api/auth/login`

### Books

* `GET /api/books`
* `POST /api/books`

### Borrow

* `POST /api/borrow`
* `GET /api/borrowed`
* `POST /api/return/{borrowId}`

---

## 🧠 Key Concepts Implemented

* Stateless Authentication using JWT
* Secure API design (no user spoofing)
* Layered Architecture (Controller → Service → Repository)
* DTO-based responses
* Axios interceptor for token handling

---

## 📌 Future Enhancements

* Role-based access (Admin/User)
* UI improvements
* Deployment (Render / Vercel / AWS)
* Search & filtering

---



---

## ⭐ Final Note

This project demonstrates a complete full-stack workflow:

* Backend API design
* Secure authentication
* Frontend integration
* Real-world system flow

---
