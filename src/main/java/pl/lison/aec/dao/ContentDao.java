package pl.lison.aec.dao;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import pl.lison.aec.model.Content;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ContentDao {
    private static Logger LOG = Logger.getLogger(ContentDao.class.getName());
    private ObjectMapper objectMapper;

    public ContentDao() {
        this.objectMapper = new ObjectMapper();
    }

    private List<Content> getContents() {
        try {
            return objectMapper.readValue(Files.readString(Paths.get("./contents.txt")), new TypeReference<>() {
            });
        } catch (IOException e) {
            LOG.log(Level.WARNING, "Error on getcontent", e);
            return new ArrayList<>();
        }
    }

    public List<Content> findAll() {
        return getContents();
    }

    public void add(Content content) {
        try {
            List<Content> contents = getContents();
            contents.add(content);

            Files.writeString(Paths.get("./contents.txt"), objectMapper.writeValueAsString(contents));

        } catch (IOException e) {
            LOG.log(Level.WARNING, "Error on addContents", e);
        }
    }

    public Optional<Content> findOne(String contentName) {
        return getContents().stream()
                .filter(c -> c.getName().equals(contentName))
                .findAny();
    }
}
