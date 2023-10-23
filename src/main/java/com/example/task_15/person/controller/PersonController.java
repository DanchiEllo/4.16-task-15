package com.example.task_15.person.controller;

import com.example.task_15.person.model.Person;
import com.example.task_15.person.service.PersonService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class PersonController {

    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    // Get requests
    @GetMapping("/persons")
    public Iterable<Person> getAllPersons() {
        return personService.getAllPersons();
    }

    @GetMapping("/person/{id}")
    public Optional<Person> findPersonById(@PathVariable int id) {
        return personService.getPersonById(id);
    }
    

    // Post requests
    @PostMapping("/persons")
    public ResponseEntity<Person> addPerson(@RequestBody Person person) {
        return new ResponseEntity<>(personService.addPerson(person), HttpStatus.CREATED);
    }


    // Put requests
    @PutMapping("/persons/{id}")
    public Optional<Person> updatePersonById(@PathVariable int id, @RequestBody Person person) {
        return personService.updatePersonById(id, person);
    }


    // Delete requests
    @DeleteMapping("/persons/{id}")
    public void deletePersonById(@PathVariable int id) {
        personService.deletePersonById(id);
    }

}
