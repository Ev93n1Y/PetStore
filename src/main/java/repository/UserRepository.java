package repository;

import entity.ApiResponse;
import entity.User;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;

import java.io.IOException;
import java.util.List;

public class UserRepository extends EntityHandling {
    private final String uri = "https://petstore.swagger.io/v2/user/";

    public UserRepository(CloseableHttpClient httpClient) {
        super(httpClient);
    }

    //POST/user/createWithArray       Creates list of users with given input array
    public ApiResponse createUsersWithList(List<User> usersList) throws IOException {
        HttpPost request = new HttpPost(uri + "createWithArray");
        setContentTypeJson(request);
        HttpEntity entity = getHttpEntity(httpClient, request);
        return objectFromJsonOrNull(entity, ApiResponse.class);
        //return gson.fromJson(EntityUtils.toString(entity), ApiResponse.class);
    }

    //POST/user/createWithList        Creates list of users with given input array
    public ApiResponse createUsersWithArray(User[] user) throws IOException {
        StringEntity requestEntity = new StringEntity(gson.toJson(user));
        HttpPost request = new HttpPost(uri + "createWithList");
        request.setEntity(requestEntity);
        setContentTypeJson(request);
        HttpEntity entity = getHttpEntity(httpClient, request);
        return objectFromJsonOrNull(entity, ApiResponse.class);
        //return gson.fromJson(EntityUtils.toString(entity), ApiResponse.class);
    }

    //GET/user/{username}     Get user by user name
    public User getUser(String userName) throws IOException {
        HttpGet request = new HttpGet(uri + "user/" + userName);
        setContentTypeJson(request);
        HttpEntity entity = getHttpEntity(httpClient, request);
        return objectFromJsonOrNull(entity, User.class);
        //return gson.fromJson(EntityUtils.toString(entity), User.class);
    }

    //PUT/user/{username}     Updated user
    //This can only be done by the logged-in user.
    public ApiResponse updateUser(User user) throws IOException {
        StringEntity requestEntity = new StringEntity(gson.toJson(user));
        HttpPut request = new HttpPut(uri + user.getUsername());
        request.setEntity(requestEntity);
        setContentTypeJson(request);
        HttpEntity entity = getHttpEntity(httpClient, request);
        return objectFromJsonOrNull(entity, ApiResponse.class);
        //return gson.fromJson(EntityUtils.toString(entity), ApiResponse.class);
    }

    //DELETE/user/{username}  Delete user
    //This can only be done by the logged-in user.
    public ApiResponse deleteUser(String userName) throws IOException {
        HttpDelete request = new HttpDelete(uri + "user/" + userName);
        setContentTypeJson(request);
        HttpEntity entity = getHttpEntity(httpClient, request);
        return objectFromJsonOrNull(entity, ApiResponse.class);
        //return gson.fromJson(EntityUtils.toString(entity), ApiResponse.class);
    }

    //GET/user/login  Logs user into the system
    public ApiResponse userLogin(String userName, String password) throws IOException {
        HttpGet request = new HttpGet(uri + "user/login?username=" + userName + "&password=" + password);
        setContentTypeJson(request);
        HttpEntity entity = getHttpEntity(httpClient, request);
        return objectFromJsonOrNull(entity, ApiResponse.class);
        //return gson.fromJson(EntityUtils.toString(entity), ApiResponse.class);
    }

    //GET/user/logout Logs out current logged-in user session
    public ApiResponse userLogout() throws IOException {
        HttpGet request = new HttpGet(uri + "user/logout");
        setContentTypeJson(request);
        HttpEntity entity = getHttpEntity(httpClient, request);
        return objectFromJsonOrNull(entity, ApiResponse.class);
        //return gson.fromJson(EntityUtils.toString(entity), ApiResponse.class);
    }

    //POST/user   Create user
    //This can only be done by the logged-in user.
    public ApiResponse createUser(User user) throws IOException {
        StringEntity requestEntity = new StringEntity(gson.toJson(user));
        HttpPost request = new HttpPost(uri);
        request.setEntity(requestEntity);
        setContentTypeJson(request);
        HttpEntity entity = getHttpEntity(httpClient, request);
        return objectFromJsonOrNull(entity, ApiResponse.class);
        //return gson.fromJson(EntityUtils.toString(entity), ApiResponse.class);
    }

}
