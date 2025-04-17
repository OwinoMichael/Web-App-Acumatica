import React, { useState, useEffect } from 'react';
import { useNavigate, Link } from 'react-router-dom';
import AuthService from './AuthService';

const Login: React.FC = () => {
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [loading, setLoading] = useState(false);
  const [message, setMessage] = useState('');
  const navigate = useNavigate();

  // Check if user is already logged in - only on component mount
  useEffect(() => {
    const currentUser = AuthService.getCurrentUser();
    if (currentUser) {
      navigate('/home', { replace: true });
    }
  }, []); // Empty dependency array to run only once

  const handleLogin = async (e: React.FormEvent) => {
    e.preventDefault();
    setMessage('');
    setLoading(true);

    try {
      const response = await AuthService.login(email, password);
      console.log('Login successful:', response);
      
      // Check if we're logged in
      const user = AuthService.getCurrentUser();
      if (user) {
        // Use replace to avoid history stack issues
        navigate('/home', { replace: true });
      } else {
        setMessage('Login successful but session data not saved. Please try again.');
        setLoading(false);
      }
    } catch (error: any) {
      console.error('Login error:', error);
      const errorMessage = error.response?.data || error.message || 'An error occurred during login';
      setMessage(errorMessage);
      setLoading(false);
    }
  };

  return (
    <div className="card-container">
      <h2 className="text-center mb-4">Login</h2>
      <form onSubmit={handleLogin}>
        <div className="form-group">
          <label htmlFor="email">Email</label>
          <input
            type="text"
            className="form-control"
            name="email"
            value={email}
            onChange={e => setEmail(e.target.value)}
            required
          />
        </div>

        <div className="form-group">
          <label htmlFor="password">Password</label>
          <input
            type="password"
            className="form-control"
            name="password"
            value={password}
            onChange={e => setPassword(e.target.value)}
            required
          />
        </div>

        <div className="form-group">
          <button className="btn btn-primary btn-block" disabled={loading}>
            {loading ? <span className="spinner-border spinner-border-sm"></span> : <span>Login</span>}
          </button>
        </div>

        {message && (
          <div className="form-group">
            <div className="alert alert-danger" role="alert">
              {message}
            </div>
          </div>
        )}
      </form>
      
      <div className="nav-links mt-3 text-center">
        Don't have an account? <Link to="/signup">Sign Up</Link>
      </div>
    </div>
  );
};

export default Login;