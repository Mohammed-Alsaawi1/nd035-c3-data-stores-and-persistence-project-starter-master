package com.udacity.jdnd.course3.critter;



import com.udacity.jdnd.course3.critter.schedule.ScheduleDTO;
import com.udacity.jdnd.course3.critter.services.SchedulesService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/schedule")
public class ScheduleController {

    private final SchedulesService schedulesService;


    public ScheduleController(SchedulesService schedulesService) {
        this.schedulesService = schedulesService;
    }


    @PostMapping
    public ScheduleDTO createSchedule(@RequestBody ScheduleDTO scheduleDTO) {

            return schedulesService.converToDto(schedulesService.save(scheduleDTO));

    }


    @GetMapping
    public List<ScheduleDTO> getAllSchedules() {

            return schedulesService.findAll().stream()
                    .map(schedulesService::converToDto)
                    .collect(Collectors.toList());

    }


    @GetMapping("/pet/{petId}")
    public List<ScheduleDTO> getScheduleForPet(@PathVariable long petId) {

            return schedulesService.findByPetId(petId).stream()
                    .map(schedulesService::converToDto)
                    .collect(Collectors.toList());

    }


    @GetMapping("/employee/{employeeId}")
    public List<ScheduleDTO> getScheduleForEmployee(@PathVariable long employeeId) {

            return schedulesService.findByEmployeeId(employeeId)
                    .stream().map(schedulesService::converToDto)
                    .collect(Collectors.toList());

    }


    @GetMapping("/customer/{customerId}")
    public List<ScheduleDTO> getScheduleForCustomer(@PathVariable long customerId) {

            return schedulesService.findByCustomerId(customerId)
                    .stream().map(schedulesService::converToDto)
                    .collect(Collectors.toList());

    }
}

