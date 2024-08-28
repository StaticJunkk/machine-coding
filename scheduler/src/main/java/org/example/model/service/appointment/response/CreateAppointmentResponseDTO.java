package org.example.model.service.appointment.response;

import lombok.Builder;
import lombok.Value;
import org.example.model.enms.StatusType;

import java.time.LocalDateTime;

@Value
@Builder
public class CreateAppointmentResponseDTO {
    Long appointmentId;
    StatusType status;
}
