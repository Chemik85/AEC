package pl.lison.aec.model;

public class Market {
    private String name;
    private String type;
    private int cost;
    private Content content;

    public Market() {
    }

    public Market(String marketName, String marketType, String marketCost, Content content) {
        this.name = marketName;
        this.type = marketType;
        this.cost = Integer.parseInt(marketCost);
        this.content = content;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public int getCost() {
        return cost;
    }
    public void setCost(int cost) {
        this.cost = cost;
    }

    public Content getContent() {
        return content;
    }

    public void setContent(Content content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Market{" +
                "name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", cost=" + cost +
                ", content=" + content.getName() +
                '}';
    }
}
