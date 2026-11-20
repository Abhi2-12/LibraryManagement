
import { useEffect, useState } from 'react'

function UserDashboard({ onLogout }) {

    const [searchTerm, setSearchTerm] = useState('')
    const [books, setBooks] = useState([])
    const [loading, setLoading] = useState(true)
    const [error, setError] = useState('')

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

    const availableBooks = books.filter(
        book => !book.borrowed
    )

    const filteredBooks = availableBooks.filter(book =>
        book.title.toLowerCase().includes(searchTerm.toLowerCase()) ||
        book.author.toLowerCase().includes(searchTerm.toLowerCase())
    )

    const borrowedBooks = books.filter(
        book => book.borrowed
    )

    return (
        <div className="dashboard-container">

            <aside className="sidebar">
                <h2>My Library</h2>

                <nav>
                    <a href="#">Dashboard</a>
                    <a href="#">Browse Books</a>
                    <a href="#">My Borrowed Books</a>
                    <a href="#">My Profile</a>
                </nav>
            </aside>

            <main className="dashboard-content">

                <header className="dashboard-header">
                    <div>
                        <h1>User Dashboard</h1>
                        <p>Welcome back, Library Member!</p>
                    </div>

                    <button onClick={onLogout}>Logout</button>
                </header>

                <section className="stats-container">

                    <div className="stat-card">
                        <h3>Available Books</h3>
                        <p>{availableBooks.length}</p>
                    </div>

                    <div className="stat-card">
                        <h3>Borrowed Books</h3>
                        <p>{borrowedBooks.length}</p>
                    </div>

                </section>

                <section className="recent-books">

                    <h2>Available Books</h2>

                    <input
                        type="text"
                        placeholder="Search by book title or author..."
                        className="search-input"
                        value={searchTerm}
                        onChange={(event) => setSearchTerm(event.target.value)}
                    />

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
                                </tr>
                            </thead>

                            <tbody>
                                {filteredBooks.map(book => (
                                    <tr key={book.ID ?? book.id}>
                                        <td>{book.ID ?? book.id}</td>
                                        <td>{book.title}</td>
                                        <td>{book.author}</td>
                                        <td>{book.year}</td>
                                    </tr>
                                ))}
                            </tbody>
                        </table>
                    )}

                    {!loading && !error && availableBooks.length === 0 && (
                        <p>No matching books found.</p>
                    )}

                </section>

                <section className="recent-books">
                    <h2>My Borrowed Books</h2>
                    <p>We'll implement personal borrowing records in a later step.</p>
                </section>

            </main>
        </div>
    )
}

export default UserDashboard
