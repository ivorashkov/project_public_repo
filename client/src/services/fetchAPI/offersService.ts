// api/home/offers

enum links {
    baseUrl = 'http://localhost:8091',
    offers = '/api/home/offers',
 }

enum methods {
    post =  'POST',
    put =  'PUT',
    get =  'GET',
}

export const getAllOffersPaging = () => {
    return fetch(`${links.baseUrl+links.offers}`, {
        method: `${methods.get}`,
    })
}