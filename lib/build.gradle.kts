/*
 * This file was generated by the Gradle 'init' task.
 *
 * This generated file contains a sample Java library project to get you started.
 * For more details take a look at the 'Building Java & JVM projects' chapter in the Gradle
 * User Manual available at https://docs.gradle.org/7.5.1/userguide/building_java_projects.html
 * This project uses @Incubating APIs which are subject to change.
 */

plugins {
    // Apply the java-library plugin for API and implementation separation.
    java
    `java-library`
    idea
}

//java.sourceCompatibility = JavaVersion.VERSION_17

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
    }
}

version = "1.2.1"

repositories {
    // Use Maven Central for resolving dependencies.
    mavenCentral()

    flatDir {
        dirs("../../LightSide")
//        dirs("/Users/rcmurray/git/LightSideWorkBench_2022-10-04/LightSide")
    }

    flatDir {
        dirs("libs")
    }

    maven {
        url = uri("https://raw.github.com/Deses/RiverLayout/mvn-repo/")
    }
}

dependencies {
    // This dependency is exported to consumers, that is to say found on their compile classpath.
    api("org.apache.commons:commons-math3:3.6.1")
    // This dependency is used internally, and not exposed to consumers on their own compile classpath.
    implementation("com.google.guava:guava:31.0.1-jre")
    implementation("nz.ac.waikato.cms.weka:weka-stable:3.8.6")
    implementation("nz.ac.waikato.cms.weka:LibSVM:1.0.10")
    implementation("tw.edu.ntu.csie:libsvm:3.31")
    implementation(":LightSide")
    implementation(":libsvm")
//    implementation("libsvm")
    api("edu.stanford.nlp:stanford-parser:3.9.2")
    api("edu.stanford.nlp:stanford-corenlp:4.4.0")
    api("se.datadosen.riverlayout:riverlayout:1.1")
    api("com.oracle.database.xml:xmlparserv2:21.5.0.0")
    api("org.apache.commons:commons-math3:3.6.1")
    api("org.simpleframework:simple-http:6.0.1")
    api("org.simpleframework:simple:5.1.6")
    api("org.simpleframework:simple-transport:6.0.1")
//    api(":LightSide")
    api("nz.ac.waikato.cms.weka:weka-stable:3.8.6")
    api("nz.ac.waikato.cms.weka:bayesianLogisticRegression:1.0.5")
//    api("nz.ac.waikato.cms.weka:LibSVM:1.0.10")
    api("nz.ac.waikato.cms.weka:LibLINEAR:1.9.7")
    api("nz.ac.waikato.cms.weka:chiSquaredAttributeEval:1.0.4")
    api(":yeritools-min-1.0")
    api(":libsvm")

    api("org.hamcrest:hamcrest-core:2.2")
    api("junit:junit:4.13.2")
    api("de.bwaldvogel:liblinear:2.21")
}

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
//    options.compilerArgs.addAll(arrayOf(
//        "--add-exports", "java.base/java.util=ALL-UNNAMED"
//    ))
}

//tasks.test {
//    useJUnitPlatform()
//    testLogging {
//        events("passed", "skipped", "failed")
//    }
//}

testing {
    suites {
        // Configure the built-in test suite
        val test by getting(JvmTestSuite::class) {
            // Use JUnit4 test framework
            useJUnit("4.13.2")
        }
    }
}

tasks.withType<Test>().all {
    jvmArgs("--add-opens=java.xml/com.sun.org.apache.xml.internal.serialize=ALL-UNNAMED",
        "--add-opens=java.base/java.util=ALL-UNNAMED")
}


