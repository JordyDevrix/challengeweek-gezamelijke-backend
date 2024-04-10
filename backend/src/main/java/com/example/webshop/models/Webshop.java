package com.example.webshop.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity(name = "webshops")
public class Webshop {
    @Id
    @GeneratedValue
    private long id;
    private String name;
    private String url;
    private String prefix;

    public Webshop(String name, String url, String prefix) {
        this.name = name;
        this.url = url;
        this.prefix = prefix;
    }

    public Webshop() {

    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String toString() {
        return "{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", url='" + url + '\'' +
                ", prefix='" + prefix + '\'' +
                '}';
    }
}
