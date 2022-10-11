package com.oms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.EnumMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

@Service
public class OSMServiceFactory {
    private final Map<ServiceType, OSMService> serviceMap = new EnumMap<>(ServiceType.class);

    @Autowired
    public OSMServiceFactory(Set<OSMService> actionSet){
        actionSet.forEach(this::createAction);
    }

    private void createAction(OSMService service){
        serviceMap.put(service.getServiceType(), service);
    }

    public OSMService getService(ServiceType type){
        return Optional.ofNullable(serviceMap.get(type))
                .orElseThrow(() -> new RuntimeException("Action (" + type + ") is not implemented yet"));
    }
}
