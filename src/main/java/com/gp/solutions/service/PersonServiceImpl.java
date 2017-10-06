package com.gp.solutions.service;

import com.gp.solutions.entity.dbo.Person;
import com.gp.solutions.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PersonServiceImpl implements PersonService {

    @Autowired
    private PersonRepository personRepository;

    /**
     * @param skillName - name of skill for finding
     * @return persons in accordance with the introduced skill name
     */
    @Override
    public List<Person> getAllPersonBySkill(final String skillName) {
        return personRepository.findBySkill(skillName);
    }


    /**
     * @param skillName - name of skill for search
     * @param location  - location of party or otherwise the name of the party
     * @return persons in accordance with the introduced skill name and party location
     */
    @Override
    public List<Person> getAllPersonBySkillAndParty(final String skillName, final String location) {
        return personRepository.findBySkillAndParty(skillName, location);
    }
}
