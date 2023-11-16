import org.jetbrains.kotlin.gradle.tasks.KotlinCompile


plugins {
    //kotlin("jvm")
    kotlin("plugin.spring") version embeddedKotlinVersion
    //kotlin("plugin.jpa") version embeddedKotlinVersion
   // id("org.jetbrains.kotlin.plugin.jpa")
  ///  application
}

//val kotlinVersion = properties["kotlinVersion"] as String

dependencies {
    val kotlinVersion = properties["kotlinVersion"] as String
    val postgresVersion = "42.1.4"
    implementation("org.springframework.boot:spring-boot-starter:2.6.0-M1")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa:2.6.1-SNAPSHOT")
    //implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.postgresql:postgresql:$postgresVersion")
}




