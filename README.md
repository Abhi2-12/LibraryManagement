# 🌐 Library Management – Phase 5 Backend (Spring Boot)

**Spring Boot REST API** that implements the library‑management core features and serves the upcoming React frontend (Phase 6). It now uses a **persistent `library_users` table** for registration and login, while books are stored in the `books` table.

---

## ✨ Key Features

| Feature | Description |
|---------|-------------|
| **User registration & login** | New `LibraryUser` entity persisted in MySQL. Users can sign‑up via the `/api/auth/register` endpoint (not shown in the current controller but ready to be added) and log in via HTTP Basic (or JWT in future). |
| **Authentication** | Spring Security authenticates against the `library_users` table (via a custom `UserDetailsService`). |
| **Role‑Based Access** | `ADMIN` can **create**, **update**, **delete** books; `USER` can **view**, **borrow**, and **return** books. |
| **Book Management** | CRUD endpoints (`/api/books`) backed by the JPA `Book` entity. |
| **Borrow / Return** | `/api/books/{id}/borrow` and `/api/books/{id}/return` toggle the `borrowed` flag and record the borrowing user via `borrowed_by`. |
| **CORS Configuration** | Allows the React dev server (`http://localhost:5173`) to call the API. |
| **Global Exception Handling** | Centralised error responses via `GlobalExceptionHandler`. |
| **Swagger UI** (optional) | API docs available at `/swagger-ui.html` when the dependency is added. |

---

## 🛠️ Tech Stack

| Technology | Purpose |
|------------|---------|
| **Java 22** | Core language |
| **Spring Boot** | Application framework & embedded Tomcat |
| **Spring Data JPA** | ORM layer for MySQL |
| **Spring Security** | Authentication & authorization |
| **MySQL 8** | Persistent storage |
| **Maven / Gradle** | Build tool |
| **JWT** (optional) | Token‑based auth – can be added later |
| **Swagger/OpenAPI** (optional) | API documentation |

---

## 📁 Project Structure (backend)

```
api/
└─ src/main/java/com/library/api/
   ├─ config/                # SecurityConfig (CORS, password encoder, custom UserDetailsService)
   ├─ controller/            # AuthController ( /api/auth/me ), BookController ( CRUD )
   ├─ dto/                   # DTOs for registration, login responses, BookDto, etc.
   ├─ exception/             # GlobalExceptionHandler
   ├─ model/                 # Book, LibraryUser (JPA entities)
   ├─ repository/            # BookRepository, LibraryUserRepository (extends JpaRepository)
   └─ service/               # UserDetailsService implementation, BookService, etc.
   └─ LibraryApiApplication.java   # Spring Boot entry point
src/main/resources/
   └─ application.properties  # DB connection, JPA settings, security logs
```

---

## 🗄️ Database Schema (current)

```sql
CREATE DATABASE IF NOT EXISTS library_db;
USE library_db;

-- Books table (managed by JPA Book entity)
CREATE TABLE IF NOT EXISTS books (
    id           INT AUTO_INCREMENT PRIMARY KEY,
    title        VARCHAR(150) NOT NULL,
    author       VARCHAR(100) NOT NULL,
    year         INT NOT NULL,
    isbn         VARCHAR(20) NOT NULL,
    borrowed     TINYINT(1) NOT NULL DEFAULT 0,
    borrowed_by  BIGINT NULL,
    CONSTRAINT fk_books_borrowed_by
        FOREIGN KEY (borrowed_by) REFERENCES library_users(id)
);

-- Library users (new registration system)
CREATE TABLE IF NOT EXISTS library_users (
    id          BIGINT AUTO_INCREMENT PRIMARY KEY,
    full_name   VARCHAR(100) NOT NULL,
    email       VARCHAR(150) NOT NULL UNIQUE,
    username    VARCHAR(50)  NOT NULL UNIQUE,
    password    VARCHAR(255) NOT NULL,
    role        VARCHAR(20)  NOT NULL DEFAULT 'USER',
    created_at  TIMESTAMP    DEFAULT CURRENT_TIMESTAMP
);
```

> The older `members` table is still present (from earlier phases) but is **not used** by the current backend. All authentication now relies on `library_users`.

---

## 🚀 Getting Started

```bash
# 1️⃣ Clone the repo
git clone https://github.com/Abhi2-12/LibraryManagement.git
cd LibraryManagement
git checkout phase-5-backend

# 2️⃣ Configure the DB (edit src/main/resources/application.properties)
#    spring.datasource.url=jdbc:mysql://localhost:3306/library_db
#    spring.datasource.username=YOUR_USER
#    spring.datasource.password=YOUR_PASSWORD
#    spring.jpa.hibernate.ddl-auto=update   # let Hibernate create/alter tables automatically

# 3️⃣ Build the project
# Maven example
mvn clean package   # produces target/library-management-*.jar
# or Gradle (if wrapper is present)
./gradlew bootJar

# 4️⃣ Run the API
java -jar target/*.jar   # defaults to http://localhost:8080
```

The API will now expose:
- **`POST /api/auth/register`** – create a new `LibraryUser` (JSON body with `fullName`, `email`, `username`, `password`).
- **`GET /api/auth/me`** – return the logged‑in user's username and roles.
- All book endpoints as described earlier.

---

## 🧪 Testing Endpoints

| Method | URL | Role required | Description |
|--------|-----|---------------|-------------|
| `GET` | `/api/books` | – (public) | List all books |
| `GET` | `/api/books/{id}` | – (public) | Get a single book |
| `POST` | `/api/books` | **ADMIN** | Add a new book |
| `PUT` | `/api/books/{id}` | **ADMIN** | Update a book |
| `DELETE` | `/api/books/{id}` | **ADMIN** | Delete a book |
| `POST` | `/api/books/{id}/borrow` | **USER / ADMIN** | Borrow a book (sets `borrowed = true` and `borrowed_by` to the current user id) |
| `POST` | `/api/books/{id}/return` | **USER / ADMIN** | Return a book |
| `POST` | `/api/auth/register` | – (public) | Register a new user (stores in `library_users`) |
| `GET` | `/api/auth/me` | any authenticated user | Get current user details |

---

## 📦 Docker (optional)

```dockerfile
# Build stage
FROM maven:3-eclipse-temurin-22-alpine AS builder
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn -B package -DskipTests

# Runtime stage
FROM eclipse-temurin:22-jre-alpine
COPY --from=builder /app/target/*.jar /app/app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app/app.jar"]
```

Build & run:

```bash
docker build -t library-backend .
docker run -d -p 8080:8080 \
  -e SPRING_DATASOURCE_URL=jdbc:mysql://host.docker.internal:3306/library_db \
  -e SPRING_DATASOURCE_USERNAME=YOUR_USER \
  -e SPRING_DATASOURCE_PASSWORD=YOUR_PASS \
  library-backend
```

---

## 🗺️ Roadmap (project‑wide)

- **Phase 4** – Complete Spring Boot REST API (now done).  
- **Phase 5** – Backend (this branch):
  - Finish registration endpoint (`/api/auth/register`).
  - Replace in‑memory users with `LibraryUser`‑based auth.
  - Optionally switch to JWT tokens for stateless auth.
  - Add integration tests.
- **Phase 6** – Frontend (React + Vite) – already updated with its own README.

---

## 👨‍💻 Author

**Md. Anamul Hassan Abhi**
- GitHub: [@Abhi2-12](https://github.com/Abhi2-12)
- Project: <https://github.com/Abhi2-12/LibraryManagement>
