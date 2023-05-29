package org.example;


import org.example.entity.User;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component(value = "communication")
public class Communication {

    private final String URL = "http://94.198.50.185:7081/api/users";
    private final RestTemplate restTemplate;
    private final SessionIdentificator sessionIdentificator;
    private HttpHeaders httpHeaders = new HttpHeaders();


    public Communication(RestTemplate restTemplate, SessionIdentificator sessionIdentificator) {
        this.restTemplate = restTemplate;
        this.sessionIdentificator = sessionIdentificator;
    }

    public List<User> getAllUsers() {

        ResponseEntity<List<User>> responseEntity = restTemplate.exchange(URL,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<User>>() {
                });

        String sessionId = restTemplate.headForHeaders(URL).get(HttpHeaders.SET_COOKIE).get(0).substring(0, "JSESSIONID=".length() + 32);
        sessionIdentificator.setValue(sessionId);
        return responseEntity.getBody();
    }


    public ResponseEntity<String> addNewUser(User user) {

        httpHeaders.add(HttpHeaders.COOKIE, sessionIdentificator.getValue());
        HttpEntity<User> httpEntity = new HttpEntity<>(user, httpHeaders);
        return restTemplate.exchange(URL, HttpMethod.POST, httpEntity, String.class);
    }

    public ResponseEntity<String> deleteUserById(Long id) {

        HttpEntity<User> httpEntity = new HttpEntity<>(new User(), httpHeaders);
        return restTemplate.exchange(String.join("", URL, "/", id.toString()), HttpMethod.DELETE, httpEntity, String.class);
    }

    public ResponseEntity<String> refactorUser(User user) {

        HttpEntity<User> httpEntity = new HttpEntity<>(user, httpHeaders);
        return restTemplate.exchange(URL, HttpMethod.PUT, httpEntity, String.class);
    }
}
