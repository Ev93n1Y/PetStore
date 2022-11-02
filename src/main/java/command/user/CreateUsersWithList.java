package command.user;

import command.Command;
import entity.ApiResponse;
import entity.User;
import service.UserService;
import view.View;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CreateUsersWithList implements Command {
    public static final String COMMAND_NAME = "create user by list";
    private final View view;
    private final UserService service;

    public CreateUsersWithList(View view, UserService service) {
        this.view = view;
        this.service = service;
    }

    @Override
    public boolean canExecute(String input) {
        return input.equals(COMMAND_NAME);
    }

    @Override
    public void execute() {
        List<User> userList = new ArrayList<>();
        ApiResponse response;
        userList.add(new User(0L, "string1", "string", "string",
                "string", "string", "string", 0)) ;
        userList.add(new User(0L, "string2", "string", "string",
                "string", "string3", "string", 0)) ;
        userList.add(new User(0L, "string", "string", "string",
                "string", "string", "string", 0)) ;
        try {
            response = service.createUsersWithList(userList);
            view.write(response.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}