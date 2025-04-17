import axios from 'axios';

const API_URL = 'http://localhost:8080'; 


axios.defaults.headers.common['Content-Type'] = 'application/json';
axios.defaults.withCredentials = true;

class AuthService {
  login(email: string, password: string) {
    console.log('Login attempt for:', email);
    
    return axios
      .post(`${API_URL}/login`, {
        email,
        password
      })
      .then(response => {
        console.log('Login response:', response);
        
        if (response.data && response.data.token) {
          localStorage.setItem('user', JSON.stringify({
            email,
            token: response.data.token
          }));
          console.log('User stored in localStorage');
        } else {
          console.warn('No token found in response:', response.data);
          throw new Error('No authentication token received');
        }
        
        return response.data;
      })
      .catch(error => {
        console.error('Login error in AuthService:', error);
        throw error;
      });
  }

  logout() {
    localStorage.removeItem('user');
    console.log('User logged out, localStorage cleared');
  }

  // Rest of your AuthService remains the same
  signup(fullName: string, email: string, password: string) {
    console.log('Signup request for:', email);
    
    return axios.post(`${API_URL}/createNewUser`, {
      fullName,
      email,
      password
    });
  }

  getCurrentUser() {
    const userStr = localStorage.getItem('user');
    if (userStr) return JSON.parse(userStr);
    return null;
  }

  getAuthHeader() {
    const user = this.getCurrentUser();
    if (user && user.token) {
      return { Authorization: `Bearer ${user.token}` };
    } else {
      return { Authorization: '' };
    }
  }
}

export default new AuthService();