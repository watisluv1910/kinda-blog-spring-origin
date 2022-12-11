package com.myFirstSpringProgect.kindaBlog.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity // Entity означает, что класс Post является моделью, которая отвечает за табличку Post в БД
public class Post {

    public Post(String title, String announcement, String fullText) {
        this.title = title;
        this.announcement = announcement;
        this.fullText = fullText;
    }

    public Post() {}

    @Id // Важно, чтобы аннотация была из javax.persistence
    @GeneratedValue(strategy = GenerationType.AUTO) // Позволяет генерировать новое значение внутри поля каждый раз при добавлении новой записи
    private Long id; // Уникальный id-идентификатор статьи

    private String title, announcement, fullText;
    private int views;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAnnouncement() {
        return announcement;
    }

    public void setAnnouncement(String announcement) {
        this.announcement = announcement;
    }

    public String getFullText() {
        return fullText;
    }

    public void setFullText(String fullText) {
        this.fullText = fullText;
    }

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }
}
