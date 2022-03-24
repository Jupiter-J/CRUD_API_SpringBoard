package com.example.crud.controller;

import com.example.crud.model.PostDto;
import com.example.crud.service.PostService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("board/{boardId}/post")
public class PostController {
    private final Logger logger = LoggerFactory.getLogger(PostController.class);
    private final PostService postService;


    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping()
    public ResponseEntity<PostDto> createPost(
            @PathVariable("boardId") Long boardId,
            @RequestBody PostDto dto){
        PostDto result = this.postService.create(boardId, dto);
        return ResponseEntity.ok(result);
    }

    @GetMapping()
    public ResponseEntity<Collection<PostDto>> readPostAll(
            @PathVariable("boardId") Long boardId){
        Collection<PostDto> postList = this.postService.readAll(boardId);
        if (postList == null)
            return ResponseEntity.notFound().build();
        else
            return ResponseEntity.ok(postList);
    }

    @GetMapping("{postId}")
    public ResponseEntity<PostDto> readPost(
            @PathVariable("boardId") Long boardId,
            @RequestParam("postId") Long postId){
        PostDto postDto = this.postService.read(boardId, postId);
        if (postDto == null)
            return ResponseEntity.notFound().build();
        else
            return ResponseEntity.ok(postDto);
    }


    @PutMapping("{postId}")
    public ResponseEntity<?> updatePost(
            @PathVariable("boardId") Long boardId,
            @RequestParam("postId") Long postId,
            @RequestBody PostDto dto){
        if (!postService.update(boardId, dto, postId))
            return ResponseEntity.notFound().build();
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("{postId}")
    public ResponseEntity<?> deletePost(
            @PathVariable("boardId") Long boardId,
            @PathVariable("postId") Long postId){
        if (!postService.delete(boardId, postId))
            return ResponseEntity.notFound().build();
        return ResponseEntity.noContent().build();
    }




}
