import axios from 'axios';

const clientId = '4e8d12ac54234d9091767fdf7890e974';
const clientSecret = '‌f4aa7c2387364f978442b3ed3c46b91e‌';
const str = clientId + ':' + clientSecret;

export const GetTokenAuthorization = async (data) => {
    const result = await fetch('https://accounts.spotify.com/api/token', {
        headers: {
            'Content-Type' : 'application/x-www-form-urlencoded', 
            'Authorization' : 'Basic NGU4ZDEyYWM1NDIzNGQ5MDkxNzY3ZmRmNzg5MGU5NzQ6ZjRhYTdjMjM4NzM2NGY5Nzg0NDJiM2VkM2M0NmI5MWU='
        },
        method: 'POST',
        body: data
    });

    const resultJson = await result.json();
    const token = resultJson.access_token;
    return token;
}

const port = 8080;

const PORT = process.env.PORT || port;

const api = axios.create({
    baseURL: `http://localhost:${PORT}`,
});

export const RequestPlayList = async (token) => {
    console.log('ccccchegou no axios', token)

    const body = {'cidade': 'Leme do Prado', 'solicitante': 'André Barroso', token}

    const response = await api.post('/chamadas', body);
    
    console.log(response.data, 'resposta do axios')
    // return result.data;
};


// export const RequestPlayList = async (data) => {
//     console.log('Assessou RequestList. token', data)

//     const result = await fetch('http://localhost:8080/chamadas', {
//         headers: {
//             'Content-Type' : 'application/json', 
//         },
//         method: 'POST',
//         body: {
//             "cidade": "Belo Horizonte",
//             "solicitante": "André Barroso",
//         }
//     });

//     const resultJson = await result.json();
//     console.log('meeeeu result: ', resultJson)
//     return resultJson;
// }