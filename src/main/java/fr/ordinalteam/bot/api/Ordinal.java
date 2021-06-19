package fr.ordinalteam.bot.api;

import fr.ordinalteam.bot.api.bots.IJDAListenerManager;
import fr.ordinalteam.bot.api.commands.ICommandRegistry;

/**
 * Create by Arinonia 18/06/2021
 */
public abstract class Ordinal {

    private static Ordinal INSTANCE;

    /**
     * Only for implementation of API
     */
    public Ordinal() {
        INSTANCE = this;
    }

    public abstract ICommandRegistry getCommandRegistry();

    public abstract IJDAListenerManager getJDAListenerManager();

    public static Ordinal getOrdinal() {
        return INSTANCE;
    }

}
