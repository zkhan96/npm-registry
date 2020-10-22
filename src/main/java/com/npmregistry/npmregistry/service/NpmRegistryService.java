package com.npmregistry.npmregistry.service;

import com.npmregistry.npmregistry.model.DependencyTree;
import com.npmregistry.npmregistry.model.NpmPackage;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.Map;

@AllArgsConstructor
@Component
public class NpmRegistryService {
    public static final String NPM_BASE_URL = "https://registry.npmjs.org/";
    private final RestTemplate restTemplate;

    public DependencyTree createDependencyTree(DependencyTree dependencyTree) {
        try {
            final NpmPackage npmPackage = getNpmPackage(dependencyTree.getName(), dependencyTree.getVersion());
            if (!CollectionUtils.isEmpty(npmPackage.getDependencies())) {
                final HashMap<String, DependencyTree> dependenciesSet = new HashMap<>();
                dependencyTree.setDependencies(dependenciesSet);
                for (Map.Entry<String, String> dep : npmPackage.getDependencies().entrySet()) {
                    dependenciesSet.put(dep.getKey(), createDependencyTree(new DependencyTree(dep.getKey(), dep.getValue(), null)));
                }
            }
            return dependencyTree;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e);
        }
    }

    private NpmPackage getNpmPackage(String dependencyName, String version) {
        return restTemplate.getForObject(NPM_BASE_URL + dependencyName + "/" + version.replace("^", ""), NpmPackage.class);
    }
}
