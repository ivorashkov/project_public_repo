export interface OfferDetails {
    id: number;
    title: string;
    contry: string;
    date: string;
    city: string;
    price: number;
    description: string;
    transportation: string;
}

export type Offer = Pick<OfferDetails, 'id'| 'title' | 'date' | 'price'>