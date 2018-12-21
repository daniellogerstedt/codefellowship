## CodeFellowship

### Table of Contents

1. [About the App](#about-the-app)<br />
1. [How to run](#how-to-run)<br />
1. [Templates](#templates)<br />
    3-1. [Index](#index)<br />
    3-2. [Sign Up](#sign-up)<br />
    3-3. [Login](#login)<br />
    3-4. [Users](#users)<br />
    3-5. [User Profile](#user-profile)<br />
    3-6. [Feed](#feed)<br />
    3-7. [Error](#error)<br />
    3-8. [Fragments](#fragments)<br />
1. [Configs](#configs)<br />
    4-1. [Web Security Config](#web-security-config)<br />
1. [Controllers](#controllers)<br />
    5-1. [Security Controller](#security-controller)<br />
    5-2. [Page Controller](#page-controller)<br />
1. [Models](#models)<br />
    6-1. [Application User](#application-user)<br />
    6-2. [Resource Not Found Exception](#resource-not-found-exception)<br />
    6-3. [Blog Post](#blog-post)<br />
    6-4. [User Details Service Impl](#user-details-service-impl)<br />
1. [Repositories](#repositories)<br />
    7-1. [Application User Repository](#application-user-repository)<br />
    7-2. [Blog Post Repository](#blog-post-repository)<br />

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

3-1. [Index](#index)<br />
3-2. [Sign Up](#sign-up)<br />
3-3. [Login](#login)<br />
3-4. [Users](#users)<br />
3-5. [User Profile](#user-profile)<br />
3-6. [Feed](#feed)<br />
3-7. [Error](#error)<br />
3-8. [Fragments](#fragments)<br />

#### Index

[index](/src/main/resources/templates/index.html)

This is the homepage for the application, it contains a signup link to navigate to the signup page.

[TOP](#table-of-contents)

#### Sign Up

[signup](/src/main/resources/templates/signup.html)

This page contains a form to create a user for the app.

[TOP](#table-of-contents)

#### Login

[login](/src/main/resources/templates/login.html)

This page contains a form to sign in to the app.

[TOP](#table-of-contents)

#### Users

[users](/src/main/resources/templates/users.html)

This page contains user data for all users, limited to username and full name.

[TOP](#table-of-contents)

#### User Profile

[userProfile](/src/main/resources/templates/userProfile.html)

This page contains a more verbose set of information for a single user.

[TOP](#table-of-contents)

#### Feed

[feed](/src/main/resources/templates/feed.html)

This page contains posts from all users that the logged in user has followed.

[TOP](#table-of-contents)

#### Error

[error](/src/main/resources/templates/error.html)

This is the formatted error page for displaying errors when they occur.

[TOP](#table-of-contents)

#### Fragments

[fragments](/src/main/resources/templates/fragments.html)

This html document is used to pull fragments to be reused throughout the other html pages. For example the nav bar is contained within this html.

[TOP](#table-of-contents)

### Configs

4-1. [Web Security Config](#web-security-config)<br />

Configs control settings for the various libraries and frameworks

#### Web Security Config

[WebSecurityConfig](/src/main/java/com/daniellogerstedt/codefellowship/configs/WebSecurityConfig.java)

This config is used to set up the web security framework for Spring.

[TOP](#table-of-contents)

### Controllers

5-1. [Security Controller](#security-controller)<br />
5-2. [Page Controller](#page-controller)<br />

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

6-1. [Application User](#application-user)<br />
6-2. [Resource Not Found Exception](#resource-not-found-exception)<br />
6-3. [Blog Post](#blog-post)<br />
6-4. [User Details Service Impl](#user-details-service-impl)<br />

Models control the formatting for certain instanced object types to be used to pass information throughout the app during operation.

#### Application User

[ApplicationUser](/src/main/java/com/daniellogerstedt/codefellowship/models/ApplicationUser.java)

This is the model for a users information.

[TOP](#table-of-contents)

#### Resource Not Found Exception

[ResourceNotFoundException](/src/main/java/com/daniellogerstedt/codefellowship/models/ResourceNotFoundException.java)

This is an extension of an Exception that is used to throw 404 errors when certain pages or pieces of data don't exist.

[TOP](#table-of-contents)

#### Blog Post

[BlogPost](/src/main/java/com/daniellogerstedt/codefellowship/models/BlogPost.java)

This is the model for blog posts

[TOP](#table-of-contents)

#### User Details Service Impl

[UserDetailsServiceImpl](/src/main/java/com/daniellogerstedt/codefellowship/models/UserDetailsServiceImpl.java)

This class implements the UserDetailsService and allows for custom manipulation of database search queries. Specifically it allows us to search by username.

[TOP](#table-of-contents)

### Repositories

7-1. [Application User Repository](#application-user-repository)<br />
7-2. [Blog Post Repository](#blog-post-repository)<br />

Repositories allow easy access to the database for making queries related to a specific model

#### Application User Repository

[ApplicationUserRepository](/src/main/java/com/daniellogerstedt/codefellowship/repositories/ApplicationUserRepository.java)

Handles database queries related to ApplicationUser instances.

[TOP](#table-of-contents)

#### Blog Post Repository

[BlogPostRepository](/src/main/java/com/daniellogerstedt/codefellowship/repositories/BlogPostRepository.java)

Handles database queries related to BlogPost instances.

[TOP](#table-of-contents)
