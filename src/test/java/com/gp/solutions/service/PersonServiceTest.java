package com.gp.solutions.service;

import com.gp.solutions.entity.dbo.Level;
import com.gp.solutions.entity.dbo.Party;
import com.gp.solutions.entity.dbo.Person;
import com.gp.solutions.entity.dbo.Skill;
import com.gp.solutions.repository.PersonRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PersonServiceTest {

    @Mock
    private PersonRepository repository;

    @InjectMocks
    private PersonServiceImpl service;

    private List<Person> people = new ArrayList<Person>();

    private List<Party> parties = new ArrayList<>();

    private Map<Skill, Level> skillByLevel = new HashMap<>();

    private Skill skill = new Skill();

    private Level level = new Level();

    private Person p1 = new Person();

    private Party party = new Party();

    private Person p2 = new Person();

    @Before
    public void prepare() throws ParseException {

        p1.setId(1l);
        p1.setName("John");
        p1.setUsername("John");
        p1.setAge(25);
        p1.setParties(parties);
        p1.setSkillByLevel(skillByLevel);
        people.add(p1);

        p2.setId(2l);
        p2.setName("Marry");
        p2.setUsername("Marry");
        p2.setAge(22);
        p2.setParties(parties);
        p2.setSkillByLevel(skillByLevel);
        people.add(p2);

        skillByLevel.put(skill, level);

        level.setId(1l);
        level.setName("GOOD");

        skill.setId(1l);
        skill.setName("Dancing");

        party.setId(1l);
        party.setLocation("Old Folks Club");
        party.setPeople(people);
        party.setDate(new SimpleDateFormat("yyyy-MM-dd").parse("2011-01-01"));
        parties.add(party);

    }


    @Test
    public void getAllPersonBySkillTest() {

        when(service.getAllPersonBySkill(skill.getName())).thenReturn(people);
        assertEquals(service.getAllPersonBySkill(skill.getName()).get(0).getId(), 1);
        assertEquals(service.getAllPersonBySkill(skill.getName()).get(0).getAge(), 25);
        assertEquals(service.getAllPersonBySkill(skill.getName()).get(0).getName(), "John");
        assertEquals(service.getAllPersonBySkill(skill.getName()).get(0).getUsername(), "John");
        assertEquals(service.getAllPersonBySkill(skill.getName()).get(0).getParties(), parties);
    }

    @Test
    public void getAllPersonBySkillAndPartyTest() {

        when(service.getAllPersonBySkillAndParty(skill.getName(), party.getLocation())).thenReturn(people);
        assertEquals(service.getAllPersonBySkillAndParty(skill.getName(), party.getLocation()).get(1).getId(), 2);
        assertEquals(service.getAllPersonBySkillAndParty(skill.getName(), party.getLocation()).get(1).getAge(), 22);
        assertEquals(service.getAllPersonBySkillAndParty(skill.getName(), party.getLocation()).get(1).getName(), "Marry");
        assertEquals(service.getAllPersonBySkillAndParty(skill.getName(), party.getLocation()).get(1).getUsername(), "Marry");
        assertEquals(service.getAllPersonBySkillAndParty(skill.getName(), party.getLocation()).get(1).getParties(), parties);

    }

}
