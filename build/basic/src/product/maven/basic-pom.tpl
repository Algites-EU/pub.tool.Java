<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0
                             https://maven.apache.org/xsd/maven-4.0.0.xsd">
  <modelVersion>4.0.0</modelVersion>

  <groupId>eu.algites.tool.build</groupId>
  <artifactId>pub.pltf.Knitstro_build.basic-maven</artifactId>
  <version>@project.build.tool.version@</version>
  <packaging>pom</packaging>

  <name>Algites Java Build - basic (Maven parent)</name>
  <description>Basic build conventions for Algites projects (engine: Maven).</description>

  <properties>
    <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>

    <!-- example versions coming from versions.properties -->
    <junit.jupiter.version>@version:org.junit.jupiter:junit-jupiter@</junit.jupiter.version>
    <slf4j.version>@version:org.slf4j:slf4j-api@</slf4j.version>
  </properties>

  <build>
    <pluginManagement>
      <plugins>
        <plugin>
          <groupId>org.apache.maven.plugins</groupId>
          <artifactId>maven-surefire-plugin</artifactId>
          <version>@version:org.apache.maven.plugins:maven-surefire-plugin@</version>
        </plugin>
      </plugins>
    </pluginManagement>
  </build>

</project>
