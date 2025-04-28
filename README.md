# NoxesiumAPI
This is an API with support for [Skript](https://github.com/SkriptLang/Skript) (using [Skript-Reflect](https://github.com/SkriptLang/skript-reflect)) in mind. You can always ask me to add something or check out [NoxesiumUtils by SuperNeon4ik](https://github.com/SuperNeon4ik/NoxesiumUtils)

## Features
- Setting server/entity rules
- [Qib system](https://github.com/Noxcrew/noxesium/wiki/Qib-System)
- Noxesium specific events
- [Custom sound system](https://github.com/Noxcrew/noxesium/wiki/Custom-Sounds)
- [Client-side glow for entities and blocks](https://github.com/SkytAsul/GlowingEntities?tab=readme-ov-file#make-entities-glow)

## Requirements
- Paper 1.21+ (or a fork)

## Planned updates
- Add support for ClientboundOpenLinkPacket
- Util for skulls and offsets
- Restrict rule commands to server versions

<details>
<summary>Developer stuff</summary>

### Gradle (Kotlin):
```gradle
maven {
    name = "noxcrewMavenPublic"
    url = uri("https://maven.noxcrew.com/public")
}

maven {
    name = "astrofoxRepository"
    url = uri("http://144.21.60.201:25566/<repository>")
}
```
```gradle
implementation "me.iris:noxesiumapi:LATEST_VERSION"
```
### Maven:
```xml
<repository>
  <id>noxcrew-maven-public</id>
  <name>Noxcrew Public Maven Repository</name>
  <url>https://maven.noxcrew.com/public</url>
</repository>
<repository>
  <id>astrofox-repository-releases</id>
  <name>Astrofox Repository</name>
  <url>http://144.21.60.201:25566/releases</url>
</repository>
```
```xml
<dependency>
  <groupId>me.iris</groupId>
  <artifactId>noxesiumapi</artifactId>
  <version>LATEST_VERSION</version>
</dependency>
```
</details>

<details>
<summary>Credits</summary>

- [Noxesium by Noxcrew](https://github.com/Noxcrew/noxesium) - Noxesium
- [GlowingEntities by SkytAsul](https://github.com/SkytAsul/GlowingEntities) - Client-side glowing
- [NoxesiumUtils by SuperNeon4ik](https://github.com/SuperNeon4ik/NoxesiumUtils) - Inspiration
- [ExpHost by Astrofox Studios](https://www.exphost.net/) - Hosting the repository
</details>
