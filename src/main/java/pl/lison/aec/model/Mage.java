package pl.lison.aec.model;

public class Mage {
    private String name;
    private Content content;

    public Mage() {
    }

    public Mage(String name, Content content) {
        this.name = name;
        this.content = content;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Content getContent() {
        return content;
    }

    public void setContent(Content content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Mage{" +
                "name='" + name + '\'' +
                ", content=" + content.getName() +
                '}';
    }
}
