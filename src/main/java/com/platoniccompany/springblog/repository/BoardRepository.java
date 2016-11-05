package com.platoniccompany.springblog.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.platoniccompany.springblog.domain.Board;

public interface BoardRepository extends JpaRepository<Board, Long> {

	List<Board> findTopByOrderByTitleDesc();

	List<Board> findTopByOrderByIdDesc();

}
