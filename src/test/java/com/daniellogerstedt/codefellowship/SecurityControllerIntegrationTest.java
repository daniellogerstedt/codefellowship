package com.daniellogerstedt.codefellowship;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SecurityControllerIntegrationTest {

    @LocalServerPort
    public int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testHomepageRoute() {
        ResponseEntity<String> response =  this.restTemplate.getForEntity("http://localhost:" + port + "/", String.class);
        assertEquals(200, response.getStatusCodeValue());
        assertTrue(response.getBody().contains("<nav>\n" +
                "            <table class=\"nav\">\n" +
                "                <tr>\n" +
                "                    <td><a href=\"/\">Home</a></td>\n" +
                "                    <td><a href=\"/signup\">Sign Up</a></td>\n" +
                "                    <td><a href=\"/login\">Log In</a></td>\n" +
                "                    <td><a href=\"/users\">Users</a></td>\n" +
                "                    <td><a href=\"/feed\">Feed</a></td>\n" +
                "                    <!--https://www.thymeleaf.org/doc/articles/springsecurity.html-->\n" +
                "                    <!--<td sec:authorize=\"isFullyAuthenticated()\"><a href=\"/perform_logout\">Log Out</a></td>-->\n" +
                "                </tr>\n" +
                "            </table>\n" +
                "        </nav>\n"));

    }

    @Test
    public void testLoginRoute() {
        ResponseEntity<String> response =  this.restTemplate.getForEntity("http://localhost:" + port + "/login", String.class);
        assertEquals(200, response.getStatusCodeValue());
        assertTrue(response.getBody().contains("<link rel=\"stylesheet\" href=\"https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css\" integrity=\"sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO\" crossorigin=\"anonymous\">\n"));
    }

    @Test
    public void testSignupRoute() {
        ResponseEntity<String> response =  this.restTemplate.getForEntity("http://localhost:" + port + "/signup", String.class);
        assertEquals(200, response.getStatusCodeValue());
        assertTrue(response.getBody().contains("<form action=\"/signup\" method=\"POST\">\n" +
                "            <label>\n" +
                "                <h4>Username:</h4>\n" +
                "                <input name=\"username\" />\n" +
                "            </label>\n" +
                "            <label>\n" +
                "                <h4>Password:</h4>\n" +
                "                <input name=\"password\" />\n" +
                "            </label>\n" +
                "            <label >\n" +
                "                <h4>First Name:</h4>\n" +
                "                <input name=\"firstName\" />\n" +
                "            </label>\n" +
                "            <label >\n" +
                "                <h4>Last Name:</h4>\n" +
                "                <input name=\"lastName\"/>\n" +
                "            </label>\n" +
                "            <label >\n" +
                "                <h4>Date of Birth (mm/dd/yy):</h4>\n" +
                "                <input name=\"dateOfBirth\"/>\n" +
                "            </label>\n" +
                "            <label >\n" +
                "                <h4>Bio:</h4>\n" +
                "                <textarea name=\"bio\"></textarea>\n" +
                "            </label>\n" +
                "            <input type=\"submit\">\n" +
                "        </form>\n"));
    }

}