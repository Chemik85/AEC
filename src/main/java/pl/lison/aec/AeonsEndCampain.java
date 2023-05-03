package pl.lison.aec;

import pl.lison.aec.handlers.CommandHandler;
import pl.lison.aec.handlers.HelpCommandHandler;
import pl.lison.aec.handlers.MageCommandHandler;
import pl.lison.aec.handlers.QuitCommandHandler;
import pl.lison.aec.input.UserInputCommand;
import pl.lison.aec.input.UserInputManager;
import pl.lison.aec.model.Mage;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AeonsEndCampain {
    public static void main(String[] args) {
        new AeonsEndCampain().start();
    }

    private void start() {
        System.out.println("Start app!");

        /**
         * mage list -> mageList()
         * mage add MageName -> mageAdd(name)
         *
         * nemesis list -> nemesisList()
         * nemesis add NemesisName NemesisLevel -> nemesisAdd(name, level)
         *
         * quit -> quitApplication()
         *
         * market list MarketType -> marketList(type)
         * market add MarketType MarketName MarketCost -> marketAdd(markertType, marketName, marketCost)
         *
         * help
         */

        boolean applicationLoop = true;

        UserInputManager userInputManager = new UserInputManager();
        List<CommandHandler> handlers = new ArrayList<>();
        handlers.add(new HelpCommandHandler());
        handlers.add(new QuitCommandHandler());
        handlers.add(new MageCommandHandler());

        while (applicationLoop) {
            try {
                UserInputCommand userInputCommand = userInputManager.nextCommand();
                System.out.println(userInputCommand);
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
                System.out.println("Quit");
                applicationLoop = false;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
