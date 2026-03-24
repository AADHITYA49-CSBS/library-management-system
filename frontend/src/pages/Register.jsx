import { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import API from '../services/api';

const Register = () => {
  const [name, setName] = useState('');
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const navigate = useNavigate();

  const handleRegister = async (e) => {
    e.preventDefault();
    try {
      await API.post('/api/auth/register', { name, email, password });
      alert("Registration successful! You can now log in.");
      navigate('/login');
    } catch (error) {
      alert("Registration failed: " + (error.response?.data || error.message));
    }
  };

  return (
    <div style={{ padding: '20px' }}>
      <h2>Library Management - Register</h2>
      <form onSubmit={handleRegister}>
        <div>
          <label style={{ display: 'inline-block', width: '80px' }}>Name:</label>
          <input 
            type="text" 
            value={name} 
            onChange={(e) => setName(e.target.value)} 
            required 
          />
        </div>
        <div style={{ marginTop: '10px' }}>
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
        <button type="submit" style={{ marginTop: '15px' }}>Register</button>
      </form>
      <p style={{ marginTop: '15px' }}>
        Already have an account? <br/>
        <button onClick={() => navigate('/login')} style={{ marginTop: '5px' }}>Go to Login</button>
      </p>
    </div>
  );
};

export default Register;
