package fr.ordinalteam.bot.api.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Logger {

    private final String name;

    public Logger() {
        this.name = "OrdinalBot-API";
    }

    public void log(String message, String color) {
        final String date = String.format("[%s]", new SimpleDateFormat("kk:mm:ss").format(new Date()));
        System.out.println(color == null ? date + String.format(" [%s] ", this.name) + message + ASCIIColor.RESET : color + date + String.format(" [%s] ", this.name) + message + ASCIIColor.RESET);

    }

    public void log(String message) {
        this.log(message, null);
    }

    public void logError(String message) {
        this.log(message, ASCIIColor.RED);
    }

    public void logWarn(String message) {
        this.log(message, ASCIIColor.RED);
    }

}
