# Discounter

## An implementation of the application included in the "Sample Code" section of "Hexagonal Architecture" pattern.

### By: Juan Manuel Garrido de Paz

__Wednesday, September 7, 2022__

![Discounter Application](discounter.png)

### Description:

This is a version of the sample code application included in the original article defining Hexagonal Architecture pattern.

The application has a driver port with the provided interface "fordiscounting", which calculates the discount to substract from a given amount of money (we will assume euros as currency). Two drivers will be implemented for this port:

- Test cases.
- A console (command line interface) for the human user.

The discount is calculated applying a rate to the amount, according to this formula:

discount(amount) = amount * rate(amount)

The rate to apply depends on the amount (a different rate value for each amount interval). For example:

| Min Amount | Max Amount | Rate |
|-----------:|-----------:|-----:|
|       0.01 |      20.00 | 0.00 |
|      20.01 |     100.00 | 0.15 |
|     100.01 |          - | 0.35 |

The application has a driven port with the required interface "for obtaining rates", which returns the rate to apply given an amount. Two adapters for this port will be implemented:

- A test double (stub) that returns pre-configured values for the rates.
- A real adapter that gets the rate values from a file.

### Packaging:

The source code is split into 3 packages:

- Application.  
This is "the hexagon". It includes the ports as Java interfaces, which are the API of the application. The implementation details should be hidden.
- Outside.  
Here is where adapters and actors are located. They are grouped by port.
- Startup.  
This is the entry point to run the application. Here we have the configurator for wiring the different parts of the application up. It uses Spring Boot framework.

Dependencies should be:

- "Startup" depends on both "Outside" and "Application".
- "Outside" depends on "Application".
- "Application" depends on nothing.

_NOTE: In Java, dependencies between packages can be defined by using "ArchUnit" tool; or with a Maven multi-module project, defining a module for each package._

### Development sequence:

| Step # | Driver                       | Rate repository    |
|:------:|:-----------------------------|:-------------------|
|   1    | Test cases                   | Test double (stub) |
|   2    | CLI (Command Line Interface) | Test double (stub) |
|   3    | CLI (Command Line Interface) | File               |

### Programming Environment:

- Java 1.8.0_311
- Spring Boot 2.6.6
- Maven 3.8.6
- IDE: IntelliJ IDEA 2021.3.3 (Community Edition)
- Ubuntu 20.04.4 LTS

### Compiling and Running the application:

1. Download the GitHub repository https://github.com/jmgarridopaz/discounter
2. Go to the directory where you downloaded it
3. Run maven to compile and create the executable "jar" file:


    mvn clean verify

4. Run the generated "jar" file:


    java -jar ./target/discounter-1.0.0.jar

### Swapping the adapters:

There are 2 ways for choosing the adapter to use on every port.

(1) Editing the "application.properties" file (it requires recompiling the source code)

    # Port: fordiscounting
    # Available drivers: testcases , cli
    fordiscounting = testcases
    
    # Port: forobtainingrates
    # Available adapters: testdouble , file
    forobtainingrates = testdouble

(2) Passing args on the command line when launching the application (no need to recompile the source code)

    java -jar ./target/discounter-1.0.0.jar --fordiscounting=cli

If a property is not present at the command line, its value is got from the "application.properties" file.
