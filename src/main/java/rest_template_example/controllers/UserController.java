package rest_template_example.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import rest_template_example.entity.User;

import java.util.List;

@RestController
public class UserController {

    private final RestTemplate restTemplate;
    private final HttpHeaders httpHeaders;

    private final String apiUrl = "http://94.198.50.185:7081/api/users";
    private HttpEntity<User> requestEntity;

    @Autowired
    public UserController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
        this.httpHeaders = new HttpHeaders();
    }

    @GetMapping
    public ResponseEntity<?> getAllUsers() {
        ResponseEntity<List<User>> responseEntity = restTemplate.exchange(
                apiUrl,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<User>>() {}
        );

        String cookieStr = responseEntity.getHeaders().getFirst(HttpHeaders.SET_COOKIE);
        httpHeaders.set("Cookie", cookieStr);

        return ResponseEntity.ok().body(responseEntity.getBody());
    }

    @PostMapping
    public String addUser(User user) {
        requestEntity = new HttpEntity<>(user, httpHeaders);
        ResponseEntity<String> responseEntity = restTemplate.exchange(
                apiUrl,
                HttpMethod.POST,
                requestEntity,
                String.class
        );

        System.out.println(user.toString() + " was added");
        return responseEntity.getBody();
    }

    @PutMapping
    public String updateUser(User user) {
        ResponseEntity<String> responseEntity = restTemplate.exchange(
                apiUrl,
                HttpMethod.PUT,
                requestEntity,
                String.class
        );

        System.out.println(
                "User " + user.getName()
                        + " "
                        + user.getLastName()
                        + " was update");
        return responseEntity.getBody();
    }

    @DeleteMapping
    public String deleteUser(Long id) {
        ResponseEntity<String> responseEntity = restTemplate.exchange(
                apiUrl + "/" + id,
                HttpMethod.DELETE,
                requestEntity,
                String.class
        );

        System.out.println("User with id = " + id + " was deleted");
        return responseEntity.getBody();
    }
}
