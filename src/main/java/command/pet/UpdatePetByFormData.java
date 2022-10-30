package command.pet;

import command.Command;
import entity.ApiResponse;
import service.PetService;
import view.View;

import java.io.IOException;

public class UpdatePetByFormData implements Command {
    public static final String COMMAND_NAME = "update pet by form";
    private final View view;
    private final PetService service;

    public UpdatePetByFormData(View view, PetService service) {
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
        String petName;
        String status;
        while(true) {
            try {
                view.write("Enter pet id that you want to update: ");
                id = Long.parseLong(view.read());
                break;
            } catch (IllegalArgumentException e){
                view.write("Wrong input,use digits");
            }
        }
        view.write("Enter pet name: ");
        petName = view.read();
        while (true) {
            try {
                view.write("Enter pet status: \"available\" or \"pending\" or \"sold\": ");
                status = view.read();
                break;
            }catch  (IllegalArgumentException ex) {
                view.write("Illegal pet status.Enter \"available\" or \"pending\" or \"sold\"");
            }
        }
        try {
            ApiResponse apiResponse = service.updatePetByFormData(id, petName, status);
            if(apiResponse == null){
                view.write("No such pet, try another id");
            }else {
                view.write(apiResponse.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}