package fr.ordinalteam.bot.api.exception;

/**
 * Create by Arinonia 18/06/2021
 */
public class InvalidPlugin extends Exception {

    public InvalidPlugin(String message) {
        super(message);
    }

    public InvalidPlugin(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidPlugin(Throwable cause) {
        super(cause);
    }
}
