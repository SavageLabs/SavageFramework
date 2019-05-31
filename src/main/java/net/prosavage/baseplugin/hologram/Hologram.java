package net.prosavage.baseplugin.hologram;


import net.prosavage.baseplugin.hologram.Line;
import net.prosavage.baseplugin.strings.StringProcessor;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.ArmorStand;

import java.util.ArrayList;
import java.util.List;

public class Hologram {

    private double lineSpacing = 0.3;
    private double x,y,z;
    private World world;
    private String[] linesText = {};
    private List<Line> lines = new ArrayList<>();
    private String text;
    private ArmorStand armorStand;
    private Hologram hologramInstance;

    public Hologram(String text, Location location, String... linesText) {
        updateLocation(location.getWorld(), location.getX(), location.getY(), location.getZ());
        this.text = text;
        this.linesText = linesText;

       // Bukkit.getLogger().info("Holo loc: "+location.getY());

        //create the hologram and the texts...
        ArmorStand armorStand = getLocation().getWorld().spawn(getLocation(), ArmorStand.class);//spawn the hologram
        armorStand.setVisible(false);//invisible
        armorStand.setGravity(false);
        //armorStand.setInvulnerable(true); // damage
        armorStand.setCustomName(StringProcessor.color(getText()));
        armorStand.setCustomNameVisible(true);
        this.armorStand = armorStand;

        hologramInstance = this;

        if (getLinesText().length > 0) {
            //we have lines
            int currentLine = 1;
            for (String line : getLinesText()) {
                //create a new line
                if (line != null) {
                    Location lineLocation = getLocation().subtract(0, currentLine*getLineSpacing(), 0);
                    Line line1 = new Line(getHologramInstance(), lineLocation, line);
                    this.lines.add(line1);
                    currentLine++;
                }
            }
        }
        //update the lines
        setLines(this.lines);

        HologramManager.hologramList.add(getHologramInstance());

        getArmorStand().teleport(getLocation());

    }

    public void updateLocation(World world, double x, double y, double z){
        if (world != null){
            this.world = world;
            this.x = x;
            this.y = y;
            this.z = z;
        }
    }

    public void teleport(Location location){
        if (getHologramInstance() != null) {
            updateLocation(location.getWorld(), location.getX(), location.getY(), location.getZ());
            getArmorStand().teleport(location);
            int currentLine = 1;
            for (Line line : getLines()) {
                Location lineLocation = getLocation().subtract(0, currentLine * getLineSpacing(), 0);
                line.teleport(lineLocation);
                currentLine++;
            }
        }
    }

    public void addLine(String text){
        int lines = getLines().size();
        //Bukkit.broadcastMessage("lines: "+lines);
        Location lineLocation = getLocation().subtract(0, lines*getLineSpacing(), 0);
        Line line1 = new Line(getHologramInstance(), lineLocation, text);
       // Bukkit.broadcastMessage("added: "+text);
        this.lines.add(line1);
    }

    public void show(boolean show){
        if (getArmorStand() != null){
            getArmorStand().setCustomNameVisible(show);
        }
    }

    public void showLines(boolean show){
        for (Line line : getLines()){
            line.show(show);
        }
    }

    public void rename(String text){
        setText(text);
        getArmorStand().setCustomName(StringProcessor.color(text));
        getArmorStand().setCustomNameVisible(true);
    }

    public Line getLine(int index) {
        return lines.get(index);
    }

    public void removeAllLines(){
        try {
            //remove all of the hologram's lines
            if (getLines().size() > 0) {
                for (int i = getLines().size() - 1; i >= 0; i--) {
                    Line line = getLines().get(i);
                    line.removeFromHologram();
                }
            }
            lines.clear();
        }catch ( IndexOutOfBoundsException e){ }
    }

    public void removeLine(int index) {
        if (lines.size() >= index) {
            //remove the line
            Line line = lines.get(index);
            line.removeFromHologram();
            lines.remove(line);
        }
    }

    public void delete() {
        removeAllLines();
        if (getArmorStand() != null){
            getArmorStand().remove();
        setArmorStand(null);
    }
        hologramInstance = null;
        HologramManager.hologramList.remove(this);
    }

    public Hologram getHologramInstance() {
        return hologramInstance;
    }

    public Location getLocation(){
        return new Location(getWorld(), getX(), getY(), getZ());
    }

    public World getWorld() {
        return world;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getZ() {
        return z;
    }

    public void setText(String text) {
        this.text = text;
        getArmorStand().setCustomName(StringProcessor.color(getText()));
        getArmorStand().setCustomNameVisible(true);
    }

    public String getText() {
        return text;
    }

    public ArmorStand getArmorStand() {
        return armorStand;
    }

    public double getLineSpacing() {
        return lineSpacing;
    }

    public List<Line> getLines() {
        return lines;
    }

    public String[] getLinesText() {
        return linesText;
    }

    public void setArmorStand(ArmorStand armorStand) {
        this.armorStand = armorStand;
    }

    public void setLines(List<Line> lines) {
        this.lines = lines;
    }

    public void setLineSpacing(double lineSpacing) {
        this.lineSpacing = lineSpacing;
    }

    public void setLinesText(String[] linesText) {
        this.linesText = linesText;
    }
}