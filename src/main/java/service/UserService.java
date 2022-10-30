package service;

import entity.ApiResponse;
import entity.User;
import repository.UserRepository;

import java.io.IOException;
import java.util.List;

public class UserService {
    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public ApiResponse createUsersWithList(List<User> usersList) throws IOException {
        return repository.createUsersWithList(usersList);
    }
    public ApiResponse createUsersWithArray(User[] user) throws IOException {
        return repository.createUsersWithArray(user);
    }
    public User getUser(String userName) throws IOException {
        return repository.getUser(userName);
    }
    public ApiResponse updateUser(User user) throws IOException {
        return repository.updateUser(user);
    }
    public ApiResponse deleteUser(String userName) throws IOException {
        return repository.deleteUser(userName);
    }
    public ApiResponse userLogin(String userName, String password) throws IOException {
        return repository.userLogin(userName, password);
    }
    public ApiResponse userLogout() throws IOException {
        return repository.userLogout();
    }
    public ApiResponse createUser(User user) throws IOException {
        return repository.createUser(user);
    }
}
