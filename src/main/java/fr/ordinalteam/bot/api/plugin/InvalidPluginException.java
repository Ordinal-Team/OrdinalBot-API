package fr.ordinalteam.bot.api.plugin;

public class InvalidPluginException extends Exception {

    public InvalidPluginException(final String message) {
        super(message);
    }

    public InvalidPluginException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public InvalidPluginException(final Throwable cause) {
        super(cause);
    }
}