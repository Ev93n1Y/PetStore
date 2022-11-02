package command.pet;

import command.Command;
import service.PetService;
import view.View;

import java.io.IOException;

public class DeletePet implements Command {
    public static final String COMMAND_NAME = "del pet";
    private final View view;
    private final PetService service;

    public DeletePet(View view, PetService service) {
        this.view = view;
        this.service = service;
    }

    @Override
    public boolean canExecute(String input) {
        return input.equals(COMMAND_NAME);
    }

    @Override
    public void execute() {
        while(true) {
            long id;
            try {
                view.write("Enter pet id that you want to delete: ");
                id = Long.parseLong(view.read());
                if(service.findPetById(id) == null){
                    view.write("No pets found by this id. Try another id.");
                } else {
                    view.write(service.deletePet(id).toString());
                    break;
                }
            } catch (IllegalArgumentException e){
                view.write("Wrong input,use digits");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
