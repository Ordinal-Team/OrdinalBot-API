[![forthebadge](https://forthebadge.com/images/badges/made-with-java.svg)](https://www.java.com/fr/)  [![forthebadge](https://forthebadge.com/images/badges/uses-git.svg)](https://github.com/)
[![GPLv3 License](https://img.shields.io/badge/License-GPL%20v3-yellow.svg)](https://opensource.org/licenses/)
[![Maintenance](https://img.shields.io/badge/Maintained%3F-yes-green.svg)](https://github.com/Ordinal-Team/Terminal/graphs/commit-activity)
[![Donate](https://img.shields.io/badge/$-support-ff69b4.svg?style=flat)](paypal.me/ArinoniaDev)
<br>
**OrdinalBot-API**

    Cette api vous permet de créer votre propre module sur l'OrdinalBot

L'API est en début de développement donc elle est pour le moment assez limitée, si vous avez une idée d'amélioration parlez en dans le salon général du discord Ordinal

L'API est assez similaire à spigot, dans un premier temps il faut faire un plugin.json comme ceci

```json
{
  "name": "Module Test",
  "main": "fr.ordinalteam.moduletest.Main",
  "version": "1.0.0",
  "author": "Arinonia"
}
```

et votre class "Main" (celle indiquée dans le plugin.json) doit hériter de Plugin

ensuite il ne vous reste qu'à appeler le onEnable et a register vos commands / JDA listener

Cette API est basée sur :

[Hermes](https://github.com/HermesBotApp/hermes-api/) <br>
[Spigot](https://github.com/SpigotMC/Spigot-API)