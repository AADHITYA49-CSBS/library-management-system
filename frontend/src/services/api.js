import axios from 'axios';

// Create an Axios instance
const API = axios.create({
  baseURL: 'http://localhost:8080', // Replace with your backend URL
});

// Add a request interceptor
API.interceptors.request.use((req) => {
  // If a token exists in localStorage, attach it to the Authorization header
  const token = localStorage.getItem('token');
  if (token) {
    req.headers.Authorization = `Bearer ${token}`; // Add Bearer prefix
  }
  return req;
}, (error) => {
  return Promise.reject(error);
});

export default API;
