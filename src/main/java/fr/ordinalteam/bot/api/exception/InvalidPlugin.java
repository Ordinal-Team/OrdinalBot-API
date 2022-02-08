package fr.ordinalteam.bot.api.exception;

/**
 * Create by Arinonia 18/06/2021
 */
public class InvalidPlugin extends Exception {

    public InvalidPlugin(final String message) {
        super(message);
    }

    public InvalidPlugin(final String message, final Throwable cause) {
        super(message, cause);
    }

    public InvalidPlugin(final Throwable cause) {
        super(cause);
    }
}
