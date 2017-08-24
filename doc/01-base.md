# 建立基本框架
## 建立`Maven`的父子`pom.xml`
pom.xml
```xml
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
  xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/maven-v4_0_0.xsd">
  <modelVersion>4.0.0</modelVersion>
  <groupId>com.keerJob</groupId>
  <artifactId>keer-job</artifactId>
  <packaging>pom</packaging>
  <version>1.0-SNAPSHOT</version>

	<name>${project.artifactId}</name>
  <url>http://maven.apache.org</url>
	<description>A lightweight distributed task scheduling framework.</description>

	<properties>
		<jetty-server.version>9.2.22.v20170606</jetty-server.version>
	</properties>

	<modules>
		<module>keer-job-admin</module>
	</modules>

  <dependencies>
    <dependency>
      <groupId>junit</groupId>
      <artifactId>junit</artifactId>
      <version>3.8.1</version>
      <scope>test</scope>
    </dependency>
  </dependencies>
</project>
```
keer-job-admin/pom.xml
```xml
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/maven-v4_0_0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <parent>
        <groupId>com.keerJob</groupId>
        <artifactId>keer-job</artifactId>
        <version>1.0-SNAPSHOT</version>
    </parent>
    <artifactId>keer-job-admin</artifactId>
    <packaging>war</packaging>
    <build>
        <plugins>
            <plugin>
                <groupId>org.eclipse.jetty</groupId>
                <artifactId>jetty-maven-plugin</artifactId>
                <version>${jetty-server.version}</version>
            </plugin>
        </plugins>
    </build>
</project>
```
