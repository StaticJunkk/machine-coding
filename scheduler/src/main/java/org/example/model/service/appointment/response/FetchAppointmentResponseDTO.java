package org.example.model.service.appointment.response;

import lombok.Builder;
import lombok.Value;
import org.example.model.enms.ServiceType;
import org.example.model.enms.StatusType;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Value
@Builder
public class FetchAppointmentResponseDTO {
    Long serviceProviderId;
    Long appointmentId;
    LocalDateTime startDateTime;
    LocalDateTime endDateTime;
    StatusType status;
    ServiceType serviceType;
    Long userId;
    Long slotId;
}
