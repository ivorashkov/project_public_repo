import { redirect } from "react-router-dom";

export const fetchAPI = async (
    url: string,
    method: string,
    formFields: object,
    redirectPage: string,
    auth?: string
) => {
    return fetch(`http://localhost:8091/${url}`, {
        method: `${method}`,
        headers: {
            Accept: 'application/json',
            'Content-Type': 'application/json',
            'Authorization': "Bearer " + auth,
        },
        body: JSON.stringify(formFields)
      })
        .then((res) => {
            console.log('ok : ', res.json())

            if(redirectPage) redirect(redirectPage);
        })
        .catch((err) => console.error(err));
}