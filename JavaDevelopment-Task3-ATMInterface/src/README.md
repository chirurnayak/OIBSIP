# ATM Machine

A console-based ATM Machine simulation developed using Java and Object-Oriented Programming (OOP) concepts as part of the Oasis Infobyte Java Development Internship.

## Objective

To build a secure and user-friendly console-based ATM simulation that allows users to authenticate using a User ID and PIN and perform basic banking transactions.

## Features

- User authentication using User ID and PIN
- Maximum of 3 incorrect login attempts
- Transaction history
- Cash withdrawal
- Cash deposit
- Money transfer between accounts
- Balance validation before withdrawal and transfer
- Insufficient Funds validation
- Transaction records using ArrayList
- Multiple bank accounts
- Input validation
- Transaction date and time
- Secure account information using encapsulation
- Goodbye message on exit

## Technologies Used

- Java 21
- Object-Oriented Programming
- ArrayList
- HashMap
- Java Date & Time API
- Console / Command Line Interface

## Project Structure

```text
ATM-Machine/
│
├── src/
│   ├── Main.java
│   ├── ATM.java
│   ├── Account.java
│   ├── Bank.java
│   └── Transaction.java
│
└── README.md
```

## Class Responsibilities

| Class | Responsibility |
|-------|-----------------|
| Main | Starts the ATM application |
| ATM | Handles login, menu, user input and ATM operations |
| Account | Stores account information and manages balance and transactions |
| Bank | Manages multiple accounts and account lookup |
| Transaction | Stores transaction details |

## Demo Accounts

### Account 1
- User ID: `user123`
- PIN: `1234`
- Account Number: `ACC1001`
- Account Holder: Chirag
- Initial Balance: Rs. 25,000.00

### Account 2
- User ID: `user456`
- PIN: `5678`
- Account Number: `ACC1002`
- Account Holder: Rahul
- Initial Balance: Rs. 15,000.00

## Main Menu

After successful login, the following options are available:

1. Transaction History
2. Withdraw
3. Deposit
4. Transfer
5. Quit

## Transaction History

The application stores transactions using an ArrayList.

Each transaction contains:

- Transaction type
- Amount
- Description
- Date and time

Example:

```text
TYPE         | AMOUNT       | DESCRIPTION
------------------------------------------------
OPENING      | Rs. 25000.00 | Opening balance
WITHDRAW     | Rs. 5000.00  | Cash withdrawn
DEPOSIT      | Rs. 2000.00  | Cash deposited
TRANSFER     | Rs. 3000.00  | Transfer to ACC1002
```

## Withdrawal

The user enters the amount to withdraw.

The application checks whether the account has sufficient balance.

Example:

```text
Enter withdrawal amount: Rs. 5000

Withdrawal successful!
Amount Withdrawn: Rs. 5000.00
Remaining Balance: Rs. 20000.00
```

If the requested amount is greater than the available balance:

```text
Insufficient Funds.
```

## Deposit

The user enters the amount to deposit.

Example:

```text
Enter deposit amount: Rs. 3000

Deposit successful!
Amount Deposited: Rs. 3000.00
New Balance: Rs. 28000.00
```

## Transfer

The user enters the recipient's account number and transfer amount.

Example:

```text
Enter recipient account number: ACC1002
Enter transfer amount: Rs. 5000

Transfer successful!
Amount Transferred: Rs. 5000.00
Recipient: Rahul
Recipient Account: ACC1002
Remaining Balance: Rs. 20000.00
```

The transfer is recorded in both the sender's and recipient's transaction histories.

## Authentication

The application allows a maximum of 3 incorrect login attempts.

Example:

```text
Enter User ID: user123
Enter PIN: 1234

Login successful!
Welcome, Chirag!
```

After 3 incorrect attempts:

```text
ACCESS DENIED
Too many incorrect login attempts.
Please try again later.
```

## How to Run

### Prerequisites

Install Java 21 or later.

Check the Java version:

```bash
java -version
```

Check the Java compiler:

```bash
javac -version
```

### Compile

Navigate to the src directory:

```bash
cd ATM-Machine/src
```

Compile all Java files:

```bash
javac *.java
```

### Run

```bash
java Main
```

## OOP Concepts Used

### Encapsulation

Account information is stored using private fields and accessed through appropriate methods.

### Abstraction

The ATM provides users with simple banking operations while the internal implementation is handled by separate classes.

### Classes and Objects

The application is divided into multiple classes, with each class responsible for a specific part of the system.

### Composition

The Account class contains an ArrayList of Transaction objects to maintain transaction history.

## Collections Used

### ArrayList

Used to store and display transaction history.

### HashMap

Used by the Bank class to manage multiple accounts.

## Input Validation

The application validates:

- Incorrect User ID or PIN
- Maximum login attempts
- Invalid menu choices
- Invalid numeric input
- Zero or negative transaction amounts
- Insufficient balance
- Invalid recipient account
- Transfer to the same account

## Future Enhancements

- Database integration
- User registration
- PIN change functionality
- Persistent account balances
- Mini statement generation
- GUI-based ATM interface
- Receipt generation
- Improved security and encrypted PIN storage

## Internship

Developed as part of the Oasis Infobyte Java Development Internship.

- Project: ATM Machine
- Language: Java
- Application Type: Console-Based
- Development Approach: Object-Oriented Programming

## License

This project was developed for educational and internship purposes.
