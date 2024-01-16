package com.udacity.jdnd.course3.critter.repository;


import com.udacity.jdnd.course3.critter.Entities.Employees;
import com.udacity.jdnd.course3.critter.Entities.Pets;
import com.udacity.jdnd.course3.critter.Entities.Schedules;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * The interface Schedule repository.
 */
@Repository
public interface SchedulesRepository extends JpaRepository<Schedules, Long> {
    /**
     * Find schedule by pets contains list.
     *
     * @param pets the pets
     * @return the list
     */
    List<Schedules> findScheduleByPetsContains(Pets pets);

    /**
     * Find schedule by employees contains list.
     *
     * @param employees the employees
     * @return the list
     */
    List<Schedules> findScheduleByEmployeesContains(Employees employees);

    /**
     * Find all by pets in list.
     *
     * @param pets the pets
     * @return the list
     */
    List<Schedules> findAllByPetsIn(List<Pets> pets);
}

