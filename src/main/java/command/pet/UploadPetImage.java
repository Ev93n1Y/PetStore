package command.pet;

import command.Command;
import entity.ApiResponse;
import service.PetService;
import view.View;

import java.io.File;
import java.io.IOException;

public class UploadPetImage implements Command {
    public static final String COMMAND_NAME = "upload pet image";
    private final View view;
    private final PetService service;

    public UploadPetImage(View view, PetService service) {
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
        while(true) {
            while (true) {
                try {
                    view.write("Enter pet id that you want to update: ");
                    id = Long.parseLong(view.read());
                    break;
                } catch (IllegalArgumentException e) {
                    view.write("Wrong input,use digits");
                }
            }
            view.write("Enter photo name in resource path");
            view.write("For example: \"the-cat.jpg\"");
            String path = String.format("./src/main/resources/%s", view.read());
            try {
                ApiResponse apiResponse = service.uploadPetImage(id, new File(path));
                if (apiResponse == null) {
                    view.write("No such pet, try another id");
                } else {
                    view.write(apiResponse.toString());
                    break;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
