package io.wlf.mc.SpigotRestAPI.Models;

import org.bukkit.Material;

public class wMaterial {
    public String clss;

    public wMaterial(Material mat){
        this.fromBukkit(mat);
    }

    public void fromBukkit(Material mat){
        if(mat != null){
            this.clss = mat.getClass().getTypeName();
        }
    }
}
