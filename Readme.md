# Spring Boot PharmacyEase Application

This is a Spring Boot application for managing an online pharmacy system.

## Overview

The application provides the following features:
- User registration and login
- Product management (add, list)
- Order placement and history tracking

## Technologies Used

- Java
- Spring Boot
- Spring Data JPA
- MySQL 
- Maven 

## Getting Started

1. Clone the repository:

```bash
git clone https://github.com/KINDREW/pharmacy-ease.git
```

2. Configure the application properties in `src/main/resources/application.properties`:
   - Set up your database connection details as environment variables or use an external configuration file.
  
  
  ```bash
	spring.datasource.url=your db url
	spring.datasource.username= your db username
	spring.datasource.password= your db password
	spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
	spring.jpa.hibernate.ddl-auto=update
```


server.port=8080
   
   - Configure any other properties as needed
   
   ```bash
	server.port=8080
```

3. Build the application:

```bash
mvn clean package
```

4. Run the application:

```bash
java -jar target/pharmacy-ease.jar
```

## Usage

- Register a new user by making a POST request to `/users/register` with a JSON payload containing user details.
- Log in by making a POST request to `/users/login` with a JSON payload containing login credentials.
- Add a new product by making a POST request to `/products` with a JSON payload containing product details.
- View all products by making a GET request to `/products`.
- Place an order by making a POST request to `/orders` with a JSON payload containing order details.
- View order history by making a GET request to `/orders/{email}` with the user's email.

## Contributing

Contributions are welcome! Please fork the repository, make your changes, and submit a pull request.

