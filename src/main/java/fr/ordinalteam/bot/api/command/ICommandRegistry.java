package fr.ordinalteam.bot.api.command;

import fr.ordinalteam.bot.api.plugin.Plugin;

import java.util.List;
import java.util.Set;

public interface ICommandRegistry {

    /**
     * Registers a command with the specified plugin.
     *
     * @param command The command to be registered.
     * @param plugin  The plugin to which the command belongs.
     */
    void registerCommand(final AbstractCommand command, final Plugin plugin);

    /**
     * Unregisters a command from the registry.
     *
     * @param command The command to be unregistered.
     */
    void unRegisterCommand(final AbstractCommand command);

    /**
     * Retrieves all commands associated with the specified plugin.
     *
     * @param plugin The plugin whose commands are to be retrieved.
     * @return A set of commands associated with the given plugin.
     */
    Set<AbstractCommand> getPluginCommands(final Plugin plugin);

    /**
     * Retrieves all registered commands.
     *
     * @return A list of all registered commands.
     */
    List<AbstractCommand> getCommands();
}
