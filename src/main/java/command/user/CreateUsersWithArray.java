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
        User[] users;
        while (true) {
            try {
                view.write("Enter number of users you want to add: ");
                int userCount = Integer.parseInt(view.read());
                users = new User[userCount];
                break;
            } catch (IllegalArgumentException e) {
                view.write("Use digits");
            }
        }
        for (int i = 0; i < users.length; i++) {
            view.write(String.format("Enter user number %d: ", i + 1));
            users[i] = new User();
            view.write("Enter username: ");
            users[i].setUsername(view.read());
            view.write("Enter firstname: ");
            users[i].setFirstName(view.read());
            view.write("Enter lastname: ");
            users[i].setLastName(view.read());
            view.write("Enter email: ");
            users[i].setEmail(view.read());
            view.write("Enter password: ");
            users[i].setPassword(view.read());
            view.write("Enter phone: ");
            users[i].setPhone(view.read());
        }
        try {
            view.write(service.createUsersWithArray(users).toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}