package org.example.dao.datastore.iface;

import org.example.exception.DataStoreException;
import org.example.model.entity.Calendar;
import org.example.model.entity.ServiceProvider;

import java.util.List;

public interface IAppointmentDataStore {
    Calendar createAppointment(Calendar requestEntity) throws DataStoreException;
    List<Calendar> getAppointments(ServiceProvider serviceProvider);

    Calendar modifyAppointment(Calendar requestEntity) throws DataStoreException;
}
