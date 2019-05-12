package io.wlf.mc.SpigotRestAPI.Util;

import com.google.gson.Gson;
import spark.ResponseTransformer;

public class Json {

    public static String toJson(Object object) {
        return new Gson().toJson(object);
    }

    public static ResponseTransformer json () {
        return Json::toJson;
    }

}
