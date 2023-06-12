package pl.lison.aec.dao;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import pl.lison.aec.model.Market;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MarketDao {
    private static Logger LOG = Logger.getLogger(MageDao.class.getName());
    private ObjectMapper objectMapper;


    public MarketDao() {
        this.objectMapper = new ObjectMapper();
    }

    private List<Market> getMarkets() {
        try {
            return objectMapper.readValue(Files.readString(Paths.get("./markets.txt")), new TypeReference<>() {
            });
        } catch (IOException e) {
            LOG.log(Level.WARNING, "Error on getMarkets", e);
            return new ArrayList<>();
        }
    }

    public List<Market> findAll() {
        return getMarkets();
    }

    public void add(Market market) {
        try {
            List<Market> markets = getMarkets();
            markets.add(market);

            Files.writeString(Paths.get("./markets.txt"), objectMapper.writeValueAsString(markets));

        } catch (IOException e) {
            LOG.log(Level.WARNING, "Error on addMarkets", e);
        }
    }

    public List<Market> drawAFew() {

        try {
            List<Market> allMarkets = findAll();

            List<Market> gemsArray = new ArrayList<>();
            List<Market> relictsArray = new ArrayList<>();
            List<Market> spellsArray = new ArrayList<>();

            for (Market market : allMarkets) {
                if (market.getType().equals("gem")) {
                    gemsArray.add(market);
                } else if (market.getType().equals("relict")) {
                    relictsArray.add(market);
                } else if (market.getType().equals("spell")) {
                    spellsArray.add(market);
                } else {
                    throw new IllegalArgumentException("Wrong type of market");

                }
            }

            Random random = new Random();
            List<Market> selectedMarkets = new ArrayList<>();

            for (int i = 0; i < 3; i++) {
                int randomIndex = random.nextInt(gemsArray.size());
                Market gem = gemsArray.remove((randomIndex));
                selectedMarkets.add(gem);
            }

            for (int i = 0; i < 2; i++) {
                int randomIndex = random.nextInt(relictsArray.size());
                Market relict = relictsArray.remove((randomIndex));
                selectedMarkets.add(relict);
            }

            for (int i = 0; i < 4; i++) {
                int randomIndex = random.nextInt(spellsArray.size());
                Market spell = spellsArray.remove((randomIndex));
                selectedMarkets.add(spell);
            }

            Files.writeString(Paths.get("./marketList.txt"), objectMapper.writeValueAsString(selectedMarkets));

        } catch (IOException e) {
            LOG.log(Level.WARNING, "Error on draw");
            return Collections.emptyList();
        }
        return getMarketList();
    }

    private List<Market> getMarketList() {
        try {
            return objectMapper.readValue(Files.readString(Paths.get("./marketList.txt")), new TypeReference<>() {
            });
        } catch (IOException e) {
            LOG.log(Level.WARNING, "Error on getMagesList", e);
            return new ArrayList<>();
        }
    }
}
