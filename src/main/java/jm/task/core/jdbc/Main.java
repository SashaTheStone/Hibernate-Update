package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        UserService userService = new UserServiceImpl();

        userService.createUsersTable();
//        System.out.println("Таблица создана");

        userService.saveUser("Саша", "Иванов", (byte) 28);
        userService.saveUser("Петя", "Петров", (byte) 21);
        userService.saveUser("Ваня", "Ванов", (byte) 15);
        userService.saveUser("Катя", "Алешкина", (byte) 18);

        List<User> aaa = userService.getAllUsers();
        for (User i : aaa) {
            System.out.println(i);
        }

        userService.cleanUsersTable();
//        System.out.println("Пользователи удалены");

        userService.dropUsersTable();
//        System.out.println("Таблица удалена");

//        userService.removeUserById(1);
//        System.out.println("Кто от удален");

    }
}
