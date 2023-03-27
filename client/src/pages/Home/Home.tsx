import { FormEvent, useEffect, useState } from 'react';
import { getAllData, getAllOffersPaging, getAllCountries, getCityByCountry } from '../../services';

import { Offer } from '../../components/';
import { PaginatedItems } from '../../components/PagesLink';

export const Home = () => {
  const [offers, setOffers] = useState([]);
  const [pages, setPages] = useState<number[]>([]);
  const [currentPage, setCurrentPage] = useState<number>(0);
  const [totalNumberOfElements, setTotalNumberOfElements] = useState<number>(0);

  const [filterPages, setFilterPages] = useState<number>(0);
  const [filterPageSize, setFilterPageSize] = useState<number>(5);
  const [filterCountry, setFilterCountry] = useState<string[]>([]);
  const [filterCity, setFilterCity] = useState<string[]>([]);

  const [sort, setSort] = useState<string>('date');
  const [order, setOrder] = useState<string>('desc');
  const [country, setCountry] = useState<string>('');
  const [city, setCity] = useState<string>('');

  useEffect(() => {
    getAllOffersPaging(filterPages, sort, country, city, order).then((el) => {
      setOffers(el.content);
      setPages(el.totalPages);
      setTotalNumberOfElements(el.numberOfElements);
    });
  }, [filterPages, order, sort, city, country]);

  useEffect(() => {
    getAllCountries().then((el) => setFilterCountry(el.countries));
  }, []);

  useEffect(() => {
    getCityByCountry(country).then((el) => setFilterCity(el.cities));
  }, [country]);

  const getPageNumber = (data: any) => {
    setFilterPages(data)
  };

  return (
    <section>
      <h1>home</h1>
      <div className="section__form">
        <form action="">
          <div className="form__row">
            <label htmlFor="country">Country</label>

            <select name="country" id="country" onChange={(e) => setCountry(e.target.value)}>
              <option value='' selected>
                All countries
              </option>

              {filterCountry?.map((country) => (
                <option key={country} value={country}>
                  {country}
                </option>
              ))}
            </select>
          </div>

          <div className="form__row">
            <label htmlFor="city">City</label>

            <select name="city" id="city" onChange={(e) => setCity(e.target.value)}>
              <option value='' selected>
                All cities
              </option>

              {filterCity?.map((city) => (
                <option key={city} value={city}>
                  {city}
                </option>
              ))}
            </select>
          </div>

          <div className="form__row">
            <label htmlFor="sort">Sort</label>

            <select name="sort" id="sort" onChange={(e) => setSort(e.target.value)}>
              <option value="date" selected>
                date
              </option>
              <option value="price">price</option>
              <option value="duration">duration</option>
              <option value="discount">discount</option>
              <option value="stars">stars</option>
            </select>
          </div>

          <div className="form__row">
            <label htmlFor="order">Order</label>

            <select name="order" id="order" onChange={(e) => setOrder(e.target.value)}>
              <option value="asc">asc</option>
              <option value="desc" selected>
                desc
              </option>
            </select>
          </div>
        </form>
      </div>
      <div className="section__grid">
        <div className="grid">
          <div className="grid__items">
            <ul>
              {offers?.map((offer) => (
                <li key={offer.id}>
                  <Offer id={offer.id} date={offer.date} price={offer.price} title={offer.title} />
                </li>
              ))}
            </ul>

            <PaginatedItems
              pages={pages}
              total={totalNumberOfElements}
              getPageNumber={getPageNumber}
            />
          </div>
        </div>
      </div>
    </section>
  );
};
