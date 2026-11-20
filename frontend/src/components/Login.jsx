
import { useState } from 'react'

function Login({ onLogin }) {

    const [username, setUsername] = useState('')
    const [password, setPassword] = useState('')
    const [error, setError] = useState('')
    const [loading, setLoading] = useState(false)

    async function handleSubmit(event) {
        event.preventDefault()

        setError('')
        setLoading(true)

        try {
            const authHeader = 'Basic ' + btoa(username + ':' + password)

            const response = await fetch(
                'http://localhost:8080/api/auth/me',
                {
                    method: 'GET',
                    headers: {
                        Authorization: authHeader
                    }
                }
            )

            if (!response.ok) {
                throw new Error('Invalid username or password')
            }

            const userData = await response.json()

            onLogin({
                ...userData,
                authHeader
            })

        } catch (err) {
            setError(err.message || 'Unable to connect to the server')
        } finally {
            setLoading(false)
        }
    }

    return (
        <div className="login-container">
            <div className="login-card">

                <h1>Library Management</h1>
                <p>Please login to continue</p>

                <form onSubmit={handleSubmit}>

                    <div>
                        <label>Username</label>
                        <input
                            type="text"
                            placeholder="Enter username"
                            value={username}
                            onChange={(event) =>
                                setUsername(event.target.value)
                            }
                            required
                        />
                    </div>

                    <div>
                        <label>Password</label>
                        <input
                            type="password"
                            placeholder="Enter password"
                            value={password}
                            onChange={(event) =>
                                setPassword(event.target.value)
                            }
                            required
                        />
                    </div>

                    {error && <p className="login-error">{error}</p>}

                    <button type="submit" disabled={loading}>
                        {loading ? 'Logging in...' : 'Login'}
                    </button>

                </form>
            </div>
        </div>
    )
}

export default Login
