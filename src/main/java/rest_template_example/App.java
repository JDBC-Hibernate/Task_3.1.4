package rest_template_example;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import rest_template_example.config.AppConfig;
import rest_template_example.controllers.UserController;
import rest_template_example.entity.User;

public class App {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext appContext =
                new AnnotationConfigApplicationContext(AppConfig.class);

        UserController userController = appContext.getBean(
                "userController",
                UserController.class
        );

        User user = new User();
        user.setId(3L);
        user.setName("James");
        user.setLastName("Brown");
        user.setAge((byte) 44);

        userController.getAllUsers();

        String resultAdd = userController.addUser(user);
        System.out.println(resultAdd);

        user.setName("Thomas");
        user.setLastName("Shelby");
        user.setAge((byte) 55);

        String resultUpd = userController.updateUser(user);
        System.out.println(resultUpd);

        String resultDel = userController.deleteUser(3L);
        System.out.println(resultDel);

        System.out.println((resultAdd + resultDel + resultUpd).length());
    }
}
