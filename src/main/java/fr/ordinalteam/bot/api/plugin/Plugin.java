package fr.ordinalteam.bot.api.plugin;

import fr.ordinalteam.bot.api.OrdinalBot;
import fr.ordinalteam.bot.api.config.PluginConfig;

import java.io.File;

public abstract class Plugin {

    private final OrdinalBot ordinalBot;
    private PluginConfig defaultConfig;
    private PluginDescriptor pluginDescriptor;

    public Plugin() {
        this.ordinalBot = OrdinalBot.getInstance();
    }

    public void onEnable() {}


    public void onDisable() {}

    public File getPluginFolder() {
        if (this.pluginDescriptor == null) {
            throw new IllegalStateException("PluginDescriptor is not set");
        }
        return new File("plugins", this.pluginDescriptor.name());
    }

    private boolean createPluginFolder() {
        if (this.pluginDescriptor == null) {
            throw new IllegalStateException("PluginDescriptor is not set");
        }
        final File file = new File("plugins", pluginDescriptor.name());
        if (!file.exists()) {
            if (!file.mkdirs()) {
                System.err.println("Failed to create plugin folder: " + file.getAbsolutePath());
                return false;
            }
        }
        return true;
    }
    public PluginConfig getDefaultConfig() {
        if (this.defaultConfig == null) {
            this.defaultConfig = new PluginConfig(new File(getPluginFolder(), "config.json"));
        }
        return this.defaultConfig;
    }

    public OrdinalBot getOrdinalBot() {
        return this.ordinalBot;
    }

    public PluginDescriptor getPluginDescriptor() {
        return this.pluginDescriptor;
    }

    public void setPluginDescriptor(final PluginDescriptor pluginDescriptor) {
        this.pluginDescriptor = pluginDescriptor;
        if (!createPluginFolder()) {
            System.err.println("Failed to create plugin folder: " + pluginDescriptor.name());
        }
    }
}
