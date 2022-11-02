package command.order;

import command.Command;
import entity.ApiResponse;
import service.OrderService;
import view.View;

import java.io.IOException;

public class DeleteOrderById implements Command {
    public static final String COMMAND_NAME = "del order";
    private final View view;
    private final OrderService service;

    public DeleteOrderById(View view, OrderService service) {
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
        int id;
        while(true) {
            try {
                view.write("Enter order id: ");
                id = Integer.parseInt(view.read());
                if(service.findOrderById(id) == null){
                    view.write("No order found by this id. Try another id");
                } else {
                    break;
                }
            } catch (IllegalArgumentException e){
                view.write("Wrong input,use digits");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            response = service.deleteOrderById(id);
            if(response == null) {
                view.write("order not found");
            } else {
                view.write(response.toString());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
