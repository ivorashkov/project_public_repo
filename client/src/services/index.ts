import { type } from 'os';
import { UserDetails } from '../types';

const baseUrl = 'localhost:8091';

export const register = ({email, password}: UserDetails) => {
    return fetch(`${baseUrl}/user/register`, {
        method: 'POST',
        headers: {
            'content-type': 'application/json'
        },
        body: JSON.stringify({email, password})
    })
    .then(res => res.json());
}

export const login = async ({email, password}: UserDetails) => {
    let res = await fetch(`${baseUrl}/user/login`, {
        method: 'POST',
        headers: {
            'content-type': 'application/json'
        },
        body: JSON.stringify({email, password})
    });

    let jsonResult = await res.json();

    if (res.ok) {
        return jsonResult;
    } else {
        throw jsonResult.message;
    }
}