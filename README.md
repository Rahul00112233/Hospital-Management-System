# 🏥 Hospital Management System

A **Hospital Management System (HMS)** built with **Spring Boot** to manage hospital operations such as **patient registration, doctor management, appointments, and role-based access**. The system provides secure authentication using **JWT**, supports CRUD operations, and follows a layered architecture for scalability and maintainability.  

---

## 🚀 Features

- 👨‍⚕️ **Doctor Management** – Add, update, view, and remove doctor details  
- 🧑‍🤝‍🧑 **Patient Management** – Register patients, update records, and view medical history  
- 📅 **Appointment Management** – Book, update, or cancel appointments  
- 🔑 **Authentication & Authorization** – JWT-based login system with **Role-based Access Control (Admin, Doctor, User)**  
- 📊 **Dashboard** – Summarized view of hospital data (patients, doctors, appointments)  
- 🛠️ **REST APIs** – Well-structured endpoints for integration  
- 🗄️ **Database Integration** – Uses **MySQL** for persistent storage  

---

## 🏗️ Tech Stack

- **Backend:** Spring Boot, Spring Security, JWT  
- **Database:** MySQL
- **ORM:** Hibernate / JPA  
- **Build Tool:** Maven / Gradle  
- **API Testing:** Postman  
  
## ⚙️ Installation & Setup

- Clone the repository

git clone https://github.com/your-username/hospital-management-system.git
cd hospital-management-system


- Configure the database (MySQL example)
In src/main/resources/application.properties:

spring.datasource.url=jdbc:mysql://localhost:3306/hospital_db
spring.datasource.username=root
spring.datasource.password=yourpassword
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
jwt.secret=your-secret-key


- Build & Run the application

mvn spring-boot:run
or

./gradlew bootRun

---
## Access the APIs

API Base URL: http://localhost:8081/

Example endpoints:

- POST /auth/login → Login with JWT

- GET /doctors → Get all doctors (Admin/User)

- POST /patients → Add new patient

- POST /appointments → Book appointment

---
## 🔑 Authentication & Roles

- Admin – Can manage doctors, patients, and appointments

- Doctor – Can view & update assigned patients

- User – Can book and manage their own appointments

---
## 🧪 Testing

Run unit and integration tests:

mvn test

---
## 📌 Future Enhancements

- UI with Jetpack Compose for frontend

- Email & SMS notifications for appointments

- Payment gateway integration

- Reports & Analytics

---
## 🤝 Contributing

Contributions, issues, and feature requests are welcome!
Feel free to fork this repo and submit a pull request.

---
## 📄 License

This project is licensed under the MIT License.
