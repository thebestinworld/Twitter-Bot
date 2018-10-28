package com.twitterbot.entity;


import javax.persistence.*;

@Entity
@Table(name = "tweets")
public class Tweet {

    private Integer id;
    private String content;

    public Tweet() {

    }

    public Tweet(String content) {
        this.content = content;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(columnDefinition = "text", name = "content", nullable = false)
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
