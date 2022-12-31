![Back to the Fabric!](.pretty_readme/bttf.png)

This is the BttF port of the [Dynamic Asset Generator](https://github.com/lukebemish/dynamic_asset_generator)
mod for [Quilt](https://quiltmc.org)

![Icon](.pretty_readme/icon.png)

---

# Dynamic Asset Generator

_**Does nothing on its own**_

A library mod for dynamically generate assets, such as textures, at runtime.

---

# How to import

### To Maven

**Step 1.** Add the JitPack repository to your build file
```xml
<repositories>
    <repository>
        <id>jitpack.io</id>
        <url>https://jitpack.io</url>
    </repository>
</repositories>
```

**Step 2.** Add the dependency (adding the version of your needs)
```xml
<dependencies>
    <dependency>
        <groupId>com.github.Back-to-the-Fabric</groupId>
        <artifactId>DynamicAssetGenerator</artifactId>
        <version>...</version>
    </dependency>
</dependencies>
```

### To Gradle

**Step 1.** Add the JitPack repository to your build file
```groovy
repositories {
    maven {
        name "JitPack"
        url "https://jitpack.io"
    }
}
```

**Step 2.** Add the dependency (adding the version of your needs)
```groovy
dependencies {
    implementation "com.github.Back-to-the-Fabric:DynamicAssetGenerator:<version>"
}
```