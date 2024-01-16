package com.udacity.jdnd.course3.critter.repository;

import com.udacity.jdnd.course3.critter.Entities.Pets;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * The interface Pet repository.
 */
@Repository
public interface PetsRepository extends JpaRepository<Pets, Long> {
    /**
     * Find pets by owner id list.
     *
     * @param customerId the customer id
     * @return the list
     */
    List<Pets> findPetsByOwner_Id(Long customerId);
}
