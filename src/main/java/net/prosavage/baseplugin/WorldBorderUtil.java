package net.prosavage.baseplugin;


import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class WorldBorderUtil {


    private Object worldBorder;
    private Method setCenter, setSize, setWarningTime, setWarningDistance, transistionSizeBetween;
    private Constructor<?> packetPlayOutWorldBorder;
    private Class<?> craftWorldClass;
    private int versionNumber;
    private String minecraftVersion;
    private JavaPlugin instance;

    public WorldBorderUtil(JavaPlugin instance) {
        try {
            worldBorder = getNMSClass("WorldBorder").getConstructor().newInstance();
            setCenter = worldBorder.getClass().getMethod("setCenter", double.class, double.class);
            setSize = worldBorder.getClass().getMethod("setSize", double.class);
            setWarningTime = worldBorder.getClass().getMethod("setWarningTime", int.class);
            setWarningDistance = worldBorder.getClass().getMethod("setWarningDistance", int.class);
            transistionSizeBetween = worldBorder.getClass().getMethod("transitionSizeBetween", double.class, double.class, long.class);
            packetPlayOutWorldBorder = getNMSClass("PacketPlayOutWorldBorder").getConstructor(getNMSClass("WorldBorder"),
                    getNMSClass("PacketPlayOutWorldBorder").getDeclaredClasses()[getVersionNumber() > 100 ? 0 : 1]);
            craftWorldClass = getCraftClass("CraftWorld");
            versionNumber = getVersionNumber();
            minecraftVersion = getVersion();
            this.instance = instance;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void sendWorldBorder(Player player, Color color, double size, Location centerLocation) {
        try {
            System.out.println(craftWorldClass);
            Object craftWorld = craftWorldClass.cast(centerLocation.getWorld());
            setField(worldBorder, "world", craftWorld.getClass().getMethod("getHandle").invoke(craftWorld), false);

            setCenter.invoke(worldBorder, centerLocation.getBlockX() + 0.5, centerLocation.getBlockZ() + 0.5);

            if (color == Color.Off) {
                setSize.invoke(worldBorder, Integer.MAX_VALUE);
            } else {
                setSize.invoke(worldBorder, size);
            }

            setWarningTime.invoke(worldBorder, 0);
            setWarningDistance.invoke(worldBorder, 0);

            switch (color) {
                case Red:
                    transistionSizeBetween.invoke(worldBorder, size, size - 1.0D, 20000000L);
                    break;
                case Green:
                    transistionSizeBetween.invoke(worldBorder, size - 0.1D, size, 20000000L);
                    break;
                case Blue:
                default:
                    // Do nothing as border is blue, default blue
            }

            Object packetPlayOutWorldBorderPacket = packetPlayOutWorldBorder.newInstance(worldBorder,
                    Enum.valueOf((Class<Enum>) getNMSClass("PacketPlayOutWorldBorder").getDeclaredClasses()[versionNumber > 100 ? 0 : 1],
                            "INITIALIZE"));
            sendPacket(player, packetPlayOutWorldBorderPacket);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void sendPacket(Player player, Object packet) {
        try {
            Object handle = player.getClass().getMethod("getHandle").invoke(player);
            Object playerConnection = handle.getClass().getField("playerConnection").get(handle);
            playerConnection.getClass().getMethod("sendPacket", getNMSClass("Packet")).invoke(playerConnection, packet);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Class<?> getNMSClass(String name) throws ClassNotFoundException {
        return Class.forName("net.minecraft.server." + minecraftVersion + "." + name);
    }

    public Class<?> getCraftClass(String name) throws ClassNotFoundException {
        return Class.forName("org.bukkit.craftbukkit." + getVersion() + "." + name);
    }

    public void setField(Object object, String fieldName, Object fieldValue, boolean declared) {
        try {
            Field field;

            if (declared) {
                field = object.getClass().getDeclaredField(fieldName);
            } else {
                field = object.getClass().getField(fieldName);
            }

            field.setAccessible(true);
            field.set(object, fieldValue);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getVersion() {
        return instance.getServer().getClass().getPackage().getName().split("\\.")[3];
    }

    public int getVersionNumber() {
        return Integer.parseInt(minecraftVersion.substring(1, getVersion().length() - 3).replace("_", ""));
    }

    public enum Color {
        Blue, Green, Red, Off
    }
}
