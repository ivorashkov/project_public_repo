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
  const [filter, setFilter] = useState<getAllData>({
    pages: 0,
    pageSize: 5,
    sort: 'date',
    location: '',
    order: 'desc'
  });

  useEffect(() => {
    getAllOffersPaging(
      filter.pageSize,
      filter.sort,
      filter.location,
      filter.order,
      filter.pages
    ).then((el) => {
      const array = Array.from({ length: el.totalPages }, (_, i) => i);

      setOffers(el.content);
      setPages(array);
      setTotalNumberOfElements(el.numberOfElements);
    });
  }, []);

  const handleClick = (event: React.MouseEvent<HTMLElement>) => {
    getAllOffersPaging().then((el) => {
      setOffers(el.content);
    });
  };

  const getPageNumber = (data: any) => {
    console.log(`getPageNumber`, getPageNumber);
  };

  return (
    <section>
      <h1>home</h1>
      <div className="section__form">
        <form action="">
          <div className="form__row">
            <label htmlFor="location">Location</label>

            <select name="location" id="location">
              <input
                type="text"
                name="location"
                id="location"
                // onChange={(e) => setSearchValue(e.target.value)}
              />
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
      3
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

            <ul>
              {pages.length != 1
                ? pages.map((el) => (
                    <li key={el}>
                      <PaginatedItems
                        pages={pages.length}
                        page={el}
                        total={totalNumberOfElements}
                        clickHandler={handleClick}
                      />
                    </li>
                  ))
                : ''}
            </ul>
          </div>
        </div>
      </div>
    </section>
  );
};
