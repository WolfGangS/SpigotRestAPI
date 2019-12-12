package io.wlf.mc.SpigotRestAPI.Models;

import org.bukkit.Material;

import java.util.ArrayList;
import java.util.List;

public class wMaterial {
    public String clss;
    public String a;
    public String b;
    public String[] c;

    public wMaterial(Material mat){
        this.fromBukkit(mat);
    }

    public void fromBukkit(Material mat){
        if(mat != null){
            this.clss = mat.getClass().getTypeName();
            List<String> prts = new ArrayList<>();
            prts.add(mat.name());
            prts.add(mat.toString());
            prts.add(mat.getData().getName());
            prts.add(mat.getData().getCanonicalName());
            prts.add(mat.getData().getSimpleName());
            prts.add(mat.getData().getTypeName());
            prts.add(mat.getData().toGenericString());
            prts.add(mat.getData().toString());


            prts.add(mat.getDeclaringClass().getName());
            prts.add(mat.getDeclaringClass().getCanonicalName());
            prts.add(mat.getDeclaringClass().getSimpleName());
            prts.add(mat.getDeclaringClass().getTypeName());
            prts.add(mat.getDeclaringClass().toGenericString());
            prts.add(mat.getDeclaringClass().toString());


            prts.add(mat.getClass().getName());
            prts.add(mat.getClass().getCanonicalName());
            prts.add(mat.getClass().getSimpleName());
            prts.add(mat.getClass().getTypeName());
            prts.add(mat.getClass().toGenericString());
            prts.add(mat.getClass().toString());

            this.c = prts.toArray(new String[0]);
        }
    }
}
