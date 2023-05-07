package pl.lison.aec.dao;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import pl.lison.aec.model.Mage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MageDao {
    private static Logger LOG = Logger.getLogger(MageDao.class.getName());
    private ObjectMapper objectMapper;

    public MageDao() {
        this.objectMapper = new ObjectMapper();
    }

    private List<Mage> getMages() {
        try {
            return objectMapper.readValue(Files.readString(Paths.get("./mages.txt")), new TypeReference<>() {
            });
        } catch (IOException e) {
            LOG.log(Level.WARNING, "Error on getMages", e);
            return new ArrayList<>();
        }
    }

    public List<Mage> findAll() {
        return getMages();
    }

    public void add(Mage mage) {
        try {
            List<Mage> mages = getMages();
            mages.add(mage);

            Files.writeString(Paths.get("./mages.txt"), objectMapper.writeValueAsString(mages));

        } catch (IOException e) {
            LOG.log(Level.WARNING, "Error on addMages", e);
        }
    }
}
