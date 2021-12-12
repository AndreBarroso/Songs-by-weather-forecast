import React, { useState } from 'react';
import UserContext from './UserContext';

function UserProvider({ children }) {
  const [userData, setUserData] = useState('André Felipe');

  return (
    <UserContext.Provider
      value={ {
        userData,
        setUserData,
      } }
    >
      {children}
    </UserContext.Provider>
  );
}

export default UserProvider;