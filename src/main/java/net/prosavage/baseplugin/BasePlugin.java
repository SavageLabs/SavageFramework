package net.prosavage.baseplugin;

import net.prosavage.baseplugin.serializer.Persist;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;


public class BasePlugin extends JavaPlugin {


    private static BasePlugin instance;
    private static Persist persist;
    private static Logger logger;

    public static Persist getPersist() {
        return persist;
    }

    public static BasePlugin getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        getLogger().info("Running Base Framework Enable...");
        instance = this;
        getDataFolder().mkdirs();
        persist = new Persist();
        logger = getLogger();
        printPluginInfo();

    }

    public void registerListeners(Listener... listeners) {
        for (Listener listener : listeners) {
            getServer().getPluginManager().registerEvents(listener, instance);
        }
    }

    private void printPluginInfo() {
        logger.info("================================================");
        logger.info("Plugin: " + getDescription().getName());
        logger.info("Version: " + getDescription().getVersion());
        logger.info("Author(s): " + getDescription().getAuthors());
        logger.info("Description: " + getDescription().getDescription());
        logger.info("================================================");
    }


}
