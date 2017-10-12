package com.gp.solutions.config;

import com.gp.solutions.controller.PartyController;
import com.gp.solutions.controller.PersonController;
import com.gp.solutions.controller.SkillController;
import com.gp.solutions.type.UserRole;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

@Configuration
@EnableResourceServer
@PropertySource("classpath:authorization.properties")
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {
    public static final String RESOURCE_ID = "resource";


    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers(PartyController.REQUEST_MAPPING + "/**").authenticated()
                .antMatchers(PersonController.REQUEST_MAPPING + "/**").authenticated()
                .antMatchers(SkillController.REQUEST_MAPPING + "/**").authenticated()
                .antMatchers(HttpMethod.POST, "/**").hasRole(UserRole.ADMIN.name())
                .antMatchers(HttpMethod.DELETE, "/**").hasRole(UserRole.ADMIN.name())
                .anyRequest().permitAll();
    }


    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources
                .resourceId(RESOURCE_ID);
    }
}