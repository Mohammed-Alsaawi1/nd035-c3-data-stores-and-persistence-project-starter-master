package com.udacity.jdnd.course3.critter.repository;

import com.udacity.jdnd.course3.critter.Entities.Customers;
import com.udacity.jdnd.course3.critter.Entities.Pets;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * The interface Customer repository.
 */
@Repository
public interface CustomersRepository extends JpaRepository<Customers, Long> {
    /**
     * Find customer by pets contains customer.
     *
     * @param pet the pet
     * @return the customer
     */
    Customers findCustomerByPetsContains(Pets pet);
}

