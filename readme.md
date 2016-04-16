# Metro Blind (temporary name)

Version : 0.0.1-alpha0

## Description
Metro is intented to deliver an powerful, free software (as in freedom) and international mapper for the public transport.

## What we provide already ?
- a minimal structure for the API based on DropWizard
- a database usable with liquibase

## Features expected for the first version (0.0.1)
- a maximum flexible and evolutive architecture based on the JEE Dropwizard framework
- a database with some metro station description from metro-connexion (available in Creative Common)
- dummy itinerary feature
- internationalization in French and English
- dummy user managment
- store favourite itenerary or station for a user

## How to install the API
This is early stage of the API, we need to make it easy to deploy in the future and more configurable.

### Requirement
Java 1.8 or highter
MySQL
Maven

### The database : MySQL
Configure MySQL with login informations
user : root
password : root
Create a database "metro_blind" with the command :
"CREATE DATABASE metro_blind;"
Check if the database was well created with the command:
"show databases;"

### generate the jar
mvn clean install

### Run
TODO