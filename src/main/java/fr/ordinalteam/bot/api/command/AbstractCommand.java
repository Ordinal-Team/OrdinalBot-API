package fr.ordinalteam.bot.api.command;

import fr.ordinalteam.bot.api.utils.OrdinalConstant;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractCommand {

    private final String name;
    private final String description;
    private final List<OptionData> options = new ArrayList<>();

    public AbstractCommand(final String name, final String description) {
        this.name = name;
        this.description = description;
    }

    public AbstractCommand(final String name) {
        this(name, OrdinalConstant.APP_NAME);
    }

    public abstract void onCommand(final SlashCommandInteractionEvent event);

    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }

    public void addOption(final OptionData option) {
        this.options.add(option);
    }

    public List<OptionData> getOptions() {
        return this.options;
    }

}
