import React, { useEffect, useState } from 'react';
import {decode as base64_decode, encode as base64_encode} from 'base-64';
import CryptoJS from 'cryptojs'
import { Redirect } from 'react-router';
import { SpotifyAuth, Scopes } from 'react-spotify-auth'
import { GetTokenAuthorization } from '../services/resquestAPIs';

const ClientId = '‌4e8d12ac54234d9091767fdf7890e974';
const ClientSecret = 'f4aa7c2387364f978442b3ed3c46b91e‌';
const str = ClientId + ':' + ClientSecret;

const base64Str = Buffer.from(str, 'utf8').toString('base64');


export default function SearchPlayList() {


  // const { search } = match.params;
//   const [token, setToken] = useState('');
//   const[erroMessage, setErrorMessage] = useState(true);


//   // const getToken = async () => {
//   //   try {
//   //     const tk = await GetTokenAuthorization();
//   //     setToken(tk);
//   //     setErrorMessage(true);
//   //   } catch (error) {
//   //     setErrorMessage(false);
//   //   }
//   // };

//   const getToken = async () => {

//     // console.log('eeeeeeeentou no get token');
//     const redirectUri = `${window.location.origin}/minhaLista`;


//     if(code) {
//       const headers = {
//         'Content-Type' : 'application/x-www-form-urlencoded', 
//         'Authorization' : 'Basic ' + base64Str
//       }
//     }
//         const data = new URLSearchParams();
//         data.append('code', code);
//         data.append('redirect_uri', redirectUri);
//         data.append('grant_type', 'client_credentials');

//       //   const result = await fetch('https://accounts.spotify.com/api/token', {
//       //     headers: {
//       //         'Content-Type' : 'application/x-www-form-urlencoded', 
//       //         'Authorization' : 'Basic ' + 
//       //     },
//       //     method: 'POST',
//       //     body: 'grant_type=client_credentials'
//       // });


//       const result = await fetch('https://accounts.spotify.com/api/token', {
//         headers: {
//             'Content-Type' : 'application/x-www-form-urlencoded', 
//             'Authorization' : 'Basic ' + base64Str
//         },
//         method: 'POST',
//         body: data
//     });

//     // const data = await result.json();

//       console.log('xxxxxxx', await result.json());
//     // try {
//     //   const tk = await GetTokenAuthorization();
//     //   setToken(tk);
//     //   setErrorMessage(true);
//     // } catch (error) {
//     //   setErrorMessage(false);
//     // }
//   };

//   useEffect(() => {
//     console.log(base64Str, 'minha string')
//     getToken();
//   }, []);

//   // // eslint-disable-next-line
//   // useEffect(() => {
//   //   console.log('token: ', token);
//   //   console.log('error: ', erroMessage)
//   //   // eslint-disable-next-line
//   // }, [token]);

const [clientId, setClientId] = useState('4e8d12ac54234d9091767fdf7890e974');
const [secret, setSecret] = useState('');
const [accessToken, setAcessToken] = useState('');
const [refresh, setRefreshToken] = useState('');
const [codeSpotfy, setCodeSpotfy] = useState('');
const redirect_uri = "http://localhost:3000/minhaLista"

const TOKEN = 'https://accounts.spotify.com/api/token';
const AUTHORIZATE = "https://accounts.spotify.com/authorize";

  function handleAuthorizationResponse() {
    console.log(this.status, 'rtgrtgrt');
    if(this.status === 200) {
        let data = JSON.parse(this.responseText);
        console.log(data, 'ssssssssssssssssssssssssssssssssssssssssss');
      if(data.access_token !== undefined) {
        setAcessToken(data.access_token);
      }
      if(data.refresh_token !== undefined) {
        setRefreshToken(data.refresh_token);
      }
      onPageLoad();
    }
    else {
      console.log(this.responseText);
      alert(this.responseText);
    }
  }
  


  const callAuthorizationApi = (body) => {
    let xhr = new XMLHttpRequest();
    xhr.open('POST', TOKEN, true);
    xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
    xhr.setRequestHeader('Authorization','Basic NGU4ZDEyYWM1NDIzNGQ5MDkxNzY3ZmRmNzg5MGU5NzQ6ZjRhYTdjMjM4NzM2NGY5Nzg0NDJiM2VkM2M0NmI5MWU=');
    xhr.send(body);
    xhr.onload = handleAuthorizationResponse;
  }


  const fetchAccessToken = (code) => {
    let body = 'grant_type=client_credentials';
    body += '&code=' + code;
    body += '&redirect_uri=' + encodeURI(redirect_uri);
    body += '&client_id=' + "4e8d12ac54234d9091767fdf7890e974";
    body += '&client_secret=' + "f4aa7c2387364f978442b3ed3c46b91e";
    callAuthorizationApi(body);
  }

    useEffect(() => {
      onPageLoad();
  }, [codeSpotfy]);

  const onPageLoad = () => {
    if(window.location.search.length > 0) handleRedirect();
    
  }



  const getCode = () => {
    let code = null;
    const queryString = window.location.search;
    if(queryString.length > 0) {
      const urlParams = new URLSearchParams(queryString);
      code = urlParams.get('code');
      setCodeSpotfy(code);
      return code;
    }
  }

  const handleRedirect = () => {
    let code = getCode();
    fetchAccessToken(code);
    window.history.pushState("", "", redirect_uri);
  }

const handleClick = (evt) => {
  let url = AUTHORIZATE;
  url += '?client_id=' + "4e8d12ac54234d9091767fdf7890e974";
  url += '&response_type=code';
  url += '&redirect_uri=' + encodeURI(redirect_uri);
  url += '&show_dialog=true';
  url += "&scope="+'ugc-image-upload user-read-playback-state user-modify-playback-state user-read-currently-playing user-read-private user-read-email user-follow-modify user-follow-read user-library-modify user-library-read streaming app-remote-control user-read-playback-position user-top-read user-read-recently-played playlist-modify-private playlist-read-collaborative playlist-read-private playlist-modify-public';
  window.location.href = url;
}

  return (
    <div>
      <div>
          Client ID
          <input
           type="text"
           onChange= {(e) => setClientId(e.target.value)}
           value="4e8d12ac54234d9091767fdf7890e974"
           />
        </div>
        <div>
          Secret
          <input
           type="text"
           onChange= {(e) => setSecret(e.target.value)}
           value="f4aa7c2387364f978442b3ed3c46b91e"
           />
        </div>
        <button
          onClick={handleClick}
        >
          envia
        </button>
    </div>
  );
}

// curl -X POST \
//      -d grant_type=refresh_token \
//      -d client_id=4e8d12ac54234d9091767fdf7890e974\
//      -d client_secret=f4aa7c2387364f978442b3ed3c46b91e‌ \
//      https://accounts.spotify.com/api/token
