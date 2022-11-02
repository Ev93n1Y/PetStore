package command.user;

import command.Command;
import entity.ApiResponse;
import entity.User;
import service.UserService;
import view.View;

import java.io.IOException;

public class CreateUser implements Command {
    public static final String COMMAND_NAME = "create user";
    private final View view;
    private final UserService service;

    public CreateUser(View view, UserService service) {
        this.view = view;
        this.service = service;
    }

    @Override
    public boolean canExecute(String input) {
        return input.equals(COMMAND_NAME);
    }

    @Override
    public void execute() {
        User user = new User();
        ApiResponse response;
        view.write("Enter username: ");
        user.setUsername(view.read());
        view.write("Enter firstname: ");
        user.setFirstName(view.read());
        view.write("Enter lastname: ");
        user.setLastName(view.read());
        view.write("Enter email: ");
        user.setEmail(view.read());
        view.write("Enter password: ");
        user.setPassword(view.read());
        view.write("Enter phone: ");
        user.setPhone(view.read());
        try {
            response = service.createUser(user);
            view.write(response.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}