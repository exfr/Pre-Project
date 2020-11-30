package app;

import model.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;


@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);

        final String uri = "http://91.241.64.178:7081/api/users";

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> responseEntityGet = restTemplate.getForEntity(
                uri,
                String.class);

        System.out.println(responseEntityGet.getBody());

        String fromHeaders = Objects.requireNonNull(responseEntityGet.getHeaders().get("Set-Cookie")).get(0);
        String[] cookie = fromHeaders.split(";");

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Cookie", cookie[0]);

        ResponseEntity<String> responseEntityPost = restTemplate.exchange(
                uri,
                HttpMethod.POST,
                new HttpEntity<>(new User(3L, "James", "Brown", (byte) 20), httpHeaders),
                String.class
        );

        System.out.println(responseEntityPost.getBody());

        ResponseEntity<String> responseEntityPut = restTemplate.exchange(
                uri,
                HttpMethod.PUT,
                new HttpEntity<>(new User(3L, "Thomas", "Shelby", (byte) 20), httpHeaders),
                String.class
        );

        System.out.println(responseEntityPut.getBody());

        ResponseEntity<String> responseEntityDelete = restTemplate.exchange(
                uri + "/3",
                HttpMethod.DELETE,
                new HttpEntity<>(httpHeaders),
                String.class
        );

        System.out.println(responseEntityDelete.getBody());

    }
}
