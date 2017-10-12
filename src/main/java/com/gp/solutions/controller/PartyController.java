package com.gp.solutions.controller;

import com.gp.solutions.entity.dbo.Party;
import com.gp.solutions.repository.PartyRepository;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping(PartyController.REQUEST_MAPPING)
@Api(description = "CRUD operations for party")
public class PartyController {

    public static final String REQUEST_MAPPING = "/parties";

    @Autowired
    private PartyRepository partyRepo;

    /**
     * @return all parties
     */
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Collection<Party>> getParties() {
        return new ResponseEntity<>(partyRepo.findAll(), HttpStatus.OK);
    }

    /**
     * @param id - party id
     * @return party by id
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Party> getParty(@PathVariable final long id) {
        final Party party = partyRepo.findOne(id);

        if (party != null) {
            return new ResponseEntity<>(partyRepo.findOne(id), HttpStatus.OK);
        } else {
            return new ResponseEntity<>((Party) null, HttpStatus.NOT_FOUND);
        }
    }

    /**
     * @param party - data on the new party
     * @return new party
     */
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Party> addParty(@RequestBody final Party party) {
        return new ResponseEntity<>(partyRepo.save(party), HttpStatus.CREATED);
    }

    /**
     * Delete party by id.
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteParty(@PathVariable final long id) {
        partyRepo.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
