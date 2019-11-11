package net.prosavage.baseplugin.serializer;

import net.prosavage.baseplugin.SavagePlugin;


public class Serializer {


    /**
     * Saves your class to a .json file.
     */
    public void save(Object instance) {
        SavagePlugin.getPersist().save(instance);
    }

    /**
     * Loads your class from a json file
     *
   */
    public <T> T load(T def, Class<T> clazz, String name) {
        return SavagePlugin.getPersist().loadOrSaveDefault(def, clazz, name);
    }



}
