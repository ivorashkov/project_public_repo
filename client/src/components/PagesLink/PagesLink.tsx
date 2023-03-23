import { useEffect } from 'react';
import ReactPaginate from 'react-paginate';

export const PaginatedItems = ({ pages, total, getPageNumber }: any) => {
  return (
    <>
      <ReactPaginate
        breakLabel="..."
        nextLabel="next >"
        onPageChange={(event) => {
          const newOffset = (event.selected * pages) % total;
          getPageNumber(newOffset);
        }}
        pageRangeDisplayed={5}
        pageCount={pages}
        previousLabel="< previous"
      />
    </>
  );
};
