package net.prosavage.baseplugin.serializer;

import net.prosavage.baseplugin.SavagePlugin;


public class Serializer {


    private boolean data;

    public Serializer(boolean data) {
        this.data = data;
    }

    public Serializer() {
        this.data = false;
    }

    /**
     * Saves your class to a .json file.
     */
    public void save(Object instance) {
        SavagePlugin.getPersist().save(data, instance);
    }

    /**
     * Loads your class from a json file
     *
   */
    public <T> T load(T def, Class<T> clazz, String name) {
        return SavagePlugin.getPersist().loadOrSaveDefault(data, def, clazz, name);
    }



}
