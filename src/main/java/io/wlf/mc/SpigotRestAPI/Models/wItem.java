package io.wlf.mc.SpigotRestAPI.Models;

import org.bukkit.inventory.ItemStack;

public class wItem {
    public String item;
    public int count;

    public wItem(ItemStack item){
        this.fromBukkit(item);
    }

    public void fromBukkit(ItemStack item){
        if(item != null){
            this.item = item.getType().toString();
            this.count = item.getAmount();
        }
    }
}
