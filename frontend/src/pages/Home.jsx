import React, { useEffect, useState } from 'react';
import { GetTokenAuthorization } from '../services/resquestAPIs';


export default function Home() {
  const redirectURL = new URL("https://accounts.spotify.com/authorize")
  redirectURL.search.Params.append('response_type', 'code');
  redirectURL.search.Params.append('client_id', '4fda7f4932bf4c068141a1e526502f7e‌');
  redirectURL.search.Params.append('redirect_uri', `${window.location.origin}/spotify/minhaLista`);

  return (
    <div>
        <button>Conecta com o spotfy</button>
    </div>
  );
}