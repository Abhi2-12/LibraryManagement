# 📚 Library Management System

A **Java-based console application** for managing library books, built to practice object-oriented programming, database integration, authentication, and clean code organization.

The project is developed in phases, with each phase focusing on practical Java development skills.

## ✨ Features

* **View Books** — Display all books stored in the database.
* **Search Books** — Find books by ID or title.
* **Add Books** — Add new books to the library (admin only).
* **Borrow Books** — Borrow available books and record the borrowing member.
* **Return Books** — Return books and clear the borrowing member's ID.
* **Filter Books** — View available books and currently borrowed books.
* **Remove Books** — Remove books (admin only).
* **Member Registration** — Register new member accounts.
* **Login and Logout** — Authenticate members and manage login sessions.
* **Password Hashing** — Store password hashes instead of plain-text passwords.
* **Role-Based Access Control** — Restrict book management to administrators.
* **Persistent Storage** — Store book and member information in MySQL.

## 🛠️ Technologies

| Technology         | Purpose                              |
| ------------------ | ------------------------------------ |
| Java (JDK 22)      | Application logic and OOP            |
| MySQL 8            | Database and persistent storage      |
| JDBC               | Communication between Java and MySQL |
| MySQL Connector/J  | MySQL JDBC driver                    |
| Git & GitHub       | Version control and project tracking |
| Visual Studio Code | Development environment              |

## 🧠 Concepts Practiced

* Classes, objects, and constructors
* Encapsulation and inheritance
* Abstract classes and interfaces
* Method overriding and overloading
* Exception handling
* JDBC and SQL operations
* Data Access Object (DAO) pattern
* Separation of application, service, and database responsibilities
* Password hashing and verification
* Authentication and role-based authorization
* Git branches and meaningful commits

## 📂 Project Structure

```text
LibraryManagement/
├── lib/
│   └── mysql-connector-j-26.7.0.jar
├── src/
│   ├── app/
│   │   └── Main.java
│   ├── dao/
│   │   ├── BookDAO.java
│   │   └── MemberDAO.java
│   ├── database/
│   │   └── DatabaseConnection.java
│   ├── exception/
│   │   └── BookNotAvailableException.java
│   ├── interfaces/
│   │   ├── Borrowable.java
│   │   ├── Downloadable.java
│   │   ├── Reservable.java
│   │   └── Returnable.java
│   ├── model/
│   │   ├── Book.java
│   │   ├── EBook.java
│   │   ├── LibraryItem.java
│   │   ├── Magazine.java
│   │   ├── Member.java
│   │   └── Role.java
│   └── service/
│       ├── Library.java
│       ├── MemberService.java
│       └── PasswordUtil.java
├── .gitignore
└── README.md
```

## ⚙️ Requirements

* JDK 22
* MySQL Server 8.0 or later
* MySQL Connector/J
* Visual Studio Code or another Java IDE

## 🚀 Getting Started

### 1. Clone the repository

```bash
git clone https://github.com/Abhi2-12/LibraryManagement.git
cd LibraryManagement
```

### 2. Set up the database

Start MySQL and create the database:

```sql
CREATE DATABASE library_db;
USE library_db;
```

Create the `books` table:

```sql
CREATE TABLE books (
    id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(150) NOT NULL,
    author VARCHAR(100) NOT NULL,
    year INT NOT NULL DEFAULT 2000,
    isbn VARCHAR(20) NOT NULL,
    borrowed TINYINT(1) NOT NULL DEFAULT 0,
    borrowed_by INT NULL
);
```

Create the `members` table:

```sql
CREATE TABLE members (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(150) NOT NULL UNIQUE,
    password_hash VARCHAR(255) NOT NULL,
    role ENUM('ADMIN', 'MEMBER') NOT NULL DEFAULT 'MEMBER'
);
```

Add the foreign key linking borrowed books to members:

```sql
ALTER TABLE books
ADD CONSTRAINT fk_books_borrowed_by
FOREIGN KEY (borrowed_by) REFERENCES members(id);
```

These commands describe a fresh database setup. If you already have the tables, do not run the `CREATE TABLE` statements again. Update the existing schema only where necessary.

### 3. Configure the database connection

Open `src/database/DatabaseConnection.java` and configure the connection URL, username, and password for your local MySQL installation.

**Security note:** Never commit real database passwords or other secrets to GitHub.

### 4. Compile the project

Run this command from the project root in PowerShell:

```powershell
javac -cp "lib\mysql-connector-j-26.7.0.jar" -d out src\interfaces\*.java src\exception\*.java src\model\*.java src\database\*.java src\dao\*.java src\service\*.java src\app\*.java
```

### 5. Run the application

```powershell
java -cp "out;lib\mysql-connector-j-26.7.0.jar" app.Main
```

## 🗺️ Development Roadmap

* [x] **Phase 1:** Java fundamentals and object-oriented programming
* [x] **Phase 2:** MySQL integration and database-backed book management
* [x] **Phase 3:** Member registration, login, password hashing, role-based access, and borrower tracking
* [ ] **Phase 4:** Spring Boot REST API
* [ ] **Phase 5:** Frontend development and API integration

## 🎯 Project Goal

The goal of this project is to strengthen Java fundamentals by building a working application step by step, understanding each component, testing its behavior, and maintaining the code with Git.

The console application provides the foundation for a future REST API and frontend.

**Current limitation:** `EBook` and `Magazine` demonstrate OOP concepts but are not yet integrated into database-backed item management.

## 👨‍💻 Author

**Md. Anamul Hassan Abhi**

* GitHub: [@Abhi2-12](https://github.com/Abhi2-12)
* Project Repository: [LibraryManagement](https://github.com/Abhi2-12/LibraryManagement)
