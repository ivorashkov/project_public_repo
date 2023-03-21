// api/home/offers

enum links {
  baseUrl = 'http://localhost:8091',
  offers = '/api/home/offers',
}

enum methods {
  post = 'POST',
  put = 'PUT',
  get = 'GET',
}

export const getAllOffersPaging = async () => {
  const data = await fetch(`${links.baseUrl + links.offers}`, {
    method: `${methods.get}`,
  }).then(res => res.json()).catch((err) => console.log(err))
  return data;
  // return fetch(`${links.baseUrl + links.offers}`, {
  //   method: `${methods.get}`,
  // })
}