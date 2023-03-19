import { redirect } from "react-router-dom";

export const fetchAPI = async (url: string, method: string, formFields: object, redirectPage: string) => {
    return fetch(`http://localhost:8091/${url}`, {
        method: `${method}`,
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(formFields)
      })
        .then((res) => {
            console.log('ok : ', res)
            
            if(redirectPage) redirect(redirectPage);
        })
        .catch((err) => console.error(err));
}