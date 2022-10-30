package command.user;

import command.Command;
import service.PetService;
import view.View;

public class CreateUsersWithList implements Command {
    public static final String COMMAND_NAME = "create user by list";
    private final View view;
    private final PetService service;

    public CreateUsersWithList(View view, PetService service) {
        this.view = view;
        this.service = service;
    }

    @Override
    public boolean canExecute(String input) {
        return input.equals(COMMAND_NAME);
    }

    @Override
    public void execute() {

    }
}