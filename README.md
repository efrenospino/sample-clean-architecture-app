# Sample App with Clean Architecture 

![Detekt](https://github.com/efrenospino/spot-app/actions/workflows/detekt.yml/badge.svg) 
![Unit Tests](https://github.com/efrenospino/spot-app/actions/workflows/test.yml/badge.svg) 
[![codecov](https://codecov.io/gh/efrenospino/spot-app/branch/main/graph/badge.svg?token=SNPZSZETRH)](https://codecov.io/gh/efrenospino/spot-app) 

Sample app that represents clean architecture and integration with tools.

## Setup

Make sure to have these keys on your `local.properties` file:

```
keystoreFilePath=PATH/TO/KEYSTORE
keystorePassword=KEYSTORE_PASSWORD
keyAlias=KEY_ALIAS
keyPassword=KEY_PASSWORD
firebaseAppId=APP_ID
serviceCredentialsJsonPath=PATH/TO/JSON
```

## Separation of layers

### Data
From DAOs to Repositories, this module is responsible for accessing and writing data considering the different data sources, such as REST APIs, local databases or local persistance for key-value pairs.

### Domain
This layer is responsible for holding all the business logic and prepare the data making it usable for specific use cases.

### UI
This is the module which is in charge of handling and displaying screens to users. Responsible for holding features, resources, and UI components.

### App
This is the app module. It is responsible for navigation, configuration and any other Android-specific components.
