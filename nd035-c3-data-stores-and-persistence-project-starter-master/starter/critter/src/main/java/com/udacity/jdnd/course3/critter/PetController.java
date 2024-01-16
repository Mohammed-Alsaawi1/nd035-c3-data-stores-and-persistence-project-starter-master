package com.udacity.jdnd.course3.critter;


import com.udacity.jdnd.course3.critter.Entities.Pets;
import com.udacity.jdnd.course3.critter.pet.PetDTO;
import com.udacity.jdnd.course3.critter.services.PetsService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/pet")
public class PetController {
    private final PetsService petsService;

    public PetController(PetsService petsService) {
        this.petsService = petsService;
    }


    @PostMapping
    public PetDTO savePet(@RequestBody PetDTO pet) {

            return petsService.convertToDTO(petsService.save(pet));


    }

    @GetMapping("/{petId}")
    public PetDTO getPet(@PathVariable long petId) {

            Pets pet = petsService.FindById(petId);
            return petsService.convertToDTO(pet);

    }


    @GetMapping
    public List<PetDTO> getPets() {

            return petsService.findAll()
                    .stream().map(petsService::convertToDTO)
                    .collect(Collectors.toList());

    }


    @GetMapping("/owner/{ownerId}")
    public List<PetDTO> getPetsByOwner(@PathVariable long ownerId) {

            return petsService.findByOwnerId(ownerId)
                    .stream().map(petsService::convertToDTO)
                    .collect(Collectors.toList());

    }
}

