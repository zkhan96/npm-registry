package com.npmregistry.npmregistry.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.HashMap;
import java.util.Set;

@AllArgsConstructor
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DependencyTree {
    private String name;
    private String version;
    private HashMap<String, DependencyTree> dependencies;

}
