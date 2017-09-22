package com.gigy.config;

import com.gigy.controller.PartyController;
import com.gigy.controller.PersonController;
import com.gigy.controller.SkillController;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

@Configuration
@EnableResourceServer
@PropertySource("classpath:authorization.properties")
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {
    // needs to be a separate config in order to be used in unit test with custom slices
    @Value("${authorization.resourceIds}")
    private String getResourceId;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers(PartyController.REQUEST_MAPPING + "/**").authenticated()
                .antMatchers(PersonController.REQUEST_MAPPING + "/**").authenticated()
                .antMatchers(SkillController.REQUEST_MAPPING + "/**").authenticated()
                .anyRequest().permitAll();
    }


    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources.resourceId(getResourceId);
    }
}