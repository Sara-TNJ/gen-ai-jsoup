package com.att.poc.jsoup.dao;

public class InputField {
    private String id;
    private String name;
    private String type;
    private String placeholder;
    private String tag;

    public InputField() {
    }

    public InputField(String id, String name, String type, String placeholder, String tag) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.placeholder = placeholder;
        this.tag = tag;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getPlaceholder() {
        return placeholder;
    }

    public void setPlaceholder(String placeholder) {
        this.placeholder = placeholder;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    @Override
    public String toString() {
        return "InputDAO{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", placeholder='" + placeholder + '\'' +
                ", tag='" + tag + '\'' +
                '}';
    }
}