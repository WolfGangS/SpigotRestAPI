package io.wlf.mc.SpigotRestAPI.Models;

import org.bukkit.entity.Player;

public class wPlayer extends wEntity {

    public float exp;
    public int level;
    public double health;

    public wPlayer(Player p) {
        this.fromBukkit(p);
    }

    public void fromBukkit(Player p) {
        super.fromBukkit(p);
        if (p != null) {
            this.exp = p.getExp();
            this.health = p.getHealth();
            this.level = p.getLevel();
        }
    }

}
