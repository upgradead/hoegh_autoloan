#INSTALLATION

github repository > https://github.com/upgradead/hoegh_autoloan
automation link > https://github.com/upgradead/hoegh_autoloan/runs/3546069295?check_suite_focus=true

Make sure maven and java 8 is installed 

Run "mvn clean install" on project directory
Target classes should be created since models are created via swagger plugin

Go to "src/main/java/auto/liners/hoegh/autoloan/AutoloanApplication"
Right Click > run main() 

Access Point
http://localhost:8080/loan

POSTMAN is also available with predefined requests 
located at src/main/resources/postman/Hoegh Autoliners.postman_collection.json

Class Mapping - > Class Mapping.png
Factory - Strategy Pattern

# Loan Calculator

Requests are for CAR and HOUSE loans
Car has 3% interest while House has 4% interest all configured in the application.yml configuration file

## autoloan-1.0.3
 - Fixed front end integration issues
 - Added security config for CORS error

## autoloan-1.0.2
 - Refactor LoanStrategy to be an interface instead of an abstract class
 
## autoloan-1.0.1
 - Refactor to use helper class instead of having builder inside abstract class

## autoloan-1.0.0
 - Creation of dynamic loan service (House/Car)
 - Unit tests creation
 - Postman Collection included
 - Automatic swagger generation
 