package org.example.model.enms;

import lombok.Getter;
import org.thymeleaf.util.StringUtils;

@Getter
public enum StatusType {
    BOOKED("BOOKED", 1L),
    CANCELED("CANCELED", 2L);
    private final String name;
    private final Long id;

    StatusType(String name, Long id) {
        this.name = name;
        this.id = id;
    }

    public static StatusType getByName(String name) throws IllegalAccessException {
        for (StatusType serviceType : StatusType.values()) {
            if (StringUtils.equals(serviceType.name, name)) {
                return serviceType;
            }
        }
        throw new IllegalAccessException(String.format("No such enum found for service type - %s", name));
    }
}
