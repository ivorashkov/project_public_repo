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

    const token = localStorage.getItem('user');
    const user = getUserDataFromToken(token);

    const login = (authData: object) => {
        // setUserData(authData);
    }

    const logout = () => {
        localStorage.removeItem("userAuth");
    };

    console.log(`userData 2`, userData)

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

