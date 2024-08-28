package org.example.model.service.appointment.request;

import lombok.Builder;
import lombok.Value;
import org.example.model.enms.ModificationType;

import java.time.LocalDateTime;

@Value
@Builder
public class ModifyAppointmentRequestDTO {
    ModificationType modificationType;
    Long newSlotId;
    LocalDateTime startDateTime;
    Long appointmentId;
}
