# Wish Time

A modern and educational Spring Boot project designed to explore and understand the fundamentals of building **RESTful APIs**, managing backend workflows, and structuring a clean, scalable Java application.

---

## 🚀 Project Overview

**Wish Time** is a learning-focused backend application developed to strengthen understanding in:

* Spring Boot fundamentals
* REST API design and implementation
* Layered architecture (Controller → Service → Repository)
* Data validation and exception handling
* DTO (Data Transfer Object) usage
* Integration of authentication/authorization concepts (JWT-ready structure)
* Best practices for building maintainable backend systems
* Test automation using Selenium

This project is ideal for beginners who want hands-on experience with Spring Boot or anyone revising core backend concepts.

---

## 📚 Purpose of the Project

The main purpose of this project is to **study and experiment** with:

* Creating and structuring APIs using Spring Boot
* Sending and receiving data between client and server
* Practicing clean code principles
* Using validation, error handling, and response formatting
* Improving understanding of how a backend communicates with modern frontends

---

## 🛠️ Technologies Used

* **Java 17+**
* **Spring Boot** (Web, Security, Validation)
* **Maven**
* **Spring Data JPA** (optional based on project features)
* **MySQL** database
* **Lombok** for boilerplate reduction
* **React** (Frontend) for building the UI** for boilerplate reduction

---

## 🌐 Frontend

This project also includes a **React** frontend that communicates with the Spring Boot backend through REST APIs.

Key frontend areas:

* Modern UI components
* API integration with backend
* Async requests using Fetch/Axios
* State management (basic React hooks)

---

## 📁 Project Structure

```
wish-time/
 └── src/
      ├── main/java/com/example/wishtime
      │       ├── controller/
      │       ├── service/
      │       ├── repository/
      │       ├── dto/
      │       ├── model/
      │       └── config/
      └── main/resources/
              ├── application.properties
              └── static/templates
```

---

## 📌 Features Implemented / To Be Implemented

* ✔️ Basic REST endpoints
* ✔️ DTO-based request & response handling
* ✔️ Centralized validation
* ✔️ Error handling and custom messages
* ⏳ User authentication (JWT)
* ⏳ Unit tests (JUnit + Mockito)
* ⏳ Database integration

---

## 🔧 How to Run the Project

1. Clone the repository:

```bash
git clone https://github.com/MudithaGayanath/wish-time.git
```

2. Navigate into the project folder:

```bash
cd wish-time
```

3. Build and run:

```bash
mvn spring-boot:run
```

4. Access API endpoints via:

```
http://localhost:8080
```

---

## 📬 Contact

If you have suggestions or want to collaborate:
**GitHub:** [https://github.com/MudithaGayanath](https://github.com/MudithaGayanath)

---

## ⭐ Contribute

Contributions are welcome! Feel free to fork the repo and create a pull request.

If you like the project, don't forget to ⭐ star the repository!
