import { FormEvent, useEffect, useState } from "react";
import { getAllOffersPaging } from '../../services';

export const Home = () => {
  const [searchValue, setSearchValue] = useState<string>('');
  const [offers, setOffers] = useState();

  useEffect(() => {
    getAllOffersPaging().then(el => {
      setOffers(el.content)
      console.log(el.content)
      console.log(offers)
    })
  }, [])

  // useEffect(() => {
  //
  // }, [searchValue])

  return (
    <section>
      <h1>home</h1>

      <div className="section__form">
        <form action="">
          <div className="form__row">
            <label htmlFor="location">Location</label>
            
            <select name="location" id="location">
              <input type="text" name="location" id="location" onChange={(e) =>setSearchValue(e.target.value) } />
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
              {/*{content.map((of) => <li><div className="offer"></div></li> )}  */}
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
