import React from 'react';

export default function PlayList({ list }) {
  const arrayListSongs = [...list];
  return (
    <div>
      <ol>
        {  arrayListSongs.map((song) => {
          const { id, name } = song;
          return (
            <li
              key={id}
            >
              {name} 
              <button>Detalhes</button>
            </li>)
        })}
      </ol>
    </div>
  );
}
