package com.gp.solutions.controller;

import com.gp.solutions.entity.dbo.Party;
import com.gp.solutions.entity.dbo.Person;
import com.gp.solutions.entity.dbo.User;
import com.gp.solutions.repository.PersonRepository;
import com.gp.solutions.service.PersonService;
import com.gp.solutions.type.UserRole;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping(PersonController.REQUEST_MAPPING)
@Api(description = "CRUD operations for person, also find person by skill and party name")
public class PersonController {

    public static final String REQUEST_MAPPING = "/people";

    @Autowired
    private PersonRepository personRepo;

    @Autowired
    private PersonService personService;

    /**
     * @return all persons
     */
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Collection<Person>> getPeople() {
        return new ResponseEntity<>(personRepo.findAll(), HttpStatus.OK);
    }

    /**
     * @param id - person id
     * @return person by id
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Person> getPerson(@PathVariable final long id) {
        final Person person = personRepo.findOne(id);

        if (person != null) {
            return new ResponseEntity<>(personRepo.findOne(id), HttpStatus.OK);
        } else {
            return new ResponseEntity<>((Person) null, HttpStatus.NOT_FOUND);
        }
    }

    /**
     * @param person     - data on the new person
     * @param activeUser - user who send request
     * @return new person
     */
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> addPerson(@RequestBody final Person person,
                                       @AuthenticationPrincipal final User activeUser) {
        if (UserRole.ADMIN.equals(activeUser.getUserRole())) {
            return new ResponseEntity<>(personRepo.save(person), HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }


    /**
     * @param id         - person id
     * @param activeUser - user who send request
     * @return delete person by id
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deletePerson(@PathVariable final long id,
                                             @AuthenticationPrincipal final User activeUser) {
        final Person currentPerson = personRepo.findByUsername(activeUser.getUsername());
        final Person deletePerson = personRepo.findOne(id);
        if ((currentPerson.getId() == id || UserRole.ADMIN.equals(activeUser.getUserRole())) && deletePerson != null) {
            personRepo.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    /**
     * @param id - person id
     * @return all persons in accordance with the introduced person id
     */
    @RequestMapping(value = "/{id}/parties", method = RequestMethod.GET)
    public ResponseEntity<Collection<Party>> getPersonParties(@PathVariable final long id) {
        final Person person = personRepo.findOne(id);

        if (person != null) {
            return new ResponseEntity<>(person.getParties(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>((Collection<Party>) null, HttpStatus.NOT_FOUND);
        }
    }

    /**
     * @param skillName -name of skill for search
     * @return all persons in accordance with the introduced skill name
     */
    @RequestMapping(value = "/skill/{skillName}", method = RequestMethod.GET)
    public ResponseEntity<Collection<Person>> getPersonsBySkill(@PathVariable final String skillName) {
        final List<Person> people = personService.getAllPersonBySkill(skillName);
        if (!people.isEmpty()) {
            return new ResponseEntity<>(people, HttpStatus.OK);
        } else {
            return new ResponseEntity<>((Collection<Person>) null, HttpStatus.NOT_FOUND);
        }
    }

    /**
     * @param skillName - name of skill for search
     * @param location  - location of party or otherwise the name of the party
     * @return all persons in accordance with the introduced party and skill name
     */
    @RequestMapping(value = "/skill/{skillName}/party/{location}", method = RequestMethod.GET)

    public ResponseEntity<Collection<Person>> getPersonsBySkillAndParty(@PathVariable final String skillName, @PathVariable final String location) {
        final List<Person> people = personService.getAllPersonBySkillAndParty(skillName, location);
        if (!people.isEmpty()) {
            return new ResponseEntity<>(people, HttpStatus.OK);
        } else {
            return new ResponseEntity<>((Collection<Person>) null, HttpStatus.NOT_FOUND);
        }
    }

}
