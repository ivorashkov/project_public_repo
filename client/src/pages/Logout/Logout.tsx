import { useContext, useEffect } from "react"
import { AuthContext } from "../../context";
import { useNavigate } from 'react-router-dom';
import { logout as authLogout } from '../../services'

export const Logout = () => {
    const {logout, token} = useContext(AuthContext);
    const navigate = useNavigate();

    useEffect(() => {
        authLogout(token)
            .then(() => {
                logout();
                navigate('/');
            })
            .catch(err => console.error(err))
    }, [])

    return null;
}