package com.schibsted.asyncsyncendpoint.framework;

import com.jayway.jsonpath.JsonPath;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class ApplicationTest {

    @LocalServerPort
    private int port;

    private final RestTemplate rest = new RestTemplate();

    private String url;

    @Before
    public void setUp() {
        url = String.format("http://localhost:%d/", port);
    }

    @Test
    public void should_be_healthy() {
        ResponseEntity<String> response = rest.getForEntity(url + "health", String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(JsonPath.parse(response.getBody()).read("$.status", String.class))
                .isEqualToIgnoringCase("UP");
    }

    @Test
    public void should_say_hello() {
        ResponseEntity<String> response = rest.getForEntity(url + "hello", String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo("Hello world");
    }
}
