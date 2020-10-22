package com.npmregistry.npmregistry;

import org.apache.commons.io.IOUtils;
import org.json.JSONException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
class NpmRegistryApplicationTests {

    @LocalServerPort
    private Integer port;

    private String serviceUrl = "http://localhost:";

    @Autowired
    private TestRestTemplate testRestTemplate;

    @BeforeEach
    void setUp() {
        this.serviceUrl = serviceUrl + port;
    }

    @Test
    void shouldReturnDependencyTree() throws IOException, JSONException {
        final ResponseEntity<String> exchange = testRestTemplate.getForEntity(serviceUrl + "/package/react/16.13.0", String.class);

        JSONAssert.assertEquals(IOUtils.toString(this.getClass().getResourceAsStream("/expected-response-react.json"), StandardCharsets.UTF_8), exchange.getBody(), true);
    }
}
