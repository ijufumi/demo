package jp.ijufumi.sample.spring.oidc.config;

import org.mitre.oauth2.model.RegisteredClient;
import org.mitre.openid.connect.client.OIDCAuthenticationFilter;
import org.mitre.openid.connect.client.service.ClientConfigurationService;
import org.mitre.openid.connect.client.service.RegisteredClientService;
import org.mitre.openid.connect.client.service.ServerConfigurationService;
import org.mitre.openid.connect.client.service.impl.DynamicRegistrationClientConfigurationService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;

import javax.servlet.Filter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    public WebSecurityConfig()
    {
        super(true);
    }

    protected void configure(HttpSecurity http) throws Exception {
        http.csrf()
            .and()
            .exceptionHandling()
            .and()
            .anonymous()
            .and()
            .addFilterBefore(authenticationFilter(), FilterSecurityInterceptor.class);
    }

    @Bean
    public Filter authenticationFilter()
    {
        OIDCAuthenticationFilter filter = new OIDCAuthenticationFilter();
        filter.setClientConfigurationService(clientConfigurationService());
        filter.setServerConfigurationService(serverConfigurationService());

        return filter;
    }

    @Bean
    public ClientConfigurationService clientConfigurationService()
    {
        return null;
    }

    @Bean
    public ServerConfigurationService serverConfigurationService()
    {
        return null;
    }
}
