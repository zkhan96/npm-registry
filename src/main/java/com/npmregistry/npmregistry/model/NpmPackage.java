
package com.npmregistry.npmregistry.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class NpmPackage {
    private String name;
    private String version;
    private HashMap<String, String> dependencies;
}
