package command.pet;

import command.Command;
import entity.Pet;
import service.PetService;
import view.View;

import java.io.IOException;

public class FindPetById implements Command {
    public static final String COMMAND_NAME = "find pet by id";
    private final View view;
    private final PetService service;

    public FindPetById(View view, PetService service) {
        this.view = view;
        this.service = service;
    }

    @Override
    public boolean canExecute(String input) {
        return input.equals(COMMAND_NAME);
    }

    @Override
    public void execute() {
        long id;
        Pet pet;
        while(true) {
            try {
                view.write("Enter pet id:");
                id = Long.parseLong(view.read());
                pet = service.findPetById(id);
                break;
            } catch (IOException e) {
                view.write("Wrong input,use digits");
            } catch (IllegalArgumentException e){
                view.write("No such id, try again");
            }
        }
        if(pet == null) {
            view.write("Pet not found");
        } else {
            view.write(pet.toString());
        }
    }
}
