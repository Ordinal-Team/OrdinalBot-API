package fr.ordinalteam.bot.api.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class PluginConfig {

    private final File configFile;
    private final Gson gson;
    private JsonObject configData;

    public PluginConfig(final File configFile) {
        this.configFile = configFile;
        this.gson = new GsonBuilder().setPrettyPrinting().create();
        loadConfig();
    }

    private void loadConfig() {
        try (final FileReader reader = new FileReader(this.configFile)) {
            this.configData = this.gson.fromJson(reader, JsonObject.class);
        } catch (final IOException e) {
            e.printStackTrace();
            this.configData = new JsonObject();
        }
    }

    public void saveConfig() {
        try (final FileWriter writer = new FileWriter(this.configFile)) {
            this.gson.toJson(this.configData, writer);
        } catch (final IOException e) {
            e.printStackTrace();
        }
    }

    public String getString(final String key) {
        return this.configData.has(key) ? this.configData.get(key).getAsString() : null;
    }

    public <T> T getObject(final String key, final Class<T> classOfT) {
        return this.configData.has(key) ? this.gson.fromJson(this.configData.get(key), classOfT) : null;
    }

    public void set(final String key, final Object value) {
        this.configData.add(key, this.gson.toJsonTree(value));
        saveConfig();
    }
}
