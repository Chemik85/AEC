package pl.lison.aec.handlers;

import pl.lison.aec.dao.ContentDao;
import pl.lison.aec.dao.MageDao;
import pl.lison.aec.input.UserInputCommand;
import pl.lison.aec.model.Content;
import pl.lison.aec.model.Mage;

import java.util.List;
import java.util.logging.Logger;

public class MageCommandHandler extends BaseCommandHandler {
    private static Logger LOG = Logger.getLogger(MageCommandHandler.class.getName());
    private static final String COMMAND_NAME = "mage";
    private MageDao mageDao;
    private ContentDao contentDao;

    public MageCommandHandler() {
        mageDao = new MageDao();
        contentDao = new ContentDao();
    }

    public MageCommandHandler(MageDao mageDao, ContentDao contentDao) {
        this.mageDao = mageDao;
        this.contentDao = contentDao;
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

                if (!command.getParam().isEmpty()) {
                    throw new IllegalArgumentException("mage list doesn't support any params");
                }

                List<Mage> mages = mageDao.findAll();
                mages.forEach(System.out::println);
                break;
            case ADD:
                LOG.info("add mage");

                if (command.getParam().size() != 2) {
                    throw new IllegalArgumentException("wrong command format");
                }
                String contentName = command.getParam().get(0);
                String mageName = command.getParam().get(1);

                Content content = contentDao.findOne(contentName)
                        .orElseThrow(() -> new IllegalArgumentException("Content not found " + contentName));

                mageDao.add(new Mage(mageName, content));
                break;

            case DRAW:
                LOG.info("Draw mages.");
                if (command.getParam().size() != 1) {
                    throw new IllegalArgumentException("wrong command format");
                }
                int hawMany = Integer.parseInt(command.getParam().get(0));
                List<Mage> magesList = mageDao.drawAFew(hawMany);
                magesList.forEach(System.out::println);
                break;
            default: {
                throw new IllegalArgumentException(String.format("Unknown action: %s from command: %s",
                        command.getAction(), command.getCommand()));
            }
        }
    }
}
