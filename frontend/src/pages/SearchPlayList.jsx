import React, { useEffect, useState, useContext } from 'react';
import { RequestPlayList } from '../services/resquestAPIs';
import { getToken } from '../services/getToken';
import PlayList from '../components/PlayList';
import UserContext from '../context/UserContext';

export default function SearchPlayList() {
  const { setUserData, isLoading, setIsLoading  } = useContext(UserContext);
  const [tokenSpotfy, setTokenSpotify] = useState('');
  const [listMusics, setListMusics] = useState('');
 
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
  //  setIsLoading(false)
  }, [listMusics]);

  return (
    <div>
      <button
        onClick={ handleClick }
      >
        Busca Lista
      </button>
      { !listMusics ? <span>Loading ... </span>: <PlayList list={listMusics.listaMusicas} />}
    </div>
  );
}
