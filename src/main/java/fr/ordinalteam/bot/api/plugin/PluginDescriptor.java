package fr.ordinalteam.bot.api.plugin;

/**
 * Create by Arinonia 18/06/2021
 */
public class PluginDescriptor {

    private final String name;
    private final String main;
    private final String version;
    private final String author;
    private final String description;

    /**
     * Only for implementation of API
     */
    public PluginDescriptor(String name, String main, String version, String author, String description) {
        this.name = name;
        this.main = main;
        this.version = version;
        this.author = author;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getMain() {
        return main;
    }

    public String getVersion() {
        return version;
    }

    public String getAuthor() {
        return author;
    }

    public String getDescription() {
        return description;
    }
}
