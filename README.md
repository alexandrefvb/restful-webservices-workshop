#Workshop RESTful Webservices - TQI 2014

Este repositório contém o material e código fonte apresentado no workshop realizado nos dias 30 e 31/07/2014 na TQI.

O workshop é dividido em duas partes sendo a primeira teórica e a segunda prática.

A primeira parte é destinada a tentar explicar o que é REST.

A segunda parte apresenta a implementação de serviços REST para realização de enquetes.

Descrição do conteúdo:

apresentacao - Apresentações criadas para o workshop. As apresentações estão no formato ODP e podem ser visualizadas com o LibreOffice. Há também uma versão em pdf para visualização.

design - Contém a modelagem do exemplo. O arquivo enquete.eap contém a modelagem feita no Entreprise Architect e pode ser aberto para visualização com o EALite. Para comodidade os diagramas foram exportados para png e incluídos juntamente com o eap.

source - Implementação do exemplo de enquetes em JAVA utilizando tanto JAX-RS quanto SpringMVC.

##Rodando os projetos

Para rodar os projetos é necessário:
* JDK 1.7
* Maven 3.0+

Antes de rodar os exemplos instale o módulo enquete-model rodando:
```mvn clean install``` em source/enquete-model.

Para rodar o exemplo JAX-RS:
```mvn jetty:run``` em source/enquete-jax-rs.

Para rodar o exemplo Spring MVC:
```mvn jetty:run``` em source/enquete-spring-mvc.
