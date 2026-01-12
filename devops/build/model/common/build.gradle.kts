java {
    sourceSets {
        val main by getting {
            java.setSrcDirs(listOf("src/product/java", "src/product/kotlin"))
            resources.setSrcDirs(listOf("src/product/resources","src/product/loader"))
        }
        val test by getting {
            java.setSrcDirs(listOf("src/develop/java", "src/develop/kotlin"))
            resources.setSrcDirs(listOf("src/develop/resources","src/develop/loader"))
        }
    }
}

group = "eu.algites.tool.build"

base {
    archivesName.set("pub.gov.Algites_build.model.intf")
}

val TESTNG_VERSION = "7.11.0"
val JAKARTA_ANNOTATION_VERSION = "3.0.0"
val JACKSON_VERSION = "3.0.3"

dependencies {
    testImplementation("org.testng:testng:" + TESTNG_VERSION)
    implementation("jakarta.annotation:jakarta.annotation-api:" + JAKARTA_ANNOTATION_VERSION)
    implementation("tools.jackson.core:jackson-databind:" + JACKSON_VERSION)
    implementation("tools.jackson.dataformat:jackson-dataformat-yaml:" + JACKSON_VERSION)
}

plugins {
    `maven-publish`
    `kotlin-dsl`
    `java-gradle-plugin`
}

publishing {

    publications {

        create<MavenPublication>("policyParentPom") {

            groupId = project.group.toString()
            artifactId = "${base.archivesName.get()}"
            version = project.version.toString()

            pom {
                packaging = "pom"
                name.set("Algites DevOps Build Model")
                description.set(
                    "Generated Algites DevOps build model intf classes."
                )
            }
        }
    }

}
