package br.com.zupacademy.gustavo.proposta.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;

@Configuration
public class ConfiguracaoSecurity extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests(authorizeRequests ->
                authorizeRequests
                        .antMatchers(HttpMethod.GET, "/proposta/**").hasAuthority("SCOPE_proposta:read")
                        .antMatchers(HttpMethod.POST, "/proposta/**").hasAuthority("SCOPE_proposta:write")
                        .antMatchers(HttpMethod.GET, "/actuator/**").permitAll()
                        .anyRequest().authenticated()
        ).oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt);
    }
}
