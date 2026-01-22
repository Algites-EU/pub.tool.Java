# pub.pltf.Knitstro

**Algites Java Tool Governance Stack**

This repository provides the foundational tooling for Java-based projects within the Algites ecosystem. It defines a unified governance layer for both **Gradle** and **Maven**, ensuring consistent project layout, dependency management, and build behavior across all Algites applications, libraries, frameworks, and tools. It also contains other tools used for Java world projects and processes.

The goal is to establish a single source of truth for:
- build conventions,
- toolchain configuration,
- plugin versions,
- custom plugins,
- and reusable build logic,
while allowing both Gradle and Maven users to achieve semantically equivalent results.

---

## Repository Purpose

This repository hosts:

- **Gradle convention plugins** – published as reusable plugins to configure projects.
- **Maven parent POMs** – published as parent artifacts to inherit standardized settings.
- **Shared version catalogs** – single authoritative definitions of dependency and plugin versions.
- **Templates and generators** – used to produce the final Maven and Gradle artifacts.
- **Plugins for various Java infrastructure tools** – used to produce plugins for CI and development tools.

It is intended to be used as the *build foundation* for all Algites Java-related repositories.

---

## Structure of the **build** module group

The repository is organized by build *levels*, each representing a layer of build conventions:

```
build/
  basic/
    src/product/
      config/versions.properties
      maven/basic-pom.tpl
      gradle/basic-plugin.tpl
  java/
    src/product/
      config/versions.properties
      maven/java-pom.tpl
      gradle/java-plugin.tpl
```

### Levels

- **basic**  
  Core conventions independent of Java specifics:
  - directory layout,
  - resource handling,
  - base plugins,
  - common defaults.

- **java**  
  Extends `basic` with Java-specific configuration:
  - toolchains,
  - compiler settings,
  - testing frameworks,
  - JVM-related conventions.

Each level defines:
- a single authoritative `versions.properties` file,
- templates for Maven parent POMs,
- templates for Gradle convention plugins.

From these sources, two artifacts are generated per level:
- `*-maven` – Maven parent POM (packaging: `pom`),
- `*-gradle` – Gradle convention plugin (packaging: `jar`).

---

## Published Artifacts

Artifacts are published under:

```
groupId: eu.algites.tool.build
```

Example for the `basic` level:

- Maven parent:
  ```
  eu.algites.tool.build:pub.pltf.Knitstro_build.basic-maven:<version>:pom
  ```

- Gradle plugin:
  ```
  eu.algites.tool.build:pub.pltf.Knitstro_build.basic-gradle:<version>:jar
  ```

Gradle plugin ID:

```
eu.algites.tool.build.basic-gradle
```

Usage:

```kotlin
plugins {
    id("eu.algites.tool.build.basic-gradle") version "<version>"
}
```

Analogously for the `java` level:

```
eu.algites.tool.build.java-gradle
```

---

## Design Principles

- **Single source of truth for versions**  
  Each level defines all dependency and plugin versions once in `versions.properties`.

- **Dual-engine parity**  
  Gradle and Maven builds must be semantically equivalent.

- **Layered conventions**  
  Higher levels extend lower ones (e.g., `java` builds on `basic`).

- **Governance over customization**  
  Projects consume conventions; they do not redefine them.

- **Reproducibility and stability**  
  Generated artifacts embed fixed versions and settings.

---

## 🔄 Continuous Integration (Algites CI)

This repository uses the **Algites unified GitHub Actions CI pipeline** (build/test/publish rules are centralized).

For exact usage and naming of the branches to utilize fully the defined possibilities, see
https://github.com/Algites-EU/pub.gov.Algites.specs/blob/main/ci/Algites-Github-CI-Policy.md

---

## License

This project is licensed under the **Apache License, Version 2.0**.

You may use, modify, and distribute this software in compliance with the License.  
See the [LICENSE](LICENSE) file for details.

---

## Status

This repository is under active development and serves as the foundation of the Algites Java build ecosystem.

Contributions, discussions, and improvements are welcome.
