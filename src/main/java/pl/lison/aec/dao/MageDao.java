package pl.lison.aec.dao;

import pl.lison.aec.model.Mage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class MageDao {
    public List<Mage> findAll() {
        try {
            List<String> lines = Files.readAllLines(Paths.get("./mages.txt"));
            List<Mage> mages = new ArrayList<>();
            for (String line : lines) {
                mages.add(new Mage(line));
            }

            return mages;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

    }

    public void add(Mage mage) {
        try {
            List<String> lines = Files.readAllLines(Paths.get("./mages.txt"));
        lines.add(mage.getName());

        Files.writeString(Paths.get("./mages.txt"), String.join("\n", lines));
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
