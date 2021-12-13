import React from 'react';
import { Redirect, BrowserRouter, Switch, Route } from 'react-router-dom';
// import './App.css';

import Home from './pages/Home'
import SearchPlayList from './pages/SearchPlayList';

function App() {
  return (
    <BrowserRouter>
      <Switch>
        <Route exact path="/home" component={ Home } />
        <Route exact path="/minhaLista" component={ SearchPlayList } />
        <Route exact path="/"><Redirect to="/home" /></Route>
        <Route exact path="/wrongCity"><Redirect to="/minhaLista" /></Route>
    </Switch>
   </BrowserRouter>
      
  );
}

export default App;
