# Online Reservation System

A GUI-based **Train Reservation System** developed using **Java Swing, JDBC, SQLite, and Maven**. The application provides a simple interface for users to log in, reserve train tickets, generate unique PNR numbers, view booking details, and cancel reservations.

## Features

* 🔐 **Login System** — Username and password authentication
* 🎫 **Ticket Reservation** — Book train tickets by entering passenger and journey details
* 🚆 **Train Details** — Train name is automatically populated based on the train number
* 🆔 **PNR Generation** — Automatically generates a unique PNR number for each booking
* 📋 **Booking Confirmation** — Displays complete booking details after successful reservation
* 🔎 **PNR Search** — Retrieve booking details using the PNR number
* ❌ **Ticket Cancellation** — Cancel an existing reservation after confirmation
* ✅ **Input Validation** — Validates required fields, train numbers, dates, and other inputs
* 💾 **SQLite Database** — Stores reservation information using JDBC

## Tech Stack

| Technology       | Purpose                           |
| ---------------- | --------------------------------- |
| **Java 21**      | Application development           |
| **Java Swing**   | Graphical User Interface          |
| **JDBC**         | Database connectivity             |
| **SQLite**       | Data storage                      |
| **Maven**        | Project and dependency management |
| **Git & GitHub** | Version control                   |

## Project Structure

```text
JavaDevelopment-Task1-OnlineReservationSystem/
│
├── pom.xml
├── reservation.db
│
└── src/
    └── main/
        └── java/
            └── reservation/
                ├── Main.java
                ├── Database.java
                ├── LoginFrame.java
                ├── ReservationFrame.java
                └── CancellationFrame.java
```

## Login Credentials

The application uses the following demo credentials:

```text
Username: admin
Password: admin123
```

> These credentials are provided for demonstration purposes.

## Sample Train Data

The application includes the following sample train numbers:

| Train Number | Train Name             |
| -----------: | ---------------------- |
|        12627 | Karnataka Express      |
|        12649 | Sampark Kranti Express |
|        16515 | Karwar Express         |
|        16575 | Gomateshwara Express   |
|        12051 | Jan Shatabdi Express   |

For other valid numeric train numbers, the application displays **Special Express**.

## Reservation Details

Users are required to provide:

* Passenger Name
* Train Number
* Train Name
* Class Type
* Journey Date
* Source Station
* Destination Station

After a successful reservation, the system generates a unique **PNR number** and displays the complete booking confirmation.

## Cancellation

To cancel a reservation:

1. Enter the PNR number.
2. Click **Fetch**.
3. Review the displayed booking details.
4. Click **Confirm Cancellation**.
5. Confirm the cancellation when prompted.

The reservation is then removed from the SQLite database.

## Input Validation

The system performs basic validation to ensure:

* Required fields are not empty.
* Passenger name contains valid characters.
* Train number contains only numeric values.
* Journey date follows the `DD-MM-YYYY` format.
* Source and destination stations are different.
* PNR input contains a valid numeric value.

## Database

The application uses **SQLite** for persistent storage.

The database file is:

```text
reservation.db
```

The reservation table stores:

* PNR
* Passenger Name
* Train Number
* Train Name
* Class Type
* Journey Date
* Source
* Destination

The database and required table are initialized automatically when the application starts.

## Prerequisites

Before running the project, make sure you have:

* **JDK 21** or later
* **Apache Maven**
* **Visual Studio Code** or any Java-compatible IDE
* Internet connection for Maven dependency downloads during the first build

## How to Run

### 1. Clone the Repository

```bash
git clone https://github.com/chirurnayak/OIBSIP.git
```

### 2. Navigate to the Project

```bash
cd OIBSIP/JavaDevelopment-Task1-OnlineReservationSystem
```

### 3. Compile the Project

```bash
mvn clean compile
```

### 4. Run the Application

```bash
mvn exec:java
```

The login window will open after the application starts.

## Application Flow

```text
Login
  ↓
Reservation Form
  ↓
Enter Journey Details
  ↓
Book Ticket
  ↓
Generate PNR
  ↓
Booking Confirmation
  ↓
Cancel Booking
  ↓
Enter PNR
  ↓
Fetch Booking Details
  ↓
Confirm Cancellation
  ↓
Booking Removed
```

## Learning Objectives

This project demonstrates practical implementation of:

* Java GUI development using Swing
* Object-oriented programming
* JDBC connectivity
* SQLite database operations
* CRUD operations
* Form validation
* Event handling
* Exception handling
* Maven project management
* Git and GitHub version control

## Project Information

**Project:** Online Reservation System
**Task:** Task 1 — Java Development Internship
**Organization:** Oasis Infobyte
**Author:** Chirag R Nayak

## License

This project was created for educational and internship purposes.
