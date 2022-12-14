package service;

import entity.ApiResponse;
import entity.Order;
import repository.OrderRepository;

import java.io.IOException;
import java.util.Map;

public class OrderService {
    private final OrderRepository repository;

    public OrderService(OrderRepository repository) {
        this.repository = repository;
    }

    public Order placeOrder(Order order) throws IOException {
        return repository.placeOrder(order);
    }

    public Order findOrderById(Integer id) throws IOException {
        return repository.findOrderById(id);
    }

    public ApiResponse deleteOrderById(Integer id) throws IOException {
        return repository.deleteOrderById(id);
    }

    public Map<String, Integer> getPetInventories() throws IOException {
        return repository.getPetInventories();
    }
}
