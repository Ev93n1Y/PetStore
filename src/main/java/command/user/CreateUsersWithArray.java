package command.user;

import command.Command;
import entity.User;
import service.UserService;
import view.View;

import java.io.IOException;

public class CreateUsersWithArray implements Command {
    public static final String COMMAND_NAME = "create user by array";
    private final View view;
    private final UserService service;

    public CreateUsersWithArray(View view, UserService service) {
        this.view = view;
        this.service = service;
    }

    @Override
    public boolean canExecute(String input) {
        return input.equals(COMMAND_NAME);
    }

    @Override
    public void execute() {
        User[] users = new User[]{new User(),new User(),new User()};
        for (User user: users) {
            user.setId(0L);
            user.setUsername("string");
            user.setFirstName("string");
            user.setLastName("string");
            user.setEmail("string");
            user.setPassword("string");
            user.setPhone("string");
            user.setUserStatus(0);
        }
        try {
            view.write(service.createUsersWithArray(users).toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}