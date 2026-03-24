import { useState, useEffect } from 'react';
import API from '../services/api';

const MyBooks = () => {
  const [borrowedBooks, setBorrowedBooks] = useState([]);

  const fetchMyBooks = async () => {
    try {
      const response = await API.get('/api/borrowed');
      setBorrowedBooks(response.data);
    } catch (error) {
      console.error("Failed to fetch borrowed books", error);
    }
  };

  useEffect(() => {
    fetchMyBooks();
  }, []);

  const handleReturn = async (borrowId) => {
    try {
      // Call POST to /api/return/{borrowId}
      await API.post(`/api/return/${borrowId}`);
      alert('Book returned successfully!');
      
      // Update list after returning
      fetchMyBooks();
    } catch (error) {
      alert('Failed to return book: ' + (error.response?.data || error.message));
    }
  };

  return (
    <div style={{ padding: '20px' }}>
      <h2>My Borrowed Books</h2>
      <ul style={{ listStyleType: 'none', padding: 0 }}>
        {borrowedBooks.map(borrow => (
          <li key={borrow.id} style={{ 
            border: '1px solid #ccc', 
            margin: '10px 0', 
            padding: '10px', 
            borderRadius: '5px' 
          }}>
            <h3>{borrow.book?.title}</h3>
            <p><strong>Author:</strong> {borrow.book?.author}</p>
            <p><strong>Borrowed On:</strong> {new Date(borrow.borrowDate).toLocaleDateString()}</p>
            <button onClick={() => handleReturn(borrow.id)}>Return Book</button>
          </li>
        ))}
      </ul>
      {borrowedBooks.length === 0 && <p>You have no active borrowed books.</p>}
    </div>
  );
};

export default MyBooks;
