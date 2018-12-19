## CodeFellowship

### Table of Contents

1. [About the App](#about-the-app)<br />
1. [How to run](#how-to-run)<br />
1. [Templates](#templates)<br />
    3-1. [Index](#index)<br />
    3-2. [Sign Up](#sign-up)<br />
    3-2. [Users](#users)<br />
    3-2. [User Profile](#user-profile)<br />
    3-2. [Error](#error)<br />
1. [Configs](#configs)<br />
    4-1. [Web Security Config](#web-security-config)<br />
1. [Controllers](#controllers)<br />
    5-1. [Security Controller](#security-controller)<br />
    5-2. [Page Controller](#page-controller)<br />
1. [Models](#models)<br />
    6-1. [Application User](#application-user)<br />
    6-2. [Resource Not Found Exception](#resource-not-found-exception)<br />
1. [Repositories](#repositories)<br />
    7-1. [Application User Repository](#application-user-repository)<br />

### About the App

This app allows users to create a profile and view other users profiles.

[TOP](#table-of-contents)

### How to Run

To run this app follow these instructions:

1. Clone the repository from github `git clone`

1. Move into the directory with terminal `cd codefellowship`

1. Run with gradlew `./gradlew run`

1. Go to `localhost:8080` in browser of choice

[TOP](#table-of-contents)

### Templates

3-1. [Index](#index)
3-2. [Sign Up](#sign-up)
3-2. [Users](#users)
3-2. [User Profile](#user-profile)
3-2. [Error](#error)

#### Index

[index](/src/main/resources/templates/index.html)

This is the homepage for the application, it contains a signup link to navigate to the signup page.

[TOP](#table-of-contents)

#### Sign Up

[signup](/src/main/resources/templates/signup.html)

This page contains a form to create a user for the app.

[TOP](#table-of-contents)

#### Users

[users](/src/main/resources/templates/users.html)

This page contains user data for all users, limited to username and full name.

[TOP](#table-of-contents)

#### User Profile

[userProfile](/src/main/resources/templates/userProfile.html)

This page contains a more verbose set of information for a single user.

[TOP](#table-of-contents)

#### Error

[error](/src/main/resources/templates/error.html)

This is the formatted error page for displaying errors when they occur.

[TOP](#table-of-contents)

### Configs

4-1. [Web Security Config](#web-security-config)

Configs control settings for the various libraries and frameworks

#### Web Security Config

[WebSecurityConfig](/src/main/java/com/daniellogerstedt/codefellowship/configs/WebSecurityConfig.java)

This config is used to set up the web security framework for Spring.

[TOP](#table-of-contents)

### Controllers

5-1. [Security Controller](#security-controller)
5-2. [Page Controller](#page-controller)

Controllers manage routing for the request response cycle.

#### Security Controller

[SecurityController](/src/main/java/com/daniellogerstedt/codefellowship/controllers/SecurityController.java)

This controller manages all user authentication and creation. This includes sign up routing.

[TOP](#table-of-contents)

#### Page Controller

[PageController](/src/main/java/com/daniellogerstedt/codefellowship/controllers/PageController.java)

This controller manages all non authentication routes. This includes viewing user profiles and the homepage.

[TOP](#table-of-contents)

### Models

6-1. [Application User](#application-user)
6-2. [Resource Not Found Exception](#resource-not-found-exception)

Models control the formatting for certain instanced object types to be used to pass information throughout the app during operation.

#### Application User

[ApplicationUser](/src/main/java/com/daniellogerstedt/codefellowship/models/ApplicationUser.java)

This is the model for a users information.

[TOP](#table-of-contents)

#### Resource Not Found Exception

[ResourceNotFoundException](/src/main/java/com/daniellogerstedt/codefellowship/models/ResourceNotFoundException.java)

This is an extension of an Exception that is used to throw 404 errors when certain pages or pieces of data don't exist.

[TOP](#table-of-contents)

### Repositories

7-1. [Application User Repository](#application-user-repository)

Repositories allow easy access to the database for making queries related to a specific model

#### Application User Repository

[ApplicationUserRepository](/src/main/java/com/daniellogerstedt/codefellowship/repositories/ApplicationUserRepository.java)


Handles database queries related to ApplicationUser instances.

[TOP](#table-of-contents)