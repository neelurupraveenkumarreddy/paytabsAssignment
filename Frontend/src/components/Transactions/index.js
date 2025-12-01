import React, { Component } from 'react'
import '../../index.css'
import Header from '../Header'
import { getJson } from '../../utils/api'


class Transactions extends Component {
    state = { transactions: [], loading: true }


    componentDidMount() {
        this.fetch()
    }


    fetch = async () => {
        try {
            const res = await getJson('/gateway/transactions')
            const {ok,data}=res
            this.setState({ transactions: data, loading: false })
        } catch (e) {
            this.setState({ loading: false })
        }
    }


    render() {
        const { transactions, loading } = this.state
        return (
            <><Header/>
            <div className="card">
                <h2>Transactions</h2>
                {loading ? <p>Loading...</p> : (
                    <table className="table">
                        <thead>
                            <tr><th>ID</th><th>Card</th><th>Amount</th><th>Type</th><th>Time</th></tr>
                        </thead>
                        <tbody>
                            {transactions.map(tx => (
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
                )}
            </div></>
        )
    }
}


export default Transactions