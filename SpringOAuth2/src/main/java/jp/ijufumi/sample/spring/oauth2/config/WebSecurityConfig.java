package jp.ijufumi.sample.spring.oauth2.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.client.DefaultOAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestOperations;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.filter.OAuth2ClientAuthenticationProcessingFilter;
import org.springframework.security.oauth2.client.filter.OAuth2ClientContextFilter;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.client.token.AccessTokenProvider;
import org.springframework.security.oauth2.client.token.AccessTokenRequest;
import org.springframework.security.oauth2.client.token.grant.code.AuthorizationCodeAccessTokenProvider;
import org.springframework.security.oauth2.client.token.grant.code.AuthorizationCodeResourceDetails;
import org.springframework.security.oauth2.common.AuthenticationScheme;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;
import org.springframework.security.web.access.ExceptionTranslationFilter;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableWebSecurity
@EnableOAuth2Client
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{

    @Resource
    @Qualifier("accessTokenRequest")
    private AccessTokenRequest accessTokenRequest;

    public WebSecurityConfig()
    {
        super(true);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf()
            .and()
            .anonymous()
            .and()
            .exceptionHandling()
            .and()
            .addFilterAfter(authenticationProcessingFilter(), FilterSecurityInterceptor.class)
            .addFilterBefore(new OAuth2ClientContextFilter(), ExceptionTranslationFilter.class);
    }

    @Bean
    public AbstractAuthenticationProcessingFilter authenticationProcessingFilter()
    {
        OAuth2ClientAuthenticationProcessingFilter filter = new OAuth2ClientAuthenticationProcessingFilter("/oauth2");
        filter.setRestTemplate(restTemplate());

        //RemoteTokenServices tokenServices = new RemoteTokenServices();
        DefaultTokenServices tokenServices = new DefaultTokenServices();
        tokenServices.setTokenStore(new InMemoryTokenStore());
        //tokenServices.setClientId("262385307653-o2f7kip090i6a4n8678n2po3j04d8atb.apps.googleusercontent.com");
        //tokenServices.setClientSecret("C2NnWo6XSqYRhc9s3Jja93Bx");
        //tokenServices.setRestTemplate(restTemplate());
        //tokenServices.setCheckTokenEndpointUrl("https://www.googleapis.com/oauth2/v3/token");

        filter.setTokenServices(tokenServices);

        return filter;
    }

    @Bean
    @Scope(value = "session", proxyMode = ScopedProxyMode.INTERFACES)
    public OAuth2RestOperations restTemplate()
    {
        OAuth2RestTemplate restTemplate = new OAuth2RestTemplate(resource(), new DefaultOAuth2ClientContext(accessTokenRequest));
        restTemplate.setAccessTokenProvider(accessTokenProvider());
        return restTemplate;
    }

    @Bean
    public OAuth2ProtectedResourceDetails resource() {

        AuthorizationCodeResourceDetails resource = new AuthorizationCodeResourceDetails();

        List scopes = new ArrayList<String>();
        scopes.add("openid");
        scopes.add("email");
        scopes.add("profile");
        resource.setId("google-api");
        resource.setClientId("262385307653-o2f7kip090i6a4n8678n2po3j04d8atb.apps.googleusercontent.com");
        resource.setClientSecret("C2NnWo6XSqYRhc9s3Jja93Bx");
        resource.setScope(scopes);
        resource.setAccessTokenUri("https://www.googleapis.com/oauth2/v3/token");
        resource.setClientAuthenticationScheme(AuthenticationScheme.form);
        resource.setAuthenticationScheme(AuthenticationScheme.form);
        resource.setUserAuthorizationUri("https://accounts.google.com/o/oauth2/auth");
        resource.setPreEstablishedRedirectUri("http://localhost:8080/oauth2");
        resource.setGrantType("authorization_code");
        resource.setUseCurrentUri(false);

        return resource;
    }

    @Bean
    public AccessTokenProvider accessTokenProvider() {
        AuthorizationCodeAccessTokenProvider accessTokenProvider = new AuthorizationCodeAccessTokenProvider();
        List<HttpMessageConverter<?>> messageConverters = new ArrayList<>();
        messageConverters.add(new MappingJackson2HttpMessageConverter());
        accessTokenProvider.setMessageConverters(messageConverters);

        return accessTokenProvider;
    }
}
