package command.order;

import command.Command;
import entity.Order;
import service.OrderService;
import view.View;

import java.io.IOException;
import java.sql.Date;

public class PlaceOrder implements Command {
    public static final String COMMAND_NAME = "place order";
    private final View view;
    private final OrderService service;

    public PlaceOrder(View view, OrderService service) {
        this.view = view;
        this.service = service;
    }

    @Override
    public boolean canExecute(String input) {
        return input.equals(COMMAND_NAME);
    }

    @Override
    public void execute() {
        Order order = new Order();
        order.setId(0L);
        while(true) {
            try {
                view.write("Enter pet id: ");
                order.setPetId(Integer.parseInt(view.read()));
                break;
            } catch (IllegalArgumentException e){
                view.write("Wrong input,use digits");
            }
        }
        while(true) {
            try {
                view.write("Enter quantity o pets: ");
                order.setQuantity(Integer.parseInt(view.read()));
                break;
            } catch (IllegalArgumentException e){
                view.write("Wrong input,use digits");
            }
        }
        view.write("Enter ship date YYYY-MM-DD: ");
        order.setShipDate(String.valueOf(Date.valueOf(view.read())));
        view.write("Enter status \"placed\" or \"approved\" or \"delivered\"");
        while (true){
            String status = view.read();
            if (status.equals("placed") || status.equals("approved") || status.equals("delivered")){
                order.setStatus(status);
                break;
            } else {
                view.write("Invalid status! Status must be \"placed\", \"approved\", \"delivered\"");
            }
        }
        System.out.println("Order completed? Enter Y/N: ");
        while (true) {
            String complete = view.read();
            if (complete.equalsIgnoreCase("Y")) {
                order.setComplete(true);
                break;
            } else if (complete.equals("N")) {
                order.setComplete(false);
                break;
            } else {
                System.out.println("Incorrect value. Enter Y/N: ");
            }
        }
        try {
            order = service.placeOrder(order);
            if(order == null) {
                view.write("order not placed");
            } else {
                view.write(order.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
