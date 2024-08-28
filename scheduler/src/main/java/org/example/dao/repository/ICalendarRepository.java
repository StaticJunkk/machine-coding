package org.example.dao.repository;

import org.example.model.entity.Calendar;
import org.example.model.entity.ServiceProvider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICalendarRepository extends JpaRepository<Calendar, Long> {

    @Query("SELECT c FROM Calendar c WHERE c.status.id != 2")
    List<Calendar> findAll();

    @Query("SELECT c FROM Calendar c WHERE c.serviceProvider = :serviceProvider AND c.status.id != 2")
    List<Calendar> findAllByServiceProviderAndNotCancelled(ServiceProvider serviceProvider);

}
