
plugins {
    `java-library`
    `maven-publish`
}

// --- Dependency resolution repos (read) ---
allprojects {
    layout.buildDirectory.set(
        rootProject.layout.projectDirectory.dir("run/bld/gradle/${project.name}")
    )
}

// Simple sanity task
tasks.register("ciHelp") {
    doLast { println("Algites root Gradle build detected.") }
}

allprojects {
    tasks.withType<Test>().configureEach {
        useTestNG()
    }
}

publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            from(components["java"])
        }
    }
}

/* --- START OF: common sniplet for repo publishment --- */
fun String.capFirst(): String =
    replaceFirstChar { if (it.isLowerCase()) it.titlecase() else it.toString() }

val locVisibility = providers.gradleProperty("ALGITES_VISIBILITY").orNull ?: "pub"
val locDirection  = providers.gradleProperty("ALGITES_DIRECTION").orNull ?: "upload"

val locRepoUrl  = providers.gradleProperty("ALGITES_REPO_URL").orNull
val locRepoUser = providers.gradleProperty("ALGITES_REPO_USER").orNull
val locRepoPass = providers.gradleProperty("ALGITES_REPO_PASS").orNull

val locHasRemoteRepo = (locRepoUrl != null && locRepoUser != null && locRepoPass != null)

/* Internal Gradle repo name (stable, camelCase) */
val locRepoName = buildString {
    append("algites")
    append(locVisibility.capFirst())
    append(locDirection.capFirst())
}

publishing {
    repositories {

        if (locRepoUrl != null && locRepoUser != null && locRepoPass != null) {
            maven {
                name = locRepoName
                url = uri(locRepoUrl)
                credentials {
                    username = locRepoUser
                    password = locRepoPass
                }
            }
        } else {
            logger.lifecycle("Remote repo disabled (ALGITES_REPO_* not set). Repo name would be '$locRepoName'.")
        }
    }
}

val locIsCi = providers.environmentVariable("CI").orNull == "true"
if (locIsCi && !locHasRemoteRepo) {
    throw GradleException("CI build requires ALGITES_REPO_* for publishing.")
}
tasks.named("publish") {
    if (!locIsCi && !locHasRemoteRepo) {
        dependsOn("publishToMavenLocal")
    }
}
/* --- END OF: common sniplet for repo publishment --- */
