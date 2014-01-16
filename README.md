# ddrs-java-client

This project is a Java client library for the DigiData REST Service.  It is built on the [Spring RestTemplate][http://docs.spring.io/spring/docs/3.0.x/api/org/springframework/web/client/RestTemplate.html] and [Jackson JSON Processor][http://wiki.fasterxml.com/JacksonHome].  

The code here is comprised of the actual client (ddrs-client) and a consuming command line application with a set of simple, functional examples (ddrs-client-examples).

## Building the Client

The **ddrs-client** can easily be built with Maven:

```
 > mvn clean compile
```

This will compile the client and install it in your local Maven repository - necessary to run the examples.

```
 > mvn clean install
```

## Building the Examples

Before building the examples you should set a DDRS username/password in the Application.java.  You can create yourself an account on the DigiData demo site - [LeapDrive][http://www.leapdrive.com].

```java
	private static final String USERNAME = "<your username>";
	private static final String PASSWORD = "<your password>";
```

The easiest way to run **ddrs-client-examples** is to compile into a single .jar file:

```
 > mvn clean compile assembly:single 
```

## Running the Examples

The example application is then run from the command line:

```
  > java -jar target\ddrs-client-examples-1.0.0-SNAPSHOT-jar-with-dependencies.jar
```


## Additional Documentation

For additional information on the DigiData REST Service itself and details on how to use this and other clients please refer to the [DDRS Wiki][http://www.leapdrive.com/rest/doc].
