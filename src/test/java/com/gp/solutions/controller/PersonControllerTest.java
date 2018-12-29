//package com.gp.solutions.controller;
//
//import com.gp.solutions.entity.dbo.Level;
//import com.gp.solutions.entity.dbo.Party;
//import com.gp.solutions.entity.dbo.Person;
//import com.gp.solutions.entity.dbo.Skill;
//import com.gp.solutions.repository.PersonRepository;
//import com.gp.solutions.repository.UserRepository;
//import com.gp.solutions.service.PersonService;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.web.servlet.MockMvc;
//
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import static org.hamcrest.Matchers.is;
//import static org.mockito.BDDMockito.given;
//import static org.mockito.Matchers.isA;
//import static org.mockito.Mockito.verify;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@RunWith(SpringRunner.class)
//@WebMvcTest(value = PersonController.class, secure = false)
//public class PersonControllerTest {
//
//    @Autowired
//    private MockMvc mvc;
//
//    @MockBean
//    private PersonRepository personRepo;
//
//    @MockBean
//    private PersonService personService;
//
//    @MockBean
//    private UserRepository userRepository;
//
//    private List<Person> people = new ArrayList<Person>();
//
//    private List<Party> parties = new ArrayList<>();
//
//    private Map<Skill, Level> skillByLevel = new HashMap<>();
//
//    private Skill skill = new Skill();
//
//    private Level level = new Level();
//
//    private Person p1 = new Person();
//
//    private Party party = new Party();
//
//    private Person p2 = new Person();
//
//
//    @Before
//    public void prepare() throws ParseException {
//        p1.setId(1l);
//        p1.setName("John");
//        p1.setUsername("John");
//        p1.setAge(25);
//        p1.setParties(parties);
//        p1.setSkillByLevel(skillByLevel);
//        people.add(p1);
//
//        p2.setId(2l);
//        p2.setName("Marry");
//        p2.setUsername("Marry");
//        p2.setAge(22);
//        p2.setParties(parties);
//        p2.setSkillByLevel(skillByLevel);
//        people.add(p2);
//
//        skillByLevel.put(skill, level);
//
//        level.setId(1l);
//        level.setName("GOOD");
//
//        skill.setId(1l);
//        skill.setName("Dancing");
//
//        party.setId(1l);
//        party.setLocation("Old Folks Club");
//        party.setPeople(people);
//        party.setDate(new SimpleDateFormat("yyyy-MM-dd").parse("2011-01-01"));
//        parties.add(party);
//    }
//
//    @Test
//    public void getPersonTest() throws Exception {
//        given(personRepo.findOne(1l)).willReturn(p1);
//        mvc.perform(get("/people/1").accept(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isOk())
//                .andExpect(jsonPath("$.id", is(1)))
//                .andExpect(jsonPath("$.name", is("John")))
//                .andExpect(jsonPath("$.age", is(25)));
//    }
//
//    @Test
//    public void personNotFoundTest() throws Exception {
//        mvc.perform(get("/people/2").accept(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isNotFound());
//    }
//
//    @Test
//    public void getPeopleTest() throws Exception {
//        given(personRepo.findAll()).willReturn(people);
//        mvc.perform(get("/people").accept(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isOk())
//                .andExpect(jsonPath("$[0].id", is(1)))
//                .andExpect(jsonPath("$[0].name", is("John")))
//                .andExpect(jsonPath("$[0].age", is(25)));
//    }
//
//    @Test
//    public void deletePersonTest() throws Exception {
//        given(personRepo.findOne(1l)).willReturn(p1);
//        mvc.perform(delete("/people/1"))
//                .andExpect(status().isOk());
//        verify(personRepo).delete(isA(Long.class));
//    }
//
//    @Test
//    public void getPersonPartiesTest() throws Exception {
//        given(personRepo.findOne(1l)).willReturn(p1);
//        mvc.perform(get("/people/1/parties").accept(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isOk())
//                .andExpect(jsonPath("$[0].id", is(1)))
//                .andExpect(jsonPath("$[0].location", is("Old Folks Club")));
//    }
//
//    @Test
//    public void getPersonsBySkillTest() throws Exception {
//        given(personService.getAllPersonBySkill(skill.getName())).willReturn(people);
//        mvc.perform(get("/people/skill/" + skill.getName()).accept(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isOk())
//                .andExpect(jsonPath("$[0].id", is(1)))
//                .andExpect(jsonPath("$[0].name", is("John")))
//                .andExpect(jsonPath("$[0].age", is(25)));
//    }
//
//    @Test
//    public void ggetPersonsBySkillAndPartyTest() throws Exception {
//        given(personService.getAllPersonBySkillAndParty(skill.getName(), party.getLocation())).willReturn(people);
//        mvc.perform(get("/people//skill/" + skill.getName() + "/party/" + party.getLocation())
//                .accept(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isOk())
//                .andExpect(jsonPath("$[1].id", is(2)))
//                .andExpect(jsonPath("$[1].name", is("Marry")))
//                .andExpect(jsonPath("$[1].age", is(22)));
//    }
//
//
//}
