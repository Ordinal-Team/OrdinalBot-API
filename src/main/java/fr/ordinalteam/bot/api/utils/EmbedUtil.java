package fr.ordinalteam.bot.api.utils;

import net.dv8tion.jda.api.EmbedBuilder;

import java.awt.*;

/**
 * @author Arinonia
 * Created at 08/02/2022 - 11:32
 **/
public class EmbedUtil {

    public static EmbedBuilder buildEmbed(final EmbedEnum type, final String title, final String description, final String footer) {
        final EmbedBuilder builder = new EmbedBuilder();
        builder.setTitle(title);
        builder.setDescription(description);
        builder.setColor(type.getEmbedColor());
        builder.setFooter(footer);
        return builder;
    }

    public static EmbedBuilder buildEmbed(final EmbedEnum type, final String title, final String description) {
        return buildEmbed(type, title, description, References.ORDINAL_FOOTER);
    }

    public static EmbedBuilder buildEmbed(final EmbedEnum type, final String description) {
        return buildEmbed(type, type.name(), description, References.ORDINAL_FOOTER);
    }

        public enum EmbedEnum {
        ERROR(ColorHelper.ERROR),
        INFO(ColorHelper.DONE),
        SUCCESS(ColorHelper.VALIDATE);

        private final Color embedColor;

        EmbedEnum(final Color error) {
            this.embedColor = error;
        }

        public Color getEmbedColor() {
            return this.embedColor;
        }
    }
}
