# event-log-parser
A Spring Boot application which takes in filepath(logfile.txt) from command line argument. Given logfile Json object will be parsed and further log events will be saved in HSQL file database with event duration timestamp and other details.

## Table of Contents

* [About the Project](#about-the-project)
   * [Built With](#built-with)
* [Getting Started](#getting-started)
   * [Installation](#installation)
* [Execution](#execution)
* [Contact](#contact)
* [References](#References)

<!-- ABOUT THE PROJECT -->
## About The Project
* Take the path to logfile.txt as an input argument.
* Parse the contents of logfile.txt
* Flag any long events that take longer than 4ms
* Write the found event details to file-based HSQLDB (http://hsqldb.org/) in the working folder
   * The application should create a new table if necessary and store the following values:
      * Event id
      * Event duration
      * Type and Host if applicable
      * Alert (true if the event took longer than 4ms, otherwise false)

### Built With
* Spring Boot(2.3.5)
* Java(1.8)
* Maven(3.6.3)
* HSQL Server(2.5.1)
* JDBC Template 
* Intellij Idea(11.0.8)

## Installation
1. Clone the repo
git clone https://github.com/jayeshdeo/event-log-parser or  download the zip directly.

2. Download HSQL database unzip the downloaded folder, to open the GUI for HSQL get into the folder hsqldb > bin > execute runManagerSwing.bat file. 

3. To run the HSQL database engine follow the below steps: 
    * From command prompt first go to the directory path till hsqldb\data 
    ~~~~
      Example: C:\Users\jayes\Downloads\hsqldb-2.5.1\hsqldb-2.5.1\hsqldb\data
    ~~~~
    * Now to start HSQL database engine use the command below
     ~~~~
     java -cp ../lib/hsqldb.jar org.hsqldb.server.Server --database.0 file:eventlog/log-event --dbname.0 log-event
     ~~~~
    
4. Inside resources folder the schema-all.sql file will help to create the table in the database automatically. 

5. For connecting to database using GUI :
     * Name of database : log-event 
     * username= sa , password=  

## Execution
* To run application from command prompt go to cloned/downloaded project directory and use the below command:
~~~~
>mvn spring-boot:run -Dspring-boot.run.arguments="<FILEPATH>"
~~~~
~~~~
Example: >mvn spring-boot:run -Dspring-boot.run.arguments="C:\Users\jayes\OneDrive\Documents\creditSuisse\logfile.txt"
~~~~
* Check the saved results in HSQL file database using the GUI.
~~~~
Select * from event;
~~~~

## Contact
email - jayeshdeogirikar@gmail.com

## References
* [Java Code Geeks](https://examples.javacodegeeks.com/enterprise-java/sql-enterprise-java/jdbc-hsqldb-tutorial/)
* [StackOverFlow For Errors](https://stackoverflow.com/questions/31870710/read-multiple-json-object-from-a-text-file)
