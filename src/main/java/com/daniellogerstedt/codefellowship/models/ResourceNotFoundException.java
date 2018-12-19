package com.daniellogerstedt.codefellowship.models;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

// Found at https://stackoverflow.com/questions/36000137/how-to-handle-404-page-not-found-exception-in-spring-mvc-with-java-configuration

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {

}

