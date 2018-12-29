//package com.gp.solutions.controller;
//
//import com.gp.solutions.entity.dbo.Party;
//import com.gp.solutions.entity.dbo.User;
//import com.gp.solutions.repository.PartyRepository;
//import com.gp.solutions.repository.UserRepository;
//import com.gp.solutions.type.UserRole;
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
//import java.util.ArrayList;
//import java.util.List;
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
//@WebMvcTest(value = PartyController.class, secure = false)
//public class PartyControllerTest {
//
//    @Autowired
//    private MockMvc mvc;
//
//    @MockBean
//    private PartyRepository partyRepo;
//
//    @MockBean
//    private UserRepository userRepository;
//
//    private Party party;
//
//    private User user;
//
//    @Before
//    public void prepare() {
//        party = new Party();
//        party.setId(1l);
//        party.setLocation("Garden");
//
//        user = new User();
//        user.setId(1l);
//        user.setUsername("John");
//        user.setPassword("secret");
//        user.setUserRole(UserRole.ADMIN);
//        user.setEnabled(true);
//    }
//
//    @Test
//    public void getPartyTest() throws Exception {
//        given(partyRepo.findOne(1l)).willReturn(party);
//        mvc.perform(get("/parties/1").accept(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isOk())
//                .andExpect(jsonPath("$.id", is(1)))
//                .andExpect(jsonPath("$.location", is("Garden")));
//    }
//
//    @Test
//    public void partyNotFoundTest() throws Exception {
//        mvc.perform(get("/parties/2").accept(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isNotFound());
//    }
//
//    @Test
//    public void getPartiesTest() throws Exception {
//        final List<Party> parties = new ArrayList<Party>();
//        parties.add(party);
//
//        given(partyRepo.findAll()).willReturn(parties);
//        mvc.perform(get("/parties").accept(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isOk())
//                .andExpect(jsonPath("$[0].id", is(1)))
//                .andExpect(jsonPath("$[0].location", is("Garden")));
//    }
//
//    @Test
//    public void deletePartyTest() throws Exception {
//        given(partyRepo.findOne(1l)).willReturn(party);
//        mvc.perform(delete("/parties/1"))
//                .andExpect(status().isOk());
//        verify(partyRepo).delete(isA(Long.class));
//    }
//
//}
