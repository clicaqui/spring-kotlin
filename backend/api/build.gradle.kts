import org.gradle.kotlin.dsl.*
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.jetbrains.kotlin.ir.backend.js.compile
import org.springframework.boot.gradle.tasks.bundling.BootJar
import org.springframework.boot.gradle.tasks.run.BootRun


plugins {
    kotlin("plugin.spring") version embeddedKotlinVersion
    application
}


apply {
    plugin("org.springframework.boot")
}

application {
    mainClass.set("com.clicaqui.Config")
}

//buildscript {

    dependencies {
        val kotlinxHtmlVersion: String = properties["kotlinxHtmlVersion"] as String
        val springBootVersion: String = project.properties["springBootVersion"] as String
        val hibernateValidatorVersion: String = project.properties["hibernateValidatorVersion"] as String
        implementation(project(":backend:project"))
        //implementation("org.springframework.boot:spring-boot-starter-web:$springBootVersion")
        implementation("org.springframework.boot:spring-boot-starter-webflux:$springBootVersion")
        compileOnly("org.springframework.boot:spring-boot-devtools:$springBootVersion")
        compileOnly("org.jetbrains.kotlinx:kotlinx-html-jvm:$kotlinxHtmlVersion")
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

//}
