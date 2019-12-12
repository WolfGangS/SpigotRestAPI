package io.wlf.mc.SpigotRestAPI.Services;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import io.wlf.mc.SpigotRestAPI.Models.HttpEvent;
import io.wlf.mc.SpigotRestAPI.Models.HttpEventType;
import io.wlf.mc.SpigotRestAPI.Util.Json;
import org.apache.http.client.fluent.Async;
import org.apache.http.client.fluent.Content;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.concurrent.FutureCallback;
import org.apache.http.entity.ContentType;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.plugin.Plugin;

public class HttpEventService {
    private Plugin plugin;
    private Boolean secure;
    private String domain;
    private ConfigurationSection endpoints;
    private int port;

    public HttpEventService(Plugin plugin, Boolean secure, String domain, int port, ConfigurationSection endpoints){
        this.plugin = plugin;
        this.secure = secure;
        this.domain = domain;
        this.port = port;
        this.endpoints = endpoints;
    }

    private String getEndpoint(HttpEventType type, HttpEvent event){
        return this.endpoints.getString(type.name() + "." + event.name(), "");
    }

    public void fire(HttpEventType type, HttpEvent event, Object data)/* throws Exception*/ {

        String endpoint = this.getEndpoint(type,event);

        if(endpoint.isEmpty()){
            return;
        }

        URIBuilder urlBuilder = new URIBuilder()
                .setScheme(this.secure ? "https" : "http")
                .setHost(this.domain)
                .setPort(this.port)
                .setPath(endpoint);

        final int nThreads = 3; // no. of threads in the pool
        final int timeout = 20000; // connection time out in milliseconds

        URI uri = null;
        try {
            uri = urlBuilder.build();
        } catch (URISyntaxException use) {
            use.printStackTrace();
        }

        ExecutorService executorService = Executors.newFixedThreadPool(nThreads);
        Async async = Async.newInstance().use(executorService);
        final Request request = Request.Post(uri).bodyString(Json.toJson(data), ContentType.APPLICATION_JSON).connectTimeout(timeout);

        Future<Content> future = async.execute(request, new FutureCallback<Content>() {
            public void failed(final Exception e) {
                System.out.println("Request failed: " + request);
            }

            public void completed(final Content content) {
                System.out.println("Request completed: " + request);
                System.out.println(content.asString());
            }

            public void cancelled() {
            }
        });

    }

}