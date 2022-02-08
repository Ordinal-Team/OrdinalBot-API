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
    public PluginDescriptor(final String name, final String main, final String version, final String author, final String description) {
        this.name = name;
        this.main = main;
        this.version = version;
        this.author = author;
        this.description = description;
    }

    public String getName() {
        return this.name;
    }

    public String getMain() {
        return this.main;
    }

    public String getVersion() {
        return this.version;
    }

    public String getAuthor() {
        return this.author;
    }

    public String getDescription() {
        return this.description;
    }
}
