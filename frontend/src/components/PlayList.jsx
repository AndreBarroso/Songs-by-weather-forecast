import React from 'react';

export default function PlayList({ list }) {
  const arrayListSongs = [...list];
  return (
    <div>
      <ul>
        {  arrayListSongs.map((song) => {
          const { id, name } = song;
          return <li key={id}>{name}</li>
        })}
      </ul>
    </div>
  );
}
