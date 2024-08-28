package org.example.model.response;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.model.enms.ServiceType;
import org.example.model.enms.StatusType;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class FetchAppointmentResponse {
    private Long appointmentId;
    private Long serviceProviderId;
    private ServiceType serviceType;
    private StatusType status;
    private Long slotId;
    private String startDateTime;
    private String endDateTime;
    private Long userId;
}
