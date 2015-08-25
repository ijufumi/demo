package jp.ijufumi.sample.spring.oidc.config;

import java.net.MalformedURLException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import org.mitre.jose.keystore.JWKSetKeyStore;
import org.mitre.jwt.signer.service.JWTSigningAndValidationService;
import org.mitre.jwt.signer.service.impl.DefaultJWTSigningAndValidationService;
import org.mitre.oauth2.model.RegisteredClient;
import org.mitre.openid.connect.client.OIDCAuthenticationFilter;
import org.mitre.openid.connect.client.OIDCAuthenticationProvider;
import org.mitre.openid.connect.client.service.ClientConfigurationService;
import org.mitre.openid.connect.client.service.IssuerService;
import org.mitre.openid.connect.client.service.RegisteredClientService;
import org.mitre.openid.connect.client.service.ServerConfigurationService;
import org.mitre.openid.connect.client.service.impl.DynamicRegistrationClientConfigurationService;
import org.mitre.openid.connect.client.service.impl.DynamicServerConfigurationService;
import org.mitre.openid.connect.client.service.impl.StaticSingleIssuerService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.UrlResource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.authentication.AuthenticationManagerFactoryBean;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;

import javax.servlet.Filter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private static final String ISSUER = "https://accounts.google.com";

    public WebSecurityConfig()
    {
        super(true);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf()
            .and()
            .exceptionHandling()
            .and()
            .anonymous()
            .and()
            .addFilterBefore(authenticationFilter(), FilterSecurityInterceptor.class);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public AuthenticationProvider authenticationProvider()
    {
        OIDCAuthenticationProvider authenticationProvider = new OIDCAuthenticationProvider();

        return authenticationProvider;
    }

    @Bean
    public Filter authenticationFilter() throws Exception {

        OIDCAuthenticationFilter filter = new OIDCAuthenticationFilter();
        filter.setClientConfigurationService(clientConfigurationService());
        filter.setServerConfigurationService(serverConfigurationService());
        filter.setIssuerService(issuerService());
        filter.setFilterProcessesUrl("/login/google");
        filter.setAuthenticationManager(authenticationManager());

        return filter;
    }

    @Bean
    public IssuerService issuerService()
    {
        StaticSingleIssuerService issuerService = new StaticSingleIssuerService();
        issuerService.setIssuer(ISSUER);

        return issuerService;
    }

    @Bean
    public ClientConfigurationService clientConfigurationService()
    {
        DynamicRegistrationClientConfigurationService configurationService = new DynamicRegistrationClientConfigurationService();

        return configurationService;
    }

    @Bean
    public ServerConfigurationService serverConfigurationService()
    {
        DynamicServerConfigurationService configurationService = new DynamicServerConfigurationService();

        return configurationService;
    }

    @Bean
    public JWTSigningAndValidationService jwtSigningAndValidationService() throws InvalidKeySpecException, NoSuchAlgorithmException, MalformedURLException {
        DefaultJWTSigningAndValidationService jwtSigningAndValidationService = new DefaultJWTSigningAndValidationService(jwkSetKeyStore());

        return jwtSigningAndValidationService;
    }

    @Bean
    public JWKSetKeyStore jwkSetKeyStore() throws MalformedURLException {
        JWKSetKeyStore keyStore = new JWKSetKeyStore();
        keyStore.setLocation(new UrlResource(serverConfigurationService().getServerConfiguration(ISSUER).getJwksUri()));

        return keyStore;
    }
}
