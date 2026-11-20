# React + Vite
# 🌐 Library Management – Phase 6 Frontend (React + Vite)
This template provides a minimal setup to get React working in Vite with HMR and some Oxlint rules.
This is the **frontend** for the Library Management System, built with **React 19** and **Vite**. It consumes the REST API provided by the **Phase 5 backend** (Spring Boot) to enable user registration, login, and library‑book operations.
Currently, two official plugins are available:
---
- [@vitejs/plugin-react](https://github.com/vitejs/vite-plugin-react/blob/main/packages/plugin-react) uses [Oxc](https://oxc.rs)
- [@vitejs/plugin-react-swc](https://github.com/vitejs/vite-plugin-react/blob/main/packages/plugin-react-swc) uses [SWC](https://swc.rs/)
## ✨ Features
## React Compiler
- **User authentication** (register / login) using JWT tokens issued by the backend.
- **Role‑based UI** – separate dashboards for `ADMIN` (book management) and `MEMBER` (browse/borrow).
- **Book CRUD** – admins can create, update, delete books; members can view and borrow/return books.
- **Responsive design** with simple assets (`hero.png`, `react.svg`, `vite.svg`).
- **Fast development workflow** thanks to Vite’s hot‑module replacement.
The React Compiler is not enabled on this template because of its impact on dev & build performances. To add it, see [this documentation](https://react.dev/learn/react-compiler/installation).
---
## Expanding the Oxlint configuration
## 🛠️ Technology Stack
If you are developing a production application, we recommend using TypeScript with type-aware lint rules enabled. Check out the [TS template](https://github.com/vitejs/vite/tree/main/packages/create-vite/template-react-ts) for information on how to integrate TypeScript and Oxlint's TypeScript related rules in your project.
## 🚀 Deployment
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
