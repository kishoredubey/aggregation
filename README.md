# Aggregation Service
# Swagger
- Can be accessed at http://localhost:8080/swagger-ui/index.html when running the application in local.

![ServiceSlow](/ServiceFlow.png)

- AggregateService `asynchronously` submits the tasks for each data point to
    - ShipmentTask
    - PricingTask
    - TrackingTask
- BackendClient is responsible for network call
- Timeout is configured in ![Config](/aggregation-service/src/main/java/org/aggregation/Aggregation.java) and is defined
  in ![Resources](/aggregation-service/src/main/resources/application.yml)

# Future scope

- Globalize executor-service, and define fine-grain access control, such as:
    - thread pool configuration
    - DNS, Connect timeout
- Improve on exception handling & logging - according to business & operations use case.
- 