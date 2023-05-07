package pl.lison.aec.handlers;

import pl.lison.aec.dao.MageDao;
import pl.lison.aec.input.UserInputCommand;
import pl.lison.aec.model.Mage;

import java.util.List;
import java.util.logging.Logger;

public class MageCommandHandler extends BaseCommandHandler {
    private static Logger LOG = Logger.getLogger(MageCommandHandler.class.getName());
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
        if (command.getAction() == null) {
            throw new IllegalArgumentException("Action can't be null");
        }

        switch (command.getAction()) {
            case LIST:
                LOG.info("list of mage...");

                if(!command.getParam().isEmpty()){
                    throw new IllegalArgumentException("mage list doesn't support any params");
                }

                List<Mage> mages = mageDao.findAll();
                mages.forEach(System.out::println);
                break;
            case ADD:
                LOG.info("add mage");

                if(command.getParam().size()!=1){
                    throw new IllegalArgumentException("wrong command format");
                }

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
