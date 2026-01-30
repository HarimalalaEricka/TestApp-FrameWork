package com.app.auth;

import com.framework.auth.UserPrincipal;
import java.util.ArrayList;
import java.util.List;

public class AppUserPrincipal implements UserPrincipal {
    
    // Données utilisateur
    private Long userId;
    private String username;
    private String email;
    private List<String> roles = new ArrayList<>();
    private List<String> authorities = new ArrayList<>();
    private boolean authenticated = false;
    
    // Constructeurs
    public AppUserPrincipal() {
        // Pour les utilisateurs anonymes
    }
    
    public AppUserPrincipal(Long userId, String username, String email) {
        this.userId = userId;
        this.username = username;
        this.email = email;
        this.authenticated = true;
    }
    
    // Méthodes de l'interface
    @Override
    public boolean isAuthenticated() {
        return authenticated;
    }
    
    @Override
    public boolean hasRole(String role) {
        // Supporte les formats: "ADMIN", "ROLE_ADMIN", "USER"
        String normalizedRole = role.startsWith("ROLE_") ? role : "ROLE_" + role;
        return roles.contains(normalizedRole) || roles.contains(role);
    }
    
    @Override
    public boolean hasAuthority(String authority) {
        return authorities.contains(authority);
    }
    
    @Override
    public Object getUserId() {
        return userId;
    }
    
    @Override
    public String getUsername() {
        return username;
    }
    
    @Override
    public List<String> getRoles() {
        return new ArrayList<>(roles);
    }
    
    @Override
    public List<String> getAuthorities() {
        return new ArrayList<>(authorities);
    }
    
    // Méthodes utilitaires pour le développeur
    public AppUserPrincipal withRole(String role) {
        String normalizedRole = role.startsWith("ROLE_") ? role : "ROLE_" + role;
        this.roles.add(normalizedRole);
        return this;
    }
    
    public AppUserPrincipal withRoles(String... roles) {
        for (String role : roles) {
            withRole(role);
        }
        return this;
    }
    
    public AppUserPrincipal withAuthority(String authority) {
        this.authorities.add(authority);
        return this;
    }
    
    public AppUserPrincipal withAuthorities(String... authorities) {
        for (String auth : authorities) {
            withAuthority(auth);
        }
        return this;
    }
    
    // Getters/Setters
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    @Override
    public String toString() {
        return "AppUserPrincipal{" +
               "userId=" + userId +
               ", username='" + username + '\'' +
               ", roles=" + roles +
               ", authenticated=" + authenticated +
               '}';
    }
}