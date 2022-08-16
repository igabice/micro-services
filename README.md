 ### build service (wallet, Bet)
 - cd project_name
 - ./gradlew clean build
 this create an executable jar file under >build>libs folder of the project

 ### Run service
 - cd project_name
 - ./gradlew bootRun

### Test
 - Use the command to run test in project-directory:

    ./gradlew test

- To view test results in broswer:

   http://localhost:63343/demo/bets/build/reports/tests/test/index.html

   
  ### Docker Compose
  a docker-compose file is located at the root directory. 
    ### To start all services run
    - docker-compose up
    - wallet service can be accessed on: http://localhost:8091
    - bet service can be accessed on: http://localhost:8090
    ### Actuator: check if service is running
    - wallet service: http://localhost:8091/actuator
    - bet service: http://localhost:8090/actuator
    ### Swagger: API documentation
    - wallet service: http://localhost:8091/swagger-ui/index.html
    - bet service: http://localhost:8090/swagger-ui/index.html
    ### To stop all services run
    - docker-compose down
     ## To view logs of service(s)
   - docker-compose logs (service_name)
