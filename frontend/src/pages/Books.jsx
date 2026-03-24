import { useState, useEffect } from 'react';
import API from '../services/api';

const Books = () => {
  const [books, setBooks] = useState([]);

  // Fetch books on component mount
  const fetchBooks = async () => {
    try {
      const response = await API.get('/api/books');
      setBooks(response.data);
    } catch (error) {
      console.error("Failed to fetch books", error);
    }
  };

  useEffect(() => {
    fetchBooks();
  }, []);

  const handleBorrow = async (bookId) => {
    try {
      // POST to /api/borrow with bookId as a query parameter (since backend uses @RequestParam)
      await API.post('/api/borrow', null, {
        params: { bookId }
      });
      alert('Book borrowed successfully!');
      // Refresh the book list to see updated available copies
      fetchBooks();
    } catch (error) {
      alert('Failed to borrow book: ' + (error.response?.data || error.message));
    }
  };

  return (
    <div style={{ padding: '20px' }}>
      <h2>Available Books</h2>
      <ul style={{ listStyleType: 'none', padding: 0 }}>
        {books.map(book => (
          <li key={book.id} style={{ 
            border: '1px solid #ccc', 
            margin: '10px 0', 
            padding: '10px', 
            borderRadius: '5px' 
          }}>
            <h3>{book.title}</h3>
            <p><strong>Author:</strong> {book.author}</p>
            <p><strong>Available Copies:</strong> {book.availableCopies}</p>
            <button 
              onClick={() => handleBorrow(book.id)}
              disabled={book.availableCopies <= 0}
            >
              {book.availableCopies > 0 ? 'Borrow' : 'Out of Stock'}
            </button>
          </li>
        ))}
      </ul>
      {books.length === 0 && <p>No books available.</p>}
    </div>
  );
};

export default Books;
