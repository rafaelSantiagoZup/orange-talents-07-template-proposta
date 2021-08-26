package com.zupedu.zupmicroservices.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;

@Configuration
@EnableWebSecurity
public class ResourceServiceConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests(authorize->
                authorize
                    .antMatchers(HttpMethod.GET,"/api/proposta/**").hasAuthority("SCOPE_proposta")
                        .antMatchers(HttpMethod.POST,"/api/proposta/**").hasAuthority("SCOPE_proposta")
                        .antMatchers(HttpMethod.GET,"/api/biometria/**").hasAuthority("SCOPE_proposta")
                        .antMatchers(HttpMethod.GET,"/api/biometria/**").hasAuthority("SCOPE_proposta")
                        .antMatchers(HttpMethod.POST,"/api/bloqueio/**").hasAuthority("SCOPE_proposta")
                        .antMatchers(HttpMethod.POST,"/api/viagem/**").hasAuthority("SCOPE_proposta")
                        .antMatchers(HttpMethod.GET,"/actuator/**").hasAuthority("SCOPE_proposta")
                        .anyRequest().authenticated()
        ).oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt);
    }
}
