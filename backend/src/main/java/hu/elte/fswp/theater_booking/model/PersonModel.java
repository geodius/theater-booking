package hu.elte.fswp.theater_booking.model;

import hu.elte.fswp.theater_booking.database.PersonRepo;
import hu.elte.fswp.theater_booking.entity.Person;
import hu.elte.fswp.theater_booking.utility.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class PersonModel {
    public static PersonModel instance;

    public static PersonModel getInstance() {
        assert(instance != null);
        return instance;
    }

    private final PersonRepo personRepo;

    @Autowired
    public PersonModel(PersonRepo personRepo) {
        assert(instance == null);
        instance = this;
        this.personRepo = personRepo;
    }

    public Optional<Person> create(Person person) {
        if (!Person.isPersonValid(person)) return Optional.empty();
        if (personRepo.findById(person.getId()).isPresent()) return Optional.empty();
        if (personRepo.findByEmail(person.getEmail()).isPresent()) return Optional.empty();
        personRepo.save(person);
        return personRepo.findByEmail(person.getEmail());
    }

    public Optional<Person> getByEmail(String email) {
        return personRepo.findByEmail(email);
    }

    public List<Person> getByName(String name) {
        return personRepo.findAllByName(name);
    }

    public List<Person> getAll() {
        return Utility.ConvertIterableToList(personRepo.findAll());
    }

    public Optional<Person> modify(Person person) {
        if (!Person.isPersonValid(person)) return Optional.empty();
        if (personRepo.findById(person.getId()).isEmpty()) return Optional.empty();
        personRepo.save(person);
        return personRepo.findById(person.getId());
    }

    public boolean delete(Person person) {
        if (!personRepo.existsById(person.getId())) return false;
        personRepo.delete(person);
        return true;
    }
}