import React, { useEffect, useState, useContext } from 'react';
import { RequestPlayList } from '../services/resquestAPIs';
import { getToken } from '../services/getToken';
import PlayList from '../components/PlayList';
import UserContext from '../context/UserContext';
import '../css/Listagem.css';


export default function SearchPlayList() {
  const { userData } = useContext(UserContext);
  const [tokenSpotfy, setTokenSpotify] = useState('');
  const [listMusics, setListMusics] = useState('');
  const [renderBeforeRequest, setRenderBeforeRequest] = useState('');
  const [ numberOfTracks, setNumberOfTracks ] = useState(0);
  const [ disable, setDisable ] = useState(true);
  const [ city, setCity ] = useState('Pato Branco');

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
    const list = await RequestPlayList(tokenSpotfy, numberOfTracks, city, userData);
    console.log('eeeee', list)
    setListMusics(list);
  } 

  useEffect( () => {
    if(tokenSpotfy && renderBeforeRequest) saveRequestPlayList();
  }, [renderBeforeRequest]);

  const handleChange = (e) => {
    const value = e.target.value;
    if(Number( value ) || value === '') setNumberOfTracks( e.target.value );
  }

  const disableButton = () => {
    if( !numberOfTracks ) setDisable(true);
    else setDisable(false);
  }

  useEffect( () => {
    disableButton();
  }, [ numberOfTracks ]);


  return (
    <div>
      <div className="listagem">
        <button
          type="button"
          disabled={ disable }
          onClick={ handleClick }
        >
          Buscar Playlist 
        </button>
        <button
          className="incrementos"
          onClick={ () => {
            if(numberOfTracks > 0) setNumberOfTracks(Number(numberOfTracks) - 1 )
          }}
        >
          -
        </button>
        <button
          className="incrementos"
          onClick={ () => setNumberOfTracks(Number(numberOfTracks) + 1 ) }
        >
          +
        </button>
        <input
          value={ numberOfTracks }
          onChange={ handleChange }
        />
        <input
          value={ city }
          onChange={ ( e ) => setCity( e.target.value )}
          placeholder='ex: Pato Branco'
        />
        { !listMusics ? renderBeforeRequest : <PlayList list={listMusics.listaMusicas} />}
      </div>
    </div>
  );
}
