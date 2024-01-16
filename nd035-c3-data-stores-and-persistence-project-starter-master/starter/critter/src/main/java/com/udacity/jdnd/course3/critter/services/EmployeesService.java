package com.udacity.jdnd.course3.critter.services;



import com.udacity.jdnd.course3.critter.Entities.Employees;
import com.udacity.jdnd.course3.critter.user.EmployeeDTO;
import com.udacity.jdnd.course3.critter.user.EmployeeSkill;


import com.udacity.jdnd.course3.critter.repository.EmployeesRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.stream.Collectors;


@Service
@Transactional
public class EmployeesService {
    private final EmployeesRepository employeesRepository;
    private final ModelMapper modelMapper;


    public EmployeesService(EmployeesRepository employeesRepository, ModelMapper modelMapper) {
        this.employeesRepository = employeesRepository;
        this.modelMapper = modelMapper;
    }

    public List<Employees> getAvailableEmployees(LocalDate date, Set<EmployeeSkill> employeeSkill) {
        List<Employees> allEmployees = findAll();
        return allEmployees.stream().filter(employee ->
                        employee.getDaysAvailable().contains(date.getDayOfWeek())
                                && employee.getSkills().containsAll(employeeSkill))
                .collect(Collectors.toList());
    }





    public EmployeeDTO convertToDto(Employees employees) {

        return modelMapper.map(employees, EmployeeDTO.class);
    }


    public Employees findById(Long id) throws NoSuchElementException {
        return employeesRepository.getOne(id);
    }


    public void setAvailability(Long id, Set<DayOfWeek> daysAvailable) {
        Employees employee = employeesRepository.getOne(id);
        employee.setDaysAvailable(daysAvailable);

        employeesRepository.save(employee);
    }


    public Set<DayOfWeek> findDaysAvailable(Long id) throws NoSuchElementException {
        return employeesRepository.findById(id).get().getDaysAvailable();
    }


    public List<Employees> findAll() {
        return employeesRepository.findAll();
    }

    public Employees save(EmployeeDTO employees) {
        return employeesRepository.save(modelMapper.map(employees, Employees.class));
    }

}

