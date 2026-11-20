
import { useEffect, useState } from 'react'

const API_URL = 'http://localhost:8080/api/books'

const emptyForm = {
    title: '',
    author: '',
    year: '',
    isbn: '',
    borrowed: false
}

function AdminDashboard({ onLogout, authHeader }) {

    const [books, setBooks] = useState([])
    const [loading, setLoading] = useState(true)
    const [error, setError] = useState('')
    const [searchTerm, setSearchTerm] = useState('')

    const [showForm, setShowForm] = useState(false)
    const [editingId, setEditingId] = useState(null)
    const [form, setForm] = useState(emptyForm)
    const [saving, setSaving] = useState(false)
    const [actionLoadingId, setActionLoadingId] = useState(null)

    const headers = {
        Authorization: authHeader
    }

    // Load all books
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

    // Search by title or author
    const filteredBooks = books.filter(book => {
        const search = searchTerm.toLowerCase()

        return (
            book.title.toLowerCase().includes(search) ||
            book.author.toLowerCase().includes(search)
        )
    })

    // Statistics
    const totalBooks = books.length

    const borrowedBooks = books.filter(
        book => book.borrowed
    ).length

    const availableBooks = totalBooks - borrowedBooks

    // Update form values
    function handleChange(event) {
        const { name, value, type, checked } = event.target

        setForm(previous => ({
            ...previous,
            [name]: type === 'checkbox' ? checked : value
        }))
    }

    // Open add form
    function openAddForm() {
        setEditingId(null)
        setForm(emptyForm)
        setShowForm(true)
    }

    // Load a particular book before editing
    async function handleEdit(book) {
        const id = book.ID ?? book.id

        try {
            const response = await fetch(`${API_URL}/${id}`, { headers })

            if (!response.ok) {
                throw new Error('Could not load book details')
            }

            const data = await response.json()

            setEditingId(id)
            setForm({
                title: data.title,
                author: data.author,
                year: String(data.year),
                isbn: data.isbn || '',
                borrowed: data.borrowed
            })

            setShowForm(true)

        } catch (err) {
            alert(err.message)
        }
    }

    // Add or update a book
    async function handleSave(event) {
        event.preventDefault()
        setSaving(true)

        const payload = {
            title: form.title.trim(),
            author: form.author.trim(),
            year: Number(form.year),
            isbn: form.isbn.trim(),
            borrowed: form.borrowed
        }

        try {
            const url = editingId === null
                ? API_URL
                : `${API_URL}/${editingId}`

            const response = await fetch(url, {
                method: editingId === null ? 'POST' : 'PUT',
                headers: {
                    ...headers,
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(payload)
            })

            if (!response.ok) {
                const message = await response.text()
                throw new Error(message || 'Could not save book')
            }

            alert(
                editingId === null
                    ? 'Book added successfully!'
                    : 'Book updated successfully!'
            )

            setShowForm(false)
            setForm(emptyForm)
            setEditingId(null)

            await loadBooks()

        } catch (err) {
            alert(err.message)
        } finally {
            setSaving(false)
        }
    }

    // Delete a book
    async function handleDelete(id) {
        if (!window.confirm('Are you sure you want to delete this book?')) {
            return
        }

        try {
            const response = await fetch(`${API_URL}/${id}`, {
                method: 'DELETE',
                headers
            })

            if (!response.ok) {
                const message = await response.text()
                throw new Error(message || 'Could not delete book')
            }

            alert('Book deleted successfully!')
            await loadBooks()

        } catch (err) {
            alert(err.message)
        }
    }

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

            await loadBooks()

        } catch (err) {
            alert(err.message)
        } finally {
            setActionLoadingId(null)
        }
    }

    return (
        <div className="dashboard-container">

            <aside className="sidebar">
                <h2>My Library</h2>

                <nav>
                    <a href="#">Dashboard</a>
                    <a href="#books">All Books</a>
                    <a href="#add-book" onClick={openAddForm}>Add Book</a>
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

                <section className="recent-books" id="books">

                    <div className="dashboard-actions">
                        <h2>Book Management</h2>

                        <button onClick={openAddForm}>
                            + Add Book
                        </button>
                    </div>

                    <input
                        className="search-input"
                        type="text"
                        placeholder="Search by title or author..."
                        value={searchTerm}
                        onChange={event => setSearchTerm(event.target.value)}
                    />

                    {showForm && (
                        <form className="book-form" onSubmit={handleSave}>

                            <h3>
                                {editingId === null ? 'Add New Book' : 'Edit Book'}
                            </h3>

                            <input
                                name="title"
                                placeholder="Book title"
                                value={form.title}
                                onChange={handleChange}
                                required
                            />

                            <input
                                name="author"
                                placeholder="Author"
                                value={form.author}
                                onChange={handleChange}
                                required
                            />

                            <input
                                name="year"
                                type="number"
                                placeholder="Publication year"
                                value={form.year}
                                onChange={handleChange}
                                required
                            />

                            <input
                                name="isbn"
                                placeholder="ISBN"
                                value={form.isbn}
                                onChange={handleChange}
                            />

                            <label>
                                <input
                                    type="checkbox"
                                    name="borrowed"
                                    checked={form.borrowed}
                                    onChange={handleChange}
                                />
                                Borrowed
                            </label>

                            <button type="submit" disabled={saving}>
                                {saving ? 'Saving...' : 'Save Book'}
                            </button>

                            <button
                                type="button"
                                onClick={() => setShowForm(false)}
                            >
                                Cancel
                            </button>

                        </form>
                    )}

                    {loading && <p>Loading books...</p>}

                    {error && <p>{error}</p>}

                    {!loading && !error && (
                        <div className="table-wrapper">
                            <table>
                                <thead>
                                    <tr>
                                        <th>ID</th>
                                        <th>Title</th>
                                        <th>Author</th>
                                        <th>Year</th>
                                        <th>ISBN</th>
                                        <th>Status</th>
                                        <th>Actions</th>
                                    </tr>
                                </thead>

                                <tbody>
                                    {filteredBooks.map(book => {
                                        const id = book.ID ?? book.id

                                        return (
                                            <tr key={id}>
                                                <td>{id}</td>
                                                <td>{book.title}</td>
                                                <td>{book.author}</td>
                                                <td>{book.year}</td>
                                                <td>{book.isbn}</td>
                                                <td>
                                                    {book.borrowed
                                                        ? 'Borrowed'
                                                        : 'Available'}
                                                </td>
                                                <td>
                                                    <div className="table-actions">

                                                        <button
                                                            onClick={() => handleEdit(book)}
                                                        >
                                                            Edit
                                                        </button>

                                                        <button
                                                            onClick={() => handleDelete(id)}
                                                            className="danger-button"
                                                        >
                                                            Delete
                                                        </button>

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

                                                    </div>
                                                </td>
                                            </tr>
                                        )
                                    })}
                                </tbody>
                            </table>

                            {filteredBooks.length === 0 && (
                                <p>No books found.</p>
                            )}
                        </div>
                    )}

                </section>

            </main>
        </div>
    )
}

export default AdminDashboard
