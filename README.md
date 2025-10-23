# Disease Detection System

This project is a **full-stack disease detection application** built with **Spring Boot (Java)** for the backend and **React.js** for the frontend.
It supports **Diabetes prediction** (using an ONNX ML model) and **Breast Cancer prediction**. 
Users can submit their medical data, receive predictions, and store results in the database.

---

## Features

- Predict **Diabetes** using medical data: pregnancies, glucose, blood pressure, BMI, insulin, etc.  
- Predict **Breast Cancer** using relevant clinical features.  
- Save predictions to the database.  
- Reset password functionality via email.  
- Clean and modular project structure with REST APIs.  

---

## Technologies Used

- **Backend:** Java, Spring Boot, Spring Data JPA, ONNX Runtime  
- **Frontend:** React.js, Node.js  
- **Database:** MySQL  
- **Email Service:** JavaMailSender (for password reset)  
- **Build Tool:** Maven  

---

## Setup and Installation

1. **Clone the repository**

```bash
git clone https://github.com/moulendra143/disease_detection.git
cd disease_detection
```
Setup MySQL database:

Create a database, e.g., disease_db.

Update src/main/resources/application.properties with your DB credentials.

Run the application; tables will be auto-created using JPA.

Backend setup

bash
Copy code
```
mvn clean install
mvn spring-boot:run
```
Frontend setup
bash
Copy code
```
cd frontend
npm install
```
npm start
