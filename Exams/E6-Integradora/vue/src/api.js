import axios from 'axios'

const api = axios.create({
    baseURL        : 'http://localhost:8000/api/v1',
    withCredentials: true
})

// api.interceptors.response.use(
//     response => response,
//     async error => {
//         if (error.response.status === 403)
//             api.post('/auth/refresh')
//                .then(res => {
//                    api.defaults.headers.common['Authorization'] = `Bearer ${ res.data.access_token }`
//                    return api.request(error.config)
//                })
//                .catch(() => {
//                    return error
//                })
//     }
// )

export default api