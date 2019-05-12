package io.wlf.mc.SpigotRestAPI.Models;

import org.bukkit.Location;

public class wPosition {
    public double x;
    public double y;
    public double z;
    public String world;

    public wPosition(Location l) {
        this.fromLocation(l);
    }

    public void fromLocation(Location loc) {
        if (loc != null) {
            this.x = loc.getX();
            this.y = loc.getY();
            this.z = loc.getZ();
            this.world = loc.getWorld().getName();
        }
    }
}
