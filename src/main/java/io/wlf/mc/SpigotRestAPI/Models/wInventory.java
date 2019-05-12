package io.wlf.mc.SpigotRestAPI.Models;

import org.bukkit.inventory.Inventory;

public class wInventory {
    public wItem[] items;

    public wInventory() {
    }

    public wInventory(Inventory inv) {
        this.fromBukkit(inv);
    }

    public void fromBukkit(Inventory inv) {
        if (inv != null) {
            this.items = new wItem[inv.getSize()];
            for (int i = 0; i < inv.getSize(); i++) {
                this.items[i] = new wItem(inv.getItem(i));
            }
        }
    }
}
