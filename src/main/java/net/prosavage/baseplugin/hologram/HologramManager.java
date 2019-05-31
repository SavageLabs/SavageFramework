package net.prosavage.baseplugin.hologram;

import net.prosavage.baseplugin.hologram.Hologram;
import net.prosavage.baseplugin.hologram.Line;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;

import java.util.ArrayList;
import java.util.List;

public class HologramManager {

    public static List<Hologram> hologramList = new ArrayList<>();

    public static void removeAllHolograms(){
        for (int i = hologramList.size() - 1; i >= 0; i--){
            Hologram hologram = hologramList.get(i);
            if (hologram != null){
                hologram.delete();
            }
        }
    }

    public static boolean isHologram(Entity entity){
        for (Hologram hologram : hologramList){
            if (hologram.getArmorStand().equals(entity)){
                return true;
            }else{
                for (Line line : hologram.getLines()){
                    if (line.getArmorStand().equals(entity)){
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public static Hologram getHologramFromEntity(Entity entity){
        if (isHologram(entity)){
            for (Hologram hologram : hologramList){
                if (hologram.getArmorStand().equals(entity)){
                    return hologram;
                }else{
                    for (Line line : hologram.getLines()){
                        if (line.getArmorStand().equals(entity)){
                            return line.getHologram();
                        }
                    }
                }
            }
        }
        return null;
    }
}