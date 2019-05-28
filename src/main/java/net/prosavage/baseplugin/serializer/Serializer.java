package net.prosavage.baseplugin.serializer;

import net.prosavage.baseplugin.BasePlugin;

public class Serializer {


    private String fileName;
    private Object object;
    private Persist persist;

    /**
     * Serializes a file into json for you.
     *
     * @param fileName - Name of file without the .json ending.
     * @param def - Instance of object to serialize.
     */
    public <T> Serializer(String fileName, T def) {
        this.persist = new Persist();
        this.fileName = fileName;
        this.object = object;
    }


    /**
     * Saves your class to a .json file.
     */
    public void save() {
        persist.save(object, fileName);
    }

    public <T> T load(Class<T> clazz) {
        return persist.load(clazz);
    }



}
