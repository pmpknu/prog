plugins {
    kotlin("jvm") version "1.8.22"
    kotlin("plugin.serialization") version "1.8.22"
    id("org.jetbrains.dokka") version "1.6.0"
    application
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(project(":shared"))
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.4.1")
    testImplementation(kotlin("test"))

    implementation(project(":shared"))
    // https://mvnrepository.com/artifact/org.postgresql/postgresql
    implementation("org.postgresql:postgresql:42.6.0")
    implementation("org.jetbrains.kotlin:kotlin-reflect:1.8.10")

    // https://mvnrepository.com/artifact/com.zaxxer/HikariCP
    implementation("com.zaxxer:HikariCP:5.0.1")

    // https://mvnrepository.com/artifact/org.apache.logging.log4j/log4j-api
    implementation("org.apache.logging.log4j:log4j-api:2.20.0")

    // https://mvnrepository.com/artifact/org.apache.logging.log4j/log4j-core
    implementation("org.apache.logging.log4j:log4j-core:2.20.0")

    // https://mvnrepository.com/artifact/org.apache.logging.log4j/log4j-slf4j2-impl
    implementation("org.apache.logging.log4j:log4j-slf4j2-impl:2.20.0")

    // https://mvnrepository.com/artifact/org.apache.logging.log4j/log4j-api-kotlin
    implementation("org.apache.logging.log4j:log4j-api-kotlin:1.2.0")

    implementation("com.google.guava:guava:31.1-jre")

    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.5.0")

    // https://mvnrepository.com/artifact/org.apache.logging.log4j/log4j-core
    implementation("org.apache.logging.log4j:log4j-core:2.20.0")


    testImplementation("io.mockk:mockk:1.13.4")
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(8)
}


application {
    mainClass.set("lab7.server.ApplicationKt")
}


tasks.register<Jar>("fatJar") {
    dependsOn.addAll(listOf("compileJava", "compileKotlin", "processResources")) // We need this for Gradle optimization to work
    archiveClassifier.set("app") // Naming the jar
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
    manifest { attributes(mapOf("Main-Class" to application.mainClass)) } // Provided we set it up in the application plugin configuration
    val sourcesMain = sourceSets.main.get()
    val contents = configurations.runtimeClasspath.get()
        .map { if (it.isDirectory) it else zipTree(it) } +
            sourcesMain.output
    from(contents)
}

/**
 * build вызывает сборку fatJar и генерацию dokkaHtml-документации
 */
tasks.build {
    finalizedBy(tasks.getByPath("fatJar").path)
}

/**
 * Зависимость от задачи build
 */
tasks.dokkaHtml.configure {
    dependsOn(tasks.build)
    outputDirectory.set(buildDir.resolve("dokka"))
}