package com.gigy.controller;

import java.util.Collection;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.gigy.entity.dbo.Party;
import com.gigy.repository.PartyRepository;

@RestController
@RequestMapping("/parties")
public class PartyController {

	@Autowired
	private PartyRepository partyRepo;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<Collection<Party>> getParties() {
		return new ResponseEntity<>(partyRepo.findAll(), HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Party> getParty(@PathVariable long id) {
		Party party = partyRepo.findOne(id);

		if (party != null) {
			return new ResponseEntity<>(partyRepo.findOne(id), HttpStatus.OK);
		} else {
			return new ResponseEntity<>((Party) null, HttpStatus.NOT_FOUND);
		}
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Party> addParty(@RequestBody Party party) {
		return new ResponseEntity<>(partyRepo.save(party), HttpStatus.CREATED);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deleteParty(@PathVariable long id) {
		partyRepo.delete(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
