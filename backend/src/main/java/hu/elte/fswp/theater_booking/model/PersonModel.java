package hu.elte.fswp.theater_booking.model;

import hu.elte.fswp.theater_booking.database.PersonRepo;
import hu.elte.fswp.theater_booking.database.RoleRepo;
import hu.elte.fswp.theater_booking.entity.Person;
import hu.elte.fswp.theater_booking.entity.Role;
import hu.elte.fswp.theater_booking.entity.RoleType;
import hu.elte.fswp.theater_booking.utility.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class PersonModel {
    private static PersonModel instance;

    public static PersonModel getInstance() {
        assert(instance != null);
        return instance;
    }

    private final PersonRepo personRepo;
    private final RoleRepo roleRepo;

    @Autowired
    public PersonModel(PersonRepo personRepo, RoleRepo roleRepo) {
        assert(instance == null);
        instance = this;
        this.personRepo = personRepo;
        this.roleRepo = roleRepo;
    }

    public Optional<Person> create(Person person) {
        if (!Person.isPersonValid(person)) return Optional.empty();
        if (personRepo.findById(person.getId()).isPresent()) return Optional.empty();
        if (personRepo.findByEmail(person.getEmail()).isPresent()) return Optional.empty();
        person.clearRoles();
        personRepo.save(person);
        person = personRepo.findByEmail(person.getEmail()).get();
        roleRepo.findByRoleType(RoleType.USER).ifPresent(person::addRole);
        personRepo.save(person);
        return personRepo.findByEmail(person.getEmail());
    }

    public Optional<Person> getByEmail(String email) {
        return personRepo.findByEmail(email);
    }

    public Optional<Person> modifyRole(Person person, Role role, boolean mode){
        Optional<Person> temp = personRepo.findById(person.getId());
        if ( temp.isEmpty() ) {
            return Optional.empty();
        }
        Person dbPerson = temp.get();
        if ( !dbPerson.equals(person) || !Boolean.logicalXor( dbPerson.hasRole(role), mode ) ) {
            return Optional.empty();
        }
        if (mode){
            dbPerson.addRole(role);
        }
        else {
            dbPerson.removeRole(role);
        }
        personRepo.save(dbPerson);
        return Optional.of(dbPerson);
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

    public Optional<Person> getById(int id) {
        return personRepo.findById(id);
    }
}
