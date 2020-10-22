package com.npmregistry.npmregistry.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.npmregistry.npmregistry.model.DependencyTree;
import com.npmregistry.npmregistry.service.NpmRegistryService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class NpmRegistryControllerTest {

    @Mock
    private NpmRegistryService npmRegistryService;

    @InjectMocks
    private NpmRegistryController underTest;
    public static final String DEPENDENCY_NAME = "some-dep";
    public static final String VERSION = "1.0.0";
    public static final String SOME_OTHER_DEPENDENCY = "some-other-dep";
    public static final String ANOTHER_VERSION = "1.0.1";

    @Test
    void shouldReturnDependencyTree() throws JsonProcessingException {
        final ResponseEntity<DependencyTree> expectedResponse = ResponseEntity
                .ok(new DependencyTree(DEPENDENCY_NAME, VERSION,
                        new HashMap<>(Map.of(SOME_OTHER_DEPENDENCY, new DependencyTree(SOME_OTHER_DEPENDENCY, ANOTHER_VERSION, null)))));
        when(npmRegistryService.createDependencyTree(new DependencyTree(DEPENDENCY_NAME, VERSION, null))).thenReturn(expectedResponse.getBody());

        final ResponseEntity<DependencyTree> result = underTest.getDependencyTree(DEPENDENCY_NAME, VERSION);

        assertEquals(result, expectedResponse);
    }
}
