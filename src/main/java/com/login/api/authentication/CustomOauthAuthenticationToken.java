package com.login.api.authentication;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;

public class CustomOauthAuthenticationToken implements Authentication {

    private final Collection<GrantedAuthority> authorities;
    private Object details;
    private boolean authenticated = false;
    private Object principal;
    private final String token;

    public String getToken() {
        return token;
    }

    public CustomOauthAuthenticationToken(Object principal, Collection<GrantedAuthority> authorities, String token) {
        this.principal = principal;
        this.token = token;
        if (authorities == null) {
            this.authorities = AuthorityUtils.NO_AUTHORITIES;
        } else {
            Iterator var2 = authorities.iterator();

            while(var2.hasNext()) {
                GrantedAuthority a = (GrantedAuthority)var2.next();
                Assert.notNull(a, "Authorities collection cannot contain any null elements");
            }

            this.authorities = Collections.unmodifiableList(new ArrayList(authorities));
        }
    }

    @Override
    public Collection<GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getDetails() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return principal;
    }

    @Override
    public boolean isAuthenticated() {
        return this.authenticated;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
        this.authenticated = isAuthenticated;
    }

    @Override
    public String getName() {
        return null;
    }

    public void setPrincipal(Object principal) throws IllegalArgumentException {
        this.principal = principal;
    }
}