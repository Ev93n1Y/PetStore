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

public class UpdatePetById implements Command {
    public static final String COMMAND_NAME = "update pet";
    private final View view;
    private final PetService service;

    public UpdatePetById(View view, PetService service) {
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
        List<Tags> tags = new ArrayList<>();
        int tagId = 0;
        long petId;
        while(true) {
            try {
                view.write("Enter pet id that you want to update: ");
                petId = Long.parseLong(view.read());
                if(service.findPetById(petId) == null) {
                    view.write("No pets found by this id. Try another id.");
                } else {
                    break;
                }
            } catch (IllegalArgumentException e){
                view.write("Wrong input,use digits");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        pet.setId(petId);
        view.write("Enter pet name: ");
        pet.setName(view.read());
        category.setId(0L);
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
        tags.add(new Tags());
        tags.get(tagId).setId(0L);
        view.write("Enter tag name: ");
        tags.get(tagId).setName(view.read());
        while (true) {
            view.write("Do you want to add more tags? Enter Y/N: ");
            if (view.read().equalsIgnoreCase("Y")) {
                tags.add(new Tags());
                tags.get(++tagId).setId((long) tagId);
                view.write("Enter tag name: ");
                tags.get(tagId).setName(view.read());
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
            pet = service.updatePetById(pet);
            view.write(String.format("Pet id = %d updated: ", pet.getId()));
            view.write(pet.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}