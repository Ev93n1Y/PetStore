package command.pet;

import command.Command;
import entity.Category;
import entity.Pet;
import entity.Tags;
import service.PetService;
import view.View;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AddNewPet implements Command {
    public static final String COMMAND_NAME = "add pet";
    private final View view;
    private final PetService service;

    public AddNewPet(View view, PetService service) {
        this.view = view;
        this.service = service;
    }

    @Override
    public boolean canExecute(String input) {
        return input.equals(COMMAND_NAME);
    }

    @Override
    public void execute() {
        Pet pet = new Pet();
        Category category = new Category();
        Tags tag = new Tags();
        List<Tags> tags = new ArrayList<>();
        while(true) {
            try {
                view.write("Enter pet id: ");
                pet.setId(Long.parseLong(view.read()));
                break;
            } catch (IllegalArgumentException e){
                view.write("Wrong input,use digits");
            }
        }
        view.write("Enter pet name: ");
        pet.setName(view.read());
        while(true) {
            try {
                view.write("Enter category id: ");
                category.setId(Long.parseLong(view.read()));
                break;
            } catch (IllegalArgumentException e){
                view.write("No such id, try again");
            }
        }
        view.write("Enter category name: ");
        category.setName(view.read());
        pet.setCategory(category);
        view.write("Enter photo url: ");
        pet.setPhotoUrls(new ArrayList<>());
        pet.getPhotoUrls().add(view.read());
        while (true) {
            view.write("Do you want to add more photos? Enter Y/N: ");
            if (view.read().equalsIgnoreCase("Y")) {
                view.write("Enter photo url: ");
                pet.getPhotoUrls().add(view.read());
            } else {
                break;
            }
        }
        while(true) {
            try {
                view.write("Enter tag id: ");
                tag.setId(Long.parseLong(view.read()));
                break;
            } catch (NumberFormatException e){
                view.write("Invalid value. Use digits");
            }
        }
        view.write("Enter tag name: ");
        tag.setName(view.read());
        tags.add(tag);
        while (true) {
            view.write("Do you want to add more tags? Enter Y/N: ");
            if (view.read().equalsIgnoreCase("Y")) {
                while (true){
                    try{
                        view.write("Enter tag id: ");
                        tag.setId(Long.parseLong(view.read()));
                        break;
                    } catch (NumberFormatException e){
                        view.write("Invalid value. Use digits");
                    }
                }
                view.write("Enter tag name: ");
                tag.setName(view.read());
                tags.add(tag);
            } else {
                break;
            }
        }
        pet.setTags(tags);
        view.write("Enter pet status: \"available\" or \"pending\" or \"sold\": ");
        while (true){
            String status = view.read();
            if (status.equals("available") || status.equals("pending") || status.equals("sold")){
                pet.setStatus(status);
                break;
            } else {
                view.write("Invalid status! Status must be \"available\", \"pending\", \"sold\"");
            }
        }
        try {
            view.write("new pet created: ");
            view.write(service.addNewPet(pet).toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}