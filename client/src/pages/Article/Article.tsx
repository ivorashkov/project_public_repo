import { useState, useEffect } from 'react';
import { useParams, Navigate } from 'react-router-dom';
import { getAnOffer } from '../../services';

export const Article = () => {
  const [details, setDetails] = useState({});
  const { offerId } = useParams();

  useEffect(() => {
    getAnOffer(offerId).then(el => setDetails(el));
  }, [])

  return (
    <article>
        <h1>{offer?.title}</h1>
        <p>{offer?.country}</p>
        <p>{offer?.city}</p>
        <p>{offer?.date}</p>
        <p>{offer?.price}</p>
        <p>{offer?.description}</p>
        <p>{offer?.transportation}</p>
    </article>
  );
};
