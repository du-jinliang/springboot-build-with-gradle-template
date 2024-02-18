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
