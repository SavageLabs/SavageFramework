package net.prosavage.baseplugin.serializer;

import net.prosavage.baseplugin.SavagePlugin;


public class Serializer {

    private boolean data;
    private Persist persist;


    public Serializer(boolean data, Persist persist) {
        this.data = data;
        this.persist = persist;
    }

    public Serializer(boolean data) {
        this.data = data;
        this.persist = SavagePlugin.getPersist();
    }

    public Serializer() {
        this.data = false;
        this.persist = SavagePlugin.getPersist();
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
        return persist.loadOrSaveDefault(data, def, clazz, name);
    }



}
