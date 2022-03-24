package com.example.crud.service;

import com.example.crud.entity.BoardEntity;
import com.example.crud.model.BoardDto;

import java.util.Collection;

public interface BoardService {

    BoardDto create(BoardDto dto);
    Collection<BoardDto> readAll();
    BoardDto read(Long id);
    boolean update(Long id, BoardDto dto);
    boolean delete(Long id);


}
