package org.example.model.enms;

import lombok.Getter;
import org.thymeleaf.util.StringUtils;

@Getter
public enum FrequencyUnitType {
    MINUTE("MINUTE", 1L),
    HOUR("HOUR", 2L),
    DAY("DAY", 3L);
    private final String name;
    private final Long id;

    FrequencyUnitType(String name, Long id) {
        this.name = name;
        this.id = id;
    }

    public static FrequencyUnitType getByName(String name) throws IllegalAccessException {
        for(FrequencyUnitType serviceType: FrequencyUnitType.values()){
            if(StringUtils.equals(serviceType.name, name)){
                return serviceType;
            }
        }
        throw new IllegalAccessException(String.format("No such enum found for service type - %s", name));
    }

    public static Long getMultiplierForMinutes(FrequencyUnitType frequencyUnitType) {
        if(frequencyUnitType.equals(FrequencyUnitType.HOUR)){
            return 60L;
        } else if(frequencyUnitType.equals(FrequencyUnitType.DAY)){
            return 60L*24L;
        } else {
            return 1L;
        }
    }
}
