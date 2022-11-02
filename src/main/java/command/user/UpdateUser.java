package command.user;

import command.Command;
import entity.ApiResponse;
import entity.User;
import service.UserService;
import view.View;

import java.io.IOException;

public class UpdateUser implements Command {
    public static final String COMMAND_NAME = "update user";
    private final View view;
    private final UserService service;

    public UpdateUser(View view, UserService service) {
        this.view = view;
        this.service = service;
    }

    @Override
    public boolean canExecute(String input) {
        return input.equals(COMMAND_NAME);
    }

    @Override
    public void execute() {
        ApiResponse response;
        User user;
        view.write("You must login first to update user");
        Command loginCommand = new UserLogin(view, service);
        loginCommand.execute();
        view.write("Enter user name you want to update: ");
        try {
            user = service.getUser(view.read());
            view.write("Enter new user name: ");
            user.setUsername(view.read());
            response = service.updateUser(user);
            if(response == null) {
                view.write("user not found");
            } else {
                view.write(response.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
