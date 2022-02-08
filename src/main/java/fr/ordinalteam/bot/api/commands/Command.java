package fr.ordinalteam.bot.api.commands;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;


public abstract class Command {

    protected final String name;
    protected final String description;
    protected final char prefix = '!';
    protected final String[] alias;

    public Command(final String name, final String description, final String[] alias) {
        this.name = name;
        this.description = description;
        this.alias = alias;
    }

    /**
     *
     * @param name name of the command, what the user need to type for execute the command (like !modules)
     * @param description description of your command, how to use it
     */
    public Command(final String name, final String description) {
        this.name = name;
        this.description = description;
        this.alias = new String[]{};
    }

    /**
     * Methode call when your command is execute
     * @param event type MessageReceivedEvent, JDA event (trigger when a message is send).
     * @param args arguments in the command
     */
    public abstract void run(MessageReceivedEvent event, String[] args);

    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }

    public char getPrefix() {
        return this.prefix;
    }

    public String[] getAlias() {
        return this.alias;
    }

    /**
     *
     * @return true if the command is only for the Owner
     */
    public boolean isOpCommand() {
        return false;
    }

    /**
     *
     * @return true if the command is a staff command (moderator + owner)
     */
    public boolean isModCommand() {
        return false;
    }

    /**
     *
     * @return true is the command is only for booster
     */
    public boolean isBoosterCommand() {
        return false;
    }


}
