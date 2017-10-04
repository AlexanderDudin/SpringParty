package com.gp.solutions.controller;

import java.security.Principal;
import java.util.Collection;

import com.gp.solutions.entity.dbo.Party;
import com.gp.solutions.entity.dbo.User;
import com.gp.solutions.repository.PartyRepository;
import com.gp.solutions.repository.UserRepository;
import com.gp.solutions.type.UserRole;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(PartyController.REQUEST_MAPPING)
@Api(description = "CRUD operations for party")
public class PartyController {

    public static final String REQUEST_MAPPING = "/parties";

    @Autowired
    private  PartyRepository partyRepo;

    /**
     * Finds and returns all parties.
     */
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Collection<Party>> getParties() {
        return new ResponseEntity<>(partyRepo.findAll(), HttpStatus.OK);
    }

    /**
     * Finds and returns party by id.
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Party> getParty(@PathVariable long id) {
        final Party party = partyRepo.findOne(id);

        if (party != null) {
            return new ResponseEntity<>(partyRepo.findOne(id), HttpStatus.OK);
        } else {
            return new ResponseEntity<>((Party) null, HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Add new party.
     */
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Party> addParty(@RequestBody Party party, @AuthenticationPrincipal User activeUser) {
        if (UserRole.ADMIN.equals(activeUser.getUserRole())) {
            return new ResponseEntity<>(partyRepo.save(party), HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    /**
     * Delete party by id.
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteParty(@PathVariable long id, @AuthenticationPrincipal User activeUser) {
        if (UserRole.ADMIN.equals(activeUser.getUserRole())) {
            partyRepo.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

}
