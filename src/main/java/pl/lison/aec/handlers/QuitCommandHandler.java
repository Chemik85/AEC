package pl.lison.aec.handlers;

import pl.lison.aec.QuitApplicationException;
import pl.lison.aec.input.UserInputCommand;

public class QuitCommandHandler extends BaseCommandHandler {
    public static final String COMMAND_NAME = "quit";

    @Override
    public void handle(UserInputCommand command) {
        throw new QuitApplicationException();
    }

    @Override
    protected String getCommandName() {
        return COMMAND_NAME;
    }
}
