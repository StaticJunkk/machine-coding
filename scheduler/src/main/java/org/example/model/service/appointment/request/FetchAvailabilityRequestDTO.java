package org.example.model.service.appointment.request;

import lombok.Builder;
import lombok.Value;
import org.example.model.enms.ServiceType;

import java.util.List;

@Value
@Builder
public class FetchAvailabilityRequestDTO {
    Long serviceProviderId;
    ServiceType serviceType;
}
