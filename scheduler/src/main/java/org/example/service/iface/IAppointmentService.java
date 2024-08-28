package org.example.service.iface;

import org.example.exception.ServiceException;
import org.example.model.service.appointment.request.CreateAppointmentRequestDTO;
import org.example.model.service.appointment.request.FetchAvailabilityRequestDTO;
import org.example.model.service.appointment.request.FetchAppointmentRequestDTO;
import org.example.model.service.appointment.request.ModifyAppointmentRequestDTO;
import org.example.model.service.appointment.response.CreateAppointmentResponseDTO;
import org.example.model.service.appointment.response.FetchAvailabilityResponseDTO;
import org.example.model.service.appointment.response.FetchAppointmentResponseDTO;
import org.example.model.service.appointment.response.ModifyAppointmentResponseDTO;
import org.springframework.stereotype.Service;

import java.util.List;

public interface IAppointmentService {
    CreateAppointmentResponseDTO createAppointment(CreateAppointmentRequestDTO createAppointmentRequestDTO) throws ServiceException;

    List<FetchAvailabilityResponseDTO> fetchAvailability(FetchAvailabilityRequestDTO fetchAvailabilityRequestDTO) throws ServiceException;

    List<FetchAppointmentResponseDTO> fetchAppointment(FetchAppointmentRequestDTO fetchAppointmentRequestDTO) throws ServiceException;

    ModifyAppointmentResponseDTO modifyAppointment(ModifyAppointmentRequestDTO modifyAppointmentRequestDTO) throws ServiceException;
}
