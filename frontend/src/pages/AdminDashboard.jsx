
import { useEffect, useState } from 'react'

function AdminDashboard({ onLogout }) {

    const [books, setBooks] = useState([])
    const [loading, setLoading] = useState(true)
    const [error, setError] = useState('')

    // Fetch books when the dashboard loads
    useEffect(() => {

        async function fetchBooks() {
            try {
                const response = await fetch(
                    'http://localhost:8080/api/books'
                )

                if (!response.ok) {
                    throw new Error('Failed to fetch books')
                }

                const data = await response.json()
                setBooks(data)

            } catch (err) {
                setError(err.message)
            } finally {
                setLoading(false)
            }
        }

        fetchBooks()

    }, [])

    // Calculate statistics from actual book records
    const totalBooks = books.length

    const borrowedBooks = books.filter(
        book => book.borrowed
    ).length

    const availableBooks = totalBooks - borrowedBooks

    return (
        <div className="dashboard-container">

            <aside className="sidebar">
                <h2>My Library</h2>

                <nav>
                    <a href="#">Dashboard</a>
                    <a href="#">All Books</a>
                    <a href="#">Add Book</a>
                    <a href="#">Borrowed Books</a>
                    <a href="#">Users</a>
                </nav>
            </aside>

            <main className="dashboard-content">

                <header className="dashboard-header">
                    <div>
                        <h1>Admin Dashboard</h1>
                        <p>Welcome back, Administrator!</p>
                    </div>

                    <button onClick={onLogout}>Logout</button>
                </header>

                <section className="stats-container">

                    <div className="stat-card">
                        <h3>Total Books</h3>
                        <p>{totalBooks}</p>
                    </div>

                    <div className="stat-card">
                        <h3>Borrowed Books</h3>
                        <p>{borrowedBooks}</p>
                    </div>

                    <div className="stat-card">
                        <h3>Available Books</h3>
                        <p>{availableBooks}</p>
                    </div>

                </section>

                <section className="recent-books">

                    <h2>All Books</h2>

                    {loading && <p>Loading books...</p>}

                    {error && <p>{error}</p>}

                    {!loading && !error && (
                        <table>
                            <thead>
                                <tr>
                                    <th>ID</th>
                                    <th>Title</th>
                                    <th>Author</th>
                                    <th>Year</th>
                                    <th>Status</th>
                                </tr>
                            </thead>

                            <tbody>
                                {books.map(book => (
                                    <tr key={book.ID ?? book.id}>
                                        <td>{book.ID ?? book.id}</td>
                                        <td>{book.title}</td>
                                        <td>{book.author}</td>
                                        <td>{book.year}</td>
                                        <td>
                                            {book.borrowed
                                                ? 'Borrowed'
                                                : 'Available'}
                                        </td>
                                    </tr>
                                ))}
                            </tbody>
                        </table>
                    )}

                    {!loading && !error && books.length === 0 && (
                        <p>No books found.</p>
                    )}

                </section>

            </main>
        </div>
    )
}

export default AdminDashboard
