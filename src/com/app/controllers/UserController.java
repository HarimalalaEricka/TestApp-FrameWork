package com.app.controllers;

import com.framework.annotation.*;
import com.framework.model.*;

@Controller
public class UserController {

    @HandleUrl("/nom")
    public void afficherAccueil() {
        System.out.println("RAKOTO");
    }

    @HandleUrl("/prenom")
    public void afficherProduits() {
        System.out.println("Jean");
    }

    @HandleUrl("/email")
    public void afficherContact() {
        System.out.println("jean@gmail.com");
    }

    @HandleUrl("/hello")
    public String sayHello()
    {
        return "Hello toi!!";
    }

    @HandleUrl("/test")
    public ModelView test()
    {
        ModelView view = new ModelView();
        view.setView("test.jsp");
        view.addAttribute("nom", "RAKOTO");
        view.addAttribute("prenom", "JEAN");
        view.addAttribute("email", "jeanrakoto@gmail.com");
        return view;
    }
}