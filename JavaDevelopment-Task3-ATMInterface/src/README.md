# ATM Machine

A console-based ATM Machine simulation developed using Java and Object-Oriented Programming concepts as part of the **Oasis Infobyte Java Development Internship**.

## 📌 Objective

The objective of this project is to simulate the basic operations of an ATM machine through a secure and user-friendly console interface.

The application allows users to authenticate using a User ID and PIN and perform common banking operations such as withdrawal, deposit, transfer, and viewing transaction history.

## 🛠️ Technologies Used

- Java 21
- Object-Oriented Programming (OOP)
- Java Collections
- ArrayList
- HashMap
- Java Date & Time API
- Console / Command Line Interface

## ✨ Features

### 🔐 User Authentication
- Login using User ID and PIN.
- Maximum of 3 incorrect login attempts.
- Access is denied after 3 failed attempts.

### 💰 Withdraw
- Enter the amount to withdraw.
- Checks whether sufficient balance is available.
- Displays `Insufficient Funds` when the balance is too low.
- Updates the account balance.
- Records the transaction.

### 💵 Deposit
- Enter the amount to deposit.
- Validates the entered amount.
- Updates the account balance.
- Records the transaction.

### 🔄 Transfer
- Enter the recipient's account number.
- Enter the transfer amount.
- Checks the sender's available balance.
- Updates both sender and recipient balances.
- Records the transaction for both accounts.
- Prevents transferring money to the same account.

### 📜 Transaction History
- Displays all transactions performed during the session.
- Includes transaction type, amount, description, and date/time.
- Transactions are stored using `ArrayList`.

### 🚪 Quit
- Displays a goodbye message.
- Safely exits the ATM application.

## 🏦 Demo Accounts

The application contains two accounts for testing the ATM functionality.

### Account 1

| Field | Details |
|---|---|
| User ID | `user123` |
| PIN | `1234` |
| Account Number | `ACC1001` |
| Account Holder | Chirag |
| Initial Balance | Rs. 25,000.00 |

### Account 2

| Field | Details |
|---|---|
| User ID | `user456` |
| PIN | `5678` |
| Account Number | `ACC1002` |
| Account Holder | Rahul |
| Initial Balance | Rs. 15,000.00 |

## ▶️ How to Run

### 1. Clone the Repository

Clone the OIBSIP repository:

```bash
git clone https://github.com/chirurnayak/OIBSIP.git
