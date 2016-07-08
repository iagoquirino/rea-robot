Toy Robot Simulator Application
===============================

[![Build Status](https://travis-ci.org/iquirino88/rea-robot.svg?branch=master)](https://travis-ci.org/iquirino88/rea-robot)

It is an application that simulates the actions of a small robot , on a board , based on informed commands to execute.

**Execution Requirements:**

JDK 1.8 - http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html

Apache Maven 3.0.5+ - http://maven.apache.org/download.cgi

**The Board:**

The board is where robot gonna execute all commands, the default size of board are 5x5, but can be parameterized in application.

+   file: application.yml
    +   board.width = $YOUR_SIZE
    +   board.height = $YOUR_SIZE

**The Robot:**

It's a little robot that will walk on the board.

**The available commands are:**
+ MOVE - It makes robot move foward.
+ LEFT - It makes robot turn to Left.
+ RIGHT - It makes robot turn to right.
+ REPORT - It makes robot report current localization on board.

**The available directions are:**

+ NORTH - North direction
+ SOUTH - South direction
+ EAST - East direction
+ WEST - West direction

**Application**

To execute application yout must be in application path and execute `mvn spring-boot:run`.
Will be executed on **localhost** port **8080**.
The port is parameterized if you wanna change default port.
Just change on `application.yml`.
+ server.port: $YOUR_PORT

API Toys to play with Robot
-------------------

http://server:port/toys/robots?withHistory=true

**Parameters**

withHistory(boolean): Show the history of all path the robot walks on board.(optional)(defaul values: true or false)

**Send a body Json with:**

positionX(integer): Position X to set Robot on board. 
positionY(integer): Position Y to set Robot on board.
direction(string): Direction of robot, NORTH,SOUTH,EAST,WEST.
actions(list): Set Actions to robot do on board.(available actions on document.)

Other Endpoint To simulate play with robot.
--

http://server:port/toys//robots/{positionX},{positionY},{direction}/play

**Parameters**

withHistory(boolean): Show the history of all path the robot walks on board.(optional)(defaul values: true or false)

**Send a body Json with:**

actions(list): Set Actions to robot do on board.(available actions on document.)
+ Others attributes passed will be ignored.

Return Json
---------

Returns a json with information of robot location; 

+ result(Object): Contains the result information.
    + action: Last action of robot.
    + positionX: Position X of robot.
    + positionY: Position Y of robot.
    + direction: Direction of robot.
	
+ history(List): It contains the history of all the locations covered by the robot.
    + action: Action of robot.
	+ positionX: Position X of robot.
	+ positionY: Position Y of robot.
	+ direction: Direction of robot.
	
	
Example
---	

**POST** http://localhost:8080/rea-robot-app/toys/robots	

	{
	  "positionX":0,
	  "positionY":0,
	  "direction":"NORTH",
	  "actions":["MOVE"]
	}


**Response:**

	{
	    "result": {
	        "action": "MOVE",
	        "positionX": 0,
	        "positionY": 1,
	        "direction": "NORTH"
	    }
	}


**POST** http://localhost:8080/rea-robot-app/toys/robots?withHistory=true	

	{
	  "positionX":0,
	  "positionY":0,
	  "direction":"NORTH",
	  "actions":["MOVE"]
	}


**Response:**

	{
	    "result": {
	        "action": "MOVE",
	        "positionX": 0,
	        "positionY": 1,
	        "direction": "NORTH"
	    },
	    "history": [
	        {
	            "action": "PLACED",
	            "positionX": 0,
	            "positionY": 0,
	            "direction": "NORTH"
	        },
	        {
	            "action": "MOVE",
	            "positionX": 0,
	            "positionY": 1,
	            "direction": "NORTH"
	        }
	    ]
	}

See Ya! 
-----

https://github.com/iquirino88

Things to Improve
---

+ Implement HealthCheck.
+ Implement Identifier of Requisitions by Header.

