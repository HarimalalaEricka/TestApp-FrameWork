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

        @HandleUrl("/user/{id}")
        public String get(Integer id)
        {
            return "Méthode get() appelée, id = " + id;
        }

        @HandleUrl("/sprint6/{id}")  // localhost:8080/TestApp/sprint6/12?nom=Ericka&age=17&poids=55.23
        public String sprint6Test(String nom, int age, double poids) {
            return "nom = " + nom + ", age = " + age + ", poids = " + poids;
        }

        @HandleUrl("/etudiant/{id}") //http://localhost:8080/TestApp/etudiant/25?var2=hello
        public String get(@RequestParam("id") int var1, String var2, int id) {
            return "var1 = " + var1 + ", var2 = " + var2 + ", id = " + id;
        }

        @HandleUrl("/test1")
        public String test1(@RequestParam("id") int var1, String var2, int id) {
            return String.format("var1=%d, var2=%s, id=%d", var1, var2, id);
        }
        @HandleUrl("/test2")
        public String test2(@RequestParam("user_id") int userId, 
                        @RequestParam("name") String username) {
            return String.format("userId=%d, username=%s", userId, username);
        }
        @HandleUrl("/test3")
        public String test3(@RequestParam("required_param") String required,
                        String optional) {
            return String.format("required=%s, optional=%s", required, optional);
        }
        @HandleUrl("/test4")
        public String test4(@RequestParam("number") int num,
                        @RequestParam("price") double price,
                        @RequestParam("active") boolean isActive) {
            return String.format("num=%d, price=%.2f, active=%b", num, price, isActive);
        }
    }