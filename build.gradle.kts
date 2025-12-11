import org.gradle.api.JavaVersion
import org.gradle.api.tasks.compile.JavaCompile
import org.gradle.language.jvm.tasks.ProcessResources

plugins {
    java
    eclipse
}

group = "com.onarandombox.legacy"
version = "1.1.0"

repositories {
    mavenCentral()
    maven {
        name = "papermc"
        url = uri("https://repo.papermc.io/repository/maven-public/")
    }
    maven {
        name = "sonatype"
        url = uri("https://oss.sonatype.org/content/groups/public/")
    }
    maven {
        name = "onarandombox"
        url = uri("https://repo.onarandombox.com/content/groups/public")
    }
    maven {
        name = "purpurmc"
        url = uri("https://repo.purpurmc.org/snapshots")
    }
}

dependencies {
    compileOnly("org.purpurmc.purpur:purpur-api:1.19.4-R0.1-SNAPSHOT")
    compileOnly("org.mvplugins.multiverse.core:multiverse-core:5.0.0-SNAPSHOT")
    compileOnly("org.mvplugins.multiverse.inventories:multiverse-inventories:5.0.0-SNAPSHOT")
    compileOnly("org.mvplugins.multiverse.portals:multiverse-portals:5.0.0-SNAPSHOT")
}

val targetJavaVersion = 17

java {
    val javaVersion = JavaVersion.toVersion(targetJavaVersion)
    sourceCompatibility = javaVersion
    targetCompatibility = javaVersion
    if (JavaVersion.current() < javaVersion) {
        toolchain.languageVersion.set(JavaLanguageVersion.of(targetJavaVersion))
    }
}

tasks.withType<JavaCompile>().configureEach {
    options.encoding = "UTF-8"

    if (targetJavaVersion >= 10 || JavaVersion.current().isJava10Compatible) {
        options.release.set(targetJavaVersion)
    }
}

tasks.named<ProcessResources>("processResources") {
    val props = mapOf("version" to project.version)
    inputs.properties(props)
    filteringCharset = "UTF-8"
    filesMatching("plugin.yml") {
        expand(props)
    }
}

tasks.jar {
    archiveBaseName.set("Multiverse-Legacy-API")
}
