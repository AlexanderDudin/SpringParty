package com.gp.solutions.service;

import com.gp.solutions.entity.dbo.Level;
import com.gp.solutions.entity.dbo.Person;
import com.gp.solutions.entity.dbo.Skill;
import com.gp.solutions.repository.SkillRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class SkillServiceTest {

    @Mock
    private SkillRepository repository;

    @InjectMocks
    private SkillServiceImpl service;

    private List<Person> people = new ArrayList<Person>();

    private Skill skill1 = new Skill();

    private Skill skill2 = new Skill();

    private Level level = new Level();

    private List<Skill> skills = new ArrayList<>();

    @Before
    public void prepare() throws ParseException {

        skill1.setId(1l);
        skill1.setName("Dancing");
        skills.add(skill1);

        skill2.setId(2l);
        skill2.setName("Juggling");
        skills.add(skill2);

        level.setId(1l);
        level.setName("GOOD");


    }

    @Test
    public void getSkillByLevelTest() {

        when(service.getSkillByLevel(level.getName())).thenReturn(skills);
        assertEquals(level.getId(), 1);
        assertEquals(service.getSkillByLevel(level.getName()).get(0).getId(), 1);
        assertEquals(service.getSkillByLevel(level.getName()).get(0).getName(), "Dancing");

    }

}
