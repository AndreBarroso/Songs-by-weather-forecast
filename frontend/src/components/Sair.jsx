
import React, { useEffect, useContext } from 'react';
import { useHistory } from 'react-router-dom';
import UserContext from '../context/UserContext';
import '../css/Sair.css';

export default function Sair() {
  const { userData, setUserData } = useContext(UserContext);
  const history = useHistory();

  const handleClick = () => {
    setUserData('');
  }

  useEffect( () => {
   if( !userData ) history.push('/home');
  }, [ userData ]);

  return (
    <button
      className="sair"
      type="button"
      onClick={ handleClick }
    >
       Sair
    </button>
  );
}
