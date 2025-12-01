import Cookies from 'js-cookie'

const uri = "http://localhost:8080/api"
export function authHeaders() {
    const token = Cookies.get('jwt_token')
    return token ? { 'Authorization': `Bearer ${token}`, 'Content-Type': 'application/json' } : { 'Content-Type': 'application/json' }
}


export async function postJson(url, body) {
    const options = { method: 'POST', headers: authHeaders(), body: JSON.stringify(body) }
    const resp = await fetch(`${uri}${url}`, options)
    try { const text = await resp.json()
        return { ok: resp.ok, data: text } 
    } catch (e) { 
        let tex = "Transaction Failed Check Your balance,Pin and Account number"
        return { ok: resp.ok, data: tex } }
}


export async function getJson(url) {
    const options = { method: 'GET', headers: authHeaders() }
    const resp = await fetch(`${uri}${url}`, options)
    const data = await resp.json()
    return { ok: resp.ok, data }
}