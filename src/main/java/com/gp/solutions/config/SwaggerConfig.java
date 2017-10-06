package com.gp.solutions.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;
import java.util.List;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket apiDocumentation() {
        return new Docket(DocumentationType.SWAGGER_2).groupName("api").
                select().apis(RequestHandlerSelectors.basePackage("com.gp.solutions.controller")).
                paths(PathSelectors.any()).
                build().
                securitySchemes(Collections.singletonList(securitySchema())).
                securityContexts(Collections.singletonList(securityContext()));

    }

    public static final String securitySchemaOAuth2 = "oauth2schema";
    public static final String authorizationScopeGlobal = "global";
    public static final String authorizationScopeGlobalDesc = "accessEverything";

    private OAuth securitySchema() {
        AuthorizationScope authorizationScope = new AuthorizationScope(authorizationScopeGlobal, authorizationScopeGlobal);
        GrantType grantType = new ResourceOwnerPasswordCredentialsGrant("http://localhost:8000/gpsolutions/oauth/token");
        return new OAuth(securitySchemaOAuth2, Collections.singletonList(authorizationScope), Collections.singletonList(grantType));
    }

    private SecurityContext securityContext() {
        return SecurityContext.builder()
                .securityReferences(defaultAuth())
                .build();
    }

    private List<SecurityReference> defaultAuth() {
        final AuthorizationScope authorizationScope
                = new AuthorizationScope(authorizationScopeGlobal, authorizationScopeGlobalDesc);
        final AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        return Collections.singletonList(
                new SecurityReference(securitySchemaOAuth2, authorizationScopes));
    }

}