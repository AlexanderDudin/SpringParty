package com.gp.solutions.service;

import com.gp.solutions.entity.dbo.Party;
import com.gp.solutions.entity.dbo.Person;
import com.gp.solutions.entity.dbo.Skill;
import com.gp.solutions.repository.PartyRepository;
import com.gp.solutions.repository.PersonRepository;
import com.gp.solutions.repository.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class PersonServiceImpl implements PersonService {

    @Autowired
    PersonRepository personRepository;
    @Autowired
    PartyRepository partyRepository;
    @Autowired
    SkillRepository skillRepository;


//    @Override
//    public List<Person> getAllPersonsByPartyAndSkill(String location, String skillName) {
//        final Party party = partyRepository.findOneByLocation(location);
//        final Skill skill = skillRepository.findByName(skillName);
//        final List<Person> personList = personRepository.findByParties(party);
//        // ? db level
//        return personList.stream().filter((p) -> p.getSkillByLevel().containsKey(skill)).collect(Collectors.toList());
//    }

    @Override
    public List<Person> getAllPersonBySkill(String skillName) {
//        final Skill skill = skillRepository.findByName(skillName);
//        final List<Person> personList = personRepository.findAll();
//        return personList.stream().filter((p) -> p.getSkillByLevel().containsKey(skill)).collect(Collectors.toList());
        return personRepository.findBySkill(skillName);
    }

    @Override
    public List<Person> getAllPersonBySkillAndParty(String skillName, String location) {
        return personRepository.findBySkillAndParty(skillName, location);
    }
}
