// api/home/offers

enum links {
  baseUrl = 'http://localhost:8091',
  offers = '/api/home/offers',
  offer = '/api/home/',
}

enum methods {
  post = 'POST',
  put = 'PUT',
  get = 'GET',
}

export const getAllOffersPaging = async () => {
  return await fetch(links.baseUrl + links.offers, {
    method: methods.get,
  }).then(res => res.json()).catch((err) => console.error(err))
}

export const getAnOffer = async (id: any) => {
  return await fetch(links.baseUrl + links.offer + id, {
    method: methods.get,
  }).then(res => res.json()).catch((err) => console.error(err))
}