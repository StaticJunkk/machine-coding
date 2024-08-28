package org.example.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.example.dao.datastore.iface.IAppointmentDataStore;
import org.example.exception.DataStoreException;
import org.example.exception.ServiceException;
import org.example.model.entity.Calendar;
import org.example.model.service.appointment.request.CreateAppointmentRequestDTO;
import org.example.model.service.appointment.request.FetchAppointmentRequestDTO;
import org.example.model.service.appointment.request.FetchAvailabilityRequestDTO;
import org.example.model.service.appointment.request.ModifyAppointmentRequestDTO;
import org.example.model.service.appointment.response.CreateAppointmentResponseDTO;
import org.example.model.service.appointment.response.FetchAppointmentResponseDTO;
import org.example.model.service.appointment.response.FetchAvailabilityResponseDTO;
import org.example.model.service.appointment.response.ModifyAppointmentResponseDTO;
import org.example.service.iface.IAppointmentService;
import org.example.service.transformer.AppointmentTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class AppointmentService implements IAppointmentService {
    private final IAppointmentDataStore dataStore;
    private final AppointmentTransformer transformer;

    @Autowired
    public AppointmentService(IAppointmentDataStore dataStore, AppointmentTransformer transformer) {
        this.dataStore = dataStore;
        this.transformer = transformer;
    }

    @Override
    public CreateAppointmentResponseDTO createAppointment(CreateAppointmentRequestDTO createAppointmentRequestDTO) throws ServiceException {
        try {
            Calendar response = dataStore.createAppointment(transformer.getCalendarEntityForAppointmentBooking(
                    createAppointmentRequestDTO));
            return transformer.getAppointmentBookingResponse(response);
        } catch (Exception ex) {
            log.error("ERROR! Could not create appointment - ServiceProvider - {}, SlotId - {} - Unable to book appointment - {}",
                    createAppointmentRequestDTO.getServiceProviderId(),
                    createAppointmentRequestDTO.getSlotId(),
                    ex.getMessage());
            throw new ServiceException(ex.getMessage());
        }

    }

    @Override
    public List<FetchAvailabilityResponseDTO> fetchAvailability(FetchAvailabilityRequestDTO fetchAvailabilityRequestDTO) throws ServiceException {
        try {
            List<Calendar> response =
                    dataStore.getAppointments(transformer.getServiceProviderToFetchAvailability(
                            fetchAvailabilityRequestDTO));
            return transformer.getAvailabilityResponse(response, fetchAvailabilityRequestDTO);
        } catch (IllegalAccessException ex) {
            log.error("ERROR! Could not fetch availability - ServiceProvider - {}, ServiceType - {} - {}",
                    fetchAvailabilityRequestDTO.getServiceProviderId(),
                    fetchAvailabilityRequestDTO.getServiceType(),
                    ex.getMessage());
            throw new ServiceException(ex.getMessage());
        }
    }

    @Override
    public List<FetchAppointmentResponseDTO> fetchAppointment(FetchAppointmentRequestDTO fetchAppointmentRequestDTO) throws ServiceException {
        try {
            List<Calendar> response =
                    dataStore.getAppointments(transformer.getServiceProviderToFetchBookings(fetchAppointmentRequestDTO));
            return transformer.getAppointmentsResponse(response);
        } catch (IllegalAccessException ex) {
            log.error("ERROR! Could not fetch appointments - ServiceProvider - {}, ServiceType - {} - {}",
                    fetchAppointmentRequestDTO.getServiceProviderId(),
                    fetchAppointmentRequestDTO.getServiceType(),
                    ex.getMessage());
            throw new ServiceException(ex.getMessage());
        }
    }

    @Override
    public ModifyAppointmentResponseDTO modifyAppointment(ModifyAppointmentRequestDTO modifyAppointmentRequestDTO) throws ServiceException {
        try {
            Calendar resp = dataStore.modifyAppointment(transformer.getCalendarEntityForAppointmentUpdate(
                    modifyAppointmentRequestDTO));
            return transformer.getAppointmentModificationResponse(resp);
        } catch (IllegalAccessException | DataStoreException ex) {
            log.error("ERROR! Could not update appointment - AppointmentId - {}, modification - {} - {}",
                    modifyAppointmentRequestDTO.getAppointmentId(),
                    modifyAppointmentRequestDTO.getModificationType(),
                    ex.getMessage());
            throw new ServiceException(ex.getMessage());
        }

    }
}
