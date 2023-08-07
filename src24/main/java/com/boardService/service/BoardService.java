package com.boardService.service;

import java.util.List;

import com.boardService.domain.BoardDTO;
import com.boardService.paging.Criteria;

public interface BoardService {
	public boolean registerBoard(BoardDTO params);
	public BoardDTO getBoardDetail(Long idx);
	public boolean deleteBoard(Long idx);
	public List<BoardDTO> getBoardList(Criteria criteria);
}