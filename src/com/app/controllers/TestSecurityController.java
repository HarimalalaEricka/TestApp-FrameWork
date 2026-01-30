package com.app.controllers;

import com.framework.annotation.*;
import com.framework.model.*;
import com.app.auth.AppUserPrincipal;
import java.util.*;

@Controller
public class TestSecurityController {
    
    // ============================================
    // 1. ACCÈS PUBLIC (ANONYME)
    // ============================================
    
    @HandleUrl("/public/welcome")
    public ModelView publicPage() {
        ModelView mv = new ModelView();
        mv.setView("/views/test/public.jsp");
        mv.addAttribute("message", "Bienvenue sur la page publique !");
        return mv;
    }
    
    // ============================================
    // 2. AUTHENTIFICATION SIMPLE
    // ============================================
    
    @HandleUrl("/auth/profile")
    @Authenticated
    public ModelView userProfile(@AuthPrincipal AppUserPrincipal user) {
        ModelView mv = new ModelView();
        mv.setView("/views/test/profile.jsp");
        mv.addAttribute("user", user);
        mv.addAttribute("message", "Bienvenue " + user.getUsername());
        return mv;
    }
    
    @HandleUrl("/auth/only-me")
    @Authenticated
    public ModelView privatePage(@AuthPrincipal AppUserPrincipal user) {
        ModelView mv = new ModelView();
        mv.setView("/views/test/private.jsp");
        mv.addAttribute("message", "Cette page est strictement privée pour " + user.getUsername());
        return mv;
    }
    
    // ============================================
    // 3. ACCÈS PAR RÔLES
    // ============================================
    
    @HandleUrl("/admin/dashboard")
    @Authenticated
    @AllowedRoles({"ADMIN"})
    public ModelView adminDashboard(@AuthPrincipal AppUserPrincipal user) {
        ModelView mv = new ModelView();
        mv.setView("/views/test/admin/dashboard.jsp");
        mv.addAttribute("user", user);
        mv.addAttribute("message", "Tableau de bord administrateur");
        return mv;
    }
    
    @HandleUrl("/admin/users")
    @AllowedRoles({"ADMIN"})
    public ModelView manageUsers() {
        ModelView mv = new ModelView();
        mv.setView("/views/test/admin/users.jsp");
        return mv;
    }
    
    @HandleUrl("/content/manager")
    @AllowedRoles(value = {"ADMIN", "MODERATOR"}, strategy = AllowedRoles.Strategy.ANY)
    public ModelView contentManagement() {
        ModelView mv = new ModelView();
        mv.setView("/views/test/content/manager.jsp");
        mv.addAttribute("message", "Gestion de contenu - Réservé aux admins et modérateurs");
        return mv;
    }
    
    // ============================================
    // 4. ACCÈS PAR AUTORISATIONS (PERMISSIONS)
    // ============================================
    
    @HandleUrl("/posts/delete/{id}")
    @AllowedAuthorities({"DELETE_POSTS"})
    public ModelView deletePost(@PathParam("id") Long postId, @AuthPrincipal AppUserPrincipal user) {
        ModelView mv = new ModelView();
        mv.setView("/views/test/posts/deleted.jsp");
        mv.addAttribute("postId", postId);
        mv.addAttribute("deletedBy", user.getUsername());
        mv.addAttribute("message", "Post #" + postId + " supprimé avec succès");
        return mv;
    }
    
    @HandleUrl("/reports/view")
    @AllowedAuthorities(value = {"VIEW_REPORTS", "MANAGE_REPORTS"}, strategy = AllowedAuthorities.Strategy.ANY)
    public ModelView viewReports() {
        ModelView mv = new ModelView();
        mv.setView("/views/test/reports/list.jsp");
        return mv;
    }
    
    @HandleUrl("/users/manage")
    @AllowedAuthorities({"MANAGE_USERS"})
    public ModelView manageAllUsers() {
        ModelView mv = new ModelView();
        mv.setView("/views/test/admin/manage-users.jsp");
        return mv;
    }
    
    // ============================================
    // 5. COMBINAISONS COMPLEXES
    // ============================================
    
    @HandleUrl("/admin/super-secure")
    @Authenticated
    @AllowedRoles({"ADMIN"})
    @AllowedAuthorities({"MANAGE_USERS", "VIEW_REPORTS"})
    public ModelView superSecurePage() {
        ModelView mv = new ModelView();
        mv.setView("/views/test/admin/super-secure.jsp");
        return mv;
    }
    
    // ============================================
    // 6. TEST DE PARAMÈTRES DANS LES URL
    // ============================================
    
    @HandleUrl("/user/{userId}/profile")
    @Authenticated
    public ModelView userProfileById(
            @PathParam("userId") Long userId,
            @AuthPrincipal AppUserPrincipal currentUser) {
        
        ModelView mv = new ModelView();
        mv.setView("/views/test/user/profile.jsp");
        
        // Vérifier si l'utilisateur peut voir ce profil
        if (userId.equals(currentUser.getUserId()) || 
            currentUser.hasRole("ADMIN")) {
            mv.addAttribute("canView", true);
            mv.addAttribute("profileUserId", userId);
            mv.addAttribute("message", "Profil de l'utilisateur #" + userId);
        } else {
            mv.addAttribute("canView", false);
            mv.addAttribute("message", "Vous n'avez pas accès à ce profil");
        }
        
        return mv;
    }
    
    // ============================================
    // 7. JSON RESPONSE AVEC AUTHENTIFICATION
    // ============================================
    
    @HandleUrl("/api/user/info")
    @Authenticated
    @JsonResponse
    public Object getUserInfo(@AuthPrincipal AppUserPrincipal user) {
        // Retourne un objet qui sera automatiquement converti en JSON
        return new Object() {
            public Long id = (Long) user.getUserId();
            public String username = user.getUsername();
            public String email = user.getEmail();
            public List<String> roles = user.getRoles();
            public boolean authenticated = user.isAuthenticated();
            public String timestamp = new java.util.Date().toString();
        };
    }
    
    @HandleUrl("/api/admin/stats")
    @AllowedRoles({"ADMIN"})
    @JsonResponse
    public Object getAdminStats() {
        return new Object() {
            public int totalUsers = 150;
            public int activeUsers = 87;
            public int totalPosts = 1250;
            public Map<String, Integer> visits = new HashMap<String, Integer>() {{
                put("today", 450);
                put("yesterday", 520);
                put("thisWeek", 3100);
            }};
        };
    }
}