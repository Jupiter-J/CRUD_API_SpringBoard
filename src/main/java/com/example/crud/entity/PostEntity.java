package com.example.crud.entity;

import javax.persistence.*;

@Entity
@Table(name = "post")
public class PostEntity {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String content;

    //N:1 board
    @ManyToOne(
            fetch = FetchType.LAZY,
            targetEntity = BoardEntity.class
    )
    @JoinColumn(name = "board_id")
    private BoardEntity boardEntity;

    //N:1 user
    @ManyToOne(
            fetch = FetchType.LAZY,
            targetEntity = UserEntity.class
    )
    @JoinColumn(name = "user_id")
    private UserEntity writer;

    public PostEntity() {
    }



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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public UserEntity getWriter() {
        return writer;
    }

    public void setWriter(UserEntity writer) {
        this.writer = writer;
    }

    public BoardEntity getBoardEntity() {
        return boardEntity;
    }

    public void setBoardEntity(BoardEntity boardEntity) {
        this.boardEntity = boardEntity;
    }
}
