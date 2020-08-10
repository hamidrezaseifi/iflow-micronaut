package com.pth.common.discovery;

import com.pth.common.enums.RoutingLifecycle;
import io.micronaut.context.annotation.Requires;
import io.micronaut.context.env.Environment;
import io.micronaut.discovery.DiscoveryClient;
import io.micronaut.discovery.ServiceInstance;
import io.reactivex.Flowable;
import org.reactivestreams.Publisher;

import javax.inject.Named;
import javax.inject.Singleton;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;
import java.util.stream.Collectors;

@Singleton
@Requires(notEnv = Environment.TEST)
public class RoutingServiceDiscoveryClient implements DiscoveryClient {

    private static final RoutingLifecycle LIFECYCLE = RoutingLifecycle.PRODUCTION;

    private Map<String, ServiceInstance> services = new HashMap<>();

    public RoutingServiceDiscoveryClient() {
        super();

        services.put("profile",new ServiceInstance() {
            @Override
            public String getId() {
                return "profile";
            }

            @Override
            public URI getURI() {
                return URI.create("http://localhost:1020");
            }
        });
        services.put("core",new ServiceInstance() {
            @Override
            public String getId() {
                return "core";
            }

            @Override
            public URI getURI() {
                return URI.create("http://localhost:1010");
            }
        });


    }

    public Publisher<List<ServiceInstance>> getInstances(String serviceId) {

        List<ServiceInstance> list = new ArrayList<>();


        list.add(services.get(serviceId));

        return Flowable.just(list);
    }

    public Publisher<List<String>> getServiceIds() {

        List<String> serviceInfoDTOs =
                Arrays.asList("profile", "core");

        return Flowable.just(serviceInfoDTOs);
    }

    @Override
    public String getDescription() {
        return null;
    }

    @Override
    public void close() throws IOException {
    }
}