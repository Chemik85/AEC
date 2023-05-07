package pl.lison.aec.handlers;


import pl.lison.aec.dao.NemesisDao;
import pl.lison.aec.input.UserInputCommand;
import pl.lison.aec.model.Nemesis;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class NemesisCommandHandler extends BaseCommandHandler {
    private static Logger LOG = Logger.getLogger(NemesisCommandHandler.class.getName());

    private static final String COMMAND_NAME = "nemesis";
    private NemesisDao nemesisDao;

    public NemesisCommandHandler() {
        nemesisDao = new NemesisDao();
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
                LOG.info("list of nemesis...");

                if(!command.getParam().isEmpty()){
                    throw new IllegalArgumentException("mage list doesn't support any params");
                }

                List<Nemesis> nemeses = nemesisDao.findAll();
                nemeses.forEach(System.out::println);
                break;
            case ADD:
                try {
                    LOG.info("add nemesis");

                    if (command.getParam().size() != 2) {
                        throw new IllegalArgumentException("wrong command format");
                    }

                    String nemesisName = command.getParam().get(0);

                    String nemesisLevel = Integer.toString(Integer.parseInt(command.getParam().get(1)));


                    nemesisDao.add(new Nemesis(nemesisName, nemesisLevel));

                }catch (NumberFormatException e){
                    LOG.log(Level.WARNING, "Level have to be an integer");

                }break;
            default: {
                throw new IllegalArgumentException(String.format("Unknown action: %s from command: %s",
                        command.getAction(), command.getCommand()));
            }
        }
    }
}
