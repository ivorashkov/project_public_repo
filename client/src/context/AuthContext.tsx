import { clear } from 'console';
import { createContext, useContext, useState, useEffect } from 'react';
import { useAuth } from '../hooks';

import { getUserDataFromToken, setLocalUserToken } from '../hooks';

interface authInterface {
    children: JSX.Element[];
}

export const AuthContext = createContext<any>({});

export const AuthProvider = ({ children }: authInterface) => {
    const [userData, setUserData] = useState<any>();

    const token = localStorage.getItem('userAuth');
    const user = getUserDataFromToken(token);

    useEffect(() => {
        setUserData(user)
    }, [])

    const login = () => {
        setUserData(user)
        console.log('login')
    }

    const logout = () => {
        setUserData({})
        localStorage.removeItem("userAuth");
        console.log('logout')
    };

    console.log('render header')

    return (
        <AuthContext.Provider value={{userData, token, login, logout}}>
            {children}
        </AuthContext.Provider>
    );
};

export const useAuthContext = () => {
    const authState = useContext(AuthContext);

    return authState;
}

