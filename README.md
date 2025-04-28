# JSP WIPO CRAWLER APP

Sistema para cadastro e busca de patentes no site da WIPO.

## Tecnologias
- Java 21
- Spring Boot 3.4.5
- MySQL 8
- Hibernate / JPA
- JSP (MVC)
- jQuery + Ajax
- Bootstrap 5.3
- Lombok
- JUnit5
- Selenium

## Como rodar

1. Suba um banco MySQL:
   - Banco: `patent_crawler`
   - Usuário: `seu nome de usuario`
   - Senha: `sua senha`
2. Clone o projeto
3. Compile e rode o `PatentCrawlerApp.java`
4. Acesse:
   - Crawler & Cadastro: [http://localhost:8080/register]
   - Pesquisa no BD: [http://localhost:8080/search]

## Endpoints REST

- `GET /fetch?applicationNumber=WO2002008676` — Busca dados no WIPO ( apenas registros iniciados com 'WO' )
- `POST /save` — Salva dados no banco
- `GET /find?applicationNumber=&applicant=` — Pesquisa dados ( ex de application number: WO/2002/008676,  ex de applicant: KRANZ )

## Testes

- Execute os testes em `PatentServiceConnectivityTest.java`
- Execute os testes em `PatentServiceTest.java`

## Nota sobre uso da classe HttpGet do Apache para coletar o html

Análise:

A extensão do site é JSF (.jsf), o que aponta para o uso de JavaServer Faces.

Não tem nenhuma requisição "fácil" tipo JSON retornando os dados direto.

O HTML completo já vem (ou não) no momento da resposta.

A requisição HttpGet pega apenas o HTML inicial.

Eu até poderia forçar uma série de requisições em série, com alguns intervalos 
num tipo de tentativa e erro até conseguir pegar os dados necessários, mas 
não poderia garantir o sucesso dessa operação.

## Nota sobre uso da biblioteca Selenium para coletar o html

Eu entendo que o Selenium é diferente de uma Http Request padrão, já que ele
carrega todo o site, incluindo imagens, JS, CSS, e executa os scripts.

No entanto após considerar o conteúdo a ser carregado julguei viável o uso de
WebDriverWait combinado com a espera pelo carregamento dos elementos SPAN que
continham os dados a serem extraídos. 

No fim, acreditei ser melhor fazer desse modo do que não fazer.
