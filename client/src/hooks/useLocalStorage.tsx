import jwt_decode from 'jwt-decode';

interface UserInterface {
    id: number,
    isActive: boolean,
    status: string,
    sub: string,
    iat: number,
    exp: number
}

export const setLocalUserToken = async (token: string, clear?: boolean) => {
    const userAuth = await localStorage.getItem('user');

    if(!userAuth) {
        localStorage.setItem('userAuth', token);
    }
};

export const getUserDataFromToken = async (token: string | null) => {
    if(token) {
        const user = await jwt_decode(token);

        return {
            user,
            token
        }
    }
}