
# OrdinalBot-API

![forthebadge](https://forthebadge.com/images/badges/made-with-java.svg)
![Discord](https://img.shields.io/badge/Discord-5865F2?style=for-the-badge&logo=discord&logoColor=white)
[![GitHub last commit](https://img.shields.io/github/last-commit/Ordinal-Team/OrdinalBot-API.svg?style=flat)]()
[![GitHub commit activity the past week, 4 weeks](https://img.shields.io/github/commit-activity/y/Ordinal-Team/OrdinalBot-API.svg?style=flat)]() 

## Overview
**OrdinalBot-API** is a framework similar to Spigot, designed to enable the creation of plugins for Discord bots.

## Version
Current Version: **0.1.0**

## Features
- Create custom modules for your Bot.
- Simplified API similar to Spigot.
- Create customs / commands and use JDAListener

## Getting Started

### Installation
1. Download the library from the [releases page](https://github.com/Ordinal-Team/OrdinalBot-API/releases) and place it in the `libs` directory of your project.
2. Add the library to your local dependency manager.

#### Gradle
Add the following to your `build.gradle`:
```groovy
repositories {
    mavenCentral()
}

dependencies {
    implementation(fileTree("/libs"))
}
```

#### Maven
Add the following to your `pom.xml`:
```xml
<repositories>
    <repository>
        <id>libs-release</id>
        <url>file://${project.basedir}/libs</url>
        <releases>
            <enabled>true</enabled>
        </releases>
    </repository>
</repositories>

<dependencies>
    <dependency>
        <groupId>com.ordinalteam</groupId>
        <artifactId>OrdinalBot-API</artifactId>
        <version>0.1.0</version>
        <scope>system</scope>
        <systemPath>${project.basedir}/libs/OrdinalBot-API-0.1.0.jar</systemPath>
    </dependency>
</dependencies>
```

### Usage
Create a `plugin.json` in your resources directory:
```json
{
  "name": "Module Test",
  "main": "fr.ordinalteam.moduletest.Main",
  "version": "1.0.0",
  "author": "Arinonia"
}
```

Implement your main class:
```java
public class Main extends Plugin {

    @Override
    public void onEnable() {
        this.logger.log("Registering test module");
    }
}
```

Register commands and listeners:
```java
public class Main extends Plugin {

    @Override
    public void onEnable() {
        this.logger.log("Registering test module");
        this.getJDAListenerManager().registerJDAListener(new YourListenerAdapter());
        this.getCommandRegistry().registerCommand(new YourCommand(), this);
    }
}

public class YourListenerAdapter extends ListenerAdapter {
    // Your listeners
}

public class YourCommand extends Command {
    public YourCommand() {
        super("test", "!test <@user> to tag user");
    }

    @Override
    public void onCommand(final SlashCommandInteractionEvent event) {
        // Command logic
    }
}
```

## Contributing
Feel free to submit issues and pull requests. For suggestions and discussions, join our [Discord server](https://discord.gg/XQnNJYv).

## License
This project is licensed under the GPLv3 License - see the [LICENSE](LICENSE) file for details.

## Contact
For more information, visit our [Website](http://185.229.220.75:8090/).
