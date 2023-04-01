import { useContext, useEffect } from "react"
import { AuthContext } from "../../context";
import { useNavigate } from 'react-router-dom';
import { logout as authLogout } from '../../services'

export const Logout = () => {
    const {token, logoutFn} = useContext(AuthContext);
    const navigate = useNavigate();

    useEffect(() => {
        authLogout(token)
            .then(() => {
                logoutFn();
                navigate('/');
            })
            .catch(err => console.error(err))
    }, [token])

    return null;
} 