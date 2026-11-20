
import { useState } from 'react'

import Login from './components/Login'
import AdminDashboard from './pages/AdminDashboard'
import UserDashboard from './pages/UserDashboard'

import './App.css'

function App() {

    const [user, setUser] = useState(null)

    function handleLogin(userData) {
        setUser(userData)
    }

    function handleLogout() {
        setUser(null)
    }

    if (!user) {
        return <Login onLogin={handleLogin} />
    }

    if (user.roles.includes('ROLE_ADMIN')) {
        return <AdminDashboard onLogout={handleLogout} />
    }

    if (user.roles.includes('ROLE_USER')) {
        return <UserDashboard onLogout={handleLogout} />
    }

    return <h1>Access denied</h1>
}

export default App
