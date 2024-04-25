
# Banking Application

## Introduction
This Banking Application is designed to provide a robust and secure platform for managing personal banking operations. It supports operations such as account creation, deposits, withdrawals, transfers, and account closure, along with real-time notifications for account activities.

This project is mainly intended to demonstrate use of GoF Design Patterns using Java.

## Features

- **Account Management**: Users can create, view, and close their banking accounts.
- **Transaction Handling**: Supports deposits, withdrawals, and transfers between accounts.
- **Real-Time Notifications**: Sends email notifications for significant account activities.


## Technologies Used

- **Backend**: Spring Boot, Hibernate, Spring Data JPA
- **Frontend**: React, Axios
- **Database**: MySQL, H2 (for development testing)
- **Build Tool**: Maven

## Getting Started

### Prerequisites

- Java JDK 11 or later
- Node.js 12.x or later
- Maven 3.6.x
- MySQL Server 8.x (or Docker to run a MySQL container)


## Front End Setup
Frontend Setup Navigate to 
```bash
cd frontend

cd banking-app
```
Run following commands to install dependencies and Run
```bash
npm install

npm start
```


## Back End Setup

In your Java IDE import project from existing sources as Maven project.

Then run maven clean, maven install and run the application

OR

Run following commands to install dependencies and Run
```bash
mvn clean install

mvn spring-boot:run

```

