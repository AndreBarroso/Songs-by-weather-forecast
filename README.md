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

## Instruções para executar 

**Foi realizado o deploy do projeto. Para executá-lo basta acessar o link:
[https://songs-by-weather-forecast-fron.herokuapp.com/](https://songs-by-weather-forecast-fron.herokuapp.com/).**

**!! ATENÇÂO !!**

Em seu primeiro acesso, a aplicação poderá estar em **modo sleep** podendo levar cerca de 20 segundos
para que a aplicação seja carregada no navegador e mais 20 segundos para a primeira consulta.

Caso queira executar o projeto do no próprio computador, será necessário cloná-lo. Siga os passos a seguir:

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
5. Em um terminal acesse a pasta frontend e instale as dependências:
```bash
cd frontend;
npm install
```

##### Instalando o Backend
6. O backend foi feito usando a IDE Eclipse. Para evitar problemas de compatibilidade, sugere-se que o mesmo seja executado nessa IDE:

6.1.1 Abra o Eclipse e na primeira janela, selecione o diretório raiz do projeto backend. Clique em Launch em seguida.

6.1.2 Caso o projeto backend não abra no workspace, vá em **file** --> **Open Projects From File Sytem** e selecione o direrório raiz.
Clique uma vez na pasta backend (sem acessa-la) e por fim clique em **Finish**.

6.2 Após a etapa anterior, aguarde as dependências serem instaladas. Elas instalam automaticamente.

##### Executando as instâncias locais
7. Inciando aplicação

##### Iniciando o Backend
7.1 Após a etapa 6.2 clique no botão verde **Run** na parte superior do Eclipse e aguarde a execução do software.

##### Iniciando o Frontend
7.2 Após as etapas 5 e 6.2 entre no terminal do frontend, verifique se você está dentro do seu diretório. E execute:
```bash
npm start
```
Aguarde um momento. A aplicação abrirá automaticamente em seu navegardor.

**!! ATENÇÂO !!**

Caso tente fazer alguma requisição no frontend antes de iniciar o backend. Uma mensagem de **erro 500** aparecerá.
