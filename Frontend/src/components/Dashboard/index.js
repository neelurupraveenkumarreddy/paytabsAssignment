import React, { Component } from 'react'
import { getJson } from '../../utils/api'
import Header from '../Header'

class Dashboard extends Component {
    state = { cardNumber: '', balance: null, message: '', loading: false,data :[]}


    changeCard = (e) => this.setState({ cardNumber: e.target.value })


    fetchBalance = async () => {
        const { cardNumber } = this.state
        if (!cardNumber) { this.setState({ message: 'Enter card number' }); return }
        this.setState({ loading: true, message: '' })
        try {
            const { ok, data } = await getJson(`/gateway/transactions/card/${cardNumber}`)
            const dt=await getJson(`/gateway/balance/${cardNumber}`)
            if (ok) {
                this.setState({ message: `Found ${data.length} transactions`, loading: false,data:data,balance:dt.data.balance })
            } else {
                this.setState({ message: 'Not found', loading: false })
            }
        } catch (e) {
            this.setState({ message: 'Server error', loading: false })
        }
    }


    render() {
        const { cardNumber, message, loading, data,balance } = this.state
        return (
            <><Header/>
            <div className="card">
                <h2>Dashboard</h2>
                <div className="form-row">
                    <input type="text" placeholder="Enter card number" value={cardNumber} onChange={this.changeCard} />
                </div>
                <div className="form-row">
                    <button className="btn" onClick={this.fetchBalance}>{loading ? 'Loading...' : 'Get Info'}</button>
                </div>
                {message && <div className="result-box"><h3>Balance:{balance}   {message}</h3>
                <table className="table">
                        <thead>
                            <tr><th>ID</th><th>Card</th><th>Amount</th><th>Type</th><th>Time</th></tr>
                        </thead>
                        <tbody>
                            {data.map(tx => (
                                <tr key={tx.id}>
                                    <td>{tx.id}</td>
                                    <td>{tx.cardNumber}</td>
                                    <td>{tx.amount}</td>
                                    <td>{tx.type}</td>
                                    <td>{tx.timestamp}</td>
                                </tr>
                            ))}
                        </tbody>
                    </table>
                </div>}
            </div></>
        )
    }
}


export default Dashboard