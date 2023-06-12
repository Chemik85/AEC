package pl.lison.aec.handlers;

import pl.lison.aec.dao.ContentDao;
import pl.lison.aec.dao.NemesisDao;
import pl.lison.aec.input.UserInputCommand;
import pl.lison.aec.model.Content;
import pl.lison.aec.model.Nemesis;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class NemesisCommandHandler extends BaseCommandHandler {
    private static Logger LOG = Logger.getLogger(NemesisCommandHandler.class.getName());
    private static final String COMMAND_NAME = "nemesis";
    private NemesisDao nemesisDao;
    private ContentDao contentDao;

    public NemesisCommandHandler() {
        nemesisDao = new NemesisDao();
        contentDao = new ContentDao();
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

                if (!command.getParam().isEmpty()) {
                    throw new IllegalArgumentException("mage list doesn't support any params");
                }

                List<Nemesis> nemeses = nemesisDao.findAll();
                nemeses.forEach(System.out::println);
                break;
            case ADD:
                try {
                    LOG.info("add nemesis");

                    if (command.getParam().size() != 3) {
                        throw new IllegalArgumentException("wrong command format");
                    }
                    String contentName = command.getParam().get(0);
                    String nemesisName = command.getParam().get(1);
                    String nemesisLevel = Integer.toString(Integer.parseInt(command.getParam().get(2)));


                    Content content = contentDao.findOne(contentName)
                            .orElseThrow(() -> new IllegalArgumentException("Content not found " + contentName));

                    nemesisDao.add(new Nemesis(nemesisName, nemesisLevel, content));

                } catch (NumberFormatException e) {
                    LOG.log(Level.WARNING, "Level have to be an integer");

                }
                break;
            case DRAW:
                LOG.info("Draw nemesis.");
                if (command.getParam().size() != 0) {
                    throw new IllegalArgumentException("wrong command format");
                }
                List<Nemesis> nemesisList = nemesisDao.drawOne();
                nemesisList.forEach(System.out::println);
                break;
            default: {
                throw new IllegalArgumentException(String.format("Unknown action: %s from command: %s",
                        command.getAction(), command.getCommand()));
            }
        }
    }
}
