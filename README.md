# webscrape-api
RPC API used as the API responsible for storing all data from the webscrapers.

# Technologies
 - Java 11
 - Spring
 - Spring MongoDB
 - Maven
 - Optional: Docker

# Getting started

Webscrape-API uses Spring MongoDB, which means you need to set up a MongoDB server. This you can do locally or you could download the [Official MongoDB Docker image](https://hub.docker.com/_/mongo), but you would first need to install [https://www.docker.com/](Docker) for that.

<p>Remember to go into application.properties folder and change the ip to the MongoDB server. It will be localhost if you're hosting it locally.</p>

If you require help on how to install MongoDB, do please refer to the [Official MongoDB documentation](https://docs.mongodb.com/manual/introduction).<br>

Following steps should be followed only IF you've got MongoDB server up and running
 1. Press the green "Code" button
 2. Either use the link with Git clone or download the zip
    - If you've downloaded the zip then extract it where you want to save the webscraper
    - If you've decided to use Git, then open your terminal, navigate to where you want to save it and then type; **git clone URL**
 3. Open your terminal if you have not already, navigate to where you've put webscrape-api
 4. Find and open the application.properties that is located in the folder /src/main/resources
 5. Change what you need here to be able to connect to your MongoDB server that you should have already set-up
 6. Go back to your terminal and type, **mvn spring-boot:run**
    - If you get a "no such command" type of response, then either type **mvnw spring-boot:run** or install [Maven](https://maven.apache.org/install.html)
 
 # Authors
  - Christoffer Hansen [Github](https://github.com/HansenChristoffer)
  - Mattias Andersen [Github](https://github.com/SiggeCinnamon)
