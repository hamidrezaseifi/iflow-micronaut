package com.pth.common.discovery;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pth.common.enums.RoutingLifecycle;
import io.micronaut.context.annotation.Requires;
import io.micronaut.context.env.Environment;
import io.micronaut.core.io.ResourceLoader;
import io.micronaut.core.io.scan.DefaultClassPathResourceLoader;
import io.micronaut.discovery.DiscoveryClient;
import io.micronaut.discovery.ServiceInstance;
import io.reactivex.Flowable;
import org.reactivestreams.Publisher;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Singleton
@Requires(notEnv = Environment.TEST)
@Requires(resources="classpath:services.json")
public class RoutingServiceDiscoveryClient implements DiscoveryClient {

    private static final RoutingLifecycle LIFECYCLE = RoutingLifecycle.PRODUCTION;

    private Map<String, ServiceInstance> services = new HashMap<>();

    public RoutingServiceDiscoveryClient(ResourceLoader resourceLoader) {
        super();

        try {
            Stream<URL> currencyStream = resourceLoader.getResources("classpath:services.json");
            List<URL> urlList = currencyStream.collect(Collectors.toList());

            ObjectMapper mapper = new ObjectMapper();
            List<RoutingServiceItem> routingItemList =
                    mapper.readValue(urlList.get(0), new TypeReference<List<RoutingServiceItem>>() {});

            services = routingItemList.stream().collect(Collectors.toMap(r -> r.getId(), r -> r.toServiceInstance()));
        }
        catch (Exception ex){

        }

    }

    public Publisher<List<ServiceInstance>> getInstances(String serviceId) {

        if(services.containsKey(serviceId)){
            List<ServiceInstance> list = new ArrayList<>();

            list.add(services.get(serviceId));

            return Flowable.just(list);
        }
        return Flowable.empty();
    }

    public Publisher<List<String>> getServiceIds() {

        return Flowable.just(services.keySet().stream().collect(Collectors.toList()));
    }

    @Override
    public String getDescription() {
        return null;
    }

    @Override
    public void close() throws IOException {
    }
}