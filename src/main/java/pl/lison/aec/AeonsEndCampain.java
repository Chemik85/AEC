package pl.lison.aec;

import pl.lison.aec.handlers.*;
import pl.lison.aec.input.UserInputCommand;
import pl.lison.aec.input.UserInputManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AeonsEndCampain {
    private static Logger LOG = Logger.getLogger(AeonsEndCampain.class.getName());
    public static void main(String[] args) {
        new AeonsEndCampain().start();
    }

    private void start() {
        LOG.info("Start app!");

        boolean applicationLoop = true;

        UserInputManager userInputManager = new UserInputManager();
        List<CommandHandler> handlers = new ArrayList<>();
        handlers.add(new HelpCommandHandler());
        handlers.add(new QuitCommandHandler());
        handlers.add(new MageCommandHandler());
        handlers.add(new NemesisCommandHandler());
        handlers.add(new MarketCommandHandler());
        handlers.add(new ContentCommandHandler());

        while (applicationLoop) {
            try {
                UserInputCommand userInputCommand = userInputManager.nextCommand();
                LOG.info(userInputCommand.toString());
                Optional<CommandHandler> currenthandler = Optional.empty();

                for (CommandHandler handler : handlers) {
                    if (handler.supports(userInputCommand.getCommand())) {
                        currenthandler = Optional.of(handler);
                        break;
                    }
                }
                currenthandler
                        .orElseThrow(() -> new IllegalArgumentException("Unknown handler: " + userInputCommand.getCommand()))
                        .handle(userInputCommand);
            } catch (QuitApplicationException e) {
                LOG.info("Quit");
                applicationLoop = false;

            }catch (IllegalArgumentException e){
                LOG.log(Level.WARNING, "validation exception " + e.getMessage());

            } catch (Exception e) {
                e.printStackTrace();
                LOG.log(Level.SEVERE, "Unknown error", e);
            }
        }
    }
}
