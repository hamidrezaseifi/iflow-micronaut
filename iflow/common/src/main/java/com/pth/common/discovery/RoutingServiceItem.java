package com.pth.common.discovery;

import io.micronaut.discovery.ServiceInstance;

import java.net.URI;

public class RoutingServiceItem {
    private String id;
    private String baseUrl;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public ServiceInstance toServiceInstance(){
        return new ServiceInstance() {
            @Override
            public String getId() {
                return id;
            }

            @Override
            public URI getURI() {
                return URI.create(baseUrl);
            }
        };
    }
}
