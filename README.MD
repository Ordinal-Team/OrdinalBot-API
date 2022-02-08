[![forthebadge](https://forthebadge.com/images/badges/made-with-java.svg)](https://www.java.com/fr/)  [![forthebadge](https://forthebadge.com/images/badges/uses-git.svg)](https://github.com/)
[![GPLv3 License](https://img.shields.io/badge/License-GPL%20v3-yellow.svg)](https://opensource.org/licenses/)
[![Maintenance](https://img.shields.io/badge/Maintained%3F-yes-green.svg)](https://github.com/Ordinal-Team/Terminal/graphs/commit-activity)
[![Donate](https://img.shields.io/badge/$-support-ff69b4.svg?style=flat)](paypal.me/ArinoniaDev)
<br>
**OrdinalBot-API**

    Cette api vous permet de créer votre propre module sur l'OrdinalBot

L'API est en début de développement donc elle est pour le moment assez limitée, si vous avez une idée d'amélioration parlez en dans le salon général du discord Ordinal, ou via une issue.
Les pull request sont les bienvenues.

## Version

_Versions history:_

**Last release :** [Pre release: SNAPSHOT 1.0.0](https://github.com/Ordinal-Team/OrdinalBot-API/releases/tag/v0.0.1)

**Stable release :** nothing

## implementation:

#### gradle:
    todo
#### maven:
    todo
## Utilisation:
L'API est assez similaire à spigot, dans un premier temps il faut faire un plugin.json comme ceci dans le dossier ressource (si vous utilisez gradle)

```json
{
  "name": "Module Test",
  "main": "fr.ordinalteam.moduletest.Main",
  "version": "1.0.0",
  "author": "Arinonia"
}
```

et votre class "Main" (celle indiquée dans le plugin.json) doit hériter de Plugin comme ceci

````java
public class Main extends Plugin {

    @Override
    public void onEnable() {
        this.logger.log("Register du module test");
    }
}
````
ensuite il ne vous reste qu'à appeler le onEnable et a register vos commands / JDA listener

````java
public class Main extends Plugin {

    @Override
    public void onEnable() {
        this.logger.log("Register du module de test");
        this.getJDAListenerManager().registerJDAListener(new YourListenerAdapter());
        this.getCommandRegistry().registerCommand(new YourCommand(), this);
    }
}
````

````java
public class YourListenerAdapter extends ListenerAdapter {
    
    //Vos listeners

}
````

````java
public class YourCommand extends Command {
    
    public YourCommand() {
        super("test"/*nom de la commande*/, "!test <@user> for tag user"/*description de la commande*/);
    }

    @Override
    public void run(final MessageReceivedEvent event, final String[] args) {
        
    }
}
````