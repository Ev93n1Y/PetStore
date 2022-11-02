package repository;

import entity.ApiResponse;
import entity.Pet;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.*;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class PetRepository extends EntityHandling {
    private final String uri = "https://petstore.swagger.io/v2/pet/";

    public PetRepository(CloseableHttpClient httpClient) {
        super(httpClient);
    }

    //GET/pet/{petId}            Find pet by ID
    //Returns a single pet
    public Pet findPetById(Long id) throws IOException {
        HttpGet request = new HttpGet(uri + id);
        setContentTypeJson(request);
        HttpEntity entity = getHttpEntity(httpClient, request);
        return objectFromJsonOrNull(entity, Pet.class);
    }

    //GET/pet/findByStatus       Finds Pets by status
    //Multiple status values can be provided with comma separated strings
    public Pet[] findPetByStatus(String status) throws IOException {
        //status: "available", "pending", "sold"
        HttpGet request = new HttpGet(uri + "findByStatus?status=" + status);
        setContentTypeJson(request);
        HttpEntity entity = getHttpEntity(httpClient, request);
        return objectFromJsonOrNull(entity, Pet[].class);
    }

    //GET/pet/findByTags         Finds Pets by tags
    //Multiple tags can be provided with comma separated strings.
    // Use tag1, tag2, tag3 for testing.
    @Deprecated
    public Pet[] findPetByTags(List<String> tags) throws IOException {
        StringBuilder stringBuilder = new StringBuilder(uri);
        stringBuilder.append("findByTags?");
        boolean firstPosition = true;
        for (String tag : tags) {
            if (!firstPosition) {
                stringBuilder.append("&");
            }
            stringBuilder.append("tags=");
            stringBuilder.append(tag);
            firstPosition = false;
        }
        HttpGet request = new HttpGet(String.valueOf(stringBuilder));
        setContentTypeJson(request);
        HttpEntity entity = getHttpEntity(httpClient, request);
        return objectFromJsonOrNull(entity, Pet[].class);
    }

    //POST/pet                    Add a new pet to the store
    public Pet addNewPet(Pet pet) throws IOException {
        StringEntity requestEntity = new StringEntity(gson.toJson(pet));
        HttpPost request = new HttpPost(uri);
        request.setEntity(requestEntity);
        setContentTypeJson(request);
        HttpEntity entity = getHttpEntity(httpClient, request);
        return objectFromJsonOrNull(entity, Pet.class);
    }

    //POST/pet/{petId}            Updates a pet in the store with form data
    public ApiResponse updatePetWithFormData(Long id, String newName, String newStatus) throws IOException {
        StringEntity requestEntity = new StringEntity(gson.toJson("name=" + newName + "&status=" + newStatus));
        HttpPost request = new HttpPost(uri + id);
        request.setEntity(requestEntity);
        setContentTypeForm(request);
        HttpEntity entity = getHttpEntity(httpClient, request);
        return objectFromJsonOrNull(entity, ApiResponse.class);
    }

    //PUT/pet                    Update an existing pet
    public Pet updatePetById(Pet pet) throws IOException {
        StringEntity requestEntity = new StringEntity(gson.toJson(pet));
        HttpPost request = new HttpPost(uri);
        request.setEntity(requestEntity);
        setContentTypeJson(request);
        HttpEntity entity = getHttpEntity(httpClient, request);
        return objectFromJsonOrNull(entity, Pet.class);
    }

    //POST/pet/{petId}/uploadImage    uploads an image
    public ApiResponse uploadImageByPetId(Long id, File image) throws IOException {
        HttpEntity requestEntity = MultipartEntityBuilder.create()
                .addBinaryBody("file", image)
                .build();
        HttpPost request = new HttpPost(uri + id + "/uploadImage");
        request.setEntity(requestEntity);
        //setContentTypeMultipart(request);
        HttpEntity entity = getHttpEntity(httpClient, request);
        return objectFromJsonOrNull(entity, ApiResponse.class);
    }

    //DELETE/pet/{petId}            Deletes a pet
    public ApiResponse deletePet(Integer id) throws IOException {
        HttpDelete request = new HttpDelete(uri + id);
        HttpEntity entity = getHttpEntity(httpClient, request);
        return objectFromJsonOrNull(entity, ApiResponse.class);
    }
}
