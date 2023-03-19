import { useState, useEffect } from "react";
import { getUserDataFromToken } from "./useLocalStorage";

export const useAuth = async () => {
    const [currUser, setCurrUser] = useState<any>();

    const token = localStorage.getItem('user');
    const user = await getUserDataFromToken(token);

    useEffect(() => {
        setCurrUser(user);
    }, [currUser])

    return currUser
}