# 🌐 Library Management – Combined Project (Backend + Frontend)

**Backend** – Spring Boot REST API (Phase 5) that provides user registration, authentication, and full book‑management features.

**Frontend** – React + Vite UI (Phase 6) that consumes the API.

---

## 📚 Backend (Phase 5)

* User registration & login (persistent `library_users` table)
* Role‑based access (`ADMIN` / `USER`)
* CRUD operations for books
* Borrow / return handling
* CORS configured for the React dev server (`http://localhost:5173`)
* Swagger UI available at `/swagger-ui.html` (optional)

See the full backend documentation in **`backend/README.md`** (or the root of the repository before this merge).

---

## 🌐 Frontend (Phase 6)

* React 19 + Vite development environment
* Login / registration UI that talks to the backend API
* Admin dashboard (book CRUD) and member dashboard (browse & borrow)

The detailed frontend guide lives in **`frontend/README.md`**.

---

## 🚀 Getting Started (both parts)

```bash
# Clone the repo
git clone https://github.com/Abhi2-12/LibraryManagement.git
cd LibraryManagement
```

### Backend
```bash
# Switch to backend branch (already merged into main)
# (no extra checkout needed)
# Configure DB in src/main/resources/application.properties
#   spring.datasource.url=jdbc:mysql://localhost:3306/library_db
#   spring.datasource.username=YOUR_USER
#   spring.datasource.password=YOUR_PASS
#   spring.jpa.hibernate.ddl-auto=update   # creates tables automatically

# Build & run
mvn clean package                # or ./gradlew bootJar
java -jar target/*.jar            # runs on http://localhost:8080
```

### Frontend
```bash
cd frontend
npm install
npm run dev   # opens http://localhost:5173
```

The frontend expects the backend at `http://localhost:8080`. Adjust the base URL in `src/components/Login.jsx` if needed.

---

## 📦 Docker (optional)

You can build a single Docker image that runs both the API and serves the built React app from `frontend/dist/` (setup left as an exercise).

---

## 👨‍💻 Author

**Md. Anamul Hassan Abhi** – [@Abhi2-12](https://github.com/Abhi2-12)
