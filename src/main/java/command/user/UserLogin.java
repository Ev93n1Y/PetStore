package command.user;

import command.Command;
import service.UserService;
import view.View;

import java.io.IOException;

public class UserLogin implements Command {
    public static final String COMMAND_NAME = "user login";
    private final View view;
    private final UserService service;

    public UserLogin(View view, UserService service) {
        this.view = view;
        this.service = service;
    }

    @Override
    public boolean canExecute(String input) {
        return input.equals(COMMAND_NAME);
    }

    @Override
    public void execute() {
        view.write("Enter username: ");
        String username = view.read();
        view.write("Enter password: ");
        String password = view.read();
        try {
            view.write(service.userLogin(username, password).toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
