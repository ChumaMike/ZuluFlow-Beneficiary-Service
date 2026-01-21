import { useState } from 'react'
import axios from 'axios'
import './App.css'

function App() {
    // STATE: This is like the memory of your app
    const [clientId, setClientId] = useState('CLIENT-BMW-001')
    const [beneficiaries, setBeneficiaries] = useState([])
    const [loading, setLoading] = useState(false)
    const [error, setError] = useState(null)

    // ACTION: Search for beneficiaries
    const searchBeneficiaries = async () => {
        setLoading(true)
        setError(null)
        try {
            // Note: We don't need http://localhost:8000 because of the Proxy!
            const response = await axios.get(`/api/v1/beneficiaries?clientId=${clientId}`)
            setBeneficiaries(response.data)
        } catch (err) {
            setError("Failed to connect to the bank. Is the Gateway running?")
            console.error(err)
        } finally {
            setLoading(false)
        }
    }

    return (
        <div className="dashboard">
            <header>
                <h1>🦁 ZuluFlow Bank</h1>
            </header>

            <div className="card">
                <h2>👥 Beneficiary Lookup</h2>
                <div className="input-group">
                    <input
                        type="text"
                        value={clientId}
                        onChange={(e) => setClientId(e.target.value)}
                        placeholder="Enter Client ID"
                    />
                    <button onClick={searchBeneficiaries} disabled={loading}>
                        {loading ? 'Searching...' : 'Search'}
                    </button>
                </div>

                {error && <div className="error-msg">{error}</div>}

                <div className="results">
                    {beneficiaries.length === 0 && !loading && <p>No beneficiaries found.</p>}

                    {beneficiaries.map((b) => (
                        <div key={b.id} className="beneficiary-item">
                            <h3>{b.name}</h3>
                            <p>Account: {b.accountNumber}</p>
                            <span className={`status ${b.status}`}>{b.status}</span>
                        </div>
                    ))}
                </div>
            </div>
        </div>
    )
}

export default App