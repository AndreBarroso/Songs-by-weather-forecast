import React, { useEffect } from 'react';
import { GetTokenAuthorization } from '../services/resquestAPIs';

export default function SearchPlayList() {

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
    console.log(token)

    return token;
  };

  useEffect(() => {
    getToken();
  }, []);

  return (
    <div>
      GETTOKEN
    </div>
  );
}
