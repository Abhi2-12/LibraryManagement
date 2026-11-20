# 🌐 Library Management – Phase 5 Backend (Spring Boot)

**Spring Boot REST API** that implements the library‑management core features and serves the upcoming React frontend (Phase 6). The API provides user authentication (in‑memory for now), role‑based access control, and full CRUD operations on books stored in a MySQL database.

---

## ✨ Key Features

| Feature | Description |
|---------|-------------|
| **Authentication** | Basic HTTP authentication (username / password) backed by Spring Security. Two in‑memory users are defined in `SecurityConfig.java`:<br>• `libraryuser / TestPass123!` → role **USER**<br>• `libraryadmin / AdminPass123!` → role **ADMIN** |
| **Role‑Based Access** | <br>• `ADMIN` can **create**, **update**, **delete** books.<br>• `USER` can **view** books and **borrow/return** them. |
| **Book Management** | CRUD endpoints (`/api/books`) backed by a JPA `Book` entity (`id`, `title`, `author`, `year`, `isbn`, `borrowed`). |
| **Borrow / Return** | Endpoints `/api/books/{id}/borrow` and `/api/books/{id}/return` toggle the `borrowed` flag. |
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
| **Maven / Gradle** | Build tool (choose one) |
| **JWT** (optional) | Token‑based auth – can be added later |
| **Swagger/OpenAPI** (optional) | API documentation |

---

## 📁 Project Structure (backend)

```
api/
└─ src/main/java/com/library/api/
   ├─ config/                # SecurityConfig (CORS, password encoder, in‑memory users)
   ├─ controller/            # AuthController ( /api/auth/me ), BookController ( CRUD )
   ├─ dto/                   # DTOs for requests/responses (RegisterRequest, etc.)
   ├─ exception/             # GlobalExceptionHandler
   ├─ model/                 # Book entity (fields + JPA annotations)
   ├─ repository/            # BookRepository (extends JpaRepository)
   └─ service/               # Business logic (UserDetailsService, BookService, etc.)
   └─ LibraryApiApplication.java   # Spring Boot entry point
src/main/resources/
   └─ application.yml        # DB connection, JWT secret, etc.
```

---

## 🗄️ Database Schema

```sql
CREATE DATABASE library_db;
USE library_db;

-- Books table (managed by JPA)
CREATE TABLE books (
    id           INT AUTO_INCREMENT PRIMARY KEY,
    title        VARCHAR(150) NOT NULL,
    author       VARCHAR(100) NOT NULL,
    year         INT          NOT NULL DEFAULT 2000,
    isbn         VARCHAR(20)  NOT NULL,
    borrowed     TINYINT(1)   NOT NULL DEFAULT 0,
    borrowed_by  INT NULL,
    CONSTRAINT fk_books_borrowed_by
        FOREIGN KEY (borrowed_by) REFERENCES members(id)
);

-- Members table (used only by the future JPA‑based auth; currently in‑memory)
CREATE TABLE members (
    id           INT AUTO_INCREMENT PRIMARY KEY,
    name         VARCHAR(100) NOT NULL,
    email        VARCHAR(150) NOT NULL UNIQUE,
    password_hash VARCHAR(255) NOT NULL,
    role         ENUM('ADMIN','MEMBER') NOT NULL DEFAULT 'MEMBER'
);
```
> **Note:** The current implementation stores users **in memory** (see `SecurityConfig`). When you’re ready to persist users, replace the `InMemoryUserDetailsManager` with a JPA‑based `UserDetailsService` that reads from the `members` table.

---

## 🚀 Getting Started

```bash
# 1️⃣ Clone the repo
git clone https://github.com/Abhi2-12/LibraryManagement.git
cd LibraryManagement
git checkout phase-5-backend

# 2️⃣ Configure the DB
# Edit src/main/resources/application.yml
#   spring.datasource.url=jdbc:mysql://localhost:3306/library_db
#   spring.datasource.username=YOUR_USER
#   spring.datasource.password=YOUR_PASS
#   spring.jpa.hibernate.ddl-auto=update   # creates/updates tables automatically

# 3️⃣ Build the project
# Maven
mvn clean package   # produces target/library-management-*.jar
# or Gradle (if wrapper exists)
./gradlew bootJar

# 4️⃣ Run the API
java -jar target/*.jar   # defaults to http://localhost:8080
```

The API will be reachable at `http://localhost:8080`. You can test it quickly with `curl` or Postman, e.g.:

```bash
curl -u libraryadmin:AdminPass123! http://localhost:8080/api/auth/me
```

---

## 🧪 Testing the Endpoints

| Method | URL | Required Role | Description |
|--------|-----|---------------|-------------|
| `GET` | `/api/books` | – (public) | List all books |
| `GET` | `/api/books/{id}` | – (public) | Get single book |
| `POST` | `/api/books` | **ADMIN** | Add a new book |
| `PUT` | `/api/books/{id}` | **ADMIN** | Update a book |
| `DELETE` | `/api/books/{id}` | **ADMIN** | Delete a book |
| `POST` | `/api/books/{id}/borrow` | **USER / ADMIN** | Mark as borrowed |
| `POST` | `/api/books/{id}/return` | **USER / ADMIN** | Mark as returned |
| `GET` | `/api/auth/me` | any authenticated user | Return username + roles (used by the frontend) |

All protected endpoints require the `Authorization: Basic <base64>` header (or the default login form).

---

## 📦 Docker (optional)

```dockerfile
# Build stage
FROM maven:3-eclipse-temurin-22-alpine AS builder
WORKDIR /app
COPY pom.xml .      # or build.gradle if you use Gradle
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

- **Phase 4** – Complete Spring Boot REST API (currently done).  
- **Phase 5** – Backend (this branch): polish authentication, switch to persistent users, add JWT, write integration tests.  
- **Phase 6** – Frontend (React + Vite) – already updated with its own README.

---

## 👨‍💻 Author

**Md. Anamul Hassan Abhi**
- GitHub: [@Abhi2-12](https://github.com/Abhi2-12)
- Project: <https://github.com/Abhi2-12/LibraryManagement>
