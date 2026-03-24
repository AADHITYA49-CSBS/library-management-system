import { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import API from '../services/api';

const Login = () => {
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const navigate = useNavigate();

  const handleLogin = async (e) => {
    e.preventDefault();
    try {
      // Clear any old/corrupted tokens before attempting login
      localStorage.removeItem('token');

      // Call /api/auth/login endpoint
      const response = await API.post('/api/auth/login', { email, password });
      
      // Store token in localStorage upon success (OpenAPI schema AuthResponse has a 'token' property)
      localStorage.setItem('token', response.data.token);
      
      // Navigate to Books page securely
      navigate('/books');
    } catch (error) {
      alert("Login failed: " + (error.response?.data || error.message));
    }
  };

  return (
    <div style={{ padding: '20px' }}>
      <h2>Library Management - Login</h2>
      <form onSubmit={handleLogin}>
        <div>
          <label style={{ display: 'inline-block', width: '80px' }}>Email:</label>
          <input 
            type="email" 
            value={email} 
            onChange={(e) => setEmail(e.target.value)} 
            required 
          />
        </div>
        <div style={{ marginTop: '10px' }}>
          <label style={{ display: 'inline-block', width: '80px' }}>Password:</label>
          <input 
            type="password" 
            value={password} 
            onChange={(e) => setPassword(e.target.value)} 
            required 
          />
        </div>
        <button type="submit" style={{ marginTop: '15px' }}>Login</button>
      </form>
      <p style={{ marginTop: '15px' }}>
        Don't have an account? <br/>
        <button onClick={() => navigate('/register')} style={{ marginTop: '5px' }}>Register Here</button>
      </p>
    </div>
  );
};

export default Login;
