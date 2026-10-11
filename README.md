# Library Management System — Phase 1: Java OOP

A console-based Library Management System built using Java to practice core Java programming and Object-Oriented Programming (OOP) concepts.

This is the first phase of a multi-phase project. The application manages books in memory and provides basic library operations through a menu-driven console interface.

## Features

* Display all books
* Search for books by ID or title
* Borrow and return books
* Display available and borrowed books
* Remove books from the library
* Handle invalid book IDs and unavailable books
* Use a menu-driven console interface

## Technologies Used

* **Language:** Java
* **Concepts:** Object-Oriented Programming (OOP)
* **Data Storage:** `ArrayList` (in-memory storage)
* **Tools:** Visual Studio Code, Git, and GitHub

## OOP Concepts Practiced

* **Classes and Objects:** Represent library items and books.
* **Encapsulation:** Keep fields private and access them through methods.
* **Constructors:** Initialize objects when they are created.
* **Inheritance:** Reuse common properties through a parent class.
* **Abstraction:** Define shared behavior using an abstract class.
* **Interfaces:** Define behaviors such as borrowing and returning books.
* **Polymorphism:** Override methods to provide specific behavior.
* **Method Overloading:** Use methods with the same name but different parameters.
* **Exception Handling:** Handle errors such as borrowing an unavailable book.

## Project Structure

```text
LibraryManagement/
└── src/
    ├── Book.java
    ├── Library.java
    ├── LibraryItem.java
    ├── EBook.java
    ├── Magazine.java
    ├── Borrowable.java
    ├── Returnable.java
    ├── Downloadable.java
    ├── Reservable.java
    ├── BookNotAvailableException.java
    └── Main.java
```

*Note: The structure lists the main Phase 1 source files. Adjust it if your actual filenames differ.*

## How to Run

1. Install a JDK.
2. Clone or download this repository.
3. Open the project folder in Visual Studio Code.
4. Compile the Java source files.
5. Run the `Main` class.

If you are using a terminal and all source files are in the default package, you can compile and run them with:

```bash
javac -d out src/*.java
java -cp out Main
```

Run these commands from the project root directory.

## Current Limitations

* Book data is stored in memory and is lost when the program closes.
* There is no database connection yet.
* User login and role-based access are not implemented.

These features are planned for later phases.

## Development Roadmap

* [x] Phase 1 — Java fundamentals and OOP
* [ ] Phase 2 — MySQL database integration using JDBC
* [ ] Phase 3 — User management, login, and authorization
* [ ] Final phase — Integrate and complete the application

## Learning Goal

The goal of this project is to strengthen Java fundamentals by building a practical application, understanding OOP principles, and gradually improving the system through testing and new features.

## Author

**Md. Anamul Hassan Abhi**

GitHub: [Abhi2-12](https://github.com/Abhi2-12)

---

*Phase 1 completed: Console-based Library Management System using Java OOP.*
