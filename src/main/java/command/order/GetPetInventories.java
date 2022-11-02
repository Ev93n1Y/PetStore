package command.order;

import command.Command;
import service.OrderService;
import view.View;

import java.io.IOException;
import java.util.Map;

public class GetPetInventories implements Command {
    public static final String COMMAND_NAME = "get pet inv";
    private final View view;
    private final OrderService service;

    public GetPetInventories(View view, OrderService service) {
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
            Map<String, Integer> map = service.getPetInventories();
            map.forEach((s,i) ->
                    view.write(String.format("\"%s\": %d,", s , i)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}