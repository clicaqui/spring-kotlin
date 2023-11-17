import org.gradle.kotlin.dsl.*
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.springframework.boot.gradle.tasks.bundling.BootJar
import org.springframework.boot.gradle.tasks.run.BootRun


plugins {
   // kotlin("jvm")
    kotlin("plugin.spring") version embeddedKotlinVersion
    //kotlin("plugin.jpa") version embeddedKotlinVersion
    application
}


apply {
    plugin("org.springframework.boot")
   // plugin("kotlin-jpa")
}

//buildscript {

    dependencies {
        val kotlinxHtmlVersion: String = properties["kotlinxHtmlVersion"] as String
        val springBootVersion: String = properties["springBootVersion"] as String
        val hibernateValidatorVersion: String = properties["hibernateValidatorVersion"] as String
        implementation(project(":backend:project"))
        //implementation("org.springframework.boot:spring-boot-starter-web:$springBootVersion")
        implementation("org.springframework.boot:spring-boot-starter-webflux:$springBootVersion")
        implementation("org.springframework.boot:spring-boot-devtools:$springBootVersion")
        implementation("org.jetbrains.kotlinx:kotlinx-html-jvm:$kotlinxHtmlVersion")
        implementation("org.hibernate:hibernate-validator:$hibernateValidatorVersion")
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
