import React, { useEffect, useState, useContext } from 'react';
import { RequestPlayList } from '../services/resquestAPIs';
import { getToken } from '../services/getToken';
import { useHistory } from 'react-router-dom';
import PlayList from '../components/PlayList';
import UserContext from '../context/UserContext';
import '../css/Listagem.css';
import Sair from '../components/Sair';

export default function SearchPlayList() {
  const history = useHistory();
  const { userData } = useContext(UserContext);
  const [ tokenSpotfy, setTokenSpotify ] = useState('');
  const [ listMusics, setListMusics ] = useState('');
  const [ renderBeforeRequest, setRenderBeforeRequest ] = useState('');
  const [ numberOfTracks, setNumberOfTracks ] = useState('');
  const [ disable, setDisable ] = useState(true);
  const [ city, setCity ] = useState('');

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
    try {
      const list = await RequestPlayList(tokenSpotfy, numberOfTracks, city, userData);
      setListMusics(list);
   }
   catch (e) {
     const error = e.toString();

    if(error === 'Error: Network Error') {
      alert( ' Server Error \n 500 - Internal server error\n Tente mais tarde :)' );
      return history.push('/home')
    }
    alert( ' Digite um nome de ciadade válido' );
    history.push('/wrongCity')
   }
  } 

  useEffect( () => {
    if(tokenSpotfy && renderBeforeRequest) saveRequestPlayList();
  }, [ renderBeforeRequest ]);

  const handleChange = (e) => {
    const value = e.target.value;
    if(Number( value ) || value === '') setNumberOfTracks( e.target.value );
  }

  const disableButton = () => {
    if( !numberOfTracks || !city ) setDisable(true);
    else setDisable(false);
  }

  useEffect( () => {
    if(!numberOfTracks) setNumberOfTracks('');
  }, [ numberOfTracks ]);

  useEffect( () => {
    disableButton();
  }, [ city, numberOfTracks ]);

  return (
    <div>
      <header>
        <div>
          Seja bem vindo(a)<span className='dataAPI'>{userData }</span>. <br/> 
          Escolha uma cidade e o número desejado
          de faixas musicais.<br/>
        </div>
        <div>
          { !listMusics ? "" : 
            <div>Neste momento faz 
              <span className='dataAPI'> { listMusics.temperatura } ºC </span>
              em  <span className='dataAPI'>{ listMusics.cidade }</span>. Sugerimos uma playlist de 
              <span className='dataAPI'>{listMusics.listaMusicas[0].estilo}</span>.
            </div> 
          }
        </div>
        <Sair/>
      </header>
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
            if(numberOfTracks > 0){
              setNumberOfTracks(Number(numberOfTracks) - 1 )
            }
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
          placeholder="Digite o número de faixas ou clique em +"
        />
        <input
          value={ city }
          onChange={ ( e ) => setCity( e.target.value )}
          placeholder="Digite o nome da cidade. ex: Pato Branco"
        />
        { !listMusics ? renderBeforeRequest : <PlayList list={ listMusics.listaMusicas } />}
      </div>
    </div>
  );
}
