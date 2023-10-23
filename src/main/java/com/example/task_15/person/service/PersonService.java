package com.example.task_15.person.service;

import com.example.task_15.person.model.Person;
import com.example.task_15.person.repository.PersonRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PersonService {

    private final PersonRepository repository;

    public PersonService(PersonRepository repository) {
        this.repository = repository;
    }

    public Iterable<Person> getAllPersons() {
        return repository.findAll();
    }

    public Optional<Person> getPersonById(int id) {
        return repository.findById(id);
    }

    public Person addPerson(Person person) {
        return repository.save(person);
    }

    public Optional<Person> updatePersonById(int id, Person person) {
        Optional<Person> optionalPerson = repository.findById(id);

        if (optionalPerson.isPresent()) {
            Person updatedPerson = optionalPerson.get();

            updatedPerson.setFirstname(person.getFirstname());
            updatedPerson.setSurname(person.getSurname());
            updatedPerson.setLastname(person.getLastname());
            updatedPerson.setBirthday(person.getBirthday());

            return Optional.of(repository.save(updatedPerson));
        } else {
            return Optional.empty();
        }
    }

    @Transactional
    public void deletePersonById(int id) {
        repository.deleteById(id);
    }

}
