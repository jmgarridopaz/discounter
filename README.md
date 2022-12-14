# Discounter

## An implementation of the application included in the "Sample Code" section of "Hexagonal Architecture" pattern.

### By: Juan Manuel Garrido de Paz

___Wednesday, September 7, 2022___

![Discounter Application](discounter.png)

### Description:

This is a version of the sample code application included in the original article defining Hexagonal Architecture pattern.

The application has a ___driving port with the provided interface "for discounting"___, which calculates the discount to substract from a given amount of money (we will assume euros as currency). Two drivers will be implemented for this port:

- Test cases.
- A CLI (Command Line Interface) for the human user.

The discount is calculated applying a rate to the amount, according to this formula:

    discount(amount) = amount * rate(amount)

The rate to apply depends on the amount, as explained below:

- The whole range of possible amount values is split into non overlapping, consecutive intervals. A concrete rate value is associated to each interval.
- The rate to be applied to an amount will be the rate value associated to the interval where the amount belongs to.
- Such intervals are created by defining the so-called _Breaking Points_. A breaking point is a concrete amount value, which is the edge between two consecutive intervals. It is the point where the rate value changes.
- So, intervals and their rates can be defined in a summarized way, with a table where each row is a _(breaking point, rate value)_ pair, ordered by increasing break point value.
- For example, the following rate table:

| Break Point (€) | Rate (%) |
|----------------:|---------:|
|               0 |        0 |
|             100 |        5 |
|            1000 |        7 |
|           10000 |       10 |

Means that:
- For any amount in between 0€ and 99.99€ (including both), the rate will be 0%
- For any amount in between 100€ and 999.99€ (including both), the rate will be 5%
- For any amount in between 1,000€ and 9,999.99€ (including both), the rate will be 7%
- For any amount greater than or equal to 10,000€ the rate will be 10%

The application knows "break point" and "rate" concepts. And it also knows the logic to apply for calculating the rate for an amount, given a table with break points and rates. What the application doesn't know (and doesn't have to) is where does that table come from (a file, a database, a remote service, an in-memory data structure, ...)

The application has a ___driven port with the required interface "for obtaining rates"___, which returns an _iterator_ to get the table rows one by one, starting from the last one. Two adapters for this port will be implemented:

- A test double (stub) that returns an iterator for an in-memory rate table with hardcoded values.
- A real adapter that gets an iterator for a rate table in a file.

### Packaging:

The source code is split into 3 packages:

- Application.  
This is "the hexagon", the business logic. It includes the ports as Java interfaces, which are the API of the application. The implementation details should be hidden.
- Outside.  
Here is where adapters and actors are located. They are grouped by port.
- Startup.  
This is the entry point to run the application. Here we have the configurator for wiring the different parts of the application up. It uses Spring Boot framework.

Dependencies are:

- "Startup" depends on both "Outside" and "Application".
- "Outside" depends on "Application".
- "Application" depends on nothing.

_NOTE ABOUT THE CONFIGURATOR: I've implemented it in a separate project ("startup"), using the Spring framework. However, for "test cases" I follow an easier approach, which allows them to act as the configurator, building directly the driven actors and the app. I do this because test cases need to do this configuration on every test, as a first step for setting up the test fixture._

_NOTE ABOUT PACKAGING & DEPENDENCIES: I've implemented the whole project as a Maven multi-module project, defining a Maven module for each of the three main packages. This way, dependencies can be defined between modules. Another approach might be to keep all packages in just one Maven module, and use the "ArchUnit" tool to define dependencies between packages._

### Development sequence:

| Step # |            Driving            |       Driven        |
|:------:|:-----------------------------:|:-------------------:|
|   1    |          Test cases           |        None         |
|   2    |          Test cases           | Test double (stub)  |
|   3    | CLI (Command Line Interface)  | Test double (stub)  |
|   4    | CLI (Command Line Interface)  |        File         |

### Programming Environment:

- Java 1.8
- Spring Boot 2.7.6
- Maven 3.8.6
- IDE: IntelliJ IDEA 2021.3.3 (Community Edition)
- Ubuntu 20.04.5 LTS

### Compiling and Running the application:

1. Download the GitHub repository https://github.com/jmgarridopaz/discounter
2. Go to the directory where you downloaded it
3. Run maven to compile, package and install the artifacts into your local Maven repository:


    mvn clean install -U

4. Run the generated "jar" file, with two arguments:
- The combination of adapters for running the app: (by default, "test")


    --config.option = [ test | cli-none | cli-test | cli-file ]

- When "config.option = cli-file", this argument is the name of the text file with the rate table: (by default, "rates.txt" in the current directory)


    --rates.file.name = /path/to/a/text/file

For example, for running the "cli" driving adapter and the test double as the driven adapter:


    java -jar ./discounter-startup/target/discounter-startup-1.0.0.jar --config.option=cli-test

You can also set the value of these args editing the "application.properties" file (it requires recompiling the source code):


    #############################################################
    #              CONFIGURATION OPTIONS                        #
    #############################################################
    # FOR_DISCOUNTING       FOR_OBTAINING_RATES     CONFIG.OPTION
    #       test            none + test             test
    #       cli             none                    cli-none
    #       cli             test                    cli-test
    #       cli             file                    cli-file
    config.option = test
    
    
    #############################################################
    #          NAME OF THE FILE WITH THE RATE TABLE             #
    #############################################################
    rates.file.name = rates.txt

If an argument is not present at the command line, its value is got from the "application.properties" file.
