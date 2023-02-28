export type Child = {
    children: JSX.Element;
}

export type GridItems = {
    offers: Grid[];
};

export interface Grid {
    title: string;
    description: string;
}

export interface RegisterForm {
    title: string;
}

export interface OfferData {
    title: string;
    date: Date;
    country: string;
    city: string;
    duration: number;
    stars: number;
    price: number;
    description: string;
    discount: number;
    transportType: string;
}

export interface User {
    id: number;
    firstName: string;
    lastName: string;
    email: string;
    username: string;
    role: {
        id: number;
        roleName: string;
    },
    status: string;
}

