import {FormEvent, useEffect, useState} from "react";
import {getAllOffersPaging} from '../../services';

import { Offer } from '../../components/'

export const Home = () => {
  const [searchValue, setSearchValue] = useState<string>('');
  const [offers, setOffers] = useState([]);

  useEffect(() => {
    getAllOffersPaging().then(el => {
      setOffers(el.content)
    })
  }, [])

  return (
      <section>
        <h1>home</h1>

        <div className="section__form">
          <form action="">
            <div className="form__row">
              <label htmlFor="location">Location</label>

              <select name="location" id="location">
                <input type="text" name="location" id="location"
                       onChange={(e) => setSearchValue(e.target.value)}/>
              </select>
            </div>

            <div className="form__row">
              <label htmlFor="city">City</label>

              <select name="city" id="city">
                <option value="City">City</option>
              </select>
            </div>
          </form>
        </div>

        <div className="section__grid">
          <div className="grid">
            <div className="grid__items">
              <ul>
                {offers.map((offer) => <li key={offer?.id}><Offer id={offer.id} date={offer.date} price={offer.price} title={offer.title} /></li> )}
              </ul>
            </div>
          </div>
        </div>

        <div className="section__paging">
          <div className="paging">
            <ul>
              <li><a href=" "></a></li>
            </ul>
          </div>
        </div>
      </section>
  );
};
