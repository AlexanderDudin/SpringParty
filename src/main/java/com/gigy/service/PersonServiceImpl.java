package com.gigy.service;

import com.gigy.entity.dbo.Party;
import com.gigy.entity.dbo.Person;
import com.gigy.entity.dbo.Skill;
import com.gigy.repository.PartyRepository;
import com.gigy.repository.PersonRepository;
import com.gigy.repository.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    PersonRepository personRepository;
    @Autowired
    PartyRepository partyRepository;
    @Autowired
    SkillRepository skillRepository;


    @Override
    public List<Person> getAllPersonsByPartyAndSkill(String location, String skillName) {
        final Party party = partyRepository.findOneByLocation(location);
        final Skill skill = skillRepository.findByName(skillName);
        final List<Person> personList = personRepository.findByParties(party);
        // ? db level
        return personList.stream().filter((p) -> p.getSkillByLevel().containsKey(skill)).collect(Collectors.toList());
    }

    @Override
    public List<Person> getAllPersonBySkill(String skillName) {
//        final Skill skill = skillRepository.findByName(skillName);
//        final List<Person> personList = personRepository.findAll();
//        return personList.stream().filter((p) -> p.getSkillByLevel().containsKey(skill)).collect(Collectors.toList());
        return personRepository.findBySkill(skillName);
    }

    @Override
    public List<Person> getAllPersonBySkillAndRarty(String skillName, String location) {
        return personRepository.findBySkillAndParty(skillName, location);
    }
}
