package com.udacity.jdnd.course3.critter.Entities;

import com.udacity.jdnd.course3.critter.pet.PetType;


import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;


@Entity
@Table
public class Pets {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private PetType type;
    private String name;
    @ManyToOne(optional = false,
            targetEntity = Customers.class)
    private Customers owner;
    private LocalDate birthDate;
    private String notes;
    @ManyToMany(targetEntity = Schedules.class)
    private List<Schedules> schedules;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public PetType getType() {
        return type;
    }

    public void setType(PetType type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Customers getOwner() {
        return owner;
    }

    public void setOwner(Customers owner) {
        this.owner = owner;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public List<Schedules> getSchedules() {
        return schedules;
    }

    public void setSchedules(List<Schedules> schedules) {
        this.schedules = schedules;
    }

    public Pets() {
    }

    public Pets(long id, PetType type, String name, Customers owner, LocalDate birthDate, String notes, List<Schedules> schedules) {
        this.id = id;
        this.type = type;
        this.name = name;
        this.owner = owner;
        this.birthDate = birthDate;
        this.notes = notes;
        this.schedules = schedules;
    }
}


