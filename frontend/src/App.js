import React from 'react';
import { Redirect, BrowserRouter, Switch, Route } from 'react-router-dom';
// import './App.css';

import SearchPlayList from './pages/searchPlayList';

function App() {
  return (
    <BrowserRouter>
      <Switch>
        <Route path="/minhaLista" component={ SearchPlayList } />
        <Route exact path="/"><Redirect to="/minhaLista" /></Route>
    </Switch>
   </BrowserRouter>
      
  );
}

export default App;
