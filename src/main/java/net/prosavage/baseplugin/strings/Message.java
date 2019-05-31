package net.prosavage.baseplugin.strings;

import org.bukkit.ChatColor;

public class Message {


    private String message;

    public Message(String message) {
        this.message = message;
    }


    @Override
    public String toString() {
        return ChatColor.translateAlternateColorCodes('&', message);
    }
}
