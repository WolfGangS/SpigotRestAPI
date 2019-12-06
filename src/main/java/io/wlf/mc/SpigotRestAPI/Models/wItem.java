package io.wlf.mc.SpigotRestAPI.Models;

import org.bukkit.inventory.ItemStack;

public class wItem {
    public String item;
    public String str;
    public int count;
    public wMaterial data;

    public wItem(ItemStack item){
        this.fromBukkit(item);
    }

    public void fromBukkit(ItemStack item){
        if(item != null){
            this.str = item.toString();
            this.item = item.getType().toString();
            this.count = item.getAmount();
            this.data = new wMaterial(item.getType());
        }
    }
}
