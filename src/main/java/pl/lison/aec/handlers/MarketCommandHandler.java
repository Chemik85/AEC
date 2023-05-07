package pl.lison.aec.handlers;

import pl.lison.aec.dao.MarketDao;
import pl.lison.aec.dao.NemesisDao;
import pl.lison.aec.input.UserInputCommand;
import pl.lison.aec.model.Market;
import pl.lison.aec.model.Nemesis;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MarketCommandHandler extends BaseCommandHandler {
    private static Logger LOG = Logger.getLogger(MarketCommandHandler.class.getName());
    private static final String COMMAND_NAME = "market";
    private MarketDao marketDao;

    public MarketCommandHandler() {
        marketDao = new MarketDao();
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
                LOG.info("list of market...");

                if(!command.getParam().isEmpty()){
                    throw new IllegalArgumentException("market list doesn't support any params");
                }

                List<Market> markets = marketDao.findAll();
                markets.forEach(System.out::println);
                break;
            case ADD:
                try {
                    LOG.info("add market");

                    if (command.getParam().size() != 3) {
                        throw new IllegalArgumentException("wrong command format");
                    }

                    String marketName = command.getParam().get(0);
                    String marketType = command.getParam().get(1);
                    String marketCost = Integer.toString(Integer.parseInt(command.getParam().get(2)));

                    marketDao.add(new Market(marketName, marketType, marketCost));
                }catch (NumberFormatException e){
                    LOG.log(Level.WARNING, "Cost have to be an integer");
                }
                break;
            default: {
                throw new IllegalArgumentException(String.format("Unknown action: %s from command: %s",
                        command.getAction(), command.getCommand()));
            }
        }
    }
}

