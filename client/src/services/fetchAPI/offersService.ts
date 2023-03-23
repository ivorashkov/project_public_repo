// api/home/offers

enum links {
  baseUrl = 'http://localhost:8091',
  offers = '/api/home/offers',
  offer = '/api/home/'
}

enum methods {
  post = 'POST',
  put = 'PUT',
  get = 'GET'
}

export interface getAllData {
  pages?: any;
  pageSize?: any;
  location?: any;
  sort?: any;
  order?: any;
}

export const getAllOffersPaging = async <getAllData>(
  pages = 0,
  pageSize = 5,
  sort = 'date',
  location = '',
  order = 'desc'
) => {
  return await fetch(
    `localhost:8091/api/home/offers?page=${pages}&pageSize=${pageSize}&location=${location}&sort=${sort};${order}`,
    {
      method: methods.get
    }
  )
    .then((res) => res.json())
    .catch((err) => console.error(err));
};

export const getAnOffer = async (id: any) => {
  return await fetch(links.baseUrl + links.offer + id, {
    method: methods.get
  })
    .then((res) => res.json())
    .catch((err) => console.error(err));
};

// export const getAllOffersPagingWithFilter
