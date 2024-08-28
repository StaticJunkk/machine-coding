package org.example.model.service.appointment.request;

import lombok.Builder;
import lombok.Value;

import java.time.LocalDateTime;

@Value
@Builder
public class CreateAppointmentRequestDTO {
    Long serviceProviderId;
    Long slotId;
    LocalDateTime startDateTime;
    Long userId;
}
