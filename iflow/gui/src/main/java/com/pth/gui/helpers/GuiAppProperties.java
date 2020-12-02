package com.pth.gui.helpers;

import io.micronaut.context.annotation.ConfigurationProperties;
import io.micronaut.http.HttpMethod;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.MutableHttpResponse;
import io.micronaut.http.annotation.Filter;
import io.micronaut.http.filter.HttpServerFilter;
import io.micronaut.http.filter.ServerFilterChain;
import io.reactivex.Flowable;
import org.reactivestreams.Publisher;

import javax.inject.Singleton;

@ConfigurationProperties("iflow.gui")
public class GuiAppProperties
{
    private String cliServer;
    private String test;

    public GuiAppProperties() {
    }

    public String getCliServer() {
        return cliServer;
    }

    public void setCliServer(String cliServer) {
        this.cliServer = cliServer;
    }

    public String getTest() {
        return test;
    }

    public void setTest(String test) {
        this.test = test;
    }
}
