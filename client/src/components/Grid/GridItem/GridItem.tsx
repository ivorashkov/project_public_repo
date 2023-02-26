import { FC } from "react";
import { Grid } from "../../../types";

export const GridItem: FC<Grid> = ({title, description}) => {
    return (
        <div className="grid__col grid__col--1of4">
            <h1>{title}</h1>

            <p>{description}</p>
        </div>
    );
}