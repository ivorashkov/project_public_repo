import { useState, useEffect } from "react";

export const useAuth = () => {
    const [currUser, setCurrUser] = useState();

    useEffect(() => {
    }, [currUser])

    return currUser
}