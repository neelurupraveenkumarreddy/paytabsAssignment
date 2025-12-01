import React, { Component } from 'react'
import Cookies from 'js-cookie'
import '../../index.css'
import withNavigation from '../../utils/withNavigation'

class Login extends Component {
    state = { username: '', password: '', error: '' }


    changeUser = (e) => this.setState({ username: e.target.value })
    changePass = (e) => this.setState({ password: e.target.value })


    submitForm = async (e) => {
        e.preventDefault()
        const { username, password } = this.state
        const url = 'http://localhost:8080/api/auth/login'

        console.log(username,password)
        try {
            const res = await fetch(url, {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify({ username, password })
            })
            const data = await res.json();
            if (res.ok) {
                Cookies.set('jwt_token', data.token)
                Cookies.set('role',data.role)
                this.props.navigate('/dashboard', { replace: true });

            } else {
                this.setState({ error: data || 'Login failed' })
            }
        } catch (err) {
            console.log(err)
            this.setState({ error: 'Server error' })
        }
    }


    render() {
        const { username, password, error } = this.state
        return (
            <div className="card">
                <h2>Login</h2>
                <form onSubmit={this.submitForm}>
                    <div className="form-row">
                        <input type="text" placeholder="Username" value={username} onChange={this.changeUser} />
                    </div>
                    <div className="form-row">
                        <input type="password" placeholder="Password" value={password} onChange={this.changePass} />
                    </div>
                    <div className="form-row">
                        <button className="btn">Login</button>
                    </div>
                    <p>Sample Info: username: "customer1" ,password: "userPassword"
                        Admin INfo: "Admin","adminpassword"
                    </p>
                    {error && <div className="error">{error}</div>}
                </form>
            </div>
        )
    }
}


export default withNavigation(Login)