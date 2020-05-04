# SavageFramework
A collection of tools to make SpigotAPI development more pleasant.


This resource provides:
- Automatic Serialization/Deserialization of data classes.
- Management of pluign data directory w/ 1 line expressions.
- StringFormatter to color strings.
- Placeholder system for basic replace based placeholders.
- XMaterial, a cross version replacement for the `Material` class in spigot-api.
- WorldBorder Packet utility to send players personalized worldborder packets using reflection (1.8-1.15.2).
- FastParticle for reflection based particle packets. (1.7-1.5.2).

This resource must be shaded as it serves as a library for your plugin to use.
I have provided the maven coordinates for the latest build.
```xml
<repositories>
  <repository>
     <id>savagelabs</id>
     <url>https://nexus.savagelabs.net/repository/maven-public/</url>
  </repository>
<repositories>


<dependencies>
  <dependency>
    <groupId>net.prosavage</groupId>
    <artifactId>BasePlugin</artifactId>
    <version>1.5.4</version>
  </dependency>
<dependencies>
```
 
