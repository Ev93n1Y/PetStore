package command;

import command.order.DeleteOrderById;
import command.order.FindOrderById;
import command.order.GetPetInventories;
import command.order.PlaceOrder;
import command.pet.*;
import command.user.*;
import view.View;

import java.util.ArrayList;
import java.util.List;

public class Help implements Command{
    private static final String HELP = "help";
    private final View view;

    public Help(View view) {
        this.view = view;
    }

    @Override
    public boolean canExecute(String input) {
        return input.equals(HELP);
    }

    @Override
    public void execute() {
        List<String> commandList = new ArrayList<>();
        commandList.add(String.format("Enter >%s< to see all commands", HELP));
        commandList.add(String.format("Enter >%s< to exit program", Exit.COMMAND_NAME));

        commandList.add(String.format("Enter >%s< to find pet by id", FindPetById.COMMAND_NAME));
        commandList.add(String.format("Enter >%s< to find pet by status", FindPetByStatus.COMMAND_NAME));
        commandList.add(String.format("Enter >%s< to add new pet", AddNewPet.COMMAND_NAME));
        commandList.add(String.format("Enter >%s< to update pet by id", UpdatePetById.COMMAND_NAME));
        commandList.add(String.format("Enter >%s< to upload image", UploadPetImage.COMMAND_NAME));
        commandList.add(String.format("Enter >%s< to delete pet", DeletePet.COMMAND_NAME));
        commandList.add(String.format("Enter >%s< to update pet by form data", UpdatePetByFormData.COMMAND_NAME));

        commandList.add(String.format("Enter >%s< to delete order by id", DeleteOrderById.COMMAND_NAME));
        commandList.add(String.format("Enter >%s< to find order by id", FindOrderById.COMMAND_NAME));
        commandList.add(String.format("Enter >%s< to get pet inventories", GetPetInventories.COMMAND_NAME));
        commandList.add(String.format("Enter >%s< to place order", PlaceOrder.COMMAND_NAME));

        commandList.add(String.format("Enter >%s< to create user", CreateUser.COMMAND_NAME));
        commandList.add(String.format("Enter >%s< to create user with array", CreateUsersWithArray.COMMAND_NAME));
        commandList.add(String.format("Enter >%s< to create user with list", CreateUsersWithList.COMMAND_NAME));
        commandList.add(String.format("Enter >%s< to delete user", DeleteUser.COMMAND_NAME));
        commandList.add(String.format("Enter >%s< to update user", UpdateUser.COMMAND_NAME));
        commandList.add(String.format("Enter >%s< to login", UserLogin.COMMAND_NAME));
        commandList.add(String.format("Enter >%s< to logout", UserLogout.COMMAND_NAME));

        view.write("Commands list:");
        for(String command: commandList){
            view.write(command);
        }
    }
}
