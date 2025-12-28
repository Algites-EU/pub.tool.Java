package eu.algites.tool.build

import org.gradle.api.Plugin
import org.gradle.api.Project

class BasicBuildPlugin : Plugin<Project> {

    override fun apply(project: Project) = with(project) {

        // Base plugins for all projects
        pluginManager.apply("base")
        pluginManager.apply("maven-publish")

        // Example: expose versions from versions.properties
        val junitVersion = "@version:org.junit.jupiter:junit-jupiter@"
        val slf4jVersion = "@version:org.slf4j:slf4j-api@"

        extensions.extraProperties["junit.jupiter.version"] = junitVersion
        extensions.extraProperties["slf4j.version"] = slf4jVersion

        // Common layout conventions (example)
        layout.buildDirectory.set(file("run/bld"))

        // Common repositories
        repositories.apply {
            mavenCentral()
        }
    }
}
