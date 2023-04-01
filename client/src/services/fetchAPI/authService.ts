enum links {
   baseUrl = 'http://localhost:8091',
   register = '/api/auth/signup',
   login = '/api/auth/authenticate',
   logout = '/api/auth/logout',
}

// http://localhost:8091/api/auth/logout

enum methods {
    post =  'POST',
    put =  'PUT',
    get =  'GET',
}

export const login = async (formFields: object) => {
    let res = await  fetch(`${links.baseUrl+links.login}`, {
        method: `${methods.post}`,
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(formFields)
    });

    let result = await res.json();

    if(res.ok) {
        return result;
    } else {
        throw result.message;
    }
}

export const register = async (formFields: object) => {
    return fetch(`${links.baseUrl+links.register}`, {
        method: `${methods.post}`,
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(formFields)
      })
        .then(res => res.json())
        .catch((err) => console.error(err));
}

export const logout = async (token: string) => {
    return fetch(`${links.baseUrl+links.logout}`, {
        method: `${methods.post}`,
        headers: {
            'Authorization': `Bearer ${token}`
        }
    })
}