

plugins {
    //kotlin("jvm")
    kotlin("plugin.spring") version embeddedKotlinVersion
    kotlin("plugin.jpa") version embeddedKotlinVersion
   // id("org.jetbrains.kotlin.plugin.jpa")
}


dependencies {
    val springBootVersion: String = properties["springBootVersion"] as String
    val postgresVersion = properties["postgresVersion"] as String
    implementation("org.springframework.boot:spring-boot-starter:$springBootVersion")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa:2.6.1-SNAPSHOT")
    //implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.postgresql:postgresql:$postgresVersion")
}




