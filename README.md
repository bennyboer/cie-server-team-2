# CiE Server (Team 2)

> This is a project which aims to be an alternative solution to using nine ([See here](https://nine.wi.hm.edu)).

## Getting started

### Development

#### In subfolder `cie-server`:
- Let maven install
- Launch the Spring server with `CieServerApplication.java`

#### In subfolder `cie-management-app`:
- Run `npm install` from the command line -> Dependencies will be installed
- Run `npm start` from the command line -> Development server is started with proxy so that all HTTP Requests are redirected to the server instead of `localhost:4200` (The default ng serve address)

### Release
In order to build the server and web management interface you can just use the maven goal `clean install -Prelease` (with the `release` profile). Run the maven command in the `cie-server` subfolder. The build process will automatically build the web application and include it into the `resources` folder of the spring application. The resulting server application will be placed in the `cie-server/target` folder.

## Background
The project consists of a server (which provides a REST API) and a Angular web app (which serves as the management interface for the Courses in English Office).

### Server
Provides a REST API for the clients (both the management interface and mobile clients for the students).

### Management Interface
The Courses in English Office is able to manage the courses and course selections.
