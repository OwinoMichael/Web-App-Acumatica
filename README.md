# Full Stack User Authentication Web App

## 📌 Overview

This is a full-stack web application that allows users to register, log in, and view a personalized dashboard. It includes:

- ✅ **Frontend**: React with form validation, navigation, and API integration
- ✅ **Backend**: Node.js with Express and MongoDB
- ✅ **Authentication**: Secure login and registration with JWT and bcrypt
- ✅ **Security**: Input validation, password hashing, and token expiry handling
- ✅ **Testing**: Manual test cases with validation reports

---

## 🚀 Features

### 🔐 User Registration
- Fields: Full Name, Email, Password, Confirm Password
- Frontend validations:
  - Valid email format using regex
  - Password must be **at least 6 characters**
  - Password and Confirm Password must match
- Backend validations using `express-validator`
- Passwords are hashed with `bcrypt`
- JWT token created and sent with expiry

### 🔑 User Login
- Authenticates via email and password
- Returns JWT token on success
- Redirects to dashboard on successful login
- Handles:
  - Invalid credentials
  - Expired token
  - Unauthorized access

### 📋 Dashboard
- Displays welcome message with user’s name
- Accessible only after authentication

---

## 🛠️ Tech Stack

### Frontend
- React + TypeScript
- React Router DOM
- Axios
- Bootstrap (optional for styling)

### Backend
- Node.js
- Express
- MongoDB + Mongoose
- Bcrypt for password hashing
- JSON Web Tokens (JWT)
- dotenv + CORS

---

## 📁 Project Structure

project-root/ │ ├── backend/ │ ├── controllers/ │ ├── models/ │ ├── routes/ │ ├── middleware/ │ ├── .env │ └── server.js │ ├── frontend/ │ ├── public/ │ ├── src/ │ │ ├── components/ │ │ ├── pages/ │ │ ├── services/ │ │ └── App.tsx │ ├── .env │ └── package.json


---

## ⚙️ Environment Variables

### Backend (`backend/.env`)
```env
PORT=5000
MONGO_URI=your_mongodb_connection
JWT_SECRET=your_jwt_secret
TOKEN_EXPIRE=3600
```

---
### Frontend (frontend/.env)
REACT_APP_API_URL=http://localhost:5000/api
