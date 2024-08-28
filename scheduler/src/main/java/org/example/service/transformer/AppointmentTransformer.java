package org.example.service.transformer;

import org.example.dao.repository.ICalendarRepository;
import org.example.dao.repository.IServiceProviderRepository;
import org.example.dao.repository.IServiceRepository;
import org.example.dao.repository.ISlotRepository;
import org.example.dao.repository.IStatusRepository;
import org.example.model.enms.FrequencyUnitType;
import org.example.model.enms.ModificationType;
import org.example.model.enms.ServiceType;
import org.example.model.enms.StatusType;
import org.example.model.entity.Calendar;
import org.example.model.entity.ServiceProvider;
import org.example.model.entity.Slot;
import org.example.model.service.appointment.request.CreateAppointmentRequestDTO;
import org.example.model.service.appointment.request.FetchAppointmentRequestDTO;
import org.example.model.service.appointment.request.FetchAvailabilityRequestDTO;
import org.example.model.service.appointment.request.ModifyAppointmentRequestDTO;
import org.example.model.service.appointment.response.CreateAppointmentResponseDTO;
import org.example.model.service.appointment.response.FetchAppointmentResponseDTO;
import org.example.model.service.appointment.response.FetchAvailabilityResponseDTO;
import org.example.model.service.appointment.response.ModifyAppointmentResponseDTO;
import org.example.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class AppointmentTransformer {
    private final ISlotRepository slotRepository;
    private final ICalendarRepository calendarRepository;
    private final IServiceProviderRepository serviceProviderRepository;
    private final IStatusRepository statusRepository;
    private final IServiceRepository serviceRepository;

    @Autowired
    public AppointmentTransformer(ISlotRepository slotRepository,
                                  ICalendarRepository calendarRepository,
                                  IServiceProviderRepository serviceProviderRepository,
                                  IStatusRepository statusRepository, IServiceRepository serviceRepository) {
        this.slotRepository = slotRepository;
        this.calendarRepository = calendarRepository;
        this.serviceProviderRepository = serviceProviderRepository;
        this.statusRepository = statusRepository;
        this.serviceRepository = serviceRepository;
    }

    public Calendar getCalendarEntityForAppointmentBooking(CreateAppointmentRequestDTO requestDTO) {
        return Calendar.builder()
                .slot(slotRepository.getReferenceById(requestDTO.getSlotId()))
                .serviceProvider(serviceProviderRepository.getReferenceById(requestDTO.getServiceProviderId()))
                .userId(requestDTO.getUserId())
                .status(statusRepository.getReferenceById(StatusType.BOOKED.getId()))
                .startDatetime(requestDTO.getStartDateTime())
                .build();
    }

    public Calendar getCalendarEntityForAppointmentUpdate(ModifyAppointmentRequestDTO requestDTO) {
        Calendar currentBooking = calendarRepository.getReferenceById(requestDTO.getAppointmentId());
        if (requestDTO.getModificationType().equals(ModificationType.CANCEL)) {
            return Calendar.builder()
                    .id(requestDTO.getAppointmentId())
                    .status(statusRepository.getReferenceById(StatusType.CANCELED.getId()))
                    .startDatetime(currentBooking.getStartDatetime())
                    .slot(currentBooking.getSlot())
                    .serviceProvider(currentBooking.getServiceProvider())
                    .createdAt(currentBooking.getCreatedAt())
                    .userId(currentBooking.getUserId())
                    .build();
        } else {
            return Calendar.builder()
                    .id(requestDTO.getAppointmentId())
                    .status(statusRepository.getReferenceById(StatusType.BOOKED.getId()))
                    .startDatetime(requestDTO.getStartDateTime())
                    .slot(slotRepository.getReferenceById(requestDTO.getNewSlotId()))
                    .serviceProvider(currentBooking.getServiceProvider())
                    .createdAt(currentBooking.getCreatedAt())
                    .userId(currentBooking.getUserId())
                    .build();
        }

    }


    public CreateAppointmentResponseDTO getAppointmentBookingResponse(Calendar request) throws IllegalAccessException {
        return CreateAppointmentResponseDTO.builder()
                .appointmentId(request.getId())
                .status(StatusType.getByName(request.getStatus().getValue()))
                .build();
    }

    public ModifyAppointmentResponseDTO getAppointmentModificationResponse(Calendar request) throws IllegalAccessException {
        return ModifyAppointmentResponseDTO.builder()
                .appointmentId(request.getId())
                .status(StatusType.getByName(request.getStatus().getValue()))
                .build();
    }

    public ServiceProvider getServiceProviderToFetchAvailability(FetchAvailabilityRequestDTO requestDTO) throws IllegalAccessException {
        return ServiceProvider.builder()
                .id(requestDTO.getServiceProviderId())
                .service(serviceRepository.getReferenceById(
                        requestDTO.getServiceType().getId()))
                .build();
    }

    public ServiceProvider getServiceProviderToFetchBookings(FetchAppointmentRequestDTO requestDTO) throws IllegalAccessException {
        return ServiceProvider.builder()
                .id(requestDTO.getServiceProviderId())
                .service(serviceRepository.getReferenceById(
                        requestDTO.getServiceType().getId()))
                .build();
    }

    public List<FetchAppointmentResponseDTO> getAppointmentsResponse(List<Calendar> request) {
        return request.stream().map(item -> {
            try {
                return FetchAppointmentResponseDTO.builder()
                        .startDateTime(item.getStartDatetime())
                        .appointmentId(item.getId())
                        .endDateTime(item.getStartDatetime()
                                .plusMinutes(getSlotFrequencyInMinutes(item.getSlot())))
                        .serviceProviderId(item.getServiceProvider().getId())
                        .status(StatusType.getByName(item.getStatus().getValue()))
                        .serviceType(ServiceType.getByName(item.getServiceProvider().getService().getName()))
                        .userId(item.getUserId())
                        .slotId(item.getSlot().getId())
                        .build();
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }).collect(Collectors.toList());
    }

    public List<FetchAvailabilityResponseDTO> getAvailabilityResponse(List<Calendar> request,
                                                                      FetchAvailabilityRequestDTO fetchAvailabilityRequestDTO) throws IllegalAccessException {
        List<ServiceProvider> serviceProviders = new ArrayList<>();
        if (Objects.isNull(fetchAvailabilityRequestDTO.getServiceProviderId())){
             serviceProviders = serviceProviderRepository.findAllByIsActiveTrue();
        } else {
            Optional<ServiceProvider> serviceProvider = serviceProviderRepository.findById(fetchAvailabilityRequestDTO.getServiceProviderId());
            if (serviceProvider.isPresent()) {
                serviceProviders.add(serviceProvider.get());
            }
        }

        request.sort(Comparator.comparing(Calendar::getStartDatetime));

        LocalTime startOfDay = LocalTime.MIDNIGHT;
        LocalTime endOfDay = LocalTime.MAX;
        LocalDate currentDate = LocalDate.now();

        List<FetchAvailabilityResponseDTO> resp = new ArrayList<>();

        for (ServiceProvider serviceProvider : serviceProviders) {
            // Filter bookings for the current service provider
            List<Calendar> bookings = request.stream()
                    .filter(item -> item.getServiceProvider().getId().equals(serviceProvider.getId()))
                    .toList();

            if (bookings.isEmpty()) {
                // No bookings for this service provider, available all day
                resp.add(FetchAvailabilityResponseDTO.builder()
                        .startDateTime(currentDate.atTime(startOfDay))
                        .endDateTime(currentDate.atTime(endOfDay))
                        .slotId(1L) // Default slot ID
                        .serviceProviderId(serviceProvider.getId())
                        .serviceType(ServiceType.getByName(serviceProvider.getService().getName()))
                        .build());
            } else {
                // Service provider has bookings
                LocalTime currentStart = startOfDay;

                for (Calendar booking : bookings) {
                    LocalTime bookingStart = booking.getStartDatetime().toLocalTime();
                    LocalTime bookingEnd = bookingStart.plusMinutes(getSlotFrequencyInMinutes(booking.getSlot()));

                    // Check for gaps before the current booking
                    if (currentStart.isBefore(bookingStart)) {
                        resp.add(FetchAvailabilityResponseDTO.builder()
                                .startDateTime(currentDate.atTime(currentStart))
                                .endDateTime(currentDate.atTime(bookingStart))
                                .slotId(1L) // Default slot ID for free time
                                .serviceProviderId(serviceProvider.getId())
                                .serviceType(ServiceType.getByName(serviceProvider.getService().getName()))
                                .build());
                    }

                    // Move the current start time to the end of the booking
                    currentStart = bookingEnd;
                }

                // Check for any remaining time after the last booking until end of day
                if (currentStart.isBefore(endOfDay)) {
                    resp.add(FetchAvailabilityResponseDTO.builder()
                            .startDateTime(currentDate.atTime(currentStart))
                            .endDateTime(currentDate.atTime(endOfDay))
                            .slotId(1L) // Default slot ID for free time
                            .serviceProviderId(serviceProvider.getId())
                            .serviceType(ServiceType.getByName(serviceProvider.getService().getName()))
                            .build());
                }
            }
        }

        return resp;
    }

    private Long getSlotFrequencyInMinutes(Slot slot) throws IllegalAccessException {
        return FrequencyUnitType.getMultiplierForMinutes(FrequencyUnitType.getByName(slot.getFrequencyUnit()
                .getValue())) * slot.getFrequency();
    }
}
