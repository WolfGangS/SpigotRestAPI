package io.wlf.mc.SpigotRestAPI.Models;

public class wResponse {
    public String status;
    public Object data;

    public wResponse(String status){
        this.status = status;
    }

    public wResponse(String status, Object data) {
        this.status = status;
        this.data = data;
    }

}
