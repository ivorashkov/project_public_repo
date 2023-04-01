export const createAnOffer = async (formFields: any, userId: number,token: string) => {
    let res = await fetch(`localhost:8091/api/offer/create/${userId}`, {
        method: `POST`,
        headers: {
            'Content-Type': 'application/json',
            Authorization: `Bearer ${token}`
        },
        body: formFields
    })
    .then((res) => res.json())
    .catch((err => console.error(err)))
}

//localhost:8091/api/offer/create/{userId}
//file -> List<multipartfile> files
//Json -> offer
//http://localhost:8091/api/offer/test
