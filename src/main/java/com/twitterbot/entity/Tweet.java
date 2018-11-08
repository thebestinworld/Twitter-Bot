package com.twitterbot.entity;


import javax.persistence.*;

@Entity
@Table(name = "tweets")
public class Tweet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(columnDefinition = "text", name = "content", nullable = false,length = 280)
    private String content;

    public Tweet() {

    }

    public Tweet(String content) {
        this.content = content;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
