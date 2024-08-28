package org.example.model.service.appointment.response;

import lombok.Builder;
import lombok.Value;
import org.example.model.enms.ServiceType;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Value
@Builder
public class FetchAvailabilityResponseDTO {
    Long serviceProviderId;
    Long slotId;
    LocalDateTime startDateTime;
    LocalDateTime endDateTime;
    ServiceType serviceType;
}
