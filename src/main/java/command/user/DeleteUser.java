package command.user;

import command.Command;
import service.UserService;
import view.View;

import java.io.IOException;

public class DeleteUser implements Command {
    public static final String COMMAND_NAME = "del user";
    private final View view;
    private final UserService service;

    public DeleteUser(View view, UserService service) {
        this.view = view;
        this.service = service;
    }

    @Override
    public boolean canExecute(String input) {
        return input.equals(COMMAND_NAME);
    }

    @Override
    public void execute() {
        view.write("You must login first to delete user");
        Command loginCommand = new UserLogin(view, service);
        loginCommand.execute();
        view.write("Enter user name you want to delete: ");
        try {
            view.write(service.deleteUser(view.read()).toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}