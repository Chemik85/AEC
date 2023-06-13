package pl.lison.aec.dao;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import pl.lison.aec.model.Mage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MageDao implements Elements{
    private static Logger LOG = Logger.getLogger(MageDao.class.getName());
    private ObjectMapper objectMapper;


    public MageDao() {
        this.objectMapper = new ObjectMapper();
    }

    public List<Mage> getElements() {
        try {
            return objectMapper.readValue(Files.readString(Paths.get("./mages.txt")), new TypeReference<>() {
            });
        } catch (IOException e) {
            LOG.log(Level.WARNING, "Error on getMages", e);
            return new ArrayList<>();
        }
    }

    public List<Mage> findAll() {
        return getElements();
    }

    public void add(Mage mage) {
        try {
            List<Mage> mages = getElements();
            mages.add(mage);

            Files.writeString(Paths.get("./mages.txt"), objectMapper.writeValueAsString(mages));

        } catch (IOException e) {
            LOG.log(Level.WARNING, "Error on addMages", e);
        }
    }

    public List<Mage> drawAFew(int hawMany) {

        try {
            List<Mage> allMages = findAll();

            Random random = new Random();
            List<Mage> selectedMages = new ArrayList<>();

            for (int i = 0; i < hawMany; i++) {

                int randomIndex = random.nextInt(allMages.size());
                Mage mage = allMages.remove((randomIndex));
                selectedMages.add(mage);
            }

            Files.writeString(Paths.get("./magesList.txt"), objectMapper.writeValueAsString(selectedMages));

        } catch (IOException e) {
            LOG.log(Level.WARNING, "Error on draw");
        }
        return getDrawnElements();
    }

    public List<Mage> getDrawnElements() {
        try {
            return objectMapper.readValue(Files.readString(Paths.get("./magesList.txt")), new TypeReference<>() {
            });
        } catch (IOException e) {
            LOG.log(Level.WARNING, "Error on getMagesList", e);
            return new ArrayList<>();
        }
    }
}

