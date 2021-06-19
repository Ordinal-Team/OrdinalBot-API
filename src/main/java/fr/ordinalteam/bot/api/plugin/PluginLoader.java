package fr.ordinalteam.bot.api.plugin;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import fr.ordinalteam.bot.api.exception.InvalidPlugin;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * Create by Arinonia 18/06/2021
 */
public class PluginLoader {

    private Plugin plugin;
    private final List<Plugin> plugins = new ArrayList<>();

    /**
     * Only for implementation of API
     */
    public PluginLoader() {
        final File pluginFolder = new File("plugins");
        if (!pluginFolder.exists()) {
            if (pluginFolder.mkdir()) {
                System.out.println("create folder " + pluginFolder.getName());
            } else {
                System.err.println("cannot create folder " + pluginFolder.getName());
            }
        }
        final ArrayList<File> jarFiles = this.listFilesJar(pluginFolder);
        jarFiles.forEach(file -> {
            try {
                this.scanJar(file);
            } catch (InvalidPlugin invalidPlugin) {
                invalidPlugin.printStackTrace();
            }
        });
    }

    private ArrayList<File> listFilesJar(final File file) {
        final ArrayList<File> jarFiles = new ArrayList<>();
        for (final File f : Objects.requireNonNull(file.listFiles())) {
            if (f.getName().endsWith(".jar")) {
                jarFiles.add(f);
            }
        }
        return jarFiles;
    }

    private void scanJar(final File file) throws InvalidPlugin {
        JarFile jarFile = null;
        InputStream stream = null;

        try {
            jarFile = new JarFile(file);
            JarEntry entry = jarFile.getJarEntry("plugin.json");

            if (entry == null) {
                throw new InvalidPlugin("plugin.json not found in " + file.getName());
            }
            stream = jarFile.getInputStream(entry);
            final JsonObject json = this.inputStreamToJSON(stream);
            final PluginDescriptor pluginDescriptor = new PluginDescriptor(json.get("name").getAsString(), json.get("main").getAsString(),
                    json.get("version").getAsString(), json.get("author").getAsString(),
                    (json.get("description") != null ? json.get("description").getAsString() : ""));

            Class<?> jarClass;
            try {
                jarClass = Class.forName(pluginDescriptor.getMain(), true, new URLClassLoader(new URL[]{file.toURI().toURL()}));
            } catch (ClassNotFoundException e) {
                throw new InvalidPlugin("Cannot find main class `" + pluginDescriptor.getMain() + "'");
            }
            Class<? extends Plugin> pluginClass;
            try {
                pluginClass = jarClass.asSubclass(Plugin.class);
            } catch (ClassCastException e) {
                throw new InvalidPlugin("main class `" + pluginDescriptor.getMain() + "' does not extend Plugin");
            }
            try {
                this.plugin = (Plugin)pluginClass.newInstance();
                this.plugin.onEnable();
                this.plugin.setPluginDescriptor(pluginDescriptor);
                this.plugins.add(this.plugin);
            } catch (InstantiationException e) {
                throw new InvalidPlugin("Abnormal plugin type");
            } catch (IllegalAccessException e) {
                throw new InvalidPlugin("No public constructor");
            }
        } catch (IOException e) {
            throw new InvalidPlugin(e);
        }
    }

    private JsonObject inputStreamToJSON(final InputStream inputStream) {
        Scanner s = new Scanner(inputStream).useDelimiter("\\A");
        String jsonString = s.hasNext() ? s.next() : "";
        return new JsonParser().parse(jsonString).getAsJsonObject();
    }

    public Plugin getPlugin() {
        return this.plugin;
    }

    public List<Plugin> getPlugins() {
        return this.plugins;
    }
}
