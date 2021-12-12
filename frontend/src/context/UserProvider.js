import React, { useState } from 'react';
import UserContext from './UserContext';

function UserProvider({ children }) {
  const [userData, setUserData] = useState('');
  // const [ isLoading, setIsLoading ] = useState(true);

  return (
    <UserContext.Provider
      value={ {
        userData,
        setUserData,
        // isLoading,
        // setIsLoading
      } }
    >
      {children}
    </UserContext.Provider>
  );
}

export default UserProvider;