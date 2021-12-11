import React, { useEffect, useState } from 'react';
import { RequestPlayList } from '../services/resquestAPIs';
import { getToken } from '../services/getToken';

export default function SearchPlayList() {
  const [tokenSpotfy, setTokenSpotify] = useState('');
  const [listMusics, setListMusics] = useState('');
  const [ isLoading, setIsLoading ] = useState(false);

  const handleClick = async () => {
    const list = await RequestPlayList(tokenSpotfy);
    setListMusics(list);
  } 

  const saveToken = async () => {
    const token = await getToken();
    setTokenSpotify(`Bearer ${token}`);
  } 

  useEffect( () => {
    saveToken();
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
