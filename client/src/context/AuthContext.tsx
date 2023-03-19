import { createContext, useContext } from 'react';

import { getUserDataFromToken } from '../hooks';

interface authInterface {
    children: JSX.Element[];
}

const initialAuthState = {
    id: 0,
    isActive: false,
    status: '',
    sub: '',
    iat: 0,
    exp: 0
}

export const AuthContext = createContext({
    id: 0,
    isActive: false,
    status: '',
    sub: '',
    iat: 0,
    exp: 0
});

export const AuthProvider = async ({ children }: any) => {
    const token = localStorage.getItem('user');
    const user = await getUserDataFromToken(token);

    console.log('user' , user)

    return (
        <AuthContext.Provider value={user}>
            {children}
        </AuthContext.Provider>
    );
};

// export const useAuthContext = () => {
//     const authState = useContext(AuthContext);

//     return authState;
// }

