package com.npmregistry.npmregistry.controller;

import com.npmregistry.npmregistry.model.DependencyTree;
import com.npmregistry.npmregistry.service.NpmRegistryService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@AllArgsConstructor
public class NpmRegistryController {

    private final NpmRegistryService npmRegistryService;

    @GetMapping("/package/{dependencyName}/{version}")
    public ResponseEntity<DependencyTree> getDependencyTree(@PathVariable String dependencyName, @PathVariable String version) {
        return ResponseEntity.ok(npmRegistryService.createDependencyTree(new DependencyTree(dependencyName, version,null)));
    }
}
