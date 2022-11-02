package command.user;

import command.Command;
import service.UserService;
import view.View;

import java.io.IOException;

public class UserLogout implements Command {
    public static final String COMMAND_NAME = "user logout";
    private final View view;
    private final UserService service;

    public UserLogout(View view, UserService service) {
        this.view = view;
        this.service = service;
    }

    @Override
    public boolean canExecute(String input) {
        return input.equals(COMMAND_NAME);
    }

    @Override
    public void execute() {
        try {
            view.write(service.userLogout().toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}