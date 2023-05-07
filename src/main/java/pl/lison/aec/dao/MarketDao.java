package pl.lison.aec.dao;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import pl.lison.aec.model.Market;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
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
}
