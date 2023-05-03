package pl.lison.aec.handlers;

import pl.lison.aec.dao.MageDao;
import pl.lison.aec.input.UserInputCommand;
import pl.lison.aec.model.Mage;

import java.util.List;

public class MageCommandHandler extends BaseCommandHandler {
    private static final String COMMAND_NAME = "mage";
    private MageDao mageDao;

    public MageCommandHandler() {
        mageDao = new MageDao();
    }

    @Override
    protected String getCommandName() {
        return COMMAND_NAME;
    }

    @Override
    public void handle(UserInputCommand command) {

        switch (command.getAction()) {
            case "list":
                System.out.println("list of mage...");
                List<Mage> mages = mageDao.findAll();
                mages.forEach(System.out::println);
                break;
            case "add":
                System.out.println("add mage");
                String mageName = command.getParam().get(0);
                mageDao.add(new Mage(mageName));
                break;
            default: {
                throw new IllegalArgumentException(String.format("Unknown action: %s from command: %s",
                        command.getAction(), command.getCommand()));
            }
        }
    }
}
