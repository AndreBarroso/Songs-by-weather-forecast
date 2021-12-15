import React, { useEffect, useState } from 'react';
import '../css/Listagem.css';

export default function Details({ name, album, artista }) {
 const musicWithDetails = (
    <div className='music'>
      <span>{ name }</span>
      <ul>
        <li>Artitsta: { artista }</li>
        <li>Album: { album }</li>
      </ul>
    </div>
 )

 const musicWithoutDetails = (
    <div className='music'>
      <span>{ name }</span>
    </div>
 )

  const [ showDetails, setShowDetails ] = useState(false);
  const [ musicDetails, setMusicDetails ] = useState(musicWithoutDetails)

  useEffect( () => {
    renderMusic();
  }, [ showDetails ]);

  const renderMusic = () => {
    if( showDetails ) setMusicDetails( musicWithDetails )
    else setMusicDetails(musicWithoutDetails);
  }

  const handleClick = () =>{
    setShowDetails(!showDetails)
  }

  return (
    <div className='musicContainer'>
      { musicDetails }
      <button
        className='bottonDetalis'
        onClick={ handleClick }  
      >
        { !showDetails ? 'Ver detalhes da música' : 'Limpar detalhes' }
      </button>
    </div>
  );
}
