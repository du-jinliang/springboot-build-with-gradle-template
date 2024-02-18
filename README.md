# springboot使用gradle构建项目模板

### 介绍
使用kotlin作为dsl的gradle构建springboot项目模板，内置常用依赖、项目包结构、统一异常处理、统一结果返回、spring、日期、去重、合法性校验工具类以及跨域、https、多线程和单机锁与分布式锁的配置



### 使用说明



#### 使用kotlin作为dsl的gradle构建项目并管理依赖

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



#### 跨域配置分离到配置文件

可以根据需要配置多个域，或直接直接使用通配符`*`表示全部放行

```yaml
cors:
  allowed-origins:
    - '*'
  allowed-methods: GET,POST,PUT,DELETE,OPTIONS
  allowed-headers: Authorization,Content-Type
  allow-credentials: true
  max-age: 3600
```



#### 多线程配置分离到配置文件

```yaml
wenhe9:
  thread:
    core-size: 5
    max-size: 9
    keep-alive-time: 10
```



#### https配置

用于`restTemplate`发送`https`请求，使用时需要new，不能自动注入

```java
RestTemplate restTemplate = new RestTemplate(new HttpsClientRequestFactory());
```



#### 统一异常处理

```cmd
    BusinessException.java // 业务异常
    GlobalExceptionHandler.java // 全局异常处理器
    ServerException.java // 系统异常
    ServiceException.java // 自定义异常基类
    ValidationException.java // 校验异常
```

其中 `ServiceException`是自定义异常基类，需要拓展新异常时可继承他



#### 统一结果返回

```cmd
    ResultResponse.java // 统一返回结果
    ResultResponseEnum.java // 统一返回状态枚举
```



#### 单机锁和分布式锁

```cmd
└─lock
    │  AbtrastILock.java // 抽象自定义锁类
    │  ILock.java // 自定义锁规范
    │  LockBeanNameConstants.java // 加锁实例bean名称
    │
    ├─annotation
    │      Lockable.java // 加锁注解
    │      LockMethodAspect.java // 给方法加锁切面类
    │
    └─impl
            ConcurrentHashMapLock.java // 单机锁 使用 concurrentHashMap + threadLocal 实现
            RedisLock.java // 分布式锁 使用redis + threadLocal 实现
```

当需要自定义加锁方式时，可以继承 AbtrastILock ，当实现新的加锁类时，需要在 LockBeanNameConstants 增加标注

#### 其他工具类
