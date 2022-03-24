package com.example.crud.repository;

import com.example.crud.entity.BoardEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Map;

public interface BoardRepository extends CrudRepository<BoardEntity, Long> {


}
