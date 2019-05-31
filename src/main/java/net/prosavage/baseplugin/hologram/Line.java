package net.prosavage.baseplugin.hologram;


import net.prosavage.baseplugin.strings.StringProcessor;
import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;

public class Line {

    private Line lineInstance;
    private Hologram hologram;
    private Location location;
    private String text;
    private ArmorStand armorStand;


    public Line(Hologram hologram, Location location, String text){
        this.hologram = hologram;
        this.location = location;
        this.text = text;

        ArmorStand armorStand = getLocation().getWorld().spawn(getLocation(), ArmorStand.class);//spawn the hologram
        armorStand.setVisible(false);//invisible
        armorStand.setGravity(false);
        //armorStand.setInvulnerable(true); // damage
        if (text.equalsIgnoreCase("")){
            armorStand.setCustomName(StringProcessor.color("&1"));
        }else{
            armorStand.setCustomName(StringProcessor.color(getText()));
        }
        armorStand.setCustomNameVisible(true);
        this.armorStand = armorStand;

        lineInstance = this;
    }

    public void rename(String text){
        setText(text);
        getArmorStand().setCustomName(StringProcessor.color(text));
        getArmorStand().setCustomNameVisible(true);
    }

    public void teleport(Location location){
        getArmorStand().teleport(location);
        this.location = location;
    }

    public void show(boolean show){
        getArmorStand().setCustomNameVisible(show);
    }

    public void removeFromHologram(){
        if (getArmorStand() != null){
            getArmorStand().remove();//remove the entity
        }
        setArmorStand(null);
        //setText(null);
        setLocation(null);
        setHologram(null);
        lineInstance = null;
    }

    public void setArmorStand(ArmorStand armorStand) {
        this.armorStand = armorStand;
    }

    public ArmorStand getArmorStand() {
        return armorStand;
    }

    public Line getLineInstance() {
        return lineInstance;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
        if (text == null){
            getArmorStand().setCustomName(null);
        }else{
            getArmorStand().setCustomName(StringProcessor.color(getText()));
        }
        getArmorStand().setCustomNameVisible(true);
    }

    public Hologram getHologram() {
        return hologram;
    }

    public Location getLocation() {
        return location;
    }

    public void setHologram(Hologram hologram) {
        this.hologram = hologram;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}
