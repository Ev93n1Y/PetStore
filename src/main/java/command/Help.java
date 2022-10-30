package command;

import command.order.DeleteOrderById;
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

        commandList.add(String.format("Enter >%s< to exit program", FindPetById.COMMAND_NAME));
        commandList.add(String.format("Enter >%s< to exit program", FindPetByStatus.COMMAND_NAME));
        commandList.add(String.format("Enter >%s< to exit program", AddNewPet.COMMAND_NAME));
        commandList.add(String.format("Enter >%s< to exit program", UpdatePetById.COMMAND_NAME));
        commandList.add(String.format("Enter >%s< to exit program", UploadPetImage.COMMAND_NAME));
        commandList.add(String.format("Enter >%s< to exit program", DeletePet.COMMAND_NAME));
        commandList.add(String.format("Enter >%s< to exit program", UpdatePetByFormData.COMMAND_NAME));

        commandList.add(String.format("Enter >%s< to exit program", DeleteOrderById.COMMAND_NAME));
        commandList.add(String.format("Enter >%s< to exit program", DeleteOrderById.COMMAND_NAME));
        commandList.add(String.format("Enter >%s< to exit program", GetPetInventories.COMMAND_NAME));
        commandList.add(String.format("Enter >%s< to exit program", PlaceOrder.COMMAND_NAME));

        commandList.add(String.format("Enter >%s< to exit program", CreateUser.COMMAND_NAME));
        commandList.add(String.format("Enter >%s< to exit program", CreateUsersWithArray.COMMAND_NAME));
        commandList.add(String.format("Enter >%s< to exit program", CreateUsersWithList.COMMAND_NAME));
        commandList.add(String.format("Enter >%s< to exit program", DeleteUser.COMMAND_NAME));
        commandList.add(String.format("Enter >%s< to exit program", UpdateUser.COMMAND_NAME));
        commandList.add(String.format("Enter >%s< to exit program", UserLogin.COMMAND_NAME));
        commandList.add(String.format("Enter >%s< to exit program", UserLogout.COMMAND_NAME));

        view.write("Commands list:");
        for(String command: commandList){
            view.write(command);
        }
    }
}
