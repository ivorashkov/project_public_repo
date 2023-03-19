import { createContext, useContext } from 'react';

import { getUserDataFromToken } from '../hooks';

export const AuthContext = createContext(null);

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

export const AuthProvider = ({ children }: authInterface) => {
    const token = localStorage.getItem('user');
    const user: any = getUserDataFromToken(token);

    console.log('user' , user)

    return (
        <AuthContext.Provider value={user}>
            {children}
        </AuthContext.Provider>
    );
};

export const useAuthContext = () => {
    const authState = useContext(AuthContext);

    return authState;
}