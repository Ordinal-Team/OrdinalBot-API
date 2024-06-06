package fr.ordinalteam.bot.api.command;

import fr.ordinalteam.bot.api.plugin.Plugin;

import java.util.List;
import java.util.Set;

public interface ICommandRegistry {

    void registerCommand(final AbstractCommand command, final Plugin plugin);
    void unRegisterCommand(final AbstractCommand command);
    Set<AbstractCommand> getPluginCommands(final Plugin plugin);
    List<AbstractCommand> getCommands();
}
