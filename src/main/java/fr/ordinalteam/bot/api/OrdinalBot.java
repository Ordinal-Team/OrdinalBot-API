package fr.ordinalteam.bot.api;

import fr.ordinalteam.bot.api.command.ICommandRegistry;
import fr.ordinalteam.bot.api.listener.IJDAListenerRegistry;

public abstract class OrdinalBot {

    private static OrdinalBot instance;

    public OrdinalBot() {
        instance = this;
    }

    public abstract ICommandRegistry getCommandRegistry();
    public abstract IJDAListenerRegistry getJDAListenerRegistry();

    public static OrdinalBot getInstance() {
        return instance;
    }
}
