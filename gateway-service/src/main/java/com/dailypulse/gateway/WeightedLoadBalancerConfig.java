package com.dailypulse.gateway;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.loadbalancer.core.DelegatingServiceInstanceListSupplier;
import org.springframework.cloud.loadbalancer.core.ServiceInstanceListSupplier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Configuration
@LoadBalancerClient(name = "news-platform", configuration = WeightedLoadBalancerConfig.class)
public class WeightedLoadBalancerConfig {

    private final Random random = new Random();

    @Bean
    public ServiceInstanceListSupplier serviceInstanceListSupplier(ServiceInstanceListSupplier serviceInstanceListSupplier) {

        return new DelegatingServiceInstanceListSupplier(serviceInstanceListSupplier) {
            @Override
            public Flux<List<ServiceInstance>> get() {
                return super.get().map(serviceInstances -> {
                    List<ServiceInstance> weightedInstances = new ArrayList<>();
                    serviceInstances.forEach(instance -> {
                        int weight = Integer.parseInt(instance.getMetadata().getOrDefault("weight", "1"));
                        for (int i = 0; i < weight; i++) {
                            weightedInstances.add(instance);
                        }
                    });

                    if (!weightedInstances.isEmpty()) {
                        ServiceInstance selected = weightedInstances.get(random.nextInt(weightedInstances.size()));
                        return List.of(selected);
                    }
                });

                return List.of();
            }
        }
    }
}
