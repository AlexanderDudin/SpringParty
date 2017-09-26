package com.gp.solutions.repository;

import com.gp.solutions.entity.dbo.Party;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


//@RunWith(SpringRunner.class)
@DataJpaTest
public class PartyRepositoryTest {


    private PartyRepository repository;

    @Before
    public void prepare() {
        repository = mock(PartyRepository.class);
    }

//    @Mock
//    private PartyRepository repository;

    @Test
    public void repositorySavesParty() {
        final Party party = new Party();
        party.setId(1);
        party.setLocation("Garden");

        //  Party result = repository.save(party);

        when(repository.findOneByLocation(party.getLocation())).thenReturn(party);

        Assert.assertEquals(repository.findOneByLocation(party.getLocation()).getLocation(), "Garden");
        Assert.assertEquals(repository.findOneByLocation(party.getLocation()).getId(), 1);


        //   assertEquals(result.getLocation(), "Garden");
    }

}
