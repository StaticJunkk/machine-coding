package org.example.dao.repository;

import org.example.model.entity.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IStatusRepository extends JpaRepository<Status, Long> {
    // Custom query methods can be defined here if needed
}
