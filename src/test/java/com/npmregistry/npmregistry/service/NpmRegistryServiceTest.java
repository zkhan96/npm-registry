package com.npmregistry.npmregistry.service;

import com.npmregistry.npmregistry.model.DependencyTree;
import com.npmregistry.npmregistry.model.NpmPackage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class NpmRegistryServiceTest {

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private NpmRegistryService underTest;

    @Test
    void shouldGenerateDependencyTree() {
        when(restTemplate.getForObject("https://registry.npmjs.org/react/1.0.0", NpmPackage.class))
                .thenReturn(new NpmPackage("react", "1.0.0", new HashMap<>()));
        final DependencyTree result = underTest.createDependencyTree(new DependencyTree("react", "1.0.0", null));
        final DependencyTree dependencyTree = new DependencyTree("react", "1.0.0", null);
        assertEquals(dependencyTree, result);
    }

    @Test
    void shouldThrowInternalServerError() {
        when(restTemplate.getForObject("https://registry.npmjs.org/react/1.0.0", NpmPackage.class))
                .thenThrow(new RuntimeException());

        final ResponseStatusException responseStatusException = assertThrows(ResponseStatusException.class,
                () -> underTest.createDependencyTree(new DependencyTree("react", "1.0.0", null)));

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseStatusException.getStatus());
    }
}
