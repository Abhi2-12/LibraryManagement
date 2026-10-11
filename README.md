# 📚 Library Management System

A **Java-based console application** for managing library books, built to practice object-oriented programming, database integration, and clean code organization.

The project is being developed in phases, with each phase focusing on practical Java development skills.

## ✨ Features

* **View Books** — Display all books stored in the database.
* **Search Books** — Find books by ID or title.
* **Add Books** — Add new books to the library.
* **Borrow Books** — Borrow available books and update their status.
* **Return Books** — Mark borrowed books as returned.
* **Filter Books** — View available books and currently borrowed books.
* **Remove Books** — Remove books that are not currently borrowed.
* **Persistent Storage** — Store book information in MySQL.

## 🛠️ Technologies

| Technology         | Purpose                              |
| ------------------ | ------------------------------------ |
| Java               | Application logic and OOP            |
| MySQL              | Database and persistent storage      |
| JDBC               | Communication between Java and MySQL |
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

## 📂 Project Structure

```text
LibraryManagement/
├── lib/
│   └── mysql-connector-j-26.7.0.jar
├── src/
│   ├── app/
│   │   └── Main.java
│   ├── dao/
│   │   └── BookDAO.java
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
│   │   └── Magazine.java
│   └── service/
│       └── Library.java
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
    title VARCHAR(150),
    author VARCHAR(100),
    year INT DEFAULT 2000,
    isbn VARCHAR(20),
    borrowed TINYINT(1) DEFAULT 0
);
```

### 3. Configure the database connection

Open `src/database/DatabaseConnection.java` and configure the connection URL, username, and password for your local MySQL installation.

**Security note:** Never commit real database passwords or other secrets to GitHub.

### 4. Compile the project

Run the following command from the project root in PowerShell:

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
* [ ] **Phase 3:** User registration, login, and role-based access
* [ ] **Final Phase:** Integrate and test the complete application

## 🎯 Project Goal

The goal of this project is to strengthen Java fundamentals by building a working application step by step, understanding each component, testing its behavior, and maintaining the code with Git.

## 👨‍💻 Author

**Md. Anamul Hassan Abhi**

* GitHub: [@Abhi2-12](https://github.com/Abhi2-12)
* Project Repository: [LibraryManagement](https://github.com/Abhi2-12/LibraryManagement)
