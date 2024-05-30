package fr.ordinalteam.bot.api.listener;

import net.dv8tion.jda.api.hooks.ListenerAdapter;

public interface IJDAListenerRegistry {
    /**
     * Register a new JDA listener in the bot (like onGuildMemberJoin)
     * @param listenerAdapter Listener you want to register
     */
    void registerJDAListener(final ListenerAdapter listenerAdapter);

    /**
     * Unregister your listener
     * @param listenerAdapter Listener you want to unregister
     */
    void unRegisterJDAListener(final ListenerAdapter listenerAdapter);

}
