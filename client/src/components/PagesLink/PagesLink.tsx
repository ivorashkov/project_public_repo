import { useEffect } from 'react';
import ReactPaginate from 'react-paginate';

export const PaginatedItems = ({ pages, total, getPageNumber }: any) => {
  return (
    <>
      <ReactPaginate
        breakLabel="..."
        nextLabel="next >"
        onPageChange={(event) => {
          getPageNumber(event.selected);
        }}
        pageRangeDisplayed={5}
        pageCount={pages}
        previousLabel="< previous"
      />
    </>
  );
};
