// const clientID‌ ‌=  ‌
// const clientSecret‌ ‌= '‌8c5b88ea1fed465badebafbfa4dfc5de)‌';

const clientId = '4e8d12ac54234d9091767fdf7890e974';
const clientSecret = '‌f4aa7c2387364f978442b3ed3c46b91e‌';
const str = clientId + ':' + clientSecret;
const base64Str = Buffer.from(str, 'utf8').toString('base64');

export const GetTokenAuthorization = async () => {
    console.log('antes do use route')
  

    // const route = useRoute();
    console.log('chegou aqui');
    // const { code } = route.query;
    const redirectUri = `${window.location.origin}/handler`;

    console.log(redirectUri, ', ssssssssssss')

    const data = new URLSearchParams();
    // data.append('code', code);
    data.append('redirect_uri', redirectUri);
    data.append('grant_type', 'authorization_code');

    console.log(data, 'meu form')

    // if(code) {

    // }


    
    const result = await fetch('https://accounts.spotify.com/api/token', {
         headers: {
             'Content-Type' : 'application/x-www-form-urlencoded', 
             'Authorization' : 'Basic ' + base64Str
         },
         method: 'POST',
         body: data
     });

     console.log('chegou aqui rrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrr', result);

     

    // const data = await result.json();
    return data.access_token
}