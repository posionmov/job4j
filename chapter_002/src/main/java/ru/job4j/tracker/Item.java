package ru.job4j.tracker;

public class Item {

    private String id;
    private String name;
    private String description;
    private long created;
    private String[] comments;

    public Item(String name, String description, long created) {
        this.setName(name);
        this.setDescription(description);
        this.setCreated(created);
    }

    public void setID(String id) {
        this.id = id;
    }

    public String getId() {
        return this.id;
    }

    public String genName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return  this.description;
    }

    public void setCreated(long created) {
        this.created = created;
    }

    public long getCreated() {
        return this.created;
    }

}
