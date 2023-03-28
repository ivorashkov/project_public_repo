export const uploadFile = (file: any, token: string, userId: string) => {
    return fetch(`http://localhost:8091/api/user/upload/all/${userId}`, {
        method: 'POST',
        headers: {
            Authorization: `Bearer ${token}`,
            'Content-type': 'multipart/form-data'
        },
        body: file
    })
    .then((res) => res.json())
    .catch((err) => console.error(err))
}