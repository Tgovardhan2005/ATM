<h1 align="center">🏧 Simple ATM System in Java</h1>

<p align="center">
This project is a basic ATM system created using Java. It simulates common banking activities like withdrawing and depositing money, checking balances, and viewing transaction history. All account details are saved in a file for later use!
</p>

## 🌟 Features

### 🔐 Secure Access
- **🔑 PIN and OTP Entry:** Users can access their account by entering a 4-digit PIN and an 8-digit OTP.

### 💵 Banking Transactions
- **💸 Withdrawal & Deposit:** Users can withdraw or deposit cash, and the balance updates automatically.

### 📋 Account Management
- **📜 Transaction History:** Keeps track of your recent transactions like deposits and withdrawals.
- **📝 Edit Account Details:** Users can change their PIN or account holder name.

### 🗂 Data Persistence
- **📂 File Storage:** Account details are saved in a file called `Account_Details.txt` for later use.
- **🆔 Random Account Number:** A unique 16-digit account number is generated for each user.

---

## 🛠️ How the Program Works

### 1. 🚪 Start the ATM System
- Enter your **4-digit PIN** and **8-digit OTP** to access the account.

### 2. ⚙️ Perform Banking Activities
- Choose options like:
  - 💳 Withdraw money
  - 🏦 Deposit money
  - 📊 Check your account balance

### 3. 🔧 Manage Account Details
- View recent transaction history.
- Edit your account holder name or PIN securely.

### 4. 💾 Data Storage
- Upon exiting, all account details (e.g., balance, transaction history) are saved in `Account_Details.txt`.

---

## 🚀 Running the Program

1. Save the code in a file named **`ATM_System.java`**.
2. Compile the program:
   ```bash
   javac ATM_System.java
