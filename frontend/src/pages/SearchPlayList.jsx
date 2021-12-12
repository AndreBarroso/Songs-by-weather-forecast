import React, { useEffect, useState, useContext } from 'react';
import { RequestPlayList } from '../services/resquestAPIs';
import { getToken } from '../services/getToken';
import PlayList from '../components/PlayList';
import UserContext from '../context/UserContext';

export default function SearchPlayList() {
  const [tokenSpotfy, setTokenSpotify] = useState('');
  const [listMusics, setListMusics] = useState('');
  const [renderBeforeRequest, setRenderBeforeRequest] = useState('');

  const saveToken = async () => {
    const token = await getToken();
    setTokenSpotify(`Bearer ${token}`);
  } 

  useEffect( () => {
    saveToken();
  }, []);

  const handleClick = async () => {
    setListMusics('');
    setRenderBeforeRequest(<span>Loading ... </span> );
  } 

  const saveRequestPlayList = async () => {
    const list = await RequestPlayList(tokenSpotfy);
    setListMusics(list);
  } 

  useEffect( () => {
    if(tokenSpotfy && renderBeforeRequest) saveRequestPlayList();
  }, [renderBeforeRequest]);

  return (
    <div>
      <button
        onClick={ handleClick }
      >
        Busca Lista
      </button>
      { !listMusics ? renderBeforeRequest : <PlayList list={listMusics.listaMusicas} />}
    </div>
  );
}
