package pl.lison.aec.handlers;

import pl.lison.aec.dao.ContentDao;
import pl.lison.aec.dao.MarketDao;
import pl.lison.aec.input.UserInputCommand;
import pl.lison.aec.model.Content;
import pl.lison.aec.model.Mage;
import pl.lison.aec.model.Market;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MarketCommandHandler extends BaseCommandHandler {
    private static Logger LOG = Logger.getLogger(MarketCommandHandler.class.getName());
    private static final String COMMAND_NAME = "market";
    private MarketDao marketDao;
    private ContentDao contentDao;


    public MarketCommandHandler() {
        marketDao = new MarketDao();
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

                    if (command.getParam().size() != 4) {
                        throw new IllegalArgumentException("wrong command format");
                    }

                    String contentName = command.getParam().get(0);
                    String marketName = command.getParam().get(1);
                    String marketType = command.getParam().get(2);
                    String marketCost = Integer.toString(Integer.parseInt(command.getParam().get(3)));

                    Content content = contentDao.findOne(contentName)
                            .orElseThrow(()->new IllegalArgumentException("Content not found " + contentName));

                    marketDao.add(new Market(marketName, marketType, marketCost, content));
                }catch (NumberFormatException e){
                    LOG.log(Level.WARNING, "Cost have to be an integer");
                }
                break;
            case DRAW:
                LOG.info("Draw market.");
                if (command.getParam().size() != 0) {
                    throw new IllegalArgumentException("wrong command format");
                }


                List<Market> marketsList = marketDao.drawAFew();
                marketsList.forEach(System.out::println);
                break;
            default: {
                throw new IllegalArgumentException(String.format("Unknown action: %s from command: %s",
                        command.getAction(), command.getCommand()));
            }
        }
    }
}

