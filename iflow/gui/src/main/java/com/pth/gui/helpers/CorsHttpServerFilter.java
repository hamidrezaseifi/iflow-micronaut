package com.pth.gui.helpers;

import io.micronaut.context.annotation.ConfigurationProperties;
import io.micronaut.http.HttpMethod;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.MutableHttpResponse;
import io.micronaut.http.filter.HttpServerFilter;
import io.micronaut.http.filter.ServerFilterChain;
import io.reactivex.Flowable;
import org.reactivestreams.Publisher;

import javax.inject.Singleton;
import io.micronaut.http.annotation.Filter;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.Optional;

@Singleton
@Filter(value = {"/**/data/**", "/auth/**"})
public class CorsHttpServerFilter implements HttpServerFilter
{
    private GuiAppProperties guiAppProperties;

    public CorsHttpServerFilter(GuiAppProperties guiAppProperties) {
        this.guiAppProperties = guiAppProperties;
    }

    @Override
    public Publisher<MutableHttpResponse<?>> doFilter(HttpRequest<?> request,
                                                      ServerFilterChain chain) {
        Publisher<MutableHttpResponse<?>> publisher = chain.proceed(request);


        return Flowable.fromPublisher(publisher)
                       .doOnNext(response -> {

                           response.getHeaders().add("Access-Control-Allow-Origin", guiAppProperties.getCliServer());
                           response.getHeaders().add("Access-Control-Allow-Credentials","true");
                           response.getHeaders().add("Access-Control-Allow-Methods", "GET,POST,OPTIONS,DELETE,PUT");
                           response.getHeaders().add("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept, Authorization, Content-Length, Host, Pragma, Cache-Control, Content-disposition");

                           if(request.getMethod() == HttpMethod.OPTIONS){
                               response = response.status(200).body(null);

                           }


                       });

        //return chain.proceed(request);
    }

}
