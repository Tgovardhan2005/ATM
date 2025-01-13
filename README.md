<h1 align="center">\ud83c\udfe7 Simple ATM System in Java</h1>

<p align="center">
This project is a basic ATM system created using Java. It simulates common banking activities like withdrawing and depositing money, checking balances, and viewing transaction history. All account details are saved in a file for later use!
</p>

---

## \ud83c\udf1f Features

### \ud83d\udd11 Secure Access
- **PIN Entry:** Users can access their account by entering a 4-digit PIN.

### \ud83d\udcb5 Banking Transactions
- **Withdrawal & Deposit:** Users can withdraw or deposit cash, and the balance updates automatically.

### \ud83d\udccb Account Management
- **Transaction History:** Keeps track of your recent transactions like deposits and withdrawals.
- **Edit Account Details:** Users can change their PIN or account holder name.

### \ud83d\udcc2 Data Persistence
- **File Storage:** Account details are saved in a file called `Account_Details.txt` for later use.
- **Random Account Number:** A unique 16-digit account number is generated for each user.

---

## \ud83d\udee0\ufe0f How the Program Works

### 1. Start the ATM System
- Indicate whether you are a new user.
- Enter your **4-digit PIN** to access the account.

### 2. Perform Banking Activities
- Choose options like:
  - Withdraw money
  - Deposit money
  - Check your account balance

### 3. Manage Account Details
- View recent transaction history.
- Edit your account holder name or PIN securely.

### 4. Data Storage
- Upon exiting, all account details (e.g., balance, transaction history) are saved in `Account_Details.txt`.

---

## \ud83d\ude80 Running the Program

1. Save the code in a file named **`ATM_System.java`**.
2. Compile the program:
   ```bash
   javac ATM_System.java
   ```
3. Run the program:
   ```bash
   java ATM_System
   ```

---
