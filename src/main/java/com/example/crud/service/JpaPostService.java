package com.example.crud.service;

import com.example.crud.entity.BoardEntity;
import com.example.crud.entity.PostEntity;
import com.example.crud.entity.UserEntity;
import com.example.crud.model.PostDto;
import com.example.crud.repository.BoardRepository;
import com.example.crud.repository.PostRepository;
import com.example.crud.repository.UserRepository;
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
public class JpaPostService implements PostService{
    private final Logger logger = LoggerFactory.getLogger(JpaPostService.class);
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final BoardRepository boardRepository;

    public JpaPostService(PostRepository postRepository, UserRepository userRepository, BoardRepository boardRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
        this.boardRepository = boardRepository;
    }

    @Override
    public PostDto create(Long boardId, PostDto postDto) {

        //board, user fk 확인
        if (!this.boardRepository.existsById(boardId))
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        if (!this.userRepository.existsById(postDto.getUserId()))
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);


        BoardEntity boardEntity = this.boardRepository.findById(boardId).get();
        UserEntity userEntity = this.userRepository.findById(postDto.getUserId()).get();

        PostEntity postEntity = new PostEntity();
        postEntity.setTitle(postDto.getTitle());
        postEntity.setContent(postDto.getContent());

        postEntity.setWriter(userEntity);
        postEntity.setBoardEntity(boardEntity);
        postEntity = this.postRepository.save(postEntity);


        return new PostDto(
                postEntity.getId(),
                postEntity.getTitle(),
                postEntity.getContent(),
                postEntity.getWriter().getId()
        );
    }



    @Override
    public Collection<PostDto> readAll(Long boardId) {
        Optional<BoardEntity> boardEntityOptional = this.boardRepository.findById(boardId);
        BoardEntity boardEntity = boardEntityOptional.get();
        List<PostDto> postDtoList = new ArrayList<>();
        boardEntity.getPostEntityList().forEach(postEntity ->
                postDtoList.add(new PostDto(
                        postEntity.getId(),
                        postEntity.getTitle(),
                        postEntity.getContent(),
                        postEntity.getWriter().getId()
                )));

        return postDtoList;
    }

    @Override
    public PostDto read(Long boardId, Long postId) {
        //postId 확인
        if (!this.postRepository.existsById(postId))
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        PostEntity postEntity = this.postRepository.findById(postId).get();
                //fk와 가져온 값 비교
        if (!postEntity.getBoardEntity().getId().equals(boardId))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);

        return new PostDto(
                postEntity.getId(),
                postEntity.getTitle(),
                postEntity.getContent(),
                postEntity.getWriter().getId()

        );
    }

    @Override
    public boolean update(Long boardId, PostDto postDto, Long postId) {

        if (!this.postRepository.existsById(postId))
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
                        //찾은값을 저장
        PostEntity postEntity = this.postRepository.findById(postId).get();

                                                //fk 확인
        if (!postEntity.getBoardEntity().getId().equals(boardId))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);

                                                //fk 확인
        if (!postEntity.getWriter().getId().equals(postDto.getUserId()))
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);

        postEntity.setTitle(
                postDto.getTitle() == null ? postEntity.getTitle() : postDto.getTitle());
        postEntity.setContent(
                postDto.getContent() == null ? postEntity.getContent() : postDto.getContent());
        this.postRepository.save(postEntity);

        return true;

    }

    @Override
    public boolean delete(Long boardId, Long postId) {

        if (!this.postRepository.existsById(postId))
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        PostEntity postEntity = this.postRepository.findById(postId).get();

        if (!postEntity.getBoardEntity().getId().equals(boardId))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        this.postRepository.deleteById(postId);

        return true;
    }
}
