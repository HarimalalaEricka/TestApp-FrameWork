package com.app.controllers;

import com.framework.annotation.*;
import com.framework.model.*;
import com.app.auth.AppUserPrincipal;
import javax.servlet.http.HttpSession;

@Controller
public class AuthController {
    
    // Service mock pour l'exemple (dans la vraie vie, vérifier en base de données)
    private boolean validateCredentials(String username, String password) {
        // Exemple simple
        return "admin".equals(username) && "admin123".equals(password) ||
               "user".equals(username) && "user123".equals(password);
    }
    
    @HandleGet("/auth/login")
    public ModelView showLoginForm() {
        ModelView mv = new ModelView();
        mv.setView("/views/auth/login.jsp");
        return mv;
    }
    
    @HandlePost("/auth/login")
    public ModelView processLogin(
            @RequestParam("username") String username,
            @RequestParam("password") String password,
            HttpSession session) {
        
        System.out.println("🔐 Tentative de login: " + username);
        
        if (validateCredentials(username, password)) {
            // Créer le UserPrincipal selon le rôle
            AppUserPrincipal principal = createUserPrincipal(username);
            
            // Stocker en session
            session.setAttribute("APP_USER_PRINCIPAL", principal);
            System.out.println("✅ Utilisateur connecté: " + principal);
            ModelView mv = new ModelView();
            mv.setView("/auth/dashboard");
            return mv;
        }
        
        // Échec de l'authentification
        ModelView mv = new ModelView();
        mv.setView("/views/auth/login.jsp");
        mv.addAttribute("error", "Identifiants incorrects");
        mv.addAttribute("username", username);
        return mv;
    }
    
    private AppUserPrincipal createUserPrincipal(String username) {
        if ("admin".equals(username)) {
            return new AppUserPrincipal(1L, "admin", "admin@example.com")
                    .withRoles("ADMIN", "USER")
                    .withAuthorities("MANAGE_USERS", "DELETE_POSTS", "VIEW_REPORTS");
        } else {
            return new AppUserPrincipal(2L, "user", "user@example.com")
                    .withRole("USER")
                    .withAuthorities("CREATE_POSTS", "VIEW_PROFILE");
        }
    }
    
    @HandleUrl("/auth/logout")
    public ModelView logout(HttpSession session) {
        if (session != null) {
            session.invalidate();
        }
        ModelView mv = new ModelView();
        mv.setView("/auth/login");
        return mv;
    }
    
    @HandleUrl("/auth/dashboard")
    @Authenticated
    public ModelView dashboard(@AuthPrincipal AppUserPrincipal user) {
        ModelView mv = new ModelView();
        mv.setView("/views/auth/dashboard.jsp");
        mv.addAttribute("user", user);
        return mv;
    }
}