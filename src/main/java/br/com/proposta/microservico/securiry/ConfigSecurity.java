package br.com.proposta.microservico.securiry;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;

@EnableWebSecurity
@Configuration
public class ConfigSecurity  extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests(authorizeRequests -> authorizeRequests
                .mvcMatchers("**/actuator/**").permitAll()
                .mvcMatchers(HttpMethod.POST, "/avisoViagem/**").hasAuthority("SCOPE_proposta")
                .mvcMatchers(HttpMethod.POST, "/biometria/**").hasAuthority("SCOPE_proposta")
                .mvcMatchers(HttpMethod.POST, "/proposta/**").hasAuthority("SCOPE_proposta")
                .mvcMatchers(HttpMethod.GET, "/proposta/**").hasAuthority("SCOPE_proposta")
                .mvcMatchers(HttpMethod.GET, "/usuarioLogado/**").hasAuthority("SCOPE_proposta")
        ).oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt);





    }
}
