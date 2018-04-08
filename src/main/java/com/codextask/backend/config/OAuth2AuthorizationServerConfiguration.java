package com.codextask.backend.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import java.util.Collections;

@Configuration
@EnableAuthorizationServer
@PropertySource("classpath:security.properties")
public class OAuth2AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {
    @Value("${security.jwt.client-id}")
    private String CLIENT_ID;

    @Value("${security.jwt.client-secret}")
    private String CLIENT_SECRET;

    @Value("${security.jwt.grant-type.password}")
    private String GRANT_TYPE_PASSWORD;

    @Value("${security.jwt.grant-type.refresh}")
    private String GRANT_TYPE_REFRESH;

    @Value("${security.authority}")
    private String AUTHORITY_TRUSTED;

    @Value("${security.jwt.scope-read}")
    private String SCOPE_READ;

    @Value("${security.jwt.scope-write}")
    private String SCOPE_WRITE;

    @Value("${security.jwt.resource-ids}")
    private String RESOURCE_IDs;

    private final AuthenticationManager authenticationManager;

    private final TokenStore tokenStore;

    private final JwtAccessTokenConverter accessTokenConverter;

    private final DefaultTokenServices tokenServices;

    @Autowired
    public OAuth2AuthorizationServerConfiguration(@Qualifier("jwtAccessTokenConverter") JwtAccessTokenConverter accessTokenConverter,
                                                  DefaultTokenServices tokenServices,
                                                  TokenStore tokenStore,
                                                  AuthenticationManager authenticationManager) {
        this.accessTokenConverter = accessTokenConverter;
        this.tokenServices = tokenServices;
        this.tokenStore = tokenStore;
        this.authenticationManager = authenticationManager;
    }


    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients
                .inMemory()
                .withClient(CLIENT_ID)
                .secret(CLIENT_SECRET)
                .authorizedGrantTypes(GRANT_TYPE_PASSWORD,GRANT_TYPE_REFRESH)
                .authorities(AUTHORITY_TRUSTED)
                .scopes(SCOPE_READ,SCOPE_WRITE)
                .resourceIds(RESOURCE_IDs);
    }


    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        TokenEnhancerChain enhancerChain = new TokenEnhancerChain();
        enhancerChain.setTokenEnhancers(Collections.singletonList(accessTokenConverter));
        endpoints
                .authenticationManager(authenticationManager)
                .tokenServices(tokenServices)
                .tokenStore(tokenStore)
                .accessTokenConverter(accessTokenConverter)
                .tokenEnhancer(enhancerChain);
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security
                .tokenKeyAccess("permitAll()")
                .checkTokenAccess("isAuthenticated()");
    }
}