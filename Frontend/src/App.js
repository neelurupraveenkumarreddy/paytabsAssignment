import React, { Component } from 'react'
import { BrowserRouter, Routes, Route, Navigate } from 'react-router-dom'
import Cookies from 'js-cookie'

import Login from './components/Login'
import Dashboard from './components/Dashboard'
import Topup from './components/Topup'
import Purchase from './components/Purchase'
import Transactions from './components/Transactions'
import Header from './components/Header'

class App extends Component {
  
  isAuthenticated = () => !!Cookies.get('jwt_token')

  ProtectedRoute = ({ children }) => {
    return this.isAuthenticated() ? children : <Navigate to="/login" replace />
  }

  render() {
    return (
      <BrowserRouter>
            <Routes>

              {/* Public Route */}
              <Route path="/login" element={<Login />} />

              {/* Protected Routes */}
              <Route path="/dashboard" element={
                <this.ProtectedRoute>
                  <Dashboard />
                </this.ProtectedRoute>
              } />

              <Route path="/topup" element={
                <this.ProtectedRoute>
                  <Topup />
                </this.ProtectedRoute>
              } />

              <Route path="/purchase" element={
                <this.ProtectedRoute>
                  <Purchase />
                </this.ProtectedRoute>
              } />

              <Route path="/transactions" element={
                <this.ProtectedRoute>
                  <Transactions />
                </this.ProtectedRoute>
              } />

              {/* Default Route */}
              <Route path="*" element={<Navigate to="/login" replace />} />

            </Routes>

      </BrowserRouter>
    )
  }
}

export default App
