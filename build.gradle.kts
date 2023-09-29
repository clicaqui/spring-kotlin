import org.gradle.kotlin.dsl.*
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.8.21"
    application
}

group = "me.tr20650ps"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
    implementation("org.jetbrains.kotlin:kotlin-stdlib:1.8.21") // Replace with your Kotlin version
}


tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

application {
    mainClass.set("br.clicaqui.MainKt")
}

var fatJar = task("fatJar", type = Jar::class) {
    baseName = "${project.name}-fat"
    manifest {
        attributes["Main-Class"] = "br.clicaqui.MainKt"
    }
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
    from(
        configurations.compileClasspath.get().filter{
            it.name.endsWith("jar")
        }
            .onEach { println("add from dependencies: ${it.name}") }
            .map {if (it.isDirectory) it else zipTree(it)
        }
    )
    with(tasks["jar"] as CopySpec)
}

tasks {
    "build" {
        dependsOn(fatJar)
    }
}