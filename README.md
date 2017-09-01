#INTRODUCTION

This is the second attempt at the robot challenge, I am writing this version with two objectives in mind:

1. To fix up the problems noticed in the REA code review for my first attempt; and
2. To create a platform that can use generic chess language cooridiantes.


#CONSIDERATIONS

The feedback that I recieved from my original code review was:

* General - Good docs, knowledge of design patterns. But application of design leads to a poor outcome in terms of code readability

There does not need to be any extra work here.

* Appropriate documentation - Documentation on both design and how to run

No extra work needs to be done here

* Tests & TDD - Good unit test coverage (using a mockist approach), but no integration tests.

Integration tests are easy to add, so in this implementation I will add them.

* Application Design - In some ways it is unreasonably over engineered - e.g. it implements its own Spring-like system using JAXB for dependency injection, and is designed to run multi-threaded. However, this only serves to to obscure the implementation of the core business logic, which is not well factored out (for example, runAction for Place does part of the parsing, and the parsing concern is split over multiple places). Overall, the code is hard to follow.

There are two things that need to be done here. 
1. Ensure that parsing concerns etc are done in the one spot and not split out in multiple locations;

2. Divide the applicaiton into implementation and framework. 

* Knowledge of Language - Demonstrates technologies from Java ecosystem like JAXB and Checkstyle.

#DESIGN

## World Class

Responsible for controlling where each PhysicalObject is located on the world, which is a grid.

Throws a exception when a Object attempts to move to a location that is not within the boundries of
the world.

* setWidth(int)  : total horizontal units within grid.

* setHeight(int) : total vertical units within grid.

* placePhysicalObject(PhysicalObject, posX, posY) : Places a previously undefined Physical object at
coordinates X,Y.
** throws CoordinateOutOfBoundsException

* movePhysicalObject(PhysicalObject, posX, posY) : Moves a physicalObject that exists within 
the world from its current location to coordinates X,Y

* displayGraph() : returns a representation of the current position of all physical objects within
the world.

* removePhysicalObject(posX, posY) : removes the physical object that exists at coordinates
X and Y.

* hasObject(posX, posY) : returns true if a physical object exists at coordinates X and Y

* getObject(posX, posY) : returns the the physical object that exists at coordinates X and Y

## World Rule Set Class

Maintains control over where a phyiscal object can move. This object is produced by a abstract factory 
because different PhysicalObjects can have different rules.

* setWorld(World) : sets the world that they ruleset will use.

* performAction(PhysicalObject, PhysicalObjectAction)

throws CoordinatesOutOfBoundsException, InvalidMovementException, OccupiedByAnotherObjectException,
CurrentlyBlockedPathException

* getPhysicalObject(posX, posY)

throws PhysicalObjectNotFoundException

## Rule set Abstract Factory

Creates a rule set class based upon the requested action

## PhysicalObjectPerspective

The perspective of the physical object is where it facing (according to it) in terms of the world.
Each physical object has its own perspective.

* setCardinalDirection(String cardinalDirection)

* getCardinalDirection()

* rotateLeft(); Rotates one unit left

* rotateRight(): Rotates one unit right

## Physical Object

A object that occuplies space within the physical world.  For simplicities sake this object
will always occupy one unit of space.

* setPerspective(PhysicalObjectPerspective)

* getType()


## ActionReceiver (Action Service)

Implements the following workflow.

1. Receives a request to perform action;
2. Determines if that action can be performed by checking the RuleSet
3. executes action
4. Sets response with the result of the action


* setAction(Action)

* setResponse(Response)

* setPhysicalObject()

## Action

An action is one of the following:

### World Action

An action which determines which places or removes a physical object on the world.

### Object Perspective Action

Changes the perspective of the physical object, but has no affect on the world.

### PhysicalObject Action

Affects the physical objects perspective and its present within the world.

## Action Response

Delivers the result of the requested action.

## Client

Accepts user input and converts it into a request.

## Request

contains the request, 

* Response: getResponse()
* setResponse(Response response)
* setPhysicalObject(PhysicalObject physicalObject)
* PhysicalObject getPhysicalObject()
* getType(int id)
* setRequestParameters(Map<String, Object> parameters)
* Map<String, Object> getRequestParameters()

## Response

* setStatus(ResponseStatus status)
* ResponseStatus getStatus()
* setResponseParameters(Map<String Object>)
* Map<String, Object> getResponseParameters()

## ResponseStatus

* SUCCESS   : 0  - Request completed
* FAIL      : 1  - Unable to perform response at this point in time
* EXCEPTION : 2  - Invalid request

# Application Flow

## Place New Object

     User         Client      Service    RuleSet   World
       |  request   |           |         |          |
       |----------> |           |         |          |
       |            | action    |         |          |
       |            |---------> |         |          |
       |            | pobject   |         |          |
       |            |<----------|         |          |
       |            | action    |         |          |
       |            |---------> |         |          |
       |            |           | action  |          |
       |            |           | ------->|          |
       |            |           |         | action   |
       |            |           |         | -------> |
       |            |           |         | response |
       |            |           |         |<---------|
       |            |           | response|          |
       |            |           |<--------|          |
       |            |  response |         |          |
       |            |<----------|         |          |
       | response   |           |         |          |
       | <----------|           |         |          |

## Move Object

     User         Client      Service           RuleSet      World
       |  request   |           |                |             |
       |----------> |           |                |             |
       |            | action    |                |             |
       |            |---------> |    getObject() |             |
       |            |           |--------------->|             |
       |            |           |                | getObject() |
       |            |           |                |------------>|
       |            |           |                | pObject     |
       |            |           |                |<------------|
       |            |           |    pObject     |             |
       |            |           |<---------------|             |
       |            |           |  action        |             |
       |            |           |--------------->|             |
       |            |           |                | action      |
       |            |           |                | ----------->|
       |            |           |                | response    |
       |            |           |                |<------------|
       |            |           | response       |             |
       |            |           |<---------------|             |
       |            |response   |                |             |
       |            |<----------|                |             |       
       | response   |           |                |             |
       | <----------|           |                |             |
       
       
## Rotate Object

     User         Client      Service           RuleSet      World
       |  request   |           |                |             |
       |----------> |           |                |             |
       |            | action    |                |             |
       |            |---------> |    getObject() |             |
       |            |           |--------------->|             |
       |            |           |                | getObject() |
       |            |           |                |------------>|
       |            |           |                | pObject     |
       |            |           |                |<------------|
       |            |           |    pObject     |             |
       |            |           |<---------------|             |
       |            |           |                |             |
       |            |           |   action       |             |
       |            |           |------+         |             |
       |            |           |      |         |             |
       |            |           |<-----+         |             |
       |            |  response |                |             |
       |            |<--------  |                |             |
       | response   |           |                |             |
       | <----------|           |                |             |
       
       
## Remove Object

     User         Client      Service           RuleSet      World
       |  request   |           |                |             |
       |----------> |           |                |             |
       |            | action    |                |             |
       |            |---------> |    getObject() |             |
       |            |           |--------------->|             |
       |            |           |                | getObject() |
       |            |           |                |------------>|
       |            |           |                | pObject     |
       |            |           |                |<------------|
       |            |           |    pObject     |             |
       |            |           |<---------------|             |
       |            |           |  action        |             |
       |            |           |--------------->|             |
       |            |           |                | action      |
       |            |           |                | ----------->|
       |            |           |                | response    |
       |            |           |                |<------------|
       |            |           | response       |             |
       |            |           |<---------------|             |
       |            |response   |                |             |
       |            |<----------|                |             |       
       | response   |           |                |             |
       | <----------|           |                |             |
       
## DISPLAY WORLD

     User         Client      Service            RuleSet          World
       |  request   |           |                  |                |
       |----------> |           |                  |                |
       |            | action    |                  |                |
       |            |---------> |    displayGraph()|                |
       |            |           |----------------->|                |
       |            |           |                  | displayGraph() |
       |            |           |                  |--------------->|
       |            |           |                  | graph          |
       |            |           |                  |<---------------|
       |            |           |    graph         |                |
       |            |           |<-----------------|                |  
       |            | graph     |                  |                |
       |            |<----------|                  |                |
       |            |  response |                  |                |
       |            |<----------|                  |                |
       | graph      |           |                  |                |
       |<-----------|           |                  |                |
       | response   |           |                  |                |
       |<-----------|           |                  |                |