package io.wlf.mc.SpigotRestAPI.Models;

import org.bukkit.entity.Entity;

public class wEntity {

    public String name;
    public String uuid;
    public wPosition position;

    public wEntity() {
    }

    public wEntity(Entity e) {
        this.fromBukkit(e);
    }

    public void fromBukkit(Entity e) {
        if (e != null) {
            this.position = new wPosition(e.getLocation());
            this.uuid = e.getUniqueId().toString();
            this.name = e.getName();
        }
    }
}
