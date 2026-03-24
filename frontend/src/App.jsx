import { BrowserRouter as Router, Routes, Route, Navigate, Link } from 'react-router-dom';
import Login from './pages/Login';
import Register from './pages/Register';
import Books from './pages/Books';
import MyBooks from './pages/MyBooks';

function App() {
  return (
    <Router>
      <div style={{ padding: '10px', background: '#f4f4f4', marginBottom: '20px' }}>
        <nav style={{ display: 'flex', gap: '15px' }}>
          <Link to="/books">Books</Link>
          <Link to="/my-books">My Books</Link>
          <Link to="/login">Login / Register</Link>
        </nav>
      </div>

      <Routes>
        <Route path="/" element={<Navigate to="/login" />} />
        <Route path="/login" element={<Login />} />
        <Route path="/register" element={<Register />} />
        <Route path="/books" element={<Books />} />
        <Route path="/my-books" element={<MyBooks />} />
      </Routes>
    </Router>
  );
}

export default App;
