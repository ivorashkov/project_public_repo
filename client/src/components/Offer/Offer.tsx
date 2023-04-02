import { Link } from 'react-router-dom';
import type { Offer as IOffer } from '../../types';
import './Offer.scss';

export const Offer = ({id ,title, date, price}: IOffer) => {
    return (
        <div className="offer">
            <h3>{title}</h3>

            <p>{date}</p>

            <p>{price}</p>

            <Link to={`/offers/offer/${id}`}>Read more</Link>
        </div>
    )
}