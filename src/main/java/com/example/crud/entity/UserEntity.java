package com.example.crud.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "user_community")
public class UserEntity {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password;

    @OneToMany(
            fetch = FetchType.LAZY,
            targetEntity = PostEntity.class,
            mappedBy = "writer"
    )
    private List<PostEntity> writtenPosts = new ArrayList<>();

    public UserEntity() {
    }

    public UserEntity(Long id, String username, String password, List<PostEntity> writtenPosts) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.writtenPosts = writtenPosts;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<PostEntity> getWrittenPosts() {
        return writtenPosts;
    }

    public void setWrittenPosts(List<PostEntity> writtenPosts) {
        this.writtenPosts = writtenPosts;
    }
}
