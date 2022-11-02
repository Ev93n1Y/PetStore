package command.user;

import command.Command;
import entity.User;
import service.UserService;
import view.View;

import java.io.IOException;

public class GetUser implements Command {
    public static final String COMMAND_NAME = "get user";
    private final View view;
    private final UserService service;

    public GetUser(View view, UserService service) {
        this.view = view;
        this.service = service;
    }

    @Override
    public boolean canExecute(String input) {
        return input.equals(COMMAND_NAME);
    }

    @Override
    public void execute() {
        User user;
        view.write("Enter user name you want to get: ");
        try {
            user = service.getUser(view.read());
            if(user == null) {
                view.write("user not found");
            } else {
                view.write(user.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
