import command.Command;
import command.Exit;
import command.Help;
import command.order.DeleteOrderById;
import command.order.FindOrderById;
import command.order.GetPetInventories;
import command.order.PlaceOrder;
import command.pet.*;
import command.user.*;
import controller.PetStore;
import repository.OrderRepository;
import repository.PetRepository;
import repository.UserRepository;
import service.OrderService;
import service.PetService;
import service.UserService;
import view.Console;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Launch {
    public static void main(String[] args) {
        HttpClient httpClient = new HttpClient(5000, 5000, 5000);
        PetRepository petRepository = new PetRepository(httpClient.getClient());
        OrderRepository orderRepository = new OrderRepository(httpClient.getClient());
        UserRepository userRepository = new UserRepository(httpClient.getClient());
        PetService petService = new PetService(petRepository);
        OrderService orderService = new OrderService(orderRepository);
        UserService userService = new UserService(userRepository);

        Scanner scanner = new Scanner(System.in);
        Console console = new Console(scanner);
        List<Command> commandList = new ArrayList<>();
        PetStore petStore = new PetStore(console, commandList);
        commandList.add(new Help(console));
        commandList.add(new Exit());

        commandList.add(new FindPetById(console, petService));
        commandList.add(new AddNewPet(console, petService));
        commandList.add(new FindPetByStatus(console, petService));
        commandList.add(new UpdatePetById(console, petService));
        commandList.add(new UploadPetImage(console, petService));
        commandList.add(new DeletePet(console, petService));
        commandList.add(new UpdatePetByFormData(console, petService));

        commandList.add(new DeleteOrderById(console, orderService));
        commandList.add(new FindOrderById(console, orderService));
        commandList.add(new GetPetInventories(console, orderService));
        commandList.add(new PlaceOrder(console, orderService));

        commandList.add(new CreateUser(console, userService));
        commandList.add(new CreateUsersWithArray(console, userService));
        commandList.add(new CreateUsersWithList(console, userService));
        commandList.add(new DeleteUser(console, userService));
        commandList.add(new GetUser(console, userService));
        commandList.add(new UpdateUser(console, userService));
        commandList.add(new UserLogin(console, userService));
        commandList.add(new UserLogout(console, userService));
        petStore.run();
    }
}
