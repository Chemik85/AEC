package pl.lison.aec.dao;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import pl.lison.aec.model.Nemesis;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class NemesisDao {
    private static Logger LOG = Logger.getLogger(NemesisDao.class.getName());
    private ObjectMapper objectMapper;

    public NemesisDao() {
        this.objectMapper = new ObjectMapper();
    }

    private List<Nemesis> getNemeses() {
        try {
            return objectMapper.readValue(Files.readString(Paths.get("./nemesis.txt")), new TypeReference<>() {
            });
        } catch (IOException e) {
            LOG.log(Level.WARNING, "Error on getNemesis", e);
            return new ArrayList<>();
        }
    }

    public List<Nemesis> findAll() {
        return getNemeses();
    }

    public void add(Nemesis nemesis) {
        try {
            List<Nemesis> nemeses = getNemeses();
            nemeses.add(nemesis);

            Files.writeString(Paths.get("./nemesis.txt"), objectMapper.writeValueAsString(nemeses));

        } catch (IOException e) {
            LOG.log(Level.WARNING, "Error on addNemesis", e);
        }
    }

    public List<Nemesis> drawOne() {
        /**
         * At the moment, single nemesis randomization is implemented.
         * Method prepared for drawing more nemesis as part of the "expedition" from Aeon's End New Ages
         */
        try {
            List<Nemesis> allNemesis = findAll();

            Random random = new Random();
            List<Nemesis> selectedNemesis = new ArrayList<>();

            for (int i = 0; i < 1; i++) {
                int randomIndex = random.nextInt(allNemesis.size());
                Nemesis nemesis = allNemesis.remove((randomIndex));
                selectedNemesis.add(nemesis);
            }

            Files.writeString(Paths.get("./nemesisList.txt"), objectMapper.writeValueAsString(selectedNemesis));
        } catch (IOException e) {
            LOG.log(Level.WARNING, "Error on draw");
        }
        return getNemesisList();
    }

    private ArrayList<Nemesis> getNemesisList() {
        try {
            return objectMapper.readValue(Files.readString(Paths.get("./nemesisList.txt")), new TypeReference<>() {
            });
        } catch (IOException e) {
            LOG.log(Level.WARNING, "Error on getNemesisList", e);
            return new ArrayList<>();
        }
    }
}
