package com.gp.solutions.controller;

import com.gp.solutions.entity.dbo.Party;
import com.gp.solutions.entity.dbo.User;
import com.gp.solutions.repository.PartyRepository;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

import static com.gp.solutions.type.UserRole.ADMIN;

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
     * @param party      - data on the new party
     * @param activeUser - user who send request
     * @return new party
     */
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Party> addParty(@RequestBody final Party party,
                                          @AuthenticationPrincipal final User activeUser) {
        if (ADMIN.equals(activeUser.getUserRole())) {
            return new ResponseEntity<>(partyRepo.save(party), HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    /**
     * Delete party by id.
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteParty(@PathVariable final long id,
                                            @AuthenticationPrincipal final User activeUser) {
        if (ADMIN.equals(activeUser.getUserRole())) {
            partyRepo.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

}
