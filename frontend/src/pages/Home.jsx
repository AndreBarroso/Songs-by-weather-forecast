import React, { useEffect, useState, useContext } from 'react';
import { useHistory } from 'react-router-dom';
import UserContext from '../context/UserContext';

export default function Home() {
  const { userData, setUserData } = useContext(UserContext);
  const [ disable, setDisable ] = useState(true);
  const history = useHistory();

  useEffect( () => {
    setUserData('');
  }, []);

  useEffect( () => {
    if( !userData ) setDisable(true);
    else setDisable(false);
  }, [ userData ]);

  return (
    <div>
        <input
          type="text"
          placeholder='Digite seu nome'
          onChange={ (e) => setUserData(e.target.value)}
        />
        <button
          type="button"
          disabled={ disable }
          onClick={() => history.push('/minhaLista')}
        >
          Acessar
        </button>
    </div>
  );
}
