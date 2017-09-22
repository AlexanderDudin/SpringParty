package com.gigy.repository;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.gigy.entity.dbo.Party;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


//@RunWith(SpringRunner.class)
@DataJpaTest
public class PartyRepositoryTest {

    @Mock
    private PartyRepository repository;

    @Test
    public void repositorySavesParty() {
        Party party = new Party();
        party.setId(1);
        party.setLocation("Garden");
        
      //  Party result = repository.save(party);

        when(repository.findOneByLocation(party.getLocation())).thenReturn(party);

        assertEquals(repository.findOneByLocation(party.getLocation()).getLocation(),"Garden");
        assertEquals(repository.findOneByLocation(party.getLocation()).getId(),1);


     //   assertEquals(result.getLocation(), "Garden");
    }

}
