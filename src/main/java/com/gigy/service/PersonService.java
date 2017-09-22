package com.gigy.service;

import com.gigy.entity.dbo.Person;

import java.util.List;

public interface PersonService {

    List<Person> getAllPersonsByPartyAndSkill(String partyLocation, String skillName);

    List<Person> getAllPersonBySkill(String skillName);

    List<Person> getAllPersonBySkillAndRarty(String skillName, String location);


}
