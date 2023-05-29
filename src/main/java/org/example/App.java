package org.example;

import org.example.configuration.AppConfig;
import org.example.entity.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class App {
    public static void main(String[] args) {

        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        Communication communication = applicationContext.getBean("communication", Communication.class);
        FinalResult finalResult = applicationContext.getBean("finalResult", FinalResult.class);

        User userOne = new User(3L, "James", "Brown", (byte) 50);
        User userTwo = new User(3L, "Thomas", "Shelby", (byte) 40);

        List<User> allUsers = communication.getAllUsers();

        finalResult.setResult(communication.addNewUser(userOne).getBody());
        finalResult.setResult(communication.refactorUser(userTwo).getBody());
        finalResult.setResult(communication.deleteUserById(3L).getBody());
        System.out.println(finalResult);
    }


}
