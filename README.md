# How To Not Fail University

How To Not Fail University is an arcade-style game based on a university setting. The game revolves around the main playable character, who is a university student trying to complete their computer science degree. The objective of the game is to graduate university by finishing all their course requirements without getting caught by the “evil” professors.

## Getting Started 

### Prerequisites

The user needs to have atleast Java SE development kit 8 or the higher version of the same installed on the local machine.
Follow this link to install Java JDK 8: 
    https://www.oracle.com/in/java/technologies/javase/javase-jdk8-downloads.html

Since the game is built using maven, the user need to have maven installed on the local machine. 
Follow this link to install maven to your system : 
    https://maven.apache.org/download.cgi

### Building the game

Follow these steps to build the game using maven:

1. Clone the repository to local machine
    Clone using SSH : git@csil-git1.cs.surrey.sfu.ca:cmpt276s21_group8/project.git
    Clone using HTTPS : https://csil-git1.cs.surrey.sfu.ca/cmpt276s21_group8/project.git

2. Open the shell of your choice (eg. Command Prompt(Windows users) or Terminal(Mac or Linux users))
3. Navigate to the project directory
4. Give the command to build and test the project: 
    mvn package

### Running the game

First the user needs to make sure the game is successfully built on the local machine.
Follow these steps to run the game: 

1. Open the shell of your choice (eg. Command Prompt(Windows users) or Terminal(macOS or Linux users))
2. Navigate to the project directory
3. Give the command to run the game: 
    For Windows
        java -jar target\project-1.0-SNAPSHOT.jar
    For macOS/Linux
        java -jar target/project-1.0-SNAPSHOT.jar

### Testing the game

First the user needs to make sure the game is successfully build on the local machine.
Follow these steps to run the tests:

1. Open the shell of your choice (eg. Command Prompt(Windows users) or Terminal(macOS or Linux users))
2. Navigate to the project directory
3. Give the command to run the tests: 
    mvn test

