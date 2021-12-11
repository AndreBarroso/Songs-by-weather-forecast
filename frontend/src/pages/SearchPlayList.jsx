import React, { useEffect, useState } from 'react';
import { GetTokenAuthorization, RequestPlayList } from '../services/resquestAPIs';

export default function SearchPlayList() {
  const [tokenSpotfy, setTokenSpotify] = useState('');
  const [listMusics, setListMusics] = useState('');
  const [ isLoading, setIsLoading ] = useState(false);

  const getCode = () => {
    let code = null;
    const queryString = window.location.search;
    if(queryString.length > 0) {
      const urlParams = new URLSearchParams(queryString);
      code = urlParams.get('code');
      return code;
    }
  }

  const getToken = async () => {

    const redirectUri = `${window.location.origin}/minhaLista`;
    
    const code = getCode()

    const data = new URLSearchParams();
    data.append('code', code);
    data.append('redirect_uri', redirectUri);
    data.append('grant_type', 'client_credentials');

    const token = await GetTokenAuthorization(data);

    setTokenSpotify(`Bearer ${token}`);

    return token;
  };

  const handleClick = async () => {
    const list = await RequestPlayList(tokenSpotfy);
    setListMusics(list);
  } 

  useEffect(() => {
    getToken();
  }, []);

  useEffect(() => {
   console.log(listMusics);
  }, [listMusics]);

  return (
    <div>
      <button
        onClick={handleClick}
      >
        Busca Lista
      </button>
    </div>
  );
}
