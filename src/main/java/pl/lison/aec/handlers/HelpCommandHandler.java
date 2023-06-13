package pl.lison.aec.handlers;

import pl.lison.aec.input.UserInputCommand;

public class HelpCommandHandler extends BaseCommandHandler {

    public static final String COMMAND_NAME = "help";

    @Override
    public void handle(UserInputCommand command) {
        System.out.println("Help");
        System.out.println("Allowed command: help, quit, content, nemesis, mage, market");
        System.out.println("Allowed Action: list, add, draw");
        System.out.println("Allowed Parameters: \n" +
                "for nemesis: Content name (eg. Aeon's End, New Age), Nemesis name, nemesis level (have to be integer) \n" +
                "for mage: Content name (eg. Aeon's End, New Age), Mage name \n" +
                "for market: Content name (eg. Aeon's End, New Age), market type (gems, relicts, spells), market name, cost (have to be integer)");
        System.out.println("Example: nemesis, list \n" +
                "nemesis, add, Aeon's End, Rageborne, 2");
    }

    @Override
    protected String getCommandName() {
        return COMMAND_NAME;
    }
}
