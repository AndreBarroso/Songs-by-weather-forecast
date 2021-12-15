import React from 'react';
import MusicCard from './MusicCard';

export default function PlayList({ list }) {
  const arrayListSongs = [...list];
  return (
    <div>
      <span>Essa é a nossa playlist de { arrayListSongs[0].estilo } </span>
      <ol>
        {  arrayListSongs.map((song) => {
          const { id, name, artista, album, estilo } = song;
          return (
              <li
                key={id}
              >
                <MusicCard name={ name }   artista={ artista } album={ album } estilo={ estilo }/>
                
              </li>
            )
        })}
      </ol>
    </div>
  );
}
