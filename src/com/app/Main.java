package com.app;
import com.app.controllers.UserController;
import java.lang.reflect.Method;
import com.framework.annotation.*;

public class Main {
    public static void main(String[] args) {
        Class<UserController> clazz = UserController.class;

        System.out.println("Liste des URLs dans la classe " + clazz.getSimpleName() + " :");
        System.out.println("-------------------------------------------------------");

        for (Method method : clazz.getDeclaredMethods()) {
            if (method.isAnnotationPresent(HandleUrl.class)) {
                HandleUrl annotation = method.getAnnotation(HandleUrl.class);
                System.out.println("Méthode : " + method.getName() + " --> URL : " + annotation.value());
            }
        }
    }
}