# Template for building Spring Boot projects with Gradle

### Introduction

Using Kotlin as the DSL for Gradle, this template facilitates building Spring Boot projects. It comes with built-in dependencies, project package structure, unified exception handling, unified result return, Spring, date, deduplication, legality verification utility classes, as well as configurations for cross-origin resource sharing (CORS), HTTPS, multithreading, and single-node locks with distributed locks.



### Instructions for Use

#### Building Projects and Managing Dependencies with Kotlin DSL for Gradle

```kotlin
import org.springframework.boot.gradle.plugin.SpringBootPlugin

plugins {
    java
    id("org.springframework.boot") version "2.6.4"
    id("io.spring.dependency-management") version "1.1.4"
}

group = "cn.wenhe9"
version = "0.0.1-SNAPSHOT"
description = "springboot使用gradle构建项目模板"

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
}

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

buildscript {
    extra["springCloudVersion"] = "2021.0.1"
    extra["springCloudAlibabaVersion"] = "2021.0.1.0"
    extra["hutoolVersion"] = "5.5.1"
    extra["validationApi"] = "2.0.1.Final"
    extra["commonsPools2"] = "2.11.1"
}

repositories {
    maven(url = "https://maven.aliyun.com/repository/public")
    maven(url = "https://mvnrepository.com")
    mavenLocal()
    mavenCentral()
}

dependencyManagement {
    imports {
        mavenBom("org.springframework.cloud:spring-cloud-dependencies:${property("springCloudVersion")}")
        mavenBom("com.alibaba.cloud:spring-cloud-alibaba-dependencies:${property("springCloudAlibabaVersion")}")
    }
    dependencies {
        dependency("org.apache.commons:commons-pool2:${property("commonsPools2")}")
        dependency("javax.validation:validation-api:${property("validationApi")}")
        dependency("cn.hutool:hutool-all:${property("hutoolVersion")}")
    }
}

dependencies {
    implementation(platform(SpringBootPlugin.BOM_COORDINATES))
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("org.springframework.boot:spring-boot-starter-data-redis")
    implementation("org.aspectj:aspectjrt")
    implementation("org.apache.commons:commons-pool2")
    implementation("javax.validation:validation-api")
    implementation("cn.hutool:hutool-all")
    compileOnly("org.projectlombok:lombok")
    developmentOnly("org.springframework.boot:spring-boot-devtools")
    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")
    annotationProcessor("org.projectlombok:lombok")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
}

tasks.withType<Test> {
    useJUnitPlatform()
}

```



#### Separating CORS Configuration into Configuration Files

Multiple domains can be configured as needed, or the wildcard `*` can be used to allow all.

```yaml
cors:
  allowed-origins:
    - '*'
  allowed-methods: GET,POST,PUT,DELETE,OPTIONS
  allowed-headers: Authorization,Content-Type
  allow-credentials: true
  max-age: 3600
```



#### Multithreading Configuration in Configuration Files

```yaml
wenhe9:
  thread:
    core-size: 5
    max-size: 9
    keep-alive-time: 10
```



#### HTTPS Configuration

Used for sending `https` requests with `RestTemplate`. When using, it needs to be instantiated with `new` and cannot be injected automatically.

```java
RestTemplate restTemplate = new RestTemplate(new HttpsClientRequestFactory());
```



#### Unified Exception Handling

```cmd
    BusinessException.java // Business exception
    GlobalExceptionHandler.java // Global exception handler
    ServerException.java // System exception
    ServiceException.java // Base class for custom exceptions
    ValidationException.java // Validation exception
```

Where `ServiceException` is the base class for custom exceptions and can be extended when extending new exceptions.

#### Unified Result Return

```cmd
    ResultResponse.java // Unified return result
    ResultResponseEnum.java // Unified return status enumeration
```



#### Single-node and Distributed Locks

```cmd
└─lock
    │  AbtrastILock.java // Abstract custom lock class
    │  ILock.java // Custom lock specification
    │  LockBeanNameConstants.java // Locked instance bean names
    │
    ├─annotation
    │      Lockable.java // Locking annotation
    │      LockMethodAspect.java // Aspect class for locking methods
    │
    └─impl
            ConcurrentHashMapLock.java // Single-node lock using ConcurrentHashMap + ThreadLocal implementation
            RedisLock.java // Distributed lock using Redis + ThreadLocal implementation
```

When customizing locking methods, you can inherit `AbtrastILock`. When implementing new locking classes, you need to add annotations in `LockBeanNameConstants`.

#### Other Utility Classes
