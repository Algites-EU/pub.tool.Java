buildDir = file("run/bld")

plugins {
    `kotlin-dsl`
    `java-gradle-plugin`
    `maven-publish`
}

group = "eu.algites.tool.build"
version = "1.0.0-SNAPSHOT"

//java {
//    toolchain {
//        languageVersion.set(JavaLanguageVersion.of(17))
//    }
//}

publishing {
    publications {
        create<MavenPublication>("pluginMaven") {
            from(components["java"])
            groupId = "eu.algites.tool.build"
            artifactId = "pub.pltf.Knitstro_build.basic-gradle"
            version = project.version.toString()
        }
    }
    repositories {
        maven {
            name = "pub.pltf.Knitstro"
            url = uri("https://maven.pkg.github.com/Algites-EU/pub.pltf.Knitstro")
            credentials {
                username = (findProperty("Algites.github.pub.pltf.Knitstro.user") as String?)
                    ?: System.getenv("ALGITES_GITHUB_PUB_TOOL_JAVA_USER")
                password = (findProperty("Algites.github.pub.pltf.Knitstro.token") as String?)
                    ?: System.getenv("ALGITES_GITHUB_PUB_TOOL_JAVA_TOKEN")
            }
        }
    }
}

val versionsFile = file("src/product/config/versions.properties")
val templateFile = file("src/product/gradle/basic-plugin.tpl")
val generatedSrcDir = layout.buildDirectory.dir("run/generated-src/basic").get().asFile

tasks.register("generateBasicPlugin") {
    inputs.file(versionsFile)
    inputs.file(templateFile)
    outputs.dir(generatedSrcDir)

    doLast {
        val props = java.util.Properties().apply {
            versionsFile.inputStream().use { load(it) }
        }

        var text = templateFile.readText()
        props.forEach { k, v ->
            text = text.replace("@${k}@", v.toString())
        }

        val outFile = File(generatedSrcDir, "eu/algites/tool/build/BasicBuildPlugin.kt")
        outFile.parentFile.mkdirs()
        outFile.writeText(text)
    }
}

sourceSets["main"].java.srcDir(generatedSrcDir)

tasks.named("compileKotlin") {
    dependsOn("generateBasicPlugin")
}

gradlePlugin {
    plugins {
        create("basicBuild") {
            id = "eu.algites.tool.build.basic-gradle"
            implementationClass = "eu.algites.tool.build.BasicBuildPlugin"
        }
    }
}

publishing {
    publications {
        create<MavenPublication>("pluginMaven") {
            from(components["java"])
            artifactId = "pub.pltf.Knitstro_build.basic-gradle"
        }
    }
}
