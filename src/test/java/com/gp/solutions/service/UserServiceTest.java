package com.gp.solutions.service;

import com.gp.solutions.entity.dbo.User;
import com.gp.solutions.repository.UserRepository;
import com.gp.solutions.type.UserRole;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.BeanUtils;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    @Mock
    private UserRepository repository;

    @InjectMocks
    private UserService service;

    private User user = new User();

    @Before
    public void prepare() {
        final String description = UserRole.ADMIN.getDescription();
        user.setId(1l);
        user.setUsername("John");
        user.setPassword("secret");
        user.setUserRole(UserRole.ADMIN);
        user.setEnabled(true);
    }

    @Test
    public void loadUserByUsernameTest() {

        when(service.loadUserByUsername(user.getUsername())).thenReturn(user);
        assertEquals(service.loadUserByUsername(user.getUsername()).getPassword(), "secret");
        assertEquals(service.loadUserByUsername(user.getUsername()).getUsername(), "John");


    }

    @Test
    public void userCoverageTest() {
        final User user2 = new User();
        BeanUtils.copyProperties(user, user2);

        assertEquals(user.getId(), user2.getId());
        assertEquals(user.getUsername(), user2.getUsername());
        assertEquals(user.getUserRole(), user2.getUserRole());
        assertEquals(user.getAuthorities(), user2.getAuthorities());
        assertEquals(user.getPassword(), user2.getPassword());
        assertEquals(user.isAccountNonExpired(), user2.isAccountNonExpired());
        assertEquals(user.isAccountNonLocked(), user2.isAccountNonLocked());
        assertEquals(user.isCredentialsNonExpired(), user2.isCredentialsNonExpired());
        assertEquals(user.isEnabled(), user2.isEnabled());

    }
}
