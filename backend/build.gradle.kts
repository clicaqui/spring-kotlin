import org.gradle.kotlin.dsl.*
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.jetbrains.kotlin.ir.backend.js.compile
import org.springframework.boot.gradle.tasks.bundling.BootJar
import org.springframework.boot.gradle.tasks.run.BootRun


plugins {
   //id("java")
    kotlin("jvm") version "1.8.21"
    kotlin("plugin.spring") version embeddedKotlinVersion
    id("io.spring.dependency-management") version "1.1.3"
    application
}


buildscript {
    val springBootVersion: String = properties["springBootVersion"] as String
    repositories {
        maven { setUrl("https://repo.spring.io/milestone") }
        maven { setUrl("https://repo.spring.io/snapshot") }
    }

    dependencies {
        classpath("org.springframework.boot:spring-boot-gradle-plugin:$springBootVersion")
        //classpath("io.spring.gradle:dependency-management-plugin:1.1.3")
    }
}

allprojects {
    repositories {
        mavenCentral()
        maven { setUrl("https://repo.spring.io/milestone") }
        maven { setUrl("https://repo.spring.io/snapshot") }
        //maven { setUrl("https://plugins.gradle.org/m2") }
    }
}

//subprojects {
    val project = mapOf(
        name to "backend"
    )
    val kotlinVersion = properties["kotlinVersion"] as String

    apply {
        plugin("org.springframework.boot")
        plugin("application")
    }

    dependencies {
        //implementation("org.springframework.boot:spring-boot-starter-data-jpa")
        implementation("org.springframework.boot:spring-boot-starter-web")
        implementation("org.springframework.boot:spring-boot-devtools")
        implementation(kotlin("stdlib-jdk8", kotlinVersion))
        implementation(kotlin("reflect", kotlinVersion))
    }


    tasks.withType<KotlinCompile> {
        kotlinOptions {
            jvmTarget = "1.8"
        }
    }

    tasks.named<BootJar>("bootJar") {
        manifest {
            attributes("Start-Class" to "com.clicaqui.Config")
        }
    }
    tasks.named<BootRun>("bootRun") {
        mainClass.set("com.clicaqui.Config")
    }

    application {
        mainClass.set("com.clicaqui.Config")

    }
//}


  /*  var fatJar = task("fatJar", type = Jar::class) {
        //baseName = "${project.name}-fat"
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

*/