package com.example.crud.controller;

import com.example.crud.model.BoardDto;
import com.example.crud.model.UserDto;
import com.example.crud.service.BoardService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("board")
public class BoardController {
    private final Logger logger = LoggerFactory.getLogger(BoardController.class);
    private final BoardService boardService;

    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @PostMapping
    public ResponseEntity<BoardDto> createBoard
            (@RequestBody BoardDto dto){
      return ResponseEntity.ok(boardService.create(dto));
    }

    @GetMapping
    public ResponseEntity<Collection<BoardDto>> readBoardAll(){
        return ResponseEntity.ok(boardService.readAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<BoardDto> readBoard
            (@PathVariable("id") Long id){
        BoardDto dto = boardService.read(id);

        //빈값인지 아닌지 검증
        if (dto == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(dto);
    }

    @PutMapping("{id}")
    public ResponseEntity<?> updateBoard
            (@PathVariable("id") Long id,
             @RequestBody BoardDto dto){

        //검증
        if (!boardService.update(id, dto))
        return ResponseEntity.notFound().build();

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteBoard
            (@PathVariable("id") Long id){

        if (!boardService.delete(id))
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok().build();
    }




}
