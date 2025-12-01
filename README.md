# 🏦 POC Banking System

A Proof-of-Concept full-stack banking application that simulates essential banking operations such as login, balance top-up, withdrawals, purchases, and transfers. It uses a microservices-like architecture with a separate gateway and core banking service.

---

## 🚀 Tech Stack

| Layer       | Technology Used |
|------------|----------------|
| Frontend   | ReactJS, Fetch API, Cookies, CSS |
| Backend    | Spring Boot (Java 21), Spring Security, JWT |
| Gateway    | Spring Cloud OpenFeign |
| Database   | H2 |
| Deployment | Docker ,Vercel & Render |

---

## 🧩 Architecture Overview

<img width="685" height="130" alt="image" src="https://github.com/user-attachments/assets/a69916e5-3af8-46b0-bdd8-9d4e3cae3896" />

## ✨ Features

### 👤 Authentication
- Login with JWT token
- Role-based access (Admin / Customer)

### 💳 Banking Operations
- Top-Up balance
- Purchase / Withdraw
- Transfer funds between accounts

### 🔐 Security
- Spring Security
- JWT protected routes

---

## 🔑 Sample Login Credentials

| Role | Username | Password |
|------|----------|----------|
| Customer | `customer1` | `userPassword` |
| Admin | `Admin` | `adminpassword` |

---

## 🖥️ Frontend Setup

```bash
cd frontend
npm install
npm start
```
---
## 🏗️ Backend Setup

The backend consists of **two Spring Boot services**:

1️⃣ **API Gateway Service** – Authentication, JWT validation, and transaction routing  
2️⃣ **Core Banking Service** – Card validation, balance operations, account storage

---

```bash
cd backend\system1\bank
mvn clean install
mvn spring-boot:run
```
Same for system 2(core Banking Service)
### Working Link 
https://paytab-assgt-frontend.vercel.app/login
