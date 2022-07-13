# Learning Spring With Spring Boot

[URL](https://www.linkedin.com/learning/learning-spring-with-spring-boot-13886371/introduction-to-spring?autoplay=true&u=125254026)

Framework is built for providing comprehensive infrastructural support for developing Java Applications.

Spring is built in OOP in mind. Things like coding to an interface instead of an implementation are **paramount** in Spring.

It also promotes DRY principles (Don't Repeat Yourself).

`POJO` - Plain Old Java Object. In Spring this any class file that contains both attributes and methods, and those methods are not just getters and setters.

`Java Beans` - Simple objects with only getters and setters (same as pojo).

`Spring Beans` - POJO's that are configured and managed by the Application Context. Tl;dr anything that Spring manages during the lifecycle of the application.

`DTO` - Data Transfer Object. Java beans with the specific purpose of moving state between logical layers of the application.

### **IOC**

If you understand how Spring uses IOC, you understand how Spring works fully.

- IOC provides mechanism of dependency injection.
- Application Context wraps the Bean Factory which serves the beans at the runtime fo the application.
- Spring Boot provides auto-configuration of the Application Context.

As Developers we don't interact with the IOC Container, we interact with the Wrapper, or the Application Context.

_"Why Spring Boot?"_

- Supports Rapid Development
- Removes boilerplate of application setup
- Many types of applications, not just web services
- Cloud Native support but also traditional

If it can be run on the JVM, Spring Boot will most likely save you time.

_Key Aspects_

- Embedded Tomcat (or others) for Application Server Support
- Auto-configuration of Application Context
- Automatic Servlet Mappings (embeded application servers)
- Database support and Hibernate / JPA dialect'
- Automatic Controller Mappings

**Auto Config**

Spring Boot provides a default and opinionated configuration of common constructs. You often don't need to change these, but they are easy to override.

### Creating a project with Spring Initializer

`start.spring.io` - default way of doing this, but you can use an IDE plugin.

You can run `mvn package` to build the package and `java -jar target/{directory}.jar` to run the project locally.

`application.properties` is used for properties files that control the configuration of the Application Context.

`pom.xml` contains information regarding the dependencies and project information.

`static` can be used to host static files like HTML.

### Inversion of Control

In IOC the container itself maintains your class dependencies for the entire lifecycle of those dependencies.

From the IOC container, objects can be injected as dependencies into other classes at either startup time or runtime depending on how the dependencies are defined in the class and how the container manages them. By default, the dependencies are injected at startup of the application as they are added to the Bean Factory.

Objects don't create dependent objects in their constructors or methods, instead the container manages them and injects them as needed.

IOC Container effectively acts as a source of truth, and the classes become pointers to the IOC, which handles the injection of those classes.

`Bean Factory` is the IOC Container. You typically will not change the behavior of this.
`Application Context` is what you interact with, this is a wrapper around the Bean Factory, and they share common interfaces.

Bean Factory maintains references to the Spring Beans that you configure, or that Spring Boot autoconfigures for you. References are not added to the Bean Factory, they are configured and Spring handles object initialization as well as the instantiation of those objects.

Once Beans have been initialized, an order of operations for construction is created based on the dependency. The beans are then instantiated in proper order by the Bean Factory. If you ever create a circular dependency where two beans rely on each other, you will receive a warning of such a circular dependency when starting or compiling application. Spring handles these from start-up to shut down.

### Annotations

_"What are Annotations?"_

- Core component of the Java language, not Spring specific
- Define metadata for code
- Metadata is often used for compiler or runtime instructions (Example - @Test)
- Great leverage point for pointcuts in the code

_Proxies_

- Beans in the Bean Factory are proxies. This is where the added behavior is providing much of the plumbing of Spring itself.
- Annotations drive proxies
- Annotations are easy extension points, for your own abstracts too
- Method calling order matters

Proxied classes have to be called through the proxy itself for the behavior to be added. Important for private method calls.

### Spring Data

_"What is Spring Data?"_

- Provides a common set of interfaces based on JPA
- Provides a common naming convention
- Provides aspected behavior
- Provides Repository and Data Mapping convention

Spring Data removes a lot of the boilerplate code compared to JDBC. It also allows for swapping data sources easier. This allows you to focus on the business logic.

_Key Components_
- Repository Interface
- Entity Object
- DataSource

### Remote Databases

Note - Constructed via Docker and Postgres

```properties
spring.jpa.database=postgresql
spring.datasource.url=jdbc:postgresql://localhost:5432/dev
spring.datasource.username=postgres
spring.datasource.password=postgres
```
