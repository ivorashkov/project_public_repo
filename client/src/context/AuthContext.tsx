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
    }, [token])

    const loginFn = () => {
        setUserData(user)
    }

    const logoutFn = () => {
        setUserData({})
        localStorage.removeItem("userAuth");
    };

    return (
        <AuthContext.Provider value={{userData, token, loginFn, logoutFn}}>
            {children}
        </AuthContext.Provider>
    );
};

export const useAuthContext = () => {
    const authState = useContext(AuthContext);

    return authState;
}

