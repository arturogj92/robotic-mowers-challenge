# ROBOTIC MOWER CHALLENGE FOR SEAT:CODE

## Introduction

In this readme.md you will find information about:
* Little code explanation and architecture used 
* How to run the project

## Clarifications

In my opinion the code used for solving the challenge is a bit over-engineered, because the domain problem is not big and complex.
The goals of that challenge is to show the programming skill, that's why I've decided to employ patterns, hexagonal architecture and functional approach for object validation even though it is not necessary in these small problems.

## Code explanation

The application has been built using **Domain Driven Design (tactical patterns)** and **hexagonal architecture (ports and adapters)**
It also contains **functional programming** in some parts of the code and a kotlin library called **[arrow](https://arrow-kt.io/)** which contains implementations for well known functional programming functions and types, for instance: _Either, Validated_, and functions like _map, flatMap, traverse or zip._
The core parts of the application were also built using TDD.

### Architecture

The architecture used is **Hexagonal Architecture**, also known as Onion Architecture or Clean Architecture
I've created a graphic which describes the architecture in a high level perspective.

![Diagrama de la arquitectura empleada](https://i.imgur.com/cwEkdBL.png)

Hexagonal architecture brings to your application a clean layer separation, that layers are:

* Domain
* Application
* Infrastructure

### Domain Layer

In the domain layer lies the business logic. All the objects inside the domain application must be in a valid state to keep your domain always valid.
Those domain objects brings you function for creating this objects in a safe way using monads (**[also known as parsing](https://lexi-lambda.github.io/blog/2019/11/05/parse-don-t-validate/)**).
Those monads represent an object with two states, for example: ```Either<Error, Mower>``` means that your object can contain an error or a valid Mower, but not both.

For kotlin language reasons, it's not possible to create a private constructor and let the developer to just use the safe object creation way (parsing), because the kotlin's ```copy``` function  will be always available, so the object needs to check before construction if is valid (in the init method), in that case an exception will be thrown.

In this layer, also lives the domain ports, this ports just understand domain language and will be implemented(adapted) in the infrastructure layer. 

The domain layer is the deeper layer of Hexagonal architecture, domain objects must just call other domain objects or domain services, the ports allow communication outside domain layer but keeping the layer safe.

Some domain objects used could be the following:

```Mower```, the **Aggregate**.
Some **Value Objects** like ```Location, Coord, Direction```

### Application Layer

The application layers contains the use cases of the application, for instance, **send the mowers start working** using the application service ```MowerActivator```

The application services will orchestrate the domain objects and communicate if necessary with some ports and are also responsible for sending **Domain Events**

### Infrastructure layer

Is the outside layer. This layer will be the responsible for receive input to your application, that data can be received in manner ways like

* HTTP
* CLI

And many others.

The infrastructure layer also contains the implementations of the domain ports, that are usually known as **adapters**

In this challenge, the mower's input data will be received in the class ```SeatMaintenanceOfficeInputReceiver```

That receiver will use a port called ```InputParser``` which have an implementation for parsing SeatMaintenanceOffice inputs. If in the future is required to understand another type of input, we just need to create a different implementation of the parser.

### Little code flow explanation

First, the infrastructure receiver ```SeatMaintenanceOfficeInputReceiver``` read the input and parse it using the ```SeatMaintenanceOfficeInputParser``` and creates valid mowers. The controller delegates to the application service which puts the mower to work, when the mower finish, a domain event is raised. That event is listened and handled, in my handling implementation, the final position of the mower will be printed.

The way of receiving the input can be done in several ways, for example:
* Rest controller
* CLI
* Reading files
* Many others

In this case I decided to read a "hardcoded" string with the input but it would be very easy to use other mentioned ways because just some adapter must be implemented and the domain layer will keep intact avoiding possible bugs

## How to run the code

The code contains a broad suite of test. There are some tests who execute the Input and Output provided in the instructions in Domain language, but you can also run the application and the terminal will log the output results

If you want to change the input, it can be done changing the ```exampleInput``` variable inside ```SeatMaintenanceOfficeInputReceiver``` class.
The calculations will be executed when the applications start.

I decided to do that for simplifying the solution, but it would be easy to create a different input receiver such as a ```@RestController``` with an endpoint for receive the desired input. 

Steps to execute.

**Pre-requisites**
* Maven
* Kotlin plugin
* JDK 8 or greater

#### CLI way
1. Clone the repository
2. maven package
3. Go to the .jar folder and run ```java -jar robotic-mower-challenge-0.0.1-SNAPSHOT.jar```

In the logs you will see the mower output

#### Using intellij
1. Clone the repository
2. Import project in intelliJ
3. Add pom.xml as maven project
4. Run ```RoboticMowerChallengeApplication```

The suite test can be executed using ```mvn test``` or inside intelliJ
