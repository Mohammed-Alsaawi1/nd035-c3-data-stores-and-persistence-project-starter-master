package com.udacity.jdnd.course3.critter.services;



import com.udacity.jdnd.course3.critter.Entities.Schedules;
import com.udacity.jdnd.course3.critter.Entities.Employees;
import com.udacity.jdnd.course3.critter.Entities.Pets;

import com.udacity.jdnd.course3.critter.repository.CustomersRepository;
import com.udacity.jdnd.course3.critter.repository.EmployeesRepository;
import com.udacity.jdnd.course3.critter.repository.PetsRepository;
import com.udacity.jdnd.course3.critter.repository.SchedulesRepository;
import com.udacity.jdnd.course3.critter.schedule.ScheduleDTO;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


@Service
@Transactional
public class SchedulesService {
    private final SchedulesRepository schedulesRepository;
    private final PetsRepository petsRepository;
    private final EmployeesRepository employeesRepository;
    private final CustomersRepository customersRepository;
    private final ModelMapper modelMapper;


    public SchedulesService(SchedulesRepository schedulesRepository, PetsService petsService, PetsRepository petsRepository, EmployeesService employeesService, EmployeesRepository employeesRepository, CustomersService customersService, CustomersRepository customersRepository, ModelMapper modelMapper) {
        this.schedulesRepository = schedulesRepository;
        this.petsRepository = petsRepository;
        this.employeesRepository = employeesRepository;
        this.customersRepository = customersRepository;
        this.modelMapper = modelMapper;
    }
    public List<Schedules> findByCustomerId(long customerId) {
        return schedulesRepository.findAllByPetsIn(customersRepository.getOne(customerId).getPets());
    }



    public Schedules save(ScheduleDTO scheduleDTO) {
        Schedules schedule = modelMapper.map(scheduleDTO, Schedules.class);

        if (scheduleDTO.getEmployeeIds() != null && !scheduleDTO.getEmployeeIds().isEmpty() && !scheduleDTO.getEmployeeIds().isEmpty() ) {
            schedule.setEmployees(employeesRepository.findAllById(scheduleDTO.getEmployeeIds()));
        }
        if (scheduleDTO.getPetIds() != null && !scheduleDTO.getPetIds().isEmpty() && !scheduleDTO.getPetIds().isEmpty()) {
            schedule.setPets(petsRepository.findAllById(scheduleDTO.getPetIds()));
        }

        return schedulesRepository.save(schedule);
    }


    public List<Schedules> findAll() {
        return schedulesRepository.findAll();
    }


    public List<Schedules> findByEmployeeId(long employeeId) {
        return schedulesRepository.findScheduleByEmployeesContains(employeesRepository.getOne(employeeId));
    }


    public List<Schedules> findByPetId(long petId) {
        return schedulesRepository.findScheduleByPetsContains(petsRepository.getOne(petId));
    }

    public ScheduleDTO converToDto(Schedules schedules) {
        ScheduleDTO scheduleDTO = modelMapper.map(schedules, ScheduleDTO.class);
        if (schedules.getEmployees() != null && !schedules.getEmployees().isEmpty() && !schedules.getEmployees().isEmpty()) {
            scheduleDTO.setEmployeeIds(schedules.getEmployees()
                    .stream().map(Employees::getId)
                    .collect(Collectors.toList()));
        }
        if (schedules.getPets() != null && !schedules.getPets().isEmpty() && !schedules.getPets().isEmpty()) {
            scheduleDTO.setPetIds(schedules.getPets()
                    .stream().map(Pets::getId)
                    .collect(Collectors.toList()));
        }

        return scheduleDTO;
    }



}





