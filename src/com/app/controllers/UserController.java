package com.app.controllers;

import com.framework.annotation.*;

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
}