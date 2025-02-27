#  <div align="center"> Relatórios JasperReports  </div>

<br/>

#  <div align="center"> 🚧 Concluído 🚧  </div>

## Conteúdos
* [Sobre](#sobre)
* [Pré-requisitos](#pré-requisitos)
* [Como usar](#como-usar)
* [Tecnologias](#tecnologias)

<br/>

## Sobre 
Este projeto é uma aplicação <code>***Java***</code> que tem como objetivo mostrar como gerar relatórios utilizando <code>***Jasper***</code>.

<br/>

## Pré-requisitos
- Mysql 8
- JDK 17
- IntelliJ
- Postman
- Jaspersoft Studio 6.21.2

<br/>

## Como Usar
Abra o projeto no Intellij e edite o arquivo <code>***application.yml***</code> informando as seguintes variáveis de ambiente:
- <code>***${DATASOURCE_USERNAME}***</code>Usuário do seu banco de dados
- <code>***${DATASOURCE_PASSWORD}***</code>Senha do seu banco de dados

Agora entre na classe <code>***JasperReportApplication***</code> e execute o método <code>***main***</code>.

No <code>***Postman***</code>, utilize o curl abaixo para gerar o relatório.
#### CURL
 ```
curl --location 'http://localhost:8083/api/v1/reports/clients?type=CLIENT_DETAILS'
 ```

<br/>

## Tecnologias

![Java](https://img.shields.io/static/v1?label=Java&message=17&color=green)
![Spring Boot](https://img.shields.io/static/v1?label=spring-boot&message=3.2.5&color=green)
![MySql](https://img.shields.io/static/v1?label=mysql&message=8&color=green)
![JasperReposrts](https://img.shields.io/static/v1?label=open-api&message=6.20.0&color=green)
![Lombok](https://img.shields.io/static/v1?label=lombok&message=1.18.4&color=green)
![Flyway](https://img.shields.io/static/v1?label=model-mapper&message=9.22.3&color=green)
![Lombok](https://img.shields.io/static/v1?label=commons-io&message=1.18.32&color=green)
![iText](https://img.shields.io/static/v1?label=itext&message=2.1.7&color=green)
![Fonts Poppins](https://img.shields.io/static/v1?label=fonts-poppins&message=1&color=green)
