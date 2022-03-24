package com.example.crud.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "board")
public class BoardEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    //1:N
    @OneToMany(
            fetch = FetchType.LAZY,
            targetEntity = PostEntity.class,
            mappedBy = "boardEntity"
    )
    private List<PostEntity> postEntityList = new ArrayList<>();

    public BoardEntity() {
    }

    public BoardEntity(Long id, String name, List<PostEntity> postEntityList) {
        this.id = id;
        this.name = name;
        this.postEntityList = postEntityList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<PostEntity> getPostEntityList() {
        return postEntityList;
    }

    public void setPostEntityList(List<PostEntity> postEntityList) {
        this.postEntityList = postEntityList;
    }
}
