package com.gp.solutions.repository;

import com.gp.solutions.entity.dbo.Skill;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


@DataJpaTest
public class SkillRepositoryTest {


    private SkillRepository skillRepository;

    @Before
    public void prepare() {
        skillRepository = mock(SkillRepository.class);
    }


    @Test
    public void repositorySavesSkill() {
        final Skill skill = new Skill();
        skill.setId(1l);
        skill.setName("Juggling");

        when(skillRepository.findOne(1l)).thenReturn(skill);

        Assert.assertEquals(skillRepository.findOne(1l).getId(), 1l);
        Assert.assertEquals(skillRepository.findOne(1l).getName(), "Juggling");

    }

}
