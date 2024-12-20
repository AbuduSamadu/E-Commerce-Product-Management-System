# E-Commerce Application

This project is a e-commerce application built using a layered architecture. It combines SQL and NoSQL databases for optimal performance and functionality. The system is designed for managing products, categories, carts, orders, and users with an audit trail for changes.

---

## Features
- User authentication and authorization (roles: USER, ADMIN).
- Product and category management using a binary tree for efficient retrieval.
- Shopping cart functionality with stock management.
- Order placement and order history tracking.
- Audit logging for database operations.
- Hybrid database setup: SQL for transactional data, NoSQL for audit logging.

---

## Database Schema
### SQL Database
- **User** - Stores user details.
- **Product** - Stores product details.
- **Category** - Stores category details.
- **Cart** - Stores cart details.
- **Order** - Stores order details.


### NoSQL Database
  - **audit_logs** - Stores audit logs.

---

## Architecture
The application is built using a layered architecture with the following components:
- **Controllers** - REST controllers for handling API requests.
- **Models** - Entity models for SQL and NoSQL.
- **Repositories** - JPA and NoSQL repositories.
- **Services** - Business logic.
- **Utilities** - Helper classes and utilities.
- **DTOs** - Data Transfer Objects.
- **Exceptions** - Custom exceptions.

---
## Entity Relationship Diagram
![ERD](UML%20class.png)

## Project Structure
```plaintext
src/
├── config/                # Configuration files for database and security.
├── controllers/           # REST controllers for handling API requests.
├── dtos/                  # Data Transfer Objects.
├── exceptions/            # Custom exceptions.
├── models/                # Entity models for SQL and NoSQL.
├── repositories/          # JPA and NoSQL repositories.
├── services/              # Business logic.
├── utilities/             # Helper classes and utilities.
└── Application.java       # Main application entry point.
```

---

## Technologies Used
### Backend
- Java with Spring Boot
- Hibernate (JPA) for SQL interactions
- MongoDB for NoSQL data

### Database
- PostgreSQL (SQL)
- MongoDB (NoSQL)

---

## Setup Instructions

### Prerequisites
1. Install Java (version 17 or higher).
2. Install PostgreSQL and MongoDB.
3. Maven for dependency management.

### Configuration
Update the following files with your database credentials:

- **application.properties** (common configurations)
- **application-dev.properties** (development environment)
- **application-prod.properties** (production environment)

Example for PostgreSQL:
```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/ecommerce_db
spring.datasource.username=your_username
spring.datasource.password=your_password
```

Example for MongoDB:
```properties
spring.data.mongodb.uri=mongodb://localhost:27017/ecommerce_auditlog
```

### Run the Application
```bash
mvn spring-boot:run
```

---

## API Endpoints

### User
- **POST** `/api/v1/auth/register` - Register a new user.
- **POST** `/api/v1/auth/login` - Login user.


### Product
- **GET** `/api/v1/products` - Get all products.
- **POST** `/api/v1/products` - Add a new product (Admin only).

### Category
- **GET** `/api/v1/categories` - Get all categories.
- **POST** `/api/v1/categories` - Add a new category (Admin only).

### Cart
- **GET** `/api/v1/cart` - View user cart.
- **POST** `/api/v1/cart/add` - Add a product to cart.

### Order
- **POST** `/api/v1/orders` - Place an order.
- **GET** `/api/v1/orders` - View user order history.

---

## Testing
Run unit tests using:
```bash
mvn test
```

---

## Contribution Guidelines
1. Fork the repository.
2. Create a feature branch (`git checkout -b feature-name`).
3. Commit changes (`git commit -m 'Add some feature'`).
4. Push to the branch (`git push origin feature-name`).
5. Open a pull request.

---

## License
This project is licensed under the MIT License. See the `LICENSE` file for details.

---

## Contact
For questions or collaboration, please contact:
- **Email:** [abudusamadu](mailto:abudusamed@gmail.com)
- **GitHub:** [Mascot](https://github.com/abudusamadu)
