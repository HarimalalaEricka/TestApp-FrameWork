    package com.app.controllers;

    import com.framework.annotation.*;
    import com.framework.model.*;
    import java.util.Map;

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

        @HandleUrl("/testform")
        public ModelView testform()
        {
            ModelView view = new ModelView();
            view.setView("testform.jsp");
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

        // GET: Afficher le formulaire
        @HandleGet("/form")
        public ModelView form() {
            ModelView model = new ModelView();
            model.setView("form.jsp");
            model.addAttribute("titre", "Formulaire d'inscription");
            return model;
        }
        
        // POST: Traiter le formulaire
        @HandlePost("/form")
        public ModelView saveUser(String id, String nom) {
            ModelView model = new ModelView();
            model.setView("result.jsp");
            model.addAttribute("message", "Utilisateur sauvegardé avec succès!");
            model.addAttribute("nom", nom);
            model.addAttribute("id", id);
            return model;
        }
        
        // Exemple avec paramètre d'URL
        @HandleGet("/user/{id}")
        public ModelView getUser(int id) {
            ModelView model = new ModelView();
            model.setView("../user-detail.jsp");
            model.addAttribute("id", id);
            return model;
        }
        
        // Exemple avec @RequestParam
        @HandleGet("/search")
        public ModelView search(@RequestParam("q") String query, 
                            @RequestParam("page") int pageNumber) {
            ModelView model = new ModelView();
            model.setView("search-results.jsp");
            model.addAttribute("query", query);
            model.addAttribute("page", pageNumber);
            return model;
        }

        // Méthode GET pour afficher le formulaire
        @HandleGet("/test-form")
        public ModelView showForm() {
            ModelView mv = new ModelView();
            mv.setView("test-form.jsp");
            mv.addAttribute("titre", "Test Sprint 8 - Map<String, Object>");
            return mv;
        }
        
        // Méthode POST pour recevoir les données
        @HandlePost("/test-map")
        public ModelView testMap(Map<String, Object> data, String nom, int age, String ville) {
            ModelView mv = new ModelView();
            mv.setView("test-result.jsp");
            
            // Ajouter toutes les données à la vue
            mv.addAttribute("mapData", data);
            mv.addAttribute("nom", nom);
            mv.addAttribute("age", age);
            mv.addAttribute("ville", ville);
            
            // Afficher dans les logs aussi
            System.out.println("=== SPRINT 8 - RÉSULTAT ===");
            System.out.println("Map complète: " + data);
            System.out.println("Nom extrait: " + nom);
            System.out.println("Âge extrait: " + age);
            System.out.println("Ville extraite: " + ville);
            
            return mv;
        }
    }