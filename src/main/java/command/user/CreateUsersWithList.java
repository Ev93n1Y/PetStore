package command.user;

import command.Command;
import entity.User;
import service.UserService;
import view.View;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CreateUsersWithList implements Command {
    public static final String COMMAND_NAME = "create user by list";
    private final View view;
    private final UserService service;

    public CreateUsersWithList(View view, UserService service) {
        this.view = view;
        this.service = service;
    }

    @Override
    public boolean canExecute(String input) {
        return input.equals(COMMAND_NAME);
    }

    @Override
    public void execute() {
        List<User> userList = new ArrayList<>();
        int userCount;
        while (true) {
            try {
                view.write("Enter number of users you want to add: ");
                userCount = Integer.parseInt(view.read());
                break;
            } catch (IllegalArgumentException e) {
                view.write("Use digits");
            }
        }
        for (int i = 0; i < userCount; i++) {
            userList.add(new User());
            view.write(String.format("Enter user number %d: ", i + 1));
            view.write("Enter username: ");
            userList.get(i).setUsername(view.read());
            view.write("Enter firstname: ");
            userList.get(i).setFirstName(view.read());
            view.write("Enter lastname: ");
            userList.get(i).setLastName(view.read());
            view.write("Enter email: ");
            userList.get(i).setEmail(view.read());
            view.write("Enter password: ");
            userList.get(i).setPassword(view.read());
            view.write("Enter phone: ");
            userList.get(i).setPhone(view.read());
        }
        try {
            view.write(service.createUsersWithList(userList).toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}