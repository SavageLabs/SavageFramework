package net.prosavage.baseplugin;

import net.prosavage.baseplugin.serializer.Persist;
import org.bukkit.plugin.java.JavaPlugin;



public class BasePlugin extends JavaPlugin {


    private static BasePlugin instance;
    private static Persist persist;

    @Override
    public void onEnable() {
        getLogger().info("Running Base Framework Enable...");
        instance = this;
        getDataFolder().mkdirs();
        persist = new Persist();
    }

    public static Persist getPersist() {
        return persist;
    }

    public static BasePlugin getInstance() {
        return instance;
    }



}
