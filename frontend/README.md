# 🌐 Library Management – Phase 6 Frontend (React + Vite)

This is the **frontend** for the Library Management System, built with **React 19** and **Vite**. It consumes the REST API provided by the **Phase 5 backend** (Spring Boot) to enable user registration, login, and library‑book operations.

---

## ✨ Features

- **User authentication** (register / login) using JWT tokens issued by the backend.
- **Role‑based UI** – separate dashboards for `ADMIN` (book management) and `MEMBER` (browse/borrow).
- **Book CRUD** – admins can create, update, delete books; members can view and borrow/return books.
- **Responsive design** with simple assets (`hero.png`, `react.svg`, `vite.svg`).
- **Fast development workflow** thanks to Vite’s hot‑module replacement.

---

## 🛠️ Technology Stack

| Technology | Purpose |
|------------|---------|
| **React 19** | UI library, component model |
| **Vite 8** | Development server, bundler |
| **Vite React plugin** (`@vitejs/plugin-react`) | JSX/TSX support |
| **oxlint** | Linter (run via `npm run lint`) |
| **npm** | Package manager |
| **Git & GitHub** | Version control |

---

## 📂 Project Structure

```
frontend/
├─ .gitignore                # Standard Node/git ignores
├─ .oxlintrc.json            # linter configuration
├─ index.html                # HTML entry point
├─ package.json              # scripts & dependencies
├─ package-lock.json         # lockfile
├─ vite.config.js            # Vite configuration
├─ README.md                 # (this file)
├─ public/                   # static assets served as‑is
│   ├─ favicon.svg
│   └─ icons.svg
└─ src/                      # application source
    ├─ App.jsx               # root component
    ├─ main.jsx              # ReactDOM render entry
    ├─ App.css               # (currently empty)
    ├─ index.css             # global styles
    ├─ assets/               # images (hero.png, react.svg, vite.svg)
    ├─ components/           # reusable UI pieces
    │   └─ Login.jsx          # login & registration form
    └─ pages/                # page‑level components
        ├─ AdminDashboard.jsx # admin UI (book CRUD)
        └─ UserDashboard.jsx  # member UI (browse/borrow)
```

---

## ⚙️ Prerequisites

- **Node.js** (v20 or later) with npm (or Yarn).
- The **Phase 5 backend** must be running (default URL `http://localhost:8080`).
- Git (for version control).

---

## 🚀 Getting Started

```bash
# 1️⃣ Clone the repository (if you haven't already)
git clone https://github.com/Abhi2-12/LibraryManagement.git
cd LibraryManagement

# 2️⃣ Switch to the frontend branch
git checkout phase-6-frontend

# 3️⃣ Install dependencies
npm install   # or `yarn install`

# 4️⃣ Run the development server
npm run dev   # opens http://localhost:5173
```

The app will automatically reload as you edit files.

### Build for Production

```bash
npm run build   # bundles into `dist/`
# To preview the production build locally:
npm run preview
```

---

## 🔧 Configuration

The frontend expects the backend API at `http://localhost:8080`. If your backend runs on a different host/port, edit the **base URL** inside `src/components/Login.jsx` (or create a dedicated `config.js` module) to point to the correct address.

---

## 📦 Deployment (optional)

You can serve the built `dist/` folder with any static‑file server (e.g., `serve`, `nginx`, or a Docker container).

```bash
# Example using the `serve` package
npm install -g serve
serve -s dist
```

Or a minimal Dockerfile:

```dockerfile
FROM node:20-alpine AS builder
WORKDIR /app
COPY package*.json ./
RUN npm install
COPY . .
RUN npm run build

FROM nginx:alpine
COPY --from=builder /app/dist /usr/share/nginx/html
EXPOSE 80
CMD ["nginx", "-g", "daemon off;"]
```

---

## 🧪 Testing

No test suite is currently included, but you can add **Jest** and **React Testing Library** for component/unit tests. A basic script could be added to `package.json`:

```json
"scripts": {
  "test": "jest"
}
```

---

## 👨‍💻 Author

**Md. Anamul Hassan Abhi**
- GitHub: [@Abhi2-12](https://github.com/Abhi2-12)
- Project Repository: [LibraryManagement](https://github.com/Abhi2-12/LibraryManagement)
