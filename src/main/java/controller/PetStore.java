package controller;


import command.Command;
import exceptions.BlancFieldException;
import exceptions.ExitException;
import exceptions.NotFoundException;
import lombok.AllArgsConstructor;
import view.View;

import java.util.List;

@AllArgsConstructor
public class PetStore {
    private final View view;
    private final List<Command> commands;

    public void run() {
        view.write("Hello, please enter help to see all commands");
        try {
            execute();
        } catch (ExitException e) {
            view.write("Thanks for your visit! See you next time...");
        }
    }

    private void execute() {
        while (true) {
            String input = view.read();
            boolean isInputCorrect = false;
            for (Command command : commands) {
                if (command.canExecute(input)) {
                    try {
                        command.execute();
                    } catch (NumberFormatException e) {
                        view.write("Incorrect input type. Please use numeric");
                    } catch (BlancFieldException e) {
                        view.write("Empty fields are not allowed here");
                    } catch (NotFoundException e) {
                        view.write(e.getMessage());
                    }
                    isInputCorrect = true;
                }
            }
            if (!isInputCorrect) {
                view.write("Command not found/not recognised.");
            }

            view.write("Please enter next command:");
        }
    }
}
