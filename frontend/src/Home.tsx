import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import AuthService from './AuthService';

const Home: React.FC = () => {
  const [currentUser, setCurrentUser] = useState<any>(null);
  const navigate = useNavigate();

  useEffect(() => {
    const user = AuthService.getCurrentUser();
    if (!user) {
      navigate('/login', { replace: true });
      return;
    }
    setCurrentUser(user);
  }, []); // Empty dependency array to run only once

  const handleLogout = () => {
    AuthService.logout();
    navigate('/login', { replace: true });
  };

  if (!currentUser) return <div className="text-center mt-5">Loading...</div>;

  return (
    <div className="home-container">
      <h1>Welcome, {currentUser.email}!</h1>
      <p className="mb-4">You have successfully logged in to your account.</p>
      <button 
        className="btn btn-secondary logout-button" 
        onClick={handleLogout}
      >
        Logout
      </button>
    </div>
  );
};

export default Home;