package com.example.demo.model;

import javax.persistence.*;

@Entity
public class News {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(length = 10000)
    String title;

    @Column(length = 10000)
    String content;

    //overall_sentiment_label
    String sentiment;

    public News(String title, String content, String sentiment) {
        this.title = title;
        this.content = content;
        this.sentiment = sentiment;
    }
    public News() {}

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSentiment() {
        return sentiment;
    }

    public void setSentiment(String sentiment) {
        this.sentiment = sentiment;
    }
}
