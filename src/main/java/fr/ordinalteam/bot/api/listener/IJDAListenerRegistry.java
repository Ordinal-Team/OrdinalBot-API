package fr.ordinalteam.bot.api.listener;

import net.dv8tion.jda.api.hooks.ListenerAdapter;

public interface IJDAListenerRegistry {
    void registerJDAListener(final ListenerAdapter listenerAdapter);
    void unRegisterJDAListener(final ListenerAdapter listenerAdapter);
}
