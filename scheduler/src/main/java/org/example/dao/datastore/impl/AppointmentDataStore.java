package org.example.dao.datastore.impl;

import lombok.extern.slf4j.Slf4j;
import org.example.dao.datastore.iface.IAppointmentDataStore;
import org.example.dao.repository.ICalendarRepository;
import org.example.dao.repository.IServiceProviderRepository;
import org.example.dao.repository.IServiceRepository;
import org.example.dao.repository.ISlotFrequencyTypeRepository;
import org.example.dao.repository.ISlotRepository;
import org.example.dao.repository.IStatusRepository;
import org.example.exception.DataStoreException;
import org.example.model.enms.StatusType;
import org.example.model.entity.Calendar;
import org.example.model.entity.ServiceProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
@Slf4j
public class AppointmentDataStore implements IAppointmentDataStore {

    private final ICalendarRepository calendarRepository;
    private final IServiceRepository serviceRepository;
    private final ISlotRepository slotRepository;
    private final ISlotFrequencyTypeRepository slotFrequencyTypeRepository;
    private final IServiceProviderRepository serviceProviderRepository;
    private final IStatusRepository statusRepository;

    @Autowired
    public AppointmentDataStore(ICalendarRepository calendarRepository,
                                IServiceRepository serviceRepository,
                                ISlotRepository slotRepository,
                                ISlotFrequencyTypeRepository slotFrequencyTypeRepository,
                                IServiceProviderRepository serviceProviderRepository,
                                IStatusRepository statusRepository) {
        this.calendarRepository = calendarRepository;
        this.serviceRepository = serviceRepository;
        this.slotRepository = slotRepository;
        this.slotFrequencyTypeRepository = slotFrequencyTypeRepository;
        this.serviceProviderRepository = serviceProviderRepository;
        this.statusRepository = statusRepository;
    }

    @Override
    public Calendar createAppointment(Calendar requestEntity) throws DataStoreException {
        List<Calendar> calendarList =
                calendarRepository.findAllByServiceProviderAndNotCancelled(requestEntity.getServiceProvider());
        if (calendarList.stream().anyMatch(item -> item.getStartDatetime().equals(requestEntity.getStartDatetime()))) {
            log.info("YAHA BHI AYA");
            log.error("Service Provider - {} Slot {} already booked!", requestEntity.getServiceProvider().getId(), requestEntity.getStartDatetime());
            throw new DataStoreException(String.format("Service Provider - %s Slot - %s already booked!",
                    requestEntity.getServiceProvider().getId(),
                    requestEntity.getStartDatetime()));
        }
        return calendarRepository.save(requestEntity);
    }

    @Override
    public List<Calendar> getAppointments(ServiceProvider serviceProvider) {
        if (Objects.isNull(serviceProvider.getId())) {
            return calendarRepository.findAll();
        }
        return calendarRepository.findAllByServiceProviderAndNotCancelled(serviceProvider);
    }

    @Override
    public Calendar modifyAppointment(Calendar requestEntity) throws DataStoreException {
        Optional<Calendar> calendarBooking =
                calendarRepository.findById(requestEntity.getId());
        if (calendarBooking.isPresent() && calendarBooking.get().getStatus().getId().equals(StatusType.CANCELED.getId())) {
            log.error("Service Provider - {} Slot {} already canceled!", requestEntity.getServiceProvider().getId(), requestEntity.getStartDatetime());
            throw new DataStoreException(String.format("Service Provider - %s Slot - %s already canceled!",
                    requestEntity.getServiceProvider().getId(),
                    requestEntity.getStartDatetime()));
        }

        return calendarRepository.save(requestEntity);
    }
}
