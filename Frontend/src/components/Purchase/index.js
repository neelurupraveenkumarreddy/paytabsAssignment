import React, { Component } from 'react'
import { postJson } from '../../utils/api'
import '../../index.css'
import Header from '../Header'

class Purchase extends Component {
    state = { cardNumber: '', pin: '', amount: '', result: null }


    change = (e) => this.setState({ [e.target.name]: e.target.value })


    submit = async (e) => {
        e.preventDefault()
        const { cardNumber, pin, amount } = this.state
        const body = { cardNumber, pin, amount: Number(amount), type: 'PURCHASE' }
        const { ok, data } = await postJson('/gateway/transaction', body)
        console.log(data);
        this.setState({ result: data })
    }


    render() {
        const { cardNumber, pin, amount, result } = this.state
        return (
            <><Header/>
            <div className="card">
                <h2>Purchase</h2>
                <form onSubmit={this.submit}>
                    <div className="form-row">
                        <input name="cardNumber" placeholder="Card Number" value={cardNumber} onChange={this.change} />
                    </div>
                    <div className="form-row">
                        <input name="pin" type="password" placeholder="PIN" value={pin} onChange={this.change} />
                    </div>
                    <div className="form-row">
                        <input name="amount" type="number" placeholder="Amount" value={amount} onChange={this.change} />
                    </div>
                    <div className="form-row">
                        <button className="btn">Purchase</button>
                    </div>
                </form>
                <p>Sample info: cardNumber :'4123456789012345' pin: '1234'</p>
                {result && <div className="result-box"><pre>{JSON.stringify(result, null, 2)}</pre></div>}
            </div></>
        )
    }
}


export default Purchase