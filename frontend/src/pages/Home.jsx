import React, { useContext } from 'react';
import { useHistory } from 'react-router-dom';
import UserContext from '../context/UserContext';
// import { GetTokenAuthorization } from '../services/resquestAPIs';

export default function Home() {
  const { setUserData } = useContext(UserContext);
  const history = useHistory();


  return (
    <div>
        <input
          type="text"
          placeholder='Digite seu nome'
          onChange={ (e) => setUserData(e.target.value)}
        />
        <button
          onClick={() => history.push('/minhaLista')}
        >
          Acessar
        </button>
    </div>
  );
}