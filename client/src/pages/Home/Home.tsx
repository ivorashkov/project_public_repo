import { FormEvent, useEffect, useState } from "react";
import { getAllOffersPaging } from '../../services';

const dummyData = {
  content : [
    {
      id: 1,
      title: "Byala Offer",
      date: "2023-03-21T19:51:34",
      country: "Bulgaria",
      city: "Byala",
      duration: 1,
      stars: 1.0,
      price: 10.00,
      description: "Byala test",
      discount: 0.0,
      transportType: "bus",
      paths: []
    },
    {
      id: 2,
      title: "Byala Offer 2",
      date: "2023-03-21T19:52:34",
      country: "Bulgaria",
      city: "Byala",
      duration: 1,
      stars: 1.0,
      price: 10.00,
      description: "Byala test",
      discount: 0.0,
      transportType: "bus",
      paths: []
    },
    {
      id: 3,
      title: "Byala Offer 3",
      date: "2023-03-21T19:53:34",
      country: "Bulgaria",
      city: "Byala",
      duration: 1,
      stars: 1.0,
      price: 10.00,
      description: "Byala test",
      discount: 0.0,
      transportType: "bus",
      paths: []
    }
  ]
}

export const Home = () => {
  const [searchValue, setSearchValue] = useState<string>('');
  const [content, setContent] = useState<object>({});

  useEffect(() => {
    getAllOffersPaging(); 

    console.log(getAllOffersPaging())
  }, [])

  useEffect(() => {

  }, [searchValue])

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
            <div className="grid__item">
              <div className="offer"></div>
            </div>
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
