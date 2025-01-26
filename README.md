# NoxesiumAPI
This is an API with support for [Skript](https://github.com/SkriptLang/Skript) in mind. You can always ask me to add something or check out [NoxesiumUtils by SuperNeon4ik](https://github.com/SuperNeon4ik/NoxesiumUtils)

## Features
- Setting server/entity rules
- [Qib system](https://github.com/Noxcrew/noxesium/wiki/Qib-System)
- Noxesium specific events
- [Custom sound system](https://github.com/Noxcrew/noxesium/wiki/Custom-Sounds)
- [Client-side glow for entities and blocks](https://github.com/SkytAsul/GlowingEntities?tab=readme-ov-file#make-entities-glow)

## Requirements
- Paper 1.21+ (or a fork)

## Planned updates
- Add a function to get SoundSource and ResourceLocation for Skript

## Developer stuff
**Adding NoxesiumAPI as a dependency**

### Gradle:
```gradle
maven {
    name "astrofoxRepositoryReleases"
    url "http://144.21.60.201:25568/releases"
}
```
```gradle
implementation "me.iris:noxesiumapi:LATEST_VERSION"
```
### Maven:
```xml
<repository>
  <id>astrofox-repository-releases</id>
  <name>Astrofox Repository</name>
  <url>http://144.21.60.201:25568/releases</url>
</repository>
```
```xml
<dependency>
  <groupId>me.iris</groupId>
  <artifactId>noxesiumapi</artifactId>
  <version>LATEST_VERSION</version>
</dependency>
```

## Credits
- [Noxesium by Noxcrew](https://github.com/Noxcrew/noxesium) - Noxesium lol
- [GlowingEntities by SkytAsul](https://github.com/SkytAsul/GlowingEntities) - Client-side glowing
- [NoxesiumUtils by SuperNeon4ik](https://github.com/SuperNeon4ik/NoxesiumUtils) - Inspiration
- [ExpHost by Astrofox Studios](https://www.exphost.net/) - Hosting the repository
