package com.platoniccompany.springblog.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.platoniccompany.springblog.domain.Board;
import com.platoniccompany.springblog.repository.BoardRepository;
import com.platoniccompany.springblog.service.BoardService;

@Service
public class BoardServiceImpl implements BoardService {
	@Autowired
	private BoardRepository boardRepository;
	
	// Insert
	@Transactional(readOnly=false, rollbackFor=Exception.class)
	@Override
	public void save(Board board) {
		boardRepository.saveAndFlush(board);
	}
	
	// List
	@Override
	public List<Board> findAll() {
		return boardRepository.findAll();
	}
	
	// List Sort
	@Override
	public List<Board> findTopByOrderByIdDesc() {
		return boardRepository.findTopByOrderByIdDesc();
	}	
	
	// List Sort
	@Override
	public List<Board> findTopByOrderByTitleDesc() {
		return boardRepository.findTopByOrderByTitleDesc();
	}
	
	// View Content
	@Transactional(readOnly=true, rollbackFor=Exception.class)
	@Override
	public Board findById(long boardId) {
		return boardRepository.findOne(boardId);
	}
	
	// Delete
	@Transactional(readOnly=false, rollbackFor=Exception.class)
	@Override
	public void delete(long boardId) {
		boardRepository.delete(boardId);
	}

	@Override
	public Page<Board> findAll(Pageable pageable) {
		return boardRepository.findAll(pageable);
	}
}
