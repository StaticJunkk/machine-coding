package org.example.model.enms;

import lombok.Getter;
import org.thymeleaf.util.StringUtils;

@Getter
public enum ServiceType {
    CAR_SERVICE("CAR_SERVICE", 1L);
    private final String name;
    private final Long id;

    ServiceType(String name, Long id) {
        this.name = name;
        this.id = id;
    }

    public static ServiceType getByName(String name) throws IllegalAccessException {
        for(ServiceType serviceType: ServiceType.values()){
            if(StringUtils.equals(serviceType.name, name)){
                return serviceType;
            }
        }
        throw new IllegalAccessException(String.format("No such enum found for service type - %s", name));
    }
}
