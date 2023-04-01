import { useState, useEffect } from 'react';
import { useParams, Navigate } from 'react-router-dom';
import { getAnOffer } from '../../services';

interface detailsInterface {
  title: string;
  country: string;
  city: string;
  date: string;
  price: string;
  description: string;
  transportation: string;
}

export const Article = () => {
  const [details, setDetails] = useState<detailsInterface>();
  const { offerId } = useParams();

  useEffect(() => {
    getAnOffer(offerId).then(el => {
      setDetails(el)
      console.log(el)
    });

  }, [])


  return (
    <article>
        <h1>{details?.title}</h1>
        <p>{details?.country}</p>
        <p>{details?.city}</p>
        <p>{details?.date}</p>
        <p>{details?.price}</p>
        <p>{details?.description}</p>
        <p>{details?.transportation}</p>
    </article>
  );
};
