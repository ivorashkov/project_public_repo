import { FormEvent, useEffect, useState } from 'react';
import { getAllData, getAllOffersPaging } from '../../services';

import { Offer } from '../../components/';
import { PaginatedItems } from '../../components/PagesLink';

export const Home = () => {
  // const [searchValue, setSearchValue] = useState<string>('');
  const [offers, setOffers] = useState([]);
  const [pages, setPages] = useState<number[]>([]);
  const [currentPage, setCurrentPage] = useState<number>(0);
  const [totalNumberOfElements, setTotalNumberOfElements] = useState<number>(0);

  const [filterPages, setFilterPages] = useState<number>(0);
  const [filterPageSize, setFilterPageSize] = useState<number>(5);
  const [filterSort, setFilterSort] = useState<string>('date');
  const [filterLocation, setFilterLocation] = useState<string>('');
  const [filterOrder, setFilterOrder] = useState<string>('desc');

  useEffect(() => {
    getAllOffersPaging(filterPages, filterPageSize, filterSort, filterLocation, filterOrder).then(
      (el) => {
        setOffers(el.content);
        setPages(el.totalPages);
        setTotalNumberOfElements(el.numberOfElements);
      }
    );
  }, [filterPages, filterPageSize, filterSort, filterLocation, filterOrder]);

  const getPageNumber = (data: any) => {
    console.log(`getPageNumber`, data);
  };

  return (
    <section>
      <h1>home</h1>
      <div className="section__form">
        <form action="">
          <div className="form__row">
            <label htmlFor="location">Location</label>

            <select name="" id=""></select>
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
              {offers.map((offer) => (
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
