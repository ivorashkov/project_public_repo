import { articleFields } from '../../staticData';
import { Form } from '../../components';
import { ChangeEvent, FormEvent, useState } from 'react';
import {useContext} from 'react';
import { AuthContext } from '../../context';
import { createAnOffer } from '../../services/offers';

interface OfferDetails {
  title: string;
  country: string;
  city: string;
  duration: number;
  stars: number;
  price: number;
  description: string;
  discount: number;
  transportType: string;
}

export const CreateArticle = () => {
  const [files, setFiles] = useState<any>();
  const { userData, token } = useContext(AuthContext);
  
  const submitHandler = (e: FormEvent<HTMLFormElement>) => {
    e.preventDefault();
    
    const formData = new FormData(e.currentTarget);  

    const formFields = {
      title: formData.get('title'),
      country: formData.get('country'),
      city: formData.get('city'),
      duration: formData.get('duration'),
      stars: formData.get('stars'),
      price: formData.get('price'),
      description: formData.get('description'),
      discount: formData.get('discount'),
      transportType: formData.get('transportType'),
    }

    fetch(`http://localhost:8091/api/offer/create/?userId=${userData.id}`, {
        method: 'POST',
        headers: {
          'Authorization': `Bearer ${token}`,
          'Content-Type':`application/json`
      },
        body: JSON.stringify(formFields)
    })
    .then((res) => res.json())
    .then((result) => {
      console.log(result)
    })
    .catch((err => console.error(err)))

    //   first endpoint POST-> ../api/offer/create/{userId} -> JSON {offerId -> "id"}
    //   second endpoint PATCH -> ../api/offer/create/{userId}/{offerId} + multipartFiles -> JSON {Full offer Details}

    // createAnOffer(formData, userData.id, token);
  }

  return (
    <section>
      <div className="form">
        <form action="" onSubmit={submitHandler}>
          {/* <div className="form__row">
            <label htmlFor="file">Images</label>
            <input type="file" name="file" id="file" onChange={(event) => setFiles(event.target.files)} multiple/>
          </div> */}

          <div className="form__row">
            <label htmlFor="title">title</label>
            <input type="text" name="title" id="title" />
          </div>

          <div className="form__row">
            <label htmlFor="country">country</label>
            <input type="text" name="country" id="country" />
          </div>

          <div className="form__row">
            <label htmlFor="city">city</label>
            <input type="text" name="city" id="city" />
          </div>

          <div className="form__row">
            <label htmlFor="duration">duration</label>
            <input type="number" name="duration" id="duration" />
          </div>

          <div className="form__row">
            <label htmlFor="stars">stars</label>
            <input type="number" name="stars" id="stars" />
          </div>

          <div className="form__row">
            <label htmlFor="description">description</label>
            <textarea name="description" id="description"></textarea>
          </div>

          <div className="form__row">
            <label htmlFor="discount">discount</label>
            <input type="number" name="discount" id="discount" />
          </div>

          <div className="form__row">
            <label htmlFor="transportType">transport type</label>
            <select name="transportType" id="transportType">
              <option value="airplane">airplane</option>
              <option value="bus">bus</option>
              <option value="ship">ship</option>
            </select>
          </div>

          <div className="form__row">
            <label htmlFor="price">price</label>
            <input type="number" name="price" id="price" />
          </div>

          <button>Submit</button>
        </form>
      </div>
    </section>
  );
};
