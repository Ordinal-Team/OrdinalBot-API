package fr.ordinalteam.bot.api.bots;

import net.dv8tion.jda.api.hooks.ListenerAdapter;

/**
 * Create by Arinonia 18/06/2021
 */
public interface IJDAListenerManager {

    /**
     * Register a new JDA listener in the bot (like onGuildMemberJoin)
     * @param listenerAdapter Listener you want to register
     */
    void registerJDAListener(ListenerAdapter listenerAdapter);

    /**
     * Unregister your listener
     * @param listenerAdapter Listener you want to unregister
     */
    void unRegisterJDAListener(ListenerAdapter listenerAdapter);

}
