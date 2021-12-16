# Boas vindas ao repositório do projeto Songs By Weather Forecast!

Esse projeto teve como objetivo, a criação de um micro-serviço capaz de aceitar solicitações RESTful recebendo como
parâmetro o nome de uma cidade, retornando em seguida uma sugestão de playlist de acordo com a temperatura atual.

---

## Principais tecnologias utilizadas

- Front-End: HTML, CSS, JavaScript, React Js
- Back-End: Java, JPA, Hibernate, Spring Boot
- Banco de dados: Postgresql
- Deploy: Heroku
---

## Aplicação em execução

![gif do app funcionando](https://github.com/AndreBarroso/Songs-by-weather-forecast/blob/master/frontend/public/songsPresentation.gif)

---

## Instruções para executar 

**Foi realizado o deploy do projeto. Para executá-lo basta acessar o link:
[https://songs-by-weather-forecast-fron.herokuapp.com/](https://songs-by-weather-forecast-fron.herokuapp.com/).**

**!!! ATENÇÃO !!!**

Em seu primeiro acesso, a aplicação poderá estar em **modo sleep** podendo levar cerca de 20 segundos
para que a aplicação seja carregada no navegador e mais 20 segundos para a primeira consulta.

Caso queira executar o projeto a partir do seu próprio computador, será necessário cloná-lo. Siga os passos a seguir:

1. Abra o terminal, acesse o diretório de sua preferência onde 
você deseja fazer o download, e crie uma pasta usando o comando **mkdir**:
```bash
mkdir projeto-andre
```

2. Entre no diretório que você acabou de criar: 
```bash
cd projeto-andre
```

3. Clone do projeto executando o comando a seguir:
```bash
git clone https://github.com/AndreBarroso/Songs-by-weather-forecast.git
```

4. Acesse a pasta criada executando o comando a seguir:
```bash
cd Songs-by-weather-forecast
```
##### Instalando o Frontend
5. Em um terminal, acesse a pasta frontend e  de dentro dela instale as dependências:
```bash
cd frontend;
npm install
```
##### Executando as instâncias locais
6. Iniciando aplicação

##### Iniciando o Backend
6.1 No terminal, acesse a pasta backend do projeto e de dentro dela execute o comando: 
```bash
mvn spring-boot:run
```
##### Iniciando o Frontend
6.2 Após a etapa 6.1, pelo terminal entre na pasta do frontend, e de dentro dela execute:
```bash
npm start
```
Aguarde um momento. A aplicação abrirá automaticamente em seu navegardor.

**!!! ATENÇÃO !!!**

Caso tente fazer alguma requisição no frontend antes de iniciar o backend, uma mensagem de **erro 500** aparecerá.
