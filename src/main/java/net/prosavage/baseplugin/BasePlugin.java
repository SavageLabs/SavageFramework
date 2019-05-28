package net.prosavage.baseplugin;

import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;


public class BasePlugin extends JavaPlugin {


    Logger logger;
    

    @Override
    public void onEnable() {
        logger = getLogger();
    }





}
