package com.sys.config.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.bus.BusProperties;
import org.springframework.cloud.bus.endpoint.AbstractBusEndpoint;
import org.springframework.cloud.bus.event.Destination;
import org.springframework.cloud.bus.event.RefreshRemoteApplicationEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Configuration;

/**
 * @author rensf
 * @date 2022/8/26 17:36
 */
@Configuration
@EnableConfigurationProperties(BusProperties.class)
public class RefreshBusHandler extends AbstractBusEndpoint {

    public RefreshBusHandler(ApplicationEventPublisher publisher, BusProperties bus, Destination.Factory destinationFactory) {
        super(publisher, bus.getId(), destinationFactory);
    }

    public void busRefresh() {
        publish(new RefreshRemoteApplicationEvent(this, getInstanceId(), getDestination(null)));
    }

}
