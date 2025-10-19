package com.app.controllers;


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
}