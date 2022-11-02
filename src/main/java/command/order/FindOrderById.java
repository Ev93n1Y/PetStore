package command.order;

import command.Command;
import entity.Order;
import service.OrderService;
import view.View;

import java.io.IOException;

public class FindOrderById implements Command {
    public static final String COMMAND_NAME = "find order";
    private final View view;
    private final OrderService service;

    public FindOrderById(View view, OrderService service) {
        this.view = view;
        this.service = service;
    }

    @Override
    public boolean canExecute(String input) {
        return input.equals(COMMAND_NAME);
    }

    @Override
    public void execute() {
        Order order;
        int id;
        while(true) {
            try {
                view.write("Enter order id: ");
                id = Integer.parseInt(view.read());
                break;
            } catch (IllegalArgumentException e){
                view.write("Wrong input,use digits");
            }
        }
        try {
            order = service.findOrderById(id);
            if(order == null) {
                view.write("order not found");
            } else {
                view.write(order.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}