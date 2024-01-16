package com.udacity.jdnd.course3.critter.services;



import com.udacity.jdnd.course3.critter.Entities.Customers;
import com.udacity.jdnd.course3.critter.Entities.Pets;
import com.udacity.jdnd.course3.critter.pet.PetDTO;

import com.udacity.jdnd.course3.critter.repository.CustomersRepository;

import com.udacity.jdnd.course3.critter.repository.PetsRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service
@Transactional
public class PetsService {
    private final PetsRepository petsRepository;
    private final ModelMapper modelMapper;
    private final CustomersRepository customersRepository;


    public PetsService(PetsRepository petsRepository, ModelMapper modelMapper, CustomersRepository customersRepository) {
        this.petsRepository = petsRepository;
        this.modelMapper = modelMapper;
        this.customersRepository = customersRepository;
    }


    public Pets save(PetDTO petDTO) {
        Pets pet = modelMapper.map(petDTO, Pets.class);
        Customers owner = customersRepository.getOne(petDTO.getOwnerId());
        List<Pets> petList = new ArrayList<>();
        if (owner.getPets() == null && owner.getPets() == null ) {
            owner.setPets(petList);
        } else {
            petList = owner.getPets();
        }
        pet.setOwner(owner);
        pet = petsRepository.save(pet);
        petList.add(pet);
        owner.setPets(petList);
        customersRepository.save(owner);

        return pet;
    }


    public Pets FindById(long Id) {
        return petsRepository.getOne(Id);
    }


    public List<Pets> findAll() {
        return petsRepository.findAll();
    }


    public List<Pets> findByOwnerId(Long customerId) {
        return petsRepository.findPetsByOwner_Id(customerId);
    }

    public PetDTO convertToDTO(Pets pets) {
        return modelMapper.map(pets, PetDTO.class);
    }


    public Pets findById(long petId) {
        return petsRepository.getOne(petId);
    }

}

