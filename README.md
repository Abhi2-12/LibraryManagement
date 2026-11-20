# 📚 Library Management System – Backend (Phase 5)

**Spring Boot REST API** providing user registration, authentication (JWT), and full library‑book management. This backend replaces the original console app and serves the upcoming frontend (Phase 6).

## ✨ Features

- **User registration & login** – Secure sign‑up and JWT‑based authentication via Spring Security.
- **Role‑Based Access Control** – `ADMIN` can create, update, delete books; `MEMBER` can view and borrow.
- **Book CRUD** – REST endpoints to create, read, update, delete books.
- **Borrow / Return** – Track which member has borrowed a book.
- **Persisted data** – MySQL database accessed through Spring Data JPA.
- **Password hashing** – BCrypt hashing for stored passwords.
- **Global exception handling** – Consistent error responses via `GlobalExceptionHandler`.

## 🛠️ Technologies

| Technology | Purpose |
|------------|---------|
| Java 22 | Core language |
| Spring Boot | Application framework |
| Spring Data JPA | Database access |
| Spring Security | Authentication & authorization |
| MySQL 8 | Persistent storage |
| Maven / Gradle | Build tool |
| JWT (jjwt) | Token‑based auth |
| Swagger/OpenAPI | API documentation |
| Git & GitHub | Version control |

## 📂 Project Structure (backend)

```
src/main/java/com/library/api/
├── config/                # SecurityConfig, SwaggerConfig
├── controller/            # AuthController, BookController, HelloController
├── dto/                   # RegisterRequest, LoginRequest, BookDto
├── exception/             # GlobalExceptionHandler
├── model/                 # LibraryUser, Book, Role (enum)
├── repository/            # LibraryUserRepository, BookRepository
├── service/               # LibraryUserDetailsService, BookService
└── LibraryApiApplication.java

src/main/resources/
├── application.yml        # DB config, JWT secret, etc.
└── data.sql               # Optional seed data
```

## 📦 API Endpoints

### Auth (`/api/auth`)
- `POST /register` – Register a new user (`RegisterRequest`).
- `POST /login` – Authenticate and receive a JWT (`LoginRequest`).

### Books (protected, `/api/books`)
| Method | URI | Description |
|--------|-----|-------------|
| `GET` | `/api/books` | List all books (admin sees all, members see available). |
| `GET` | `/api/books/{id}` | Get details of a single book. |
| `POST` | `/api/books` | Create a new book (ADMIN only). |
| `PUT` | `/api/books/{id}` | Update book info (ADMIN only). |
| `DELETE` | `/api/books/{id}` | Delete a book (ADMIN only). |
| `POST` | `/api/books/{id}/borrow` | Borrow a book (MEMBER). |
| `POST` | `/api/books/{id}/return` | Return a borrowed book (MEMBER). |

All protected endpoints require the `Authorization: Bearer <jwt>` header.

## ⚙️ Requirements

- JDK 22
- MySQL 8 (or compatible)
- Maven (`mvn`) **or** Gradle (`./gradlew`)
- Git

## 🚀 Getting Started

```bash
# 1. Clone the repository
git clone https://github.com/Abhi2-12/LibraryManagement.git
cd LibraryManagement

# 2. Switch to the backend branch
git checkout phase-5-backend

# 3. Configure the database (src/main/resources/application.yml)
#    set spring.datasource.url, username, password, and jwt.secret.

# 4. Build the project (Maven example)
mvn clean package   # produces target/library-management-*.jar

# 5. Run the application
java -jar target/*.jar
```

The API will be available at `http://localhost:8080`. Swagger UI (if enabled) can be accessed at `http://localhost:8080/swagger-ui.html`.

## 📦 Docker Deployment (optional)

```bash
# Build Docker image
docker build -t library-backend:latest .
# Run container (replace DB env values as needed)
docker run -d -p 8080:8080 \
  -e SPRING_DATASOURCE_URL=jdbc:mysql://host.docker.internal:3306/library_db \
  -e SPRING_DATASOURCE_USERNAME=YOUR_USER \
  -e SPRING_DATASOURCE_PASSWORD=YOUR_PASS \
  library-backend:latest
```

## 🗺️ Roadmap (project‑wide)

- Phase 4 – Spring Boot REST API (completed)
- **Phase 5 – Backend** – Full authentication & book management (this branch)
- Phase 6 – Frontend (React + Vite) integrating with this backend

## 👨‍💻 Author

**Md. Anamul Hassan Abhi**
- GitHub: [@Abhi2-12](https://github.com/Abhi2-12)
- Project Repository: [LibraryManagement](https://github.com/Abhi2-12/LibraryManagement)
