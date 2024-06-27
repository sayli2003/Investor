package com.example.demo.model;


import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class SearchForm {
    @Id
    private int id;

    private String query;

    public int getId() {
        return id;
    }

    public SearchForm() {
    }

    public void setId(int id) {
        this.id = id;
    }
    public SearchForm(String query) {
        this.query = query;
    }

    // Getters and Setters
    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }
}