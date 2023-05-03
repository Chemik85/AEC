package pl.lison.aec.handlers;

import pl.lison.aec.input.UserInputCommand;

public class HelpCommandHandler extends BaseCommandHandler {

    public static final String COMMAND_NAME = "help";

    @Override
    public void handle(UserInputCommand command) {
        System.out.println("Help");
        System.out.println("Allowed command: help, quit, nemesis, mage, market");


    }


    @Override
    protected String getCommandName() {
        return COMMAND_NAME;
    }
}
