import axios from 'axios';

const port = 8080;

const PORT = process.env.PORT || port;

const api = axios.create({
    baseURL: `http://localhost:${PORT}`,
    headers: {
        'Content-Type': 'application/json; charset=utf-8'
    },
});

export const GetTokenAuthorization = async (data) => {

    const headers = { 'Content-Type' : 'application/x-www-form-urlencoded', 
    'Authorization' : 'Basic NGU4ZDEyYWM1NDIzNGQ5MDkxNzY3ZmRmNzg5MGU5NzQ6ZjRhYTdjMjM4NzM2NGY5Nzg0NDJiM2VkM2M0NmI5MWU=' }

    const result = await axios.post(
        'https://accounts.spotify.com/api/token',
        data,
        { headers: headers }
    )
    const token = result.data.access_token;
    return token;
}

export const RequestPlayList = async (token, numberOfTracks, city, userData) => {
   
    const body = { token, numberOfTracks, 'cidade': city, 'solicitante': userData }

    const response = await api.post('/chamadas', body);
    
    return response.data
};
