package jp.ijufumi.sample.spring.oidc.config;

import java.io.IOException;
import java.net.MalformedURLException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;
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
import org.mitre.openid.connect.client.service.impl.*;
import org.mitre.openid.connect.config.ServerConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

import javax.annotation.PostConstruct;
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
        filter.setAuthRequestUrlBuilder(new PlainAuthRequestUrlBuilder());
        // TODO SuccessHandlerを設定する。

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
        RegisteredClient client = new RegisteredClient();
        client.setClientId("262385307653-o2f7kip090i6a4n8678n2po3j04d8atb.apps.googleusercontent.com");
        client.setClientSecret("C2NnWo6XSqYRhc9s3Jja93Bx");
        client.setScope(new HashSet<>(Arrays.asList("openid", "email", "profile")));
        client.setRedirectUris(new HashSet<>(Arrays.asList("http://localhost:8080/login/google")));
        client.setGrantTypes(new HashSet<>(Arrays.asList("code")));

        StaticClientConfigurationService configurationService = new StaticClientConfigurationService();
        configurationService.setClients(Collections.singletonMap(ISSUER, client));

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
