package com.gp.solutions.controller;

import java.security.Principal;
import java.util.Collection;
import java.util.List;

import com.gp.solutions.entity.dbo.User;
import com.gp.solutions.repository.UserRepository;
import com.gp.solutions.service.PersonService;
import com.gp.solutions.service.UserService;
import com.gp.solutions.type.UserRole;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.gp.solutions.entity.dbo.Party;
import com.gp.solutions.entity.dbo.Person;
import com.gp.solutions.repository.PersonRepository;

@RestController
@RequestMapping(PersonController.REQUEST_MAPPING)
@Api(description = "CRUD operations for person, also find person by skill and party name")
public class PersonController {

    public static final String REQUEST_MAPPING = "/people";

    @Autowired
    private PersonRepository personRepo;

    @Autowired
    private PersonService personService;

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Collection<Person>> getPeople() {
        return new ResponseEntity<>(personRepo.findAll(), HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Person> getPerson(@PathVariable long id) {
        final Person person = personRepo.findOne(id);

        if (person != null) {
            return new ResponseEntity<>(personRepo.findOne(id), HttpStatus.OK);
        } else {
            return new ResponseEntity<>((Person) null, HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> addPerson(@RequestBody Person person, Principal principal) {
        final User currentUser = userRepository.findOneByUsername(principal.getName());
        if (UserRole.ADMIN.equals(currentUser.getUserRole())) {
            return new ResponseEntity<>(personRepo.save(person), HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deletePerson(@PathVariable long id, Principal principal) {
        final Person currentPerson = personRepo.findByUsername(principal.getName());
        final User currentUser = userRepository.findOneByUsername(principal.getName());
        final Person deletePerson = personRepo.findOne(id);
        if ((currentPerson.getId() == id || UserRole.ADMIN.equals(currentUser.getUserRole())) && deletePerson != null) {
            personRepo.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    @RequestMapping(value = "/{id}/parties", method = RequestMethod.GET)
    public ResponseEntity<Collection<Party>> getPersonParties(@PathVariable long id) {
        final Person person = personRepo.findOne(id);

        if (person != null) {
            return new ResponseEntity<>(person.getParties(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>((Collection<Party>) null, HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/skill/{skillName}", method = RequestMethod.GET)
    public ResponseEntity<Collection<Person>> getPersonsBySkill(@PathVariable String skillName) {
        final List<Person> people = personService.getAllPersonBySkill(skillName);
        if (!people.isEmpty()) {
            return new ResponseEntity<>(people, HttpStatus.OK);
        } else {
            return new ResponseEntity<>((Collection<Person>) null, HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/skill/{skillName}/party/{location}", method = RequestMethod.GET)

    public ResponseEntity<Collection<Person>> getPersonsBySkillAndParty(@PathVariable String skillName, @PathVariable String location) {
        final List<Person> people = personService.getAllPersonBySkillAndParty(skillName, location);
        if (!people.isEmpty()) {
            return new ResponseEntity<>(people, HttpStatus.OK);
        } else {
            return new ResponseEntity<>((Collection<Person>) null, HttpStatus.NOT_FOUND);
        }
    }

}
