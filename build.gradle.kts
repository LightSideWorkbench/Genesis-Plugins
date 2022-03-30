/*
 * This file was [INITIALLY!] generated by the Gradle 'init' task.
 */

plugins {
    java
    `maven-publish`
}

repositories {

    mavenCentral()

    flatDir {
        dirs("../LightSide/")
    }

    flatDir {
        dirs("libs")
    }

    maven {
        url = uri("https://raw.github.com/Deses/RiverLayout/mvn-repo/")
    }
}

dependencies {
    implementation("edu.stanford.nlp:stanford-parser:3.9.2")
    implementation("edu.stanford.nlp:stanford-corenlp:3.9.2")
    implementation("se.datadosen.riverlayout:riverlayout:1.1")
    implementation("com.oracle.database.xml:xmlparserv2:21.5.0.0")
    implementation("org.apache.commons:commons-math3:3.6.1")
    implementation("org.simpleframework:simple-http:6.0.1")
    implementation("org.simpleframework:simple:5.1.6")
    implementation("org.simpleframework:simple-transport:6.0.1")
    implementation(":LightSide")
    implementation("nz.ac.waikato.cms.weka:weka-dev:3.9.6")
    implementation(":yeritools-min-1.0")
    implementation("org.hamcrest:hamcrest-core:2.2")
    implementation("junit:junit:4.13.2")
    implementation("de.bwaldvogel:liblinear:2.21")
}

group = "edu.cmu.side"
version = "1.0-SNAPSHOT"
description = "Genesis-Plugins"
java.sourceCompatibility = JavaVersion.VERSION_1_8

val testsJar by tasks.registering(Jar::class) {
    archiveClassifier.set("tests")
    from(sourceSets["test"].output)
}

publishing {
    publications.create<MavenPublication>("maven") {
        from(components["java"])
        artifact(testsJar)
    }
}

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
}
