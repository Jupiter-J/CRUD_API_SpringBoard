package com.example.crud.service;

import com.example.crud.entity.PostEntity;
import com.example.crud.model.PostDto;

import java.util.Collection;

public interface PostService {

    PostDto create(Long boardId, PostDto dto);
    PostDto read(Long boardId, Long postId);
    Collection<PostDto> readAll(Long boardId);
    boolean update(Long boardId, PostDto dto, Long postId);
    boolean delete(Long boardId, Long postId);
}
