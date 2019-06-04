package net.prosavage.baseplugin.serializer.commonobjects;

import net.prosavage.baseplugin.MultiversionMaterials;
import org.bukkit.Material;

import java.util.List;

public class GlowingSerializableItem extends SerializableItem {
    private boolean isGlowing;

    public GlowingSerializableItem(MultiversionMaterials material, String name, List<String> lore, int amt, boolean isGlowing) {
        super(material, name, lore, amt);
        this.isGlowing = isGlowing;
    }

    public boolean isGlowing() {
        return isGlowing;
    }

    public void setGlowing(boolean glowing) {
        isGlowing = glowing;
    }
}
