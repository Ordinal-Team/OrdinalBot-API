package fr.ordinalteam.bot.api.utils;

import fr.ordinalteam.bot.api.commands.Command;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

//create by doudox le con
public class CommandBase extends Command {
    private final Action action;
    public CommandBase(String name, String description, Action action) {
        super(name, description);
        this.action = action;
    }

    @Override
    public void run(MessageReceivedEvent messageReceivedEvent, String[] strings) {
        this.action.run(messageReceivedEvent, strings);
    }

    public interface Action
    {
        void run(MessageReceivedEvent event, String[] strings);
    }
}

