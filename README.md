# GC Cake Manager
Sample cake project

## Original Project
Checked the original cake manager project running, mainly to see the expected output in operation.
To get it working I added servlet config to the web.xml, and forced the jetty plugin version down to 10 - but didn't dwell on getting it running with latest version & annotation as decided the spring boot version would be a new project, rather than a rework of this one.
Only code reused from the original project was retrieving the external list of cakes (although still changed the json parsing to just use object mapper).
Would have also considered keeping the entity class, but appeared to be for a different project (employees rather than cakes).

## New Project
- Used Spring Initializr to create the project
- Added Swagger to document the endpoints and for checking behaviour without rest client

## Building the project
- can use standard maven command (mvn clean install) at the root of the project to build

## Running the project
- can run the CakesApplication class within an IDE, or once built run the jar file directly through command line e.g. java -jar target/cakes-0.0.1.jar
- runs on port 8282 as per the original project, this can be changed either through application.properties or command line argument
- once started, can check the service is up and running through the actuator health endpoint http://localhost:8282/actuator/health
- can view the api documentation through swagger http://localhost:8282/swagger-ui.html

## Changes from original requirements
- The original requirement asked to list the cakes through "the root of the server (/)". I thought the api was cleaner when everything beneath "/cakes" instead, but could change this back if needed.
- I have not added a UI (not sure if this was the intention?), so the requirement "It must be possible for a human to add a new cake to the server" is only being met if the human can operate swagger or a rest client

## Quick list of operations
- for a list of cakes (just the titles) in the system, http://localhost:8282/cakes/list
- for a json list with full details of all the cakes in the system http://localhost:8282/cakes/
- to add a new cake to the system, Post request (with cake model) to http://localhost:8282/cakes/ (see http://localhost:8282/swagger-ui.html#/cake-controller/addCakeUsingPOST)
- to find an existing cake by title, use localhost:8282/cakes/findCake, e.g. http://localhost:8282/cakes/findCake?title=Banana cake

## Additional
- a simple Cucumber test run (using SpringBootTest) has also been added, and will run automatically as part of maven build
- can also run the Cucumber tests through IDE by running CucumberRunnerIntegrationTest class (or run with IntelliJ plugin on the feature file)
