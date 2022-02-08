package fr.ordinalteam.bot.api.commands;

import fr.ordinalteam.bot.api.plugin.Plugin;

import java.util.Set;

/**
 * Create by Arinonia 18/06/2021
 */
public interface ICommandRegistry {

    /**
     * Register a command in your module
     * @param command your command
     * @param plugin the plugin
     */
    void registerCommand(final Command command, final Plugin plugin);

    /**
     * Unregister a command in your plugin
     * @param command your command
     */
    void unRegisterCommand(final Command command);

    /**
     *
     * @param plugin the plugin
     * @return all commands in the plugin
     */
    Set<Command> getPluginCommands(final Plugin plugin);
}
