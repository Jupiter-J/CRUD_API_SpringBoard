package com.example.crud.service;

import com.example.crud.entity.BoardEntity;
import com.example.crud.model.BoardDto;
import com.example.crud.repository.BoardRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class JpaBoardService implements BoardService{
    private final Logger logger = LoggerFactory.getLogger(JpaBoardService.class);
    private final BoardRepository boardrepository;

    public JpaBoardService(BoardRepository boardrepository) {
        this.boardrepository = boardrepository;
    }

    @Override
    public BoardDto create(BoardDto dto) {
        BoardEntity boardEntity = new BoardEntity();
        boardEntity.setName(dto.getName());
        boardEntity = this.boardrepository.save(boardEntity);
        return new BoardDto(
                boardEntity.getId(), boardEntity.getName()
        );
    }

    @Override
    public Collection<BoardDto> readAll() {
        List<BoardDto> boardDtoList = new ArrayList<>();

        this.boardrepository.findAll().forEach(boardEntity ->
                boardDtoList.add( new BoardDto (boardEntity.getId(), boardEntity.getName())));
        return boardDtoList;
    }

    @Override
    public BoardDto read(Long id) {
        Optional<BoardEntity> boardEntityOptional = this.boardrepository.findById(id);
        if (boardEntityOptional.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        BoardEntity boardEntity = boardEntityOptional.get();

        return new BoardDto(
                boardEntity.getId(),
                boardEntity.getName()
        );
    }

    @Override
    public boolean update(Long id, BoardDto dto) {
        Optional<BoardEntity> boardEntityOptional= this.boardrepository.findById(id);

        if (boardEntityOptional.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        BoardEntity boardEntity = boardEntityOptional.get();
        //수정
        boardEntity.setName(dto.getName());

        this.boardrepository.save(boardEntity);
        return true;
    }

    @Override
    public boolean delete(Long id) {
        Optional<BoardEntity> boardEntityOptional = this.boardrepository.findById(id);

        if (boardEntityOptional.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        BoardEntity boardEntity = boardEntityOptional.get();
        this.boardrepository.delete(boardEntity);
        return true;
    }


}
