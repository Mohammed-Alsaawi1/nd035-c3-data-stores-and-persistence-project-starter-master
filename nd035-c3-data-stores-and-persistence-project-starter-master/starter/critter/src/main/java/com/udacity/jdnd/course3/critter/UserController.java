package com.udacity.jdnd.course3.critter;


import com.udacity.jdnd.course3.critter.user.CustomerDTO;
import com.udacity.jdnd.course3.critter.user.EmployeeDTO;
import com.udacity.jdnd.course3.critter.user.EmployeeRequestDTO;
import com.udacity.jdnd.course3.critter.services.CustomersService;
import com.udacity.jdnd.course3.critter.services.EmployeesService;
import com.udacity.jdnd.course3.critter.services.SchedulesService;
import org.springframework.web.bind.annotation.*;

import java.time.DayOfWeek;
import java.util.List;

import java.util.Set;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/user")
public class UserController {
    private final CustomersService customersService;
    private final EmployeesService employeesService;
    private final SchedulesService schedulesService;


    public UserController(CustomersService customersService, EmployeesService employeesService, SchedulesService schedulesService) {
        this.customersService = customersService;
        this.employeesService = employeesService;
        this.schedulesService = schedulesService;
    }


    @PostMapping("/customer")
    public CustomerDTO saveCustomer(@RequestBody CustomerDTO customerDTO) {
        return customersService.convertToDTO(customersService.save(customerDTO));

    }


    @GetMapping("/customer")
    public List<CustomerDTO> getAllCustomers() {

            return customersService.findAll()
                    .stream().map(customersService::convertToDTO)
                    .collect(Collectors.toList());

    }


    @GetMapping("/customer/pet/{petId}")
    public CustomerDTO getOwnerByPet(@PathVariable long petId) {

            return customersService.convertToDTO(customersService.findByPetId(petId));

    }


    @PostMapping("/employee")
    public EmployeeDTO saveEmployee(@RequestBody EmployeeDTO employeeDTO) {

            return employeesService.convertToDto(employeesService.save(employeeDTO));

    }


    @PostMapping("/employee/{employeeId}")
    public EmployeeDTO getEmployee(@PathVariable long employeeId) {

            return employeesService.convertToDto(employeesService.findById(employeeId));

    }


    @PutMapping("/employee/{employeeId}")
    public void setAvailability(@RequestBody Set<DayOfWeek> daysAvailable, @PathVariable long employeeId) {

            employeesService.setAvailability(employeeId, daysAvailable);

    }


    @GetMapping("/employee/availability")
    public List<EmployeeDTO> findEmployeesForService(@RequestBody EmployeeRequestDTO employeeDTO) {

            return employeesService.getAvailableEmployees(employeeDTO.getDate(), employeeDTO.getSkills())
                    .stream().map(employeesService::convertToDto)
                    .collect(Collectors.toList());

    }

}

