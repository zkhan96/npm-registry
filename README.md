# Getting Started
If you wish to run the tests:
```
./gradlew clean build
```

If you wish to run the service:
```
./gradlew bootRun
```

To use the service, you must make a GET request (or just use your browser) using the following path:

```
http://localhost:3000/{dependencyName}/{version}
```
For example, a visit to
http://localhost:3000/react/16.13.0
would result in:
```json
{
  "name": "react",
  "version": "16.13.0",
  "dependencies": {
    "loose-envify": {
      "name": "loose-envify",
      "version": "^1.1.0",
      "dependencies": {
        "js-tokens": {
          "name": "js-tokens",
          "version": "^1.0.1"
        }
      }
    },
    "prop-types": {
      "name": "prop-types",
      "version": "^15.6.2",
      "dependencies": {
        "loose-envify": {
          "name": "loose-envify",
          "version": "^1.3.1",
          "dependencies": {
            "js-tokens": {
              "name": "js-tokens",
              "version": "^3.0.0"
            }
          }
        },
        "object-assign": {
          "name": "object-assign",
          "version": "^4.1.1"
        }
      }
    },
    "object-assign": {
      "name": "object-assign",
      "version": "^4.1.1"
    }
  }
}
```
