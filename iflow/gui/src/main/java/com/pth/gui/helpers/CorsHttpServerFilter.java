package com.pth.gui.helpers;

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
@Filter(value = {"/**/data/**", "/login"})
public class CorsHttpServerFilter implements HttpServerFilter
{
    @Override
    public Publisher<MutableHttpResponse<?>> doFilter(HttpRequest<?> request,
                                                      ServerFilterChain chain) {
        Publisher<MutableHttpResponse<?>> publisher = chain.proceed(request);


        return Flowable.fromPublisher(publisher)
                       .doOnNext(response -> {


                           //if(request.getPath().contains("/data/")){

                           response.getHeaders().add("Access-Control-Allow-Origin","http://localhost:4200");
                           response.getHeaders().add("Access-Control-Allow-Credentials","true");
                           response.getHeaders().add("Access-Control-Allow-Methods", "GET,POST,OPTIONS,DELETE,PUT");
                           response.getHeaders().add("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept, Authorization, Content-Length, Host");
                           //}

                       });

        //return chain.proceed(request);
    }
}
