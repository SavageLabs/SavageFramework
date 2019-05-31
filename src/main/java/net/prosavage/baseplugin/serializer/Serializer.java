package net.prosavage.baseplugin.serializer;

import net.prosavage.baseplugin.BasePlugin;


public class Serializer {


    /**
     * Saves your class to a .json file.
     */
    public void save(Object instance) {
        BasePlugin.getPersist().save(instance);
    }

    /**
     * Loads your class from a json file
     *
   */
    public <T> T load(T def, Class<T> clazz, String name) {
        return BasePlugin.getPersist().loadOrSaveDefault(def, clazz, name);
    }



}
