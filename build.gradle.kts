
allprojects {
    group = "com.clicaqui"
    version = "1.0"
    repositories {
        jcenter()
    }
}

plugins {
    base
}

dependencies {
    subprojects.forEach {
        archives(it)
    }
}

/*
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
 */