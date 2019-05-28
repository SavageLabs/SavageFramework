package net.prosavage.baseplugin.strings;


/**
 * A utility class for placeholder management
 */
public class Placeholder {

    private String key;
    private String value;

    /**
     * Create a placeholder.
     *
     * @param key - The text to replace.
     * @param value - The text will change to this.
     */
    public Placeholder(String key, String value) {
        this.key = "{" + key + "}";
        this.value = value;
    }


    /**
     * Processes placeholders in a line.
     *
     * @param line - the line to process.
     * @return - A line with all the placeholders replaced.
     */
    public String process(String line) {
        return line.replace(key, value);
    }


}
