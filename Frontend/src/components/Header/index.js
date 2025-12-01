import React, { Component } from 'react'
import { Link, useNavigate } from 'react-router-dom'
import Cookies from 'js-cookie'
import '../../index.css'
import withNavigation from '../../utils/withNavigation'
// HOC to inject navigate into class component (replacing withRouter)

class Header extends Component {

  state={loggedIN:""}
  componentDidMount(){
    const role=Cookies.get("role")
    this.setState({loggedIN:role})
  }
  logout = () => {
    Cookies.remove('jwt_token')
    this.props.navigate('/login', { replace: true })
  }

  render() {
    const {loggedIN}=this.state
    return (
      <div className="header">
        <div><strong>Banking POC</strong></div>
        {loggedIN==="ROLE_CUSTOMER" &&
        <div className="nav-links">
          <Link to="/dashboard">Dashboard</Link>
          <Link to="/topup">Topup</Link>
          <Link to="/purchase">Purchase</Link>
          <button className="btn primary" onClick={this.logout}>
            Logout
          </button>
        </div>
        }{loggedIN==="ROLE_ADMIN" &&
        <div className="nav-links">
          <Link to="/dashboard">Dashboard</Link>
          <Link to="/topup">Topup</Link>
          <Link to="/purchase">Purchase</Link>
          <Link to="/transactions">Transactions</Link>
          <button className="btn primary" onClick={this.logout}>
            Logout
          </button>
        </div>}
      </div>
    )
  }
}

export default withNavigation(Header)
