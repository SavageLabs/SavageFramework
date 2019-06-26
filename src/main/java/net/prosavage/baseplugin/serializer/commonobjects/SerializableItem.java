package net.prosavage.baseplugin.serializer.commonobjects;

import net.prosavage.baseplugin.XMaterial;
import org.bukkit.Material;

import java.util.List;

public class SerializableItem {

    private XMaterial material;
    private String name;
    private List<String> lore;
    private int amt;

    public SerializableItem(XMaterial material, String name, List<String> lore, int amount) {
        this.material = material;
        this.name = name;
        this.lore = lore;
        this.amt = amount;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public XMaterial getMaterial() {
        return material;
    }

    public void setMaterial(XMaterial material) {
        this.material = material;
    }

    public List<String> getLore() {
        return lore;
    }

    public void setLore(List<String> lore) {
        this.lore = lore;
    }

    public int getAmt() {
        return amt;
    }

    public void setAmt(int amt) {
        this.amt = amt;
    }
}
