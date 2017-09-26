package com.gp.solutions.service;

import com.gp.solutions.entity.dbo.Person;

import java.util.List;

public interface PersonService {

    List<Person> getAllPersonBySkill(String skillName);

    List<Person> getAllPersonBySkillAndParty(String skillName, String location);


}
