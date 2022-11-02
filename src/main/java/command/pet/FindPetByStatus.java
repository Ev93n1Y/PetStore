package command.pet;

import command.Command;
import entity.Pet;
import service.PetService;
import view.View;

import java.io.IOException;

public class FindPetByStatus implements Command {
    public static final String COMMAND_NAME = "find pet by status";
    private final View view;
    private final PetService service;

    public FindPetByStatus(View view, PetService service) {
        this.view = view;
        this.service = service;
    }

    @Override
    public boolean canExecute(String input) {
        return input.equals(COMMAND_NAME);
    }

    @Override
    public void execute() {
        String status;
        Pet[] pets;
        while (true) {
            view.write("Enter pet status: \"available\" or \"pending\" or \"sold\": ");
            status = view.read();
            if (status.equals("available") || status.equals("pending") || status.equals("sold")){
                try {
                    pets = service.findPetByStatus(status);
                    if (pets.length == 0) {
                        view.write("No pets found, try another status");
                    } else {
                        view.write(String.format("Pets by status \"%s\" : ", status));
                        for(Pet pet : pets){
                            view.write(pet.toString());
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

                break;
            } else {
                view.write("Invalid status! Status must be \"available\", \"pending\", \"sold\"");
            }
        }

    }
}