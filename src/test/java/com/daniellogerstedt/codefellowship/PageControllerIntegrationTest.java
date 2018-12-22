package com.daniellogerstedt.codefellowship;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PageControllerIntegrationTest {

    @LocalServerPort
    public int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testSignUpPost() {

        HttpHeaders signupHeaders = new HttpHeaders();
        HttpHeaders loginHeaders = new HttpHeaders();
//        HttpHeaders requestHeaders = new HttpHeaders();

        MultiValueMap<String, String> bodySignUp = new LinkedMultiValueMap<>();
        MultiValueMap<String, String> bodyLogin = new LinkedMultiValueMap<>();
        bodySignUp.add("username","testUser");
        bodySignUp.add("password","testPassword");
        bodySignUp.add("firstName","test");
        bodySignUp.add("lastName","test");
        bodySignUp.add("dateOfBirth","11/11/11");
        bodySignUp.add("bio","test");
        bodyLogin.add("username","testUser");
        bodyLogin.add("password","testPassword");

        HttpEntity<Object> httpEntitySignUp = new HttpEntity<>(bodySignUp, signupHeaders);
//        HttpEntity<Object> httpEntityLogin = new HttpEntity<>(bodyLogin, loginHeaders);


        System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<HERE I AM BEFORE SIGNUP ATTEMPT >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        ResponseEntity<String> response =  this.restTemplate.postForEntity("http://localhost:" + port + "/signup", httpEntitySignUp, String.class);

        assertEquals("Should have a response code of 201", 201, response.getStatusCodeValue());

    }

    @Test
    public void testUsers() {

        /* Figuring out how to build an Http Entity using MultiValueMaps and Headers was found at:
         *
         * https://stackoverflow.com/questions/10358345/making-authenticated-post-requests-with-spring-resttemplate-for-android
         *
         * This solved the problem of figuring out how to get the Entity to send as the headers and body using "postForEntity"
         */

        HttpHeaders signupHeaders = new HttpHeaders();
        HttpHeaders loginHeaders = new HttpHeaders();
        HttpHeaders requestHeaders = new HttpHeaders();

//        "{\"username\":\"testUser\",\"password\":\"testPassword\",\"firstName\":\"test\",\"lastName\":\"test\",\"dateOfBirth\":\"11/11/11\",\"bio\":\"test\"}"
        MultiValueMap<String, String> bodySignUp = new LinkedMultiValueMap<>();
        MultiValueMap<String, String> bodyLogin = new LinkedMultiValueMap<>();
        bodySignUp.add("username","testUser");
        bodySignUp.add("password","testPassword");
        bodySignUp.add("firstName","test");
        bodySignUp.add("lastName","test");
        bodySignUp.add("dateOfBirth","11/11/11");
        bodySignUp.add("bio","test");
        bodyLogin.add("username","testUser");
        bodyLogin.add("password","testPassword");

        HttpEntity<Object> httpEntitySignUp = new HttpEntity<>(bodySignUp, signupHeaders);
        HttpEntity<Object> httpEntityLogin = new HttpEntity<>(bodyLogin, loginHeaders);

        /* I figured out how to use restTemplate with the assistance of Nick pointing me in the right direction and the documentation at:
         *
         * https://www.baeldung.com/rest-template
         *
         * This was something I found by searching for "postForEntity Spring Boot" after being showed "getForEntity" and thinking there probably was a post for as well.
         */

        System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<HERE I AM BEFORE SIGNUP ATTEMPT >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        this.restTemplate.postForEntity("http://localhost:" + port + "/signup", httpEntitySignUp, String.class);
        System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<HERE I AM BEFORE LOGIN ATTEMPT >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        ResponseEntity<String> loginResponse = this.restTemplate.postForEntity("http://localhost:" + port + "/perform_login", httpEntityLogin, String.class);

        /* I figured out how to set the cookie at:
         *
         * https://stackoverflow.com/questions/32177045/spring-rest-resttemplate
         *
         * This allows me to pull the SessionID cookie form the response for use in the rest of the application for testing.
         */


        requestHeaders.set("Cookie", loginResponse.getHeaders().get("Set-Cookie").get(0));
        HttpEntity<Object> httpEntityRequest = new HttpEntity<>(requestHeaders);

        /* I found the .exchange method on stack overflow at:
         *
         * https://stackoverflow.com/questions/16781680/http-get-with-headers-using-resttemplate
         *
         * This allows me to do the same thing as the getForEntity except it can also take in some extra arguments including an HttpEntity containing both a body and header.
         */
//      ResponseEntity<String> response = this.restTemplate.getForEntity("http://localhost:" + port + "/users", String.class);
        ResponseEntity<String> response = this.restTemplate.exchange("/users", HttpMethod.GET, httpEntityRequest, String.class);
        System.out.println(response.toString());

        // Time for Some Tests
        assertEquals(200, response.getStatusCodeValue());
        assertTrue(response.getBody().contains("<p>User: testUser First Name: test Date of Birth: 11/11/11</p>"));

    }

    @Test
    public void testMyProfile() {

        HttpHeaders loginHeaders = new HttpHeaders();
        HttpHeaders requestHeaders = new HttpHeaders();

        MultiValueMap<String, String> bodyLogin = new LinkedMultiValueMap<>();
        bodyLogin.add("username","testUser");
        bodyLogin.add("password","testPassword");

        HttpEntity<Object> httpEntityLogin = new HttpEntity<>(bodyLogin, loginHeaders);

        ResponseEntity<String> loginResponse = this.restTemplate.postForEntity("http://localhost:" + port + "/perform_login", httpEntityLogin, String.class);

        requestHeaders.set("Cookie", loginResponse.getHeaders().get("Set-Cookie").get(0));
        HttpEntity<Object> httpEntityRequest = new HttpEntity<>(requestHeaders);

        ResponseEntity<String> response = this.restTemplate.exchange("/myprofile", HttpMethod.GET, httpEntityRequest, String.class);
        System.out.println(response.toString());
        assertEquals(200, response.getStatusCodeValue());
        assertTrue(response.getBody().contains("<h4>New Post:</h4>"));

    }
}