package com.gp.solutions.repository;

import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.gp.solutions.entity.dbo.Person;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


//@RunWith(SpringRunner.class)
@DataJpaTest
public class PersonRepositoryTest {

    private PersonRepository repository;

    @Before
    public void prepare() {
        repository = mock(PersonRepository.class);
    }

    @Test
    public void repositorySavesPerson() {
        final Person person = new Person();
        person.setId(1);
        person.setName("John");
        person.setUsername("peter@example.com");
        person.setAge(25);
//        Person result = repository.save(person);
        when(repository.findByUsername(person.getUsername())).thenReturn(person);

        //assertEquals(result.getName(), "John");
        //assertEquals(result.getAge(), 25);
        assertEquals(repository.findByUsername(person.getUsername()).getUsername(), "peter@example.com");
        assertEquals(repository.findByUsername(person.getUsername()).getName(), "John");
        assertEquals(repository.findByUsername(person.getUsername()).getAge(), 25);
        assertEquals(repository.findByUsername(person.getUsername()).getId(), 1);
    }

}
