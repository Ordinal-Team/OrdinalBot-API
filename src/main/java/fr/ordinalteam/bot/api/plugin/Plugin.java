package fr.ordinalteam.bot.api.plugin;

import fr.ordinalteam.bot.api.Ordinal;
import fr.ordinalteam.bot.api.bots.IJDAListenerManager;
import fr.ordinalteam.bot.api.commands.ICommandRegistry;
import fr.ordinalteam.bot.api.utils.Logger;

import javax.annotation.Nullable;
import java.io.File;

/**
 * Create by Arinonia 18/06/2021
 */
public abstract class Plugin {

    private final Ordinal ordinal;
    private PluginDescriptor pluginDescriptor;
    public final Logger logger = new Logger();

    public Plugin() {
        this.ordinal = Ordinal.getOrdinal();
    }

    /**
     * Methode call when your plugin is load by OrdinalBot
     */
    public void onEnable(){}

    public Ordinal getOrdinal() {
        return this.ordinal;
    }

    /**
     * @return the command registry where you can register / unregister your command
     */
    public ICommandRegistry getCommandRegistry() {
        return this.ordinal.getCommandRegistry();
    }

    /**
     *
     * @return the JDAListener where you can register / unregister your listener
     */
    public IJDAListenerManager getJDAListenerManager() {
        return this.ordinal.getJDAListenerManager();
    }

    /**
     *
     * @return PluginDescriptor (can be null careful)
     */
    @Nullable
    public PluginDescriptor getPluginDescriptor() {
        return this.pluginDescriptor;
    }

    /**
     * only for implementation of api, don't use it.
     * @param pluginDescriptor the pluginDescriptor
     */
    public void setPluginDescriptor(final PluginDescriptor pluginDescriptor) {
        this.pluginDescriptor = pluginDescriptor;
    }

    /**
     *
     * @return your plugin folder, where you can put your config or i don't know what
     */
    public File getPluginFolder() {
        final File file = new File("plugins", pluginDescriptor.getName());
        if (!file.exists()) {
            if (!file.mkdirs()) {
                this.logger.logError("cannot create plugin folder");
            }
        }
        return file;
    }
}
