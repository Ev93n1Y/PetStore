package command.order;

import command.Command;
import service.PetService;
import view.View;

public class FindOrderById implements Command {
    public static final String COMMAND_NAME = "find order";
    private final View view;
    private final PetService service;

    public FindOrderById(View view, PetService service) {
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