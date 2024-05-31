package fr.ordinalteam.bot.api.plugin;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class PluginLoader {

    private final List<Plugin> plugins = new ArrayList<>();
    private final Map<Plugin, URLClassLoader> pluginClassLoaders = new HashMap<>();
    private final File pluginFolder = new File("plugins");

    public void start() {
        if (!this.pluginFolder.exists()) {
            if (this.pluginFolder.mkdir()) {
                System.out.println("Created folder " + this.pluginFolder.getName());
            } else {
                System.err.println("Cannot create folder " + this.pluginFolder.getName());
            }
        }
        loadAllPlugins();
    }

    private void loadAllPlugins() {
        final ArrayList<File> jarFiles = this.listFilesJar(this.pluginFolder);
        jarFiles.forEach(file -> {
            try {
                this.loadPlugin(file);
            } catch (final InvalidPluginException e) {
                e.printStackTrace();
            }
        });
    }

    private ArrayList<File> listFilesJar(final File file) {
        final ArrayList<File> jarFiles = new ArrayList<>();
        final File[] files = file.listFiles();

        if (files != null) {
            for (final File f : files) {
                if (f.getName().endsWith(".jar")) {
                    jarFiles.add(f);
                }
            }
        }

        return jarFiles;
    }

    public void loadPlugin(final File file) throws InvalidPluginException {
        URLClassLoader classLoader = null;
        try {
            final JarFile jarFile = new JarFile(file);
            final JsonObject json = loadPluginJson(jarFile, file);

            if (json == null) {
                System.err.println("Skipping JAR: " + file.getName() + " (missing plugin.json)");
                return;
            }
            final PluginDescriptor pluginDescriptor = createPluginDescriptor(json);
            classLoader = createClassLoader(file);
            final Class<? extends Plugin> pluginClass = loadPluginClass(classLoader, pluginDescriptor, file);
            final Plugin plugin = instantiatePlugin(pluginClass, pluginDescriptor, jarFile);
            addPlugin(plugin, classLoader);
        } catch (IOException e) {
            throw new InvalidPluginException("IOException occurred while processing file: " + file.getName(), e);
        } finally {
            closeClassLoader(classLoader);
        }
    }

    private JsonObject loadPluginJson(final JarFile jarFile, final File file) throws InvalidPluginException {
        final JarEntry entry = jarFile.getJarEntry("plugin.json");
        if (entry == null) {
            return null;
        }
        try (final InputStream stream = jarFile.getInputStream(entry)) {
            return inputStreamToJSON(stream);
        } catch (final IOException e) {
            throw new InvalidPluginException("Failed to read 'plugin.json' in " + file.getName(), e);
        }
    }

    private PluginDescriptor createPluginDescriptor(final JsonObject json) {
        return new PluginDescriptor(
                json.get("name").getAsString(),
                json.get("main").getAsString(),
                json.get("version").getAsString(),
                json.get("author").getAsString(),
                json.has("description") ? json.get("description").getAsString() : ""
        );
    }

    private URLClassLoader createClassLoader(final File file) throws InvalidPluginException {
        try {
            return new URLClassLoader(new URL[]{file.toURI().toURL()}, this.getClass().getClassLoader());
        } catch (final IOException e) {
            throw new InvalidPluginException("Failed to create URLClassLoader for " + file.getName(), e);
        }
    }

    private Class<? extends Plugin> loadPluginClass(final URLClassLoader classLoader, final PluginDescriptor pluginDescriptor, final File file) throws InvalidPluginException {
        try {
            final Class<?> jarClass = classLoader.loadClass(pluginDescriptor.main());
            return jarClass.asSubclass(Plugin.class);
        } catch (final ClassNotFoundException e) {
            throw new InvalidPluginException("Cannot find main class '" + pluginDescriptor.main() + "' in " + file.getName(), e);
        } catch (final ClassCastException e) {
            throw new InvalidPluginException("Main class '" + pluginDescriptor.main() + "' does not extend Plugin in " + file.getName(), e);
        }
    }

    private Plugin instantiatePlugin(final Class<? extends Plugin> pluginClass, final PluginDescriptor pluginDescriptor, final JarFile file) throws InvalidPluginException {
        try {
            final Plugin plugin = pluginClass.getDeclaredConstructor().newInstance();
            plugin.setPluginDescriptor(pluginDescriptor);
            final File plugin_folder = plugin.getPluginFolder();
            final File configFile = new File(plugin_folder, "config.json");
            if (!configFile.exists()) {
                copyDefaultConfig(file, configFile);
            }
            plugin.onEnable();
            return plugin;
        } catch (final InstantiationException | IllegalAccessException e) {
            throw new InvalidPluginException("Failed to instantiate plugin class '" + pluginDescriptor.main() + "' in " + file.getName(), e);
        } catch (final InvocationTargetException e) {
            throw new InvalidPluginException("Invocation target exception while instantiating plugin class '" + pluginDescriptor.main() + "' in " + file.getName(), e);
        } catch (final NoSuchMethodException e) {
            throw new InvalidPluginException("No suitable constructor found for plugin class '" + pluginDescriptor.main() + "' in " + file.getName(), e);
        }
    }

    private void copyDefaultConfig(final JarFile jarFile, final File configFile) throws InvalidPluginException {
        final JarEntry configEntry = jarFile.getJarEntry("config.json");
        if (configEntry == null) {
            //! Need to be change
            throw new InvalidPluginException("Default config.json not found in JAR");
        }
        try (final InputStream input = jarFile.getInputStream(configEntry)) {
            Files.copy(input, configFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new InvalidPluginException("Failed to copy default config.json", e);
        }
    }

    private void addPlugin(final Plugin plugin, final URLClassLoader classLoader) {
        this.plugins.add(plugin);
        this.pluginClassLoaders.put(plugin, classLoader);
    }

    private void closeClassLoader(final URLClassLoader classLoader) {
        if (classLoader != null) {
            try {
                classLoader.close();
            } catch (final IOException e) {
                e.printStackTrace();
            }
        }
    }

    private JsonObject inputStreamToJSON(final InputStream inputStream) {
        final Scanner s = new Scanner(inputStream).useDelimiter("\\A");
        final String jsonString = s.hasNext() ? s.next() : "";
        return JsonParser.parseString(jsonString).getAsJsonObject();
    }

    public void unloadPlugin(final Plugin plugin) {
        plugin.onDisable();
        final URLClassLoader classLoader = this.pluginClassLoaders.get(plugin);

        try {
            classLoader.close();
        } catch (final IOException e) {
            e.printStackTrace();
        }

        this.plugins.remove(plugin);
        this.pluginClassLoaders.remove(plugin);
    }

    public void reloadPlugin(final File file) {
        Plugin pluginToReload = null;
        for (final Plugin plugin : plugins) {
            if (plugin.getPluginDescriptor().name().equals(file.getName())) {
                pluginToReload = plugin;
                break;
            }
        }
        if (pluginToReload != null) {
            unloadPlugin(pluginToReload);
        }
        try {
            loadPlugin(file);
        } catch (final InvalidPluginException e) {
            e.printStackTrace();
        }
    }

    public List<Plugin> getPlugins() {
        return this.plugins;
    }
}
