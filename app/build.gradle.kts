plugins {
    application
}

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(libs.junit.jupiter)
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

application {
    mainClass = "icc.pl2.patrones.SnakeLab"
}

tasks.named<Test>("test") {
    useJUnitPlatform()
}

tasks.jar {
    archiveFileName.set("SnakeLab.jar")
    destinationDirectory.set(rootDir)
    manifest {
        attributes["Main-Class"] = "icc.pl2.patrones.SnakeLab"
    }
}