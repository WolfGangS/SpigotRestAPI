package io.wlf.mc.SpigotRestAPI.Services;

import io.wlf.mc.SpigotRestAPI.Models.wResponse;

abstract public class WebResponseService {
    public WebResponseService(){}

    protected wResponse success(){
        return success(null);
    }

    protected wResponse success(Object data){
        return new wResponse("Okay",data);
    }

    protected wResponse failure(){
        return failure(null);
    }

    protected wResponse failure(Object data){
        return new wResponse("Fail",data);
    }

    protected wResponse failIfNull(Object data) {
        if(data == null){
            return failure();
        }
        return success(data);
    }
}
