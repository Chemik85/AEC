package pl.lison.aec.handlers;

import pl.lison.aec.dao.ContentDao;
import pl.lison.aec.input.UserInputCommand;
import pl.lison.aec.model.Content;

import java.util.List;
import java.util.logging.Logger;

public class ContentCommandHandler extends BaseCommandHandler {
    private static Logger LOG = Logger.getLogger(ContentCommandHandler.class.getName());
    private static final String COMMAND_NAME = "content";
    private ContentDao contentDao;

    public ContentCommandHandler() {
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
                LOG.info("list of content...");

                if (!command.getParam().isEmpty()) {
                    throw new IllegalArgumentException("content list doesn't support any params");
                }
                List<Content> contents = contentDao.findAll();
                contents.forEach(System.out::println);
                break;

            case ADD:
                LOG.info("add content");
                if (command.getParam().size() != 1) {
                    throw new IllegalArgumentException("wrong command format");
                }
                String contentName = command.getParam().get(0);
                contentDao.add(new Content(contentName));
                break;

            default: {
                throw new IllegalArgumentException(String.format("Unknown action: %s from command: %s",
                        command.getAction(), command.getCommand()));
            }
        }
    }
}
