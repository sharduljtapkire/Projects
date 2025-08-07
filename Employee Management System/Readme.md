# 📘 Employee Management System (Spring Boot)

## 🧾 Project Description
The Employee Management System is a RESTful web application developed using Spring Boot. It provides basic CRUD operations to manage employee records via a REST API and is testable through Postman.

---

## ✅ Features
- Add new employees
- Get all employees
- Get employee by ID
- Update employee
- Delete employee
- Global exception handling

---

## 🛠 Technologies Used
- Java 17+
- Spring Boot
- Spring Data JPA
- MySQL (or H2 for in-memory testing)
- Maven
- Postman for testing

---

## 📁 Project Structure

```
src/
├── main/
│   ├── java/com/ems/
│   │   ├── controller/EmployeeController.java
│   │   ├── exception/ResourceNotFoundException.java
│   │   ├── model/Employee.java
│   │   ├── repository/EmployeeRepository.java
│   │   └── EmployeeManagementSystemApplication.java
│   └── resources/
│       └── application.properties
└── test/
    └── java/com/ems/
        └── EmployeeManagementSystemApplicationTests.java
```

---

## 🚀 How to Run

1. Import the project into IntelliJ IDEA / Eclipse.
2. Configure your database credentials in `application.properties`:
   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/ems_db
   spring.datasource.username=root
   spring.datasource.password=your_password
   spring.jpa.hibernate.ddl-auto=update
   ```
3. Run `EmployeeManagementSystemApplication.java`.
4. Test the API using Postman.

---

## 🧪 API Endpoints

| Method | Endpoint                          | Description                |
|--------|-----------------------------------|----------------------------|
| GET    | `/api/v1/employees`               | Fetch all employees        |
| GET    | `/api/v1/employees/{id}`          | Get employee by ID         |
| POST   | `/api/v1/employees`               | Create a new employee      |
| PUT    | `/api/v1/employees/{id}`          | Update an existing employee|
| DELETE | `/api/v1/employees/{id}`          | Delete an employee         |

#### Example Request (POST):
```json
{
  "firstName": "John",
  "lastName": "Doe",
  "emailId": "john.doe@example.com"
}
```

---

## 📦 Build and Run with Maven

```bash
mvn clean install
mvn spring-boot:run
```