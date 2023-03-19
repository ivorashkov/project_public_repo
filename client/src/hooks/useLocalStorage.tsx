import jwt_decode from 'jwt-decode';

interface UserInterface {
    id: number,
    isActive: boolean,
    status: string,
    sub: string,
    iat: number,
    exp: number
}

export const setLocalUserToken = (token: string, clear?: boolean) => {
    const userAuth = localStorage.getItem('user');

    if(!userAuth) {
        localStorage.setItem('userAuth', token);
    }

    if(clear) {
        localStorage.removeItem("userAuth");
    }
};

export const getUserDataFromToken = async (token: string | null) => {
    if(token) {
        const userData = await jwt_decode(token);

        return userData
    }
}