package org.example.controller.transformer;

import org.example.model.request.CreateAppointmentRequest;
import org.example.model.request.FetchAppointmentsRequest;
import org.example.model.request.FetchAvailabilityRequest;
import org.example.model.request.ModifyAppointmentRequest;
import org.example.model.response.CreateAppointmentResponse;
import org.example.model.response.FetchAppointmentResponse;
import org.example.model.response.FetchAvailabilityResponse;
import org.example.model.response.ModifyAppointmentResponse;
import org.example.model.service.appointment.request.CreateAppointmentRequestDTO;
import org.example.model.service.appointment.request.FetchAppointmentRequestDTO;
import org.example.model.service.appointment.request.FetchAvailabilityRequestDTO;
import org.example.model.service.appointment.request.ModifyAppointmentRequestDTO;
import org.example.model.service.appointment.response.CreateAppointmentResponseDTO;
import org.example.model.service.appointment.response.FetchAppointmentResponseDTO;
import org.example.model.service.appointment.response.FetchAvailabilityResponseDTO;
import org.example.model.service.appointment.response.ModifyAppointmentResponseDTO;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class AppointmentServiceTransformer {

    private final DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");

    public CreateAppointmentResponse getAppointmentBookingResponse(CreateAppointmentResponseDTO request) {
        return CreateAppointmentResponse.builder()
                .appointmentId(request.getAppointmentId())
                .status(request.getStatus())
                .build();
    }

    public CreateAppointmentRequestDTO getAppointmentBookingRequest(CreateAppointmentRequest request) {
        return CreateAppointmentRequestDTO.builder()
                .slotId(request.getSlotId())
                .startDateTime(LocalDateTime.parse(request.getStartDateTime(),outputFormatter))
                .serviceProviderId(request.getServiceProviderId())
                .userId(request.getUserId())
                .build();
    }


    public ModifyAppointmentResponse getAppointmentModificationResponse(ModifyAppointmentResponseDTO request) {
        return ModifyAppointmentResponse.builder()
                .appointmentId(request.getAppointmentId())
                .status(request.getStatus())
                .build();
    }


    public ModifyAppointmentRequestDTO getAppointmentModificationRequest(ModifyAppointmentRequest request) {
        return ModifyAppointmentRequestDTO.builder()
                .appointmentId(request.getAppointmentId())
                .modificationType(request.getModificationType())
                .newSlotId(request.getNewSlotId())
                .startDateTime(Objects.isNull(request.getStartDateTime()) ? null : LocalDateTime.parse(request.getStartDateTime(),
                        outputFormatter))
                .build();
    }

    public List<FetchAppointmentResponse> getAppointmentsResponse(List<FetchAppointmentResponseDTO> request) {
        return request.stream().map(item -> FetchAppointmentResponse.builder()
                .status(item.getStatus())
                .startDateTime(String.valueOf(item.getStartDateTime()))
                .appointmentId(item.getAppointmentId())
                .endDateTime(String.valueOf(item.getEndDateTime()))
                .serviceProviderId(item.getServiceProviderId())
                .serviceType(item.getServiceType())
                .slotId(item.getSlotId())
                .userId(item.getUserId())
                .build()).collect(Collectors.toList());
    }

    public FetchAppointmentRequestDTO fetchAppointmentRequestDTO(FetchAppointmentsRequest request) {
        return FetchAppointmentRequestDTO.builder()
                .serviceProviderId(request.getServiceProviderId())
                .serviceType(request.getServiceType())
                .build();
    }

    public FetchAvailabilityRequestDTO fetchAvailabilityRequestDTO(FetchAvailabilityRequest request) {
        return FetchAvailabilityRequestDTO.builder()
                .serviceProviderId(request.getServiceProviderId())
                .serviceType(request.getServiceType())
                .build();
    }

    public List<FetchAvailabilityResponse> getAvailabilityResponse(List<FetchAvailabilityResponseDTO> request) {
        return request.stream().map(item -> FetchAvailabilityResponse.builder()
                .startDateTime(item.getStartDateTime().format(outputFormatter))
                .endDateTime(item.getEndDateTime().format(outputFormatter))
                .slotId(item.getSlotId())
                .serviceProviderId(item.getServiceProviderId())
                .serviceType(item.getServiceType())
                .build()).collect(Collectors.toList());
    }
}
