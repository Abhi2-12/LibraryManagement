
import { useEffect, useState } from 'react'

const API_URL = 'http://localhost:8080/api/books'

function UserDashboard({ onLogout, authHeader }) {

    const [books, setBooks] = useState([])
    const [loading, setLoading] = useState(true)
    const [error, setError] = useState('')
    const [searchTerm, setSearchTerm] = useState('')
    const [actionLoadingId, setActionLoadingId] = useState(null)

    const headers = {
        Authorization: authHeader
    }

    // Fetch all books
    async function loadBooks() {
        try {
            setError('')

            const response = await fetch(API_URL, { headers })

            if (!response.ok) {
                throw new Error('Could not load books')
            }

            const data = await response.json()
            setBooks(data)

        } catch (err) {
            setError(err.message)
        } finally {
            setLoading(false)
        }
    }

    useEffect(() => {
        loadBooks()
    }, [])

    // Search books by title or author
    const filteredBooks = books.filter(book => {
        const search = searchTerm.toLowerCase()

        return (
            book.title.toLowerCase().includes(search) ||
            book.author.toLowerCase().includes(search)
        )
    })

    const availableBooks = filteredBooks.filter(
        book => !book.borrowed
    )

    const borrowedBooks = filteredBooks.filter(
        book => book.borrowed
    )

    const totalAvailable = books.filter(
        book => !book.borrowed
    ).length

    const totalBorrowed = books.filter(
        book => book.borrowed
    ).length

    // Borrow or return a book
    async function handleBookAction(id, action) {
        setActionLoadingId(id)

        try {
            const response = await fetch(
                `${API_URL}/${id}/${action}`,
                {
                    method: 'POST',
                    headers
                }
            )

            if (!response.ok) {
                const message = await response.text()
                throw new Error(message || 'Operation failed')
            }

            alert(
                action === 'borrow'
                    ? 'Book borrowed successfully!'
                    : 'Book returned successfully!'
            )

            await loadBooks()

        } catch (err) {
            alert(err.message)
        } finally {
            setActionLoadingId(null)
        }
    }

    function renderBookRows(bookList) {
        return bookList.map(book => {
            const id = book.ID ?? book.id

            return (
                <tr key={id}>
                    <td>{id}</td>
                    <td>{book.title}</td>
                    <td>{book.author}</td>
                    <td>{book.year}</td>
                    <td>
                        {book.borrowed ? 'Borrowed' : 'Available'}
                    </td>
                    <td>
                        <button
                            disabled={actionLoadingId === id}
                            onClick={() => handleBookAction(
                                id,
                                book.borrowed ? 'return' : 'borrow'
                            )}
                        >
                            {actionLoadingId === id
                                ? 'Please wait...'
                                : book.borrowed ? 'Return' : 'Borrow'}
                        </button>
                    </td>
                </tr>
            )
        })
    }

    return (
        <div className="dashboard-container">

            <aside className="sidebar">
                <h2>My Library</h2>

                <nav>
                    <a href="#">Dashboard</a>
                    <a href="#available-books">Available Books</a>
                    <a href="#borrowed-books">Borrowed Books</a>
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
                        <p>{totalAvailable}</p>
                    </div>

                    <div className="stat-card">
                        <h3>Borrowed Books</h3>
                        <p>{totalBorrowed}</p>
                    </div>

                </section>

                <section className="recent-books">

                    <h2>Find Books</h2>

                    <input
                        type="text"
                        className="search-input"
                        placeholder="Search by title or author..."
                        value={searchTerm}
                        onChange={event => setSearchTerm(event.target.value)}
                    />

                    {loading && <p>Loading books...</p>}

                    {error && <p>{error}</p>}

                    {!loading && !error && (
                        <>
                            <div id="available-books">
                                <h2>Available Books</h2>

                                <div className="table-wrapper">
                                    <table>
                                        <thead>
                                            <tr>
                                                <th>ID</th>
                                                <th>Title</th>
                                                <th>Author</th>
                                                <th>Year</th>
                                                <th>Status</th>
                                                <th>Action</th>
                                            </tr>
                                        </thead>

                                        <tbody>
                                            {renderBookRows(availableBooks)}
                                        </tbody>
                                    </table>

                                    {availableBooks.length === 0 && (
                                        <p>No available books found.</p>
                                    )}
                                </div>
                            </div>

                            <div id="borrowed-books" style={{ marginTop: '30px' }}>
                                <h2>Currently Borrowed Books</h2>

                                <div className="table-wrapper">
                                    <table>
                                        <thead>
                                            <tr>
                                                <th>ID</th>
                                                <th>Title</th>
                                                <th>Author</th>
                                                <th>Year</th>
                                                <th>Status</th>
                                                <th>Action</th>
                                            </tr>
                                        </thead>

                                        <tbody>
                                            {renderBookRows(borrowedBooks)}
                                        </tbody>
                                    </table>

                                    {borrowedBooks.length === 0 && (
                                        <p>No borrowed books found.</p>
                                    )}
                                </div>
                            </div>
                        </>
                    )}

                </section>

            </main>
        </div>
    )
}

export default UserDashboard
