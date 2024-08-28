package org.example.dao.repository;

import org.example.model.entity.Slot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ISlotRepository extends JpaRepository<Slot, Long> {
    // Custom query methods can be defined here if needed
}
