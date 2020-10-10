# webscrape-api
REST API ment to be the API responsible for storing all data from the webscrapers.

# Technologies
 - Java 15
 - Spring
 - Spring HATEOAS
 - Spring MongoDB
 - Maven
 - Optional: Docker

# Getting started

Webscrape-API uses Spring MongoDB, which means you need to set up a MongoDB server. This you can do locally or you could download the [Official MongoDB Docker image](https://hub.docker.com/_/mongo), but you would first need to install [https://www.docker.com/](Docker) for that.
<p>Further down the line this API will be hosted alongside the database on a docker, at least that is the plan.
Just remember to go into application.properties folder and change the ip to the MongoDB. It will be localhost if you're hosting it locally.</p>
If you require help on how to install MongoDB, do please refer to the [Official MongoDB documentation](https://docs.mongodb.com/manual/introduction).<br>

# How to start
 1. Press the green "Code" button
 2. Either use the link with Git clone or download the zip
    - If you've downloaded the zip then extract it where you want to save the webscraper
    - If you've decided to use Git, then open your terminal, navigate to where you want to save it and then type; **git clone URL**
 3. Open your terminal if you have not already, navigate to where you've put webscrape-api
 4. Type, **mvn spring-boot:run**
    - If you get a "no such command" type of response, then either type **mvnw spring-boot:run** or install [Maven](https://maven.apache.org/install.html)
 
 # Authors
  - Christoffer Hansen [Github](https://github.com/HansenChristoffer)
