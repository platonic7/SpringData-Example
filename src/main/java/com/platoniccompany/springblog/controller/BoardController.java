package com.platoniccompany.springblog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.platoniccompany.springblog.domain.Board;
import com.platoniccompany.springblog.service.BoardService;

@Controller
public class BoardController {
	@Autowired 
	private BoardService boardService;
	
	static final String JSONVIEW = "jsonView";
	
	// List & Paging
	@RequestMapping(value = { "/", "/home" }, method = RequestMethod.GET)
	public String home(Model model,@PageableDefault(sort = { "id" }, direction = Direction.DESC, size = 4) Pageable pageable) {
		
		Page<Board> boardPage = boardService.findAll(pageable);
		
		int current = boardPage.getNumber() + 1;
		int begin = Math.max(1, current - 5);
		int end = Math.min(begin + 9, boardPage.getTotalPages());
		
		model.addAttribute("boardPage", boardPage);
	    model.addAttribute("beginIndex", begin);
	    model.addAttribute("endIndex", end);
	    model.addAttribute("currentIndex", current);
		
		return "home";
		
		}
	
	//GO to Write Page
	@RequestMapping(value = "/write", method = RequestMethod.GET)
	public String goWritePage() {
		return "write";
	}
	
	// Do Insert
	@RequestMapping(value  = "/insert", method = RequestMethod.POST)
	public String insert(Board board) {
		
		boardService.save(board);
		return "redirect:/home";
	}
	
	// View Content
	@RequestMapping(value = "/board/{boardId}/content", method = RequestMethod.GET)
	public String readPage(@PathVariable long boardId, Model model) {
		Board board = boardService.findById(boardId);
		
		// Count + 1
		board.setCount(board.getCount() + 1);
		boardService.save(board);
		
		model.addAttribute("board", board);
		return "content";
	}

	// Delete
	@RequestMapping(value = "/board/{boardId}/delete", method = RequestMethod.GET)
	public String deleteBoard(@PathVariable long boardId) {
		Board board = boardService.findById(boardId);
		long boardIdCheck = board.getId();
			
		if (board.getId().equals(boardId)) {
			boardService.delete(boardId);
			return "redirect:/home";
		}
		return "redirect:/board/"+boardIdCheck+"/content";
	}	
	
	// Update
	@RequestMapping(value = "/board/{boardId}/update", method = RequestMethod.GET)
	public String commentUpdatePage(@PathVariable long boardId, Model model) {
		Board boardUpdate = boardService.findById(boardId);
		long boardIdCheck = boardUpdate.getId();
		
		// Count + 1
		boardUpdate.setCount(boardUpdate.getCount());
		
		if (boardUpdate.getId().equals(boardId)) {
			model.addAttribute("boardUpdate", boardUpdate);	
			return "update";
		}
		return "redirect:/board/"+boardIdCheck+"/content";
	}
}
