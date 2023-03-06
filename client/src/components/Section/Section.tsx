import { FC } from "react";
import { Child } from "../../types";
import { Shell } from '../Shell';

export const Section: FC<Child> = ({children}) => {
    return (
        <>
            <Shell>
                {children}
            </Shell>
        </>
    );
}