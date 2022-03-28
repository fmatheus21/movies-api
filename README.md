<h1 align="center">Cadastro de Filmes</h1>

</br></br>

Conteúdos
=================
<!--ts-->
   * [Sobre](#sobre)
   * [Features](#features)
   * [Pré-requisitos](#pre-requisitos)
   * [Instalação](#instalacao)
        * [API](#instalacao-api)  
        * [UI](#instalacao-ui) 
   * [Como usar](#como-usar)  
       * [API](#como-usar-api)     
       * [UI](#como-usar-ui) 
   * [Tecnologias](#tecnologias)
   * [Demonstração](#demonstracao) 
<!--te-->

</br></br></br></br>


# Sobre <a id="sobre"></a>
<p align="left">Este é um pequeno CRUD de filmes utilizando Spring Boot com autenticação oAuth2, JWT e Angular 12</p>
 <p align="left">A implementação conta com upload de arquivos, login expirado por inatividade, envio de email, cors e muito mais.</p>
 <p align="left">Esse projeto está em construção e periodicamente será atualizado.</p> 


</br></br></br></br>

>## <center>  🚧 Em construção...🚧  </center>

</br></br></br></br>

# Features<a id="features"></a>

## Back-end
>- [x] Access Token
>- [x] Refresh Token
>- [x] Logout
>- [x] Cadastrar Filme
>- [x] Consultar Filme
>- [ ] Atualizar Filme
>- [x] Listar Filme
>- [ ] Excluir Filme

## Front-end
>- [ ] Login
>- [ ] Logout
>- [ ] Cadastrar Filme
>- [ ] Consultar Filme
>- [ ] Atualizar Filme
>- [x] Listar Filme
>- [ ] Excluir Filme

</br></br></br></br>


# Pré-requisitos <a id="pre-requisitos"></a>
> - Mysql 8
> - JDK 11+
> - Postman v9.13.2
> - Node 4.15.0
> - NPM 6.14.8
> - Angular CLI 12.2.16


  </br></br></br></br>

# Instalação <a id="instalacao"></a> 
## API (back-end) <a id="instalacao-api"></a> 
> Importe para o seu Mysql o arquivo  <code>***database.sql***</code>, que se encontra em <code>***_files***</code>. Na pasta <code>***_files***</code> encontra-se também o arquivo  <code>***movies.postman_collection.json***</code> para que você importe para o seu postman. Esse arquivo contém os endpoints da aplicação. A API está documentada com Swagger e você pode acessar pela url  <code>***localhost:port/api/v1/swagger-ui.html***</code>. No rquivo <code>***application.properties***</code>  contém as configurações da API, uma das mais importantes é <code>***api.config.allow-origin***</code> , nela você vai informar onde a aplicação Angular está rodando. Só serão aceitas requisições desta origem.

><code>***[Repositório API](https://github.com/fmatheus21/movies-api)***</code>

<br />

## UI (front-end) <a id="instalacao-ui"></a>

> Abra o projeto no  <code>***VSCode***</code>  ou a IDE de sua preferência, abra o terminal e digite  <code>***npm install***</code>. Isso irá baixar todas as dependências necessária para rodar o projeto. Após baixar as dependências, digite  <code>***npm start***</code> para iniciar o projeto.  No arquivo  <code>***environment.ts***</code> existem duas configurações  <code>***(apiUrl e startWatching)***</code>, a <code>***apiUrl***</code> aponta para a url que está rodando a <code>***[API](https://github.com/fmatheus21/movies-api)***</code>  e a <code>***startWatching***</code> é o tempo em segundos que a aplicação irá expirar caso fique inativo. Configure o tempo que achar necessário para aplicação inativa expirar.

><code>***[Repositório UI](https://github.com/fmatheus21/movies-ui)***</code>

  </br></br></br></br>

# Como Usar <a id="como-usar"></a>

## API (back-end)<a id="como-usar-api"></a>
> Primeiramente, edite o arquivo <code>***application.properties***</code>  e altere as seguintes informações:  
> - spring.datasource.url=<code><***sua url do banco***></code>.
> - spring.datasource.username=<code><***seu usuário do banco***></code>.
> - spring.datasource.password=<code><***sua senhado banco***></code>.
> - server.port=<code><***port***></code> - Caso queira rodar em outra porta.
> - api.config.allow-origin=<code><***origem permitida***></code>. Url onde está rodando a aplicação Angular. 
> - server.port=<code><***port***></code> - Caso queira rodar em outra porta.

> Abra o projeto no Intellij (ou na sua IDE preferia), e execute-o utilizando o Maven.  
> Para testar os endpoints no  <code>***Postman***</code>, abra-o  e importe o arquivo  <code>***movies.postman_collection.json***</code> que se encontra em <code>***_files_***</code>.
> Gere o  <code>***Access Token***</code> e coleque-o no Bearer. Após, é só testar os endpoints.  
> A API está documentada com o <code>***Swagger***</code>, é só entrar no seu navegador e digitar  <code>***localhost:port/api/v1/swagger-ui.html***</code> e você verá todos os endpoints da API.

><code>***[Repositório API](https://github.com/fmatheus21/movies-api)***</code>

  </br></br>

## UI (front-end) <a id="como-usar-ui"></a>

> Abra o projeto no  <code>***VSCode***</code>  ou na IDE de sua preferência, abra o terminal e digite  <code>***npm start***</code>. Por padrão, a aplicação irá rodar  na porta 4200  <code>***(http://localhost:4200/)***</code>.

> ***Atenção:*** Se você alterar a porta padrão, será necessário alterar  a configuração na API informando a Origem Permitida no arquivo application.properties <code>***api.config.allow-origin=http://localhost:port/***</code>

><code>***[Repositório UI](https://github.com/fmatheus21/movies-ui)***</code>

</br></br></br></br>

# Tecnologias  <a id="tecnologias"></a>

![Java](https://img.shields.io/static/v1?label=Java&message=11&color=green)
![Spring Boot](https://img.shields.io/static/v1?label=spring-boot&message=2.5.8&color=green)
![oAuth](https://img.shields.io/static/v1?label=oauth&message=2.2.6&color=green)
![JWT](https://img.shields.io/static/v1?label=jwt&message=1.1.0&color=green)
![MySql](https://img.shields.io/static/v1?label=mysql&message=8&color=green)
![OpenAPI](https://img.shields.io/static/v1?label=openapi&message=1.6.3&color=green)
![Angular CLI](https://img.shields.io/static/v1?label=angular&message=12.2.16&color=green)
![Angular JWT](https://img.shields.io/static/v1?label=angular-jwt&message=4.0.3&color=green)
![Toasty](https://img.shields.io/static/v1?label=ng2-toasty&message=4.0.3&color=green)
![Node](https://img.shields.io/static/v1?label=node&message=14.15.0&color=green)
![NPM](https://img.shields.io/static/v1?label=npm&message=6.14.8&color=green)

</br></br>

<div id="demonstracao"></div>
  
# Demonstração
<pre>
<div>
<p align="center">
<a target="_blank" rel="noopener noreferrer" href="https://github.com/fmatheus21/movies-api/blob/main/_files/demonstracao.gif">
<img src="https://github.com/fmatheus21/movies-api/blob/main/_files/demonstracao.gif" alt="Logo" style="max-width:100%;" ></a>
</p>
</div>
</pre>





