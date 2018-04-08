package com.codextask.backend.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;

@Configuration
@EnableResourceServer
@PropertySource("classpath:security.properties")
public class OAuth2ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

    private ResourceServerTokenServices tokenServices;

    @Value("${security.jwt.resource-ids}")
    protected String RESOURCE_ID;

    @Autowired
    public OAuth2ResourceServerConfiguration(ResourceServerTokenServices tokenServices) {
        super();
        this.tokenServices = tokenServices;
    }

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources.resourceId(RESOURCE_ID).tokenServices(tokenServices);
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .requestMatchers()
                .and()
                .authorizeRequests()
                .antMatchers("/api/users/**").permitAll()
                .anyRequest().authenticated();

        /*
                .antMatchers("/phonebook/api/profiles/csv/**").hasRole("ADMIN")
                .antMatchers("/phonebook/api/profiles/**").authenticated()
                .antMatchers("/phonebook/api/news/**").authenticated();
        */
    }


//    @Override
//    public void configure(HttpSecurity http) throws Exception {
//        http
//                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                .and()
//                .csrf().disable()
//                .authorizeRequests()
//                .antMatchers("/**").permitAll()
//        .anyRequest().permitAll();
//                .authorizeRequests()
//                .antMatchers(HttpMethod.GET, "/api/users/me").authenticated()
//                .antMatchers(HttpMethod.POST, "/api/projects").hasRole("MANAGER")
//                .antMatchers("/api/users/**").hasRole("MANAGER")
//                .antMatchers(HttpMethod.PUT, "/api/tasks/switchStatus/**").hasRole("DEVELOPER")
//                .antMatchers("/api/comments/**").hasRole("DEVELOPER")
//                .anyRequest().authenticated();
//    }
}
