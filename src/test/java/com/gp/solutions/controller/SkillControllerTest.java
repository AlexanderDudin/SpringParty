package com.gp.solutions.controller;

import com.gp.solutions.entity.dbo.Level;
import com.gp.solutions.entity.dbo.Skill;
import com.gp.solutions.repository.SkillRepository;
import com.gp.solutions.service.SkillService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(value = SkillController.class, secure = false)
public class SkillControllerTest {

	@Autowired
	private MockMvc mvc;

	@MockBean
	private SkillService skillService;

	@MockBean
	private SkillRepository skillRepository;

   	private Skill skill;
   	private Level level;
	
	@Before
	public void prepare() {
		skill = new Skill();
		skill.setId(1l);
		skill.setName("Story-telling");
	}

	@Test
	public void getSkillTest() throws Exception {
		final List<Skill> skills = new ArrayList<Skill>();
		skills.add(skill);

		given(skillService.getSkillByLevel("GODLIKE")).willReturn(skills);
		mvc.perform(get("/skill/level/GODLIKE").accept(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isOk())
				.andExpect(jsonPath("$[0].id", is(1)))
				.andExpect(jsonPath("$[0].name", is("Story-telling")));
	}
	
	@Test
	public void skillNotFoundTest() throws Exception {
		mvc.perform(get("/skill/level/BAD").accept(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isNotFound());
	}
}
