package jm.task.core.jdbc;

import jm.task.core.jdbc.service.*;

public class Main {
    public static void main(String[] args) {
        UserService service = new UserServiceImpl();
        service.createUsersTable();
        
        service.saveUser("Ilon", "Musk", (byte) 48);
        service.saveUser("Volodimir", "Zelensky", (byte) 39);
        service.saveUser("John", "Coltrane", (byte) 25);
        service.saveUser("Alexey", "Navalny", (byte) 45);
        
        service.getAllUsers().stream().forEach(System.out::println);
        service.cleanUsersTable();
        service.dropUsersTable();
    }
}
