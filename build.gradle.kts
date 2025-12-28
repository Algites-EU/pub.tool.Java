// Root aggregator only (no artifact by default)
tasks.register("ciHelp") {
    doLast { println("Algites root Gradle build detected.") }
}
