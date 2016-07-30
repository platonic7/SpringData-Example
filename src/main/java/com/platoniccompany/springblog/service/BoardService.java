package com.platoniccompany.springblog.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.platoniccompany.springblog.domain.Board;

public interface BoardService {

	// Insert
	void save(Board board);
	
	// List
	List<Board> findAll();
	
	// List Sort
	List<Board> findTopByOrderByTitleDesc();
	List<Board> findTopByOrderByIdDesc();
	
	// View Content
	Board findById(long boardId);
	
	// Delete
	void delete(long boardId);

	Page<Board> findAll(Pageable pageable);	
	
}
