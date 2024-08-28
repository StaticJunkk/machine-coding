package org.example.dao.repository;

import org.example.model.entity.ServiceProvider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IServiceProviderRepository extends JpaRepository<ServiceProvider, Long> {
    // Custom query methods can be defined here if needed
    List<ServiceProvider> findAllByIsActiveTrue();
}
