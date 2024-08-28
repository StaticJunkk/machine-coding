package org.example.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.controller.transformer.AppointmentServiceTransformer;
import org.example.exception.ServiceException;
import org.example.model.enms.ServiceType;
import org.example.model.request.CreateAppointmentRequest;
import org.example.model.request.FetchAppointmentsRequest;
import org.example.model.request.FetchAvailabilityRequest;
import org.example.model.request.ModifyAppointmentRequest;
import org.example.model.response.CreateAppointmentResponse;
import org.example.model.response.FetchAppointmentResponse;
import org.example.model.response.FetchAvailabilityResponse;
import org.example.model.response.ModifyAppointmentResponse;
import org.example.service.iface.IAppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/api/appointments")
@Slf4j
public class AppointmentController {

    private final IAppointmentService appointmentService;
    private final AppointmentServiceTransformer transformer;

    @Autowired
    public AppointmentController(IAppointmentService appointmentService, AppointmentServiceTransformer transformer) {
        this.appointmentService = appointmentService;
        this.transformer = transformer;
    }

    @PostMapping("/create")
    public ResponseEntity<CreateAppointmentResponse> createAppointment(@RequestBody CreateAppointmentRequest createAppointmentRequest) throws ServiceException {
        try {
            log.info("YAHA TOH AYA");
            return new ResponseEntity<>(transformer.getAppointmentBookingResponse(appointmentService.createAppointment(
                    transformer.getAppointmentBookingRequest(
                            createAppointmentRequest))), HttpStatus.OK);
        }
        catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<ModifyAppointmentResponse> updateAppointment(@RequestBody ModifyAppointmentRequest request) {
        try {
            return new ResponseEntity<>(transformer.getAppointmentModificationResponse(appointmentService.modifyAppointment(transformer.getAppointmentModificationRequest(
                    request))), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("{serviceType}/providers/{serviceProviderId}/availability/")
    public ResponseEntity<List<FetchAvailabilityResponse>> getAvailability(@PathVariable ServiceType serviceType,
                                                           @PathVariable Long serviceProviderId) throws ServiceException {
        try {
            FetchAvailabilityRequest request = FetchAvailabilityRequest.builder()
                    .serviceType(serviceType)
                    .serviceProviderId(serviceProviderId)
                    .build();
            return new ResponseEntity<>(transformer.getAvailabilityResponse(appointmentService.fetchAvailability(transformer.fetchAvailabilityRequestDTO(
                    request))), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("{serviceType}/providers/availability/")
    public ResponseEntity<List<FetchAvailabilityResponse>> getAvailability(@PathVariable ServiceType serviceType) throws ServiceException {
        try {
            FetchAvailabilityRequest request = FetchAvailabilityRequest.builder()
                    .serviceType(serviceType)
                    .build();
            return new ResponseEntity<>(transformer.getAvailabilityResponse(appointmentService.fetchAvailability(transformer.fetchAvailabilityRequestDTO(
                    request))), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("{serviceType}/providers/{serviceProviderId}/appointments/")
    public ResponseEntity<List<FetchAppointmentResponse>> getAppointments(@PathVariable ServiceType serviceType,
                                                          @PathVariable Long serviceProviderId) throws ServiceException {
        try {
            FetchAppointmentsRequest request = FetchAppointmentsRequest.builder()
                    .serviceType(serviceType)
                    .serviceProviderId(serviceProviderId)
                    .build();
            return new ResponseEntity<>(transformer.getAppointmentsResponse(appointmentService.fetchAppointment(transformer.fetchAppointmentRequestDTO(
                    request))), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}