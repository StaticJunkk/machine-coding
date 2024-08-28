package org.example.dao.repository;

import org.example.model.entity.SlotFrequencyType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ISlotFrequencyTypeRepository extends JpaRepository<SlotFrequencyType, Long> {
    // Custom query methods can be defined here if needed
}
