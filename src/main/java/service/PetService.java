package service;

import entity.ApiResponse;
import entity.Pet;
import repository.PetRepository;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class PetService {
    private final PetRepository repository;

    public PetService(PetRepository repository) {
        this.repository = repository;
    }

    public Pet findPetById(Long id) throws IOException {
        return repository.findPetById(id);
    }

    public Pet[] findPetByStatus(String status) throws IOException {
        return repository.findPetByStatus(status);
    }

    @Deprecated
    public Pet[] findPetByTags(List<String> tagsList) throws IOException {
        return repository.findPetByTags(tagsList);
    }

    public Pet addNewPet(Pet pet) throws IOException {
        return repository.addNewPet(pet);
    }

    public Pet updatePetById(Pet pet) throws IOException {
        return repository.updatePetById(pet);
    }

    public ApiResponse uploadPetImage(Long id, File image) throws IOException {
        return repository.uploadImageByPetId(id, image);
    }

    public ApiResponse deletePet(Integer id) throws IOException {
        return repository.deletePet(id);
    }

    public ApiResponse updatePetByFormData(Long id, String newName, String newStatus) throws IOException {
        return repository.updatePetWithFormData(id, newName, newStatus);
    }
}
