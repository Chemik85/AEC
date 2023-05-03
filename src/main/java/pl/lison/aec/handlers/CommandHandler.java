package pl.lison.aec.handlers;

import pl.lison.aec.input.UserInputCommand;

public interface CommandHandler {
    void handle(UserInputCommand command);

    boolean supports(String name);
}
