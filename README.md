# Online Merchant Application
This is an online merchant application which handles transaction requests e.g. creating, reading, updating and deleting.

## Running
### Prerequisites
- Java 17
- Maven

### Execution
You can run the code in this repository by executing `src/main/java/com/online/merchant/Application.java`.

### Swagger

URL: http://localhost:8080/api/swagger-ui/index.html#/ - Once clicked, it will prompt you for the username and password 
to be able to interact with the underlying Swagger UI/service. Further information can be found on the authentication section.

### Health Check

URL: http://localhost:8080/api/actuator/health - This will return the status of the application, for example:
`{"status":"UP"} | {"status":"DOWN"}` where UP signifies that the system is currently running
and DOWN represents that the service is currently unavailable.

### Authentication

To be able to interact with the service/Swagger UI, username and password is
required. This can be found in the `src/main/resources/application.properties` with the prefix `authentication.<name>`.
