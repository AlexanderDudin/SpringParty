package com.gp.solutions.controller;

import java.security.Principal;
import java.util.Collection;
import java.util.List;

import com.gp.solutions.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
public class PersonController {

	public static final String REQUEST_MAPPING = "/people";

	@Autowired
	private PersonRepository personRepo;

	@Autowired
	private PersonService personService;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<Collection<Person>> getPeople() {
		return new ResponseEntity<>(personRepo.findAll(), HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Person> getPerson(@PathVariable long id) {
		Person person = personRepo.findOne(id);

		if (person != null) {
			return new ResponseEntity<>(personRepo.findOne(id), HttpStatus.OK);
		} else {
			return new ResponseEntity<>((Person) null, HttpStatus.NOT_FOUND);
		}
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> addPerson(@RequestBody Person person) {
		return new ResponseEntity<>(personRepo.save(person), HttpStatus.CREATED);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deletePerson(@PathVariable long id, Principal principal) {
		Person currentPerson = personRepo.findByUsername(principal.getName());

		if (currentPerson.getId() == id) {
			personRepo.delete(id);
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
	}

	@RequestMapping(value = "/{id}/parties", method = RequestMethod.GET)
	public ResponseEntity<Collection<Party>> getPersonParties(@PathVariable long id) {
		Person person = personRepo.findOne(id);

		if (person != null) {
			return new ResponseEntity<>(person.getParties(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>((Collection<Party>) null, HttpStatus.NOT_FOUND);
		}
	}

    @RequestMapping(value = "/skill/{skillName}", method = RequestMethod.GET)
    public ResponseEntity<Collection<Person>> getPersonsBySkill(@PathVariable String skillName) {
        List<Person> people = personService.getAllPersonBySkill(skillName);
        if (!people.isEmpty()) {
            return new ResponseEntity<>(people, HttpStatus.OK);
        } else {
            return new ResponseEntity<>((Collection<Person>) null, HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/skill/{skillName}/party/{location}", method = RequestMethod.GET)

    public ResponseEntity<Collection<Person>> getPersonsBySkillAndParty(@PathVariable String skillName, @PathVariable String location) {
        List<Person> people = personService.getAllPersonBySkillAndParty(skillName,location);
        if (!people.isEmpty()) {
            return new ResponseEntity<>(people, HttpStatus.OK);
        } else {
            return new ResponseEntity<>((Collection<Person>) null, HttpStatus.NOT_FOUND);
        }
    }

}