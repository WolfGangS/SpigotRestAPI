package io.wlf.mc.SpigotRestAPI.Models;

import org.bukkit.inventory.PlayerInventory;

public class wPlayerInventory extends wInventory {
    public wItem helmet;
    public wItem chestplate;
    public wItem leggings;
    public wItem boots;

    public wPlayerInventory(PlayerInventory pi) {
        this.fromBukkit(pi);
    }

    public void fromBukkit(PlayerInventory pi) {
        super.fromBukkit(pi);
        if (pi != null) {
            this.helmet = new wItem(pi.getHelmet());
            this.chestplate = new wItem(pi.getChestplate());
            this.leggings = new wItem(pi.getLeggings());
            this.boots = new wItem(pi.getBoots());
        }
    }

}
