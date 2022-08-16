package com.example.petiteboard.domain;

import org.springframework.util.Assert;

import javax.persistence.*;
import java.sql.Time;

@Entity
@Table(name = "board")
public class Board  extends Time {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 10, nullable = false)
    private String writer;

    @Column(length = 100, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    @OneToOne(fetch = FetchType.LAZY)
    private User user;

    public Board(Long id, String title, String content, String writer, User user) {
        Assert.hasText(writer, "WRITER can not be empty!");
        Assert.hasText(title, "TITLE can not be empty!");
        Assert.hasText(content, "CONTENT can not be empty!");

        this.id = id;
        this.writer = writer;
        this.title = title;
        this.content = content;
        this.user = user;
    }

    public static BoardBuilder builder() {
        return new BoardBuilder();
    }

    public Long getId() {
        return this.id;
    }

    public String getWriter() {
        return this.writer;
    }

    public String getTitle() {
        return this.title;
    }

    public String getContent() {
        return this.content;
    }

    public User getUser() {
        return this.user;
    }

    public static class BoardBuilder {
        private Long id;
        private String title;
        private String content;
        private String writer;
        private User user;

        BoardBuilder() {
        }

        public BoardBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public BoardBuilder title(String title) {
            this.title = title;
            return this;
        }

        public BoardBuilder content(String content) {
            this.content = content;
            return this;
        }

        public BoardBuilder writer(String writer) {
            this.writer = writer;
            return this;
        }

        public BoardBuilder user(User user) {
            this.user = user;
            return this;
        }

        public Board build() {
            return new Board(id, title, content, writer, user);
        }

        public String toString() {
            return "Board.BoardBuilder(id=" + this.id + ", title=" + this.title + ", content=" + this.content + ", writer=" + this.writer + ", user=" + this.user + ")";
        }
    }
}
