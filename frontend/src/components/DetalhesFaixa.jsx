import React from 'react';

export default function Details({ estilo, album, artista, nome }) {
  return (
    <div>
      <ul>
        <li>{nome}</li>
        <li>{artista}</li>
        <li>{album}</li>
        <li>{estilo}</li>
      </ul>
    </div>
  );
}