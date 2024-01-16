package com.udacity.jdnd.course3.critter.services;



import com.udacity.jdnd.course3.critter.Entities.Pets;
import com.udacity.jdnd.course3.critter.user.CustomerDTO;
import com.udacity.jdnd.course3.critter.Entities.Customers;


import com.udacity.jdnd.course3.critter.repository.CustomersRepository;

import com.udacity.jdnd.course3.critter.repository.PetsRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@Transactional
public class CustomersService {
    private final CustomersRepository customersRepository;
    private final ModelMapper modelMapper;
    private final PetsRepository petRepository;


    public CustomersService(CustomersRepository customersRepository, ModelMapper modelMapper, PetsRepository petsRepository) {
        this.customersRepository = customersRepository;
        this.modelMapper = modelMapper;
        this.petRepository = petsRepository;
    }
    private Customers setPetIds(List<Long> petIds, Customers customers) {
        List<Pets> pets = petRepository.findAllById(petIds);
        customers.setPets(pets);
        return customers;
    }

    public Customers findByPetId(long petId) throws NoSuchElementException {
        return customersRepository.findCustomerByPetsContains(petRepository.getOne(petId));
    }


    public CustomerDTO convertToDTO(Customers customers) {
        CustomerDTO customerDTO = modelMapper.map(customers, CustomerDTO.class);
        if (customers.getPets() != null && !customers.getPets().isEmpty() && !customers.getPets().isEmpty() ) {
            customerDTO.setPetIds(customers.getPets()
                    .stream().map(Pets::getId)
                    .collect(Collectors.toList()));
        }
        return customerDTO;
    }

    public List<Customers> findAll() {
        return customersRepository.findAll();
    }


    public Customers findById(long id) throws NoSuchElementException {
        return customersRepository.findById(id).get();
    }



    public Customers save(CustomerDTO customerDTO) {
        Customers owner = modelMapper.map(customerDTO, Customers.class);
        if (customerDTO.getPetIds() != null && !customerDTO.getPetIds().isEmpty() && !customerDTO.getPetIds().isEmpty()   ) {
            owner = setPetIds(customerDTO.getPetIds(), owner);
        }
        return customersRepository.save(owner);
    }
}
