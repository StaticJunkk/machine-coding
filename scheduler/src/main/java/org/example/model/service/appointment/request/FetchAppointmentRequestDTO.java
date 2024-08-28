package org.example.model.service.appointment.request;

import lombok.Builder;
import lombok.Value;
import org.example.model.enms.ServiceType;

@Value
@Builder
public class FetchAppointmentRequestDTO {
    Long serviceProviderId;
    ServiceType serviceType;
}
