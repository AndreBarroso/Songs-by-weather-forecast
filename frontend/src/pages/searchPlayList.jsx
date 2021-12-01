import React, { useEffect, useState } from 'react';
import { GetTokenAuthorization } from '../services/resquestAPIs';

const ClientId = '‌4e8d12ac54234d9091767fdf7890e974';
const ClientSecret = 'f4aa7c2387364f978442b3ed3c46b91e‌';
const str = ClientId + ':' + ClientSecret;

export default function SearchPlayList() {

  const getToken = async () => {

    const redirectUri = `${window.location.origin}/minhaLista`;


    const getCode = () => {
    let code = null;
    const queryString = window.location.search;
    if(queryString.length > 0) {
      const urlParams = new URLSearchParams(queryString);
      code = urlParams.get('code');
      return code;
    }
  }

  const code = getCode()

        const data = new URLSearchParams();
        data.append('code', code);
        data.append('redirect_uri', redirectUri);
        data.append('grant_type', 'client_credentials');

        const token = GetTokenAuthorization(data).access_token;

      const result = await fetch('https://accounts.spotify.com/api/token', {
        headers: {
            'Content-Type' : 'application/x-www-form-urlencoded', 
            'Authorization' : 'Basic NGU4ZDEyYWM1NDIzNGQ5MDkxNzY3ZmRmNzg5MGU5NzQ6ZjRhYTdjMjM4NzM2NGY5Nzg0NDJiM2VkM2M0NmI5MWU='
        },
        method: 'POST',
        body: data
    });
      console.log('xxxxxxx', await result.json());
  };

  useEffect(() => {
    getToken();
  }, []);

  return (
    <div>
      <div>
          Client ID
          <input
           type="text"
          //  onChange= {(e) => setClientId(e.target.value)}
           value="4e8d12ac54234d9091767fdf7890e974"
           />
        </div>
        <div>
          Secret
          <input
           type="text"
          //  onChange= {(e) => setSecret(e.target.value)}
           value="f4aa7c2387364f978442b3ed3c46b91e"
           />
        </div>
        <button
          // onClick={handleClick}
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
