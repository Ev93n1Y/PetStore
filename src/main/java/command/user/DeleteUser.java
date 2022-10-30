package command.user;

import command.Command;
import service.PetService;
import view.View;

public class DeleteUser implements Command {
    public static final String COMMAND_NAME = "del user";
    private final View view;
    private final PetService service;

    public DeleteUser(View view, PetService service) {
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