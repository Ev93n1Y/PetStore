package command.user;

import command.Command;
import service.PetService;
import view.View;

public class GetUser implements Command {
    public static final String COMMAND_NAME = "get user";
    private final View view;
    private final PetService service;

    public GetUser(View view, PetService service) {
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
