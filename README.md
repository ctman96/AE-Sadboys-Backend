# Integrated Paper File Management System

### Documents
+ [Terms of Reference](https://docs.google.com/document/d/1k9Gmtb0L3zqTBaOnvB-eq8Obxz5VlAwzpe1LB1dyoBA/edit?usp=sharing)
+ [Project Plans](https://docs.google.com/document/d/1IKngV72_EYL6rAMY3DvrzpGVKLzlvRlxGoFmvasICm0/edit?usp=sharing)
+ [Requirements](https://docs.google.com/document/d/1Djg4L-YAQTuvPJzN_YTseXIbH7M3VkukYJa9BH9EeEs/edit?usp=sharing)
+ [Design](https://docs.google.com/document/d/14Zj8EaKOQhONG_4i_13lF357bj5a-IQ1PS2Hglv9g8I/edit?usp=sharing)

Ensure you have:
+ jdk 1.8
+ maven 3.5.0

Install dependencies: mvn clean install

Run: mvn spring-boot:run

###Configuration
Environmental Variables:
+ ENV - set as 'dev' or 'prod' to toggle certain functionality. At the moment, the only change is switching the IdentityFilter.java functionality. If not present, defaults to dev functionality
+ CORS - set as the address of the frontend, if hosted at a separate location, to allow cross origin resource sharing.

See application.properties in the resources folder for the rest of the configuration options.