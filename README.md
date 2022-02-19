<h1 align="center">Cadastro de Filmes</h1>

</br></br>

Conteúdos
=================
<!--ts-->
   * [Sobre](#sobre)
   * [Features](#features)
   * [Pré-requisitos](#pre-requisitos)
   * [Instalação](#instalacao)     
   * [Como usar](#como-usar)  
       * [API](#api)     
       * [UI](#ui) 
   * [Tecnologias](#tecnologias)  
<!--te-->

</br></br></br></br>

<div id="sobre"></div>

# Sobre
<div>
<p align="left">Este é um pequeno CRUD de filmes utilizando Spring Boot com autenticação oAuth2, JWT e Angular</p>
    <p align="left">O cadastro e atualização de filmes está implementado com upload de arquivos, utilizando Thumbnails do pacote <b>net.coobird</b></p>    
<p align="left">Esse projeto está em construção e periodicamente será atualizado.</p>
<p align="left">O front-end ainda não foi iniciado. Assim que tiver início, disponibilizarei o repositório do projeto.</p>
</div>


</br></br></br></br>

<h4 align="center"> 
🚧  Em construção...  🚧
</h4>

</br></br></br></br>


<div id="features"></div>

# Features

## Back-end
- [x] Access Token
- [x] Refresh Token
- [x] Logout
- [x] Cadastrar Filme
- [x] Consultar Filme
- [ ] Atualizar Filme
- [x] Listar Filme
- [ ] Excluir Filme

## Front-end
- [ ] Login
- [ ] Logout
- [ ] Cadastrar Filme
- [ ] Consultar Filme
- [ ] Atualizar Filme
- [ ] Listar Filme
- [ ] Excluir Filme

</br></br></br></br>


<div id="pre-requisitos"></div>

# Pré-requisitos
<p align="left">
  <ul>
    <li>Mysql 8</li>
    <li>JDK 11+</li>
    <li>Postman 7.36.1</li>
    <li>Node 4.15.1</li>
    <li>NPM 6.14.8</li>
    <li>Angular CLI 9.0.7</li>
    </ul>
  </p>


</br></br></br></br>

<div id="instalacao"></div>

# Instalação
<div>
<p align="left">Importe para o seu Mysql o arquivo <b>database.sql</b>, que se encontra em _files. Na pasta _files encontra-se também o arquivo <b>movies.postman_collection.json</b> para que você importe para o seu postman. Esse arquivo contém os endpoints da aplicação. A API está documentada com Swagger e você pode acessar pela url http://localhost:port/api/v1/swagger-ui.html</p>
</div>


</br></br></br></br>

<div id="como-usar"></div>

# Como Usar

<div id="api"></div>

## API (back-end)
<div>
<p align="left">Primeiramente, edite o arquivo <b>/api/src/main/resources/application.yml</b> e altere as seguintes informações:</p>  
  <ul>
  <li><b>server.port=</b>PORT - Porta do Mysql</li>
  <li><b>spring.datasource.username=</b>USER_NAME - Usuário do Mysql</li>
  <li><b>spring.datasource.password=</b>PASSWORD - Senha do Mysql</li>
  <li><b>api.allow.origin=</b>http://localhost:4200 - Essa é a url padrão que roda a apicação Angular. Se você rodar o front-end em outra porta, essa linha também deverá ser alterada.</li>
  <li><b>api.domain=</b>http://localhost:8083 - Se a porta da API for alterada, altere também esta linha</li>
  </ul>  
 </br>
 <p align="left">Abra o projeto na sua IDE (originalmente desenvolvida no Intellij) e execute. Abra o Postman e importe o arquivo <b>postman_collection.json</b> e faça os testes.</p>  
</div>

</br>


<div id="ui"></div>

## UI (front-end)

[Repositório do front-end](https://github.com/fmatheus21/movies-ui) 
<div>   
<p align="left">  
  </p>  
<div>

</div>

</br></br>

<div id="tecnologias"><div>

# Tecnologias 
<div>
<img src="https://img.shields.io/static/v1?label=Java&message=12&color=green"/>
<img src="https://img.shields.io/static/v1?label=spring-boot&message=2.5.8&color=green"/>
<img src="https://img.shields.io/static/v1?label=oauth&message=2.2.6&color=green"/>
<img src="https://img.shields.io/static/v1?label=jwt&message=1.1.0&color=green"/>
<img src="https://img.shields.io/static/v1?label=mysql&message=8&color=green"/>
<img src="https://img.shields.io/static/v1?label=openapi&message=1.6.3&color=green"/>
<img src="https://img.shields.io/static/v1?label=angular&message=10&color=green"/>

<img src="https://img.shields.io/static/v1?label=angular-jwt&message=4.0.3&color=green"/>
<img src="https://img.shields.io/static/v1?label=ng2-toasty&message=4.0.3&color=green"/>
<img src="https://img.shields.io/static/v1?label=rxjs&message=6.5.4&color=green"/>
<img src="https://img.shields.io/static/v1?label=rxjs-compat&message=6.6.3&color=green"/>
<img src="https://img.shields.io/static/v1?label=bootstrap&message=4.5.3&color=green"/>

<img src="https://img.shields.io/static/v1?label=metronic&message=7.0.8&color=green"/>

</div>

