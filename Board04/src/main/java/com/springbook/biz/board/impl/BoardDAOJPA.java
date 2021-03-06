package com.springbook.biz.board.impl;

import java.util.List;
import javax.persistence.*;
import org.springframework.stereotype.Repository;
import com.springbook.biz.BoardVO;

@Repository
public class BoardDAOJPA {

	@PersistenceContext
	private EntityManager entityManager;

	public void insertBoard(BoardVO vo) {
		System.out.println("==> JPA로 insertBoard() 기능 처리");
		entityManager.persist(vo);
	}

	public void updateBoard(BoardVO vo) {
		System.out.println("==> JPA로 updateBoard() 기능 처리");
		BoardVO updateBoard = entityManager.find(BoardVO.class, vo.getSeq());
		updateBoard.setTitle(vo.getTitle());
		updateBoard.setContent(vo.getContent());
		updateBoard.setImages(vo.getImages());
		entityManager.merge(updateBoard);
	}

	public void deleteBoard(BoardVO vo) {
		System.out.println("==> JPA로 deleteBoard() 기능 처리");
		entityManager.remove(entityManager.find(BoardVO.class, vo.getSeq()));
	}

	public BoardVO getBoard(BoardVO vo) {
		System.out.println("==> JPA로 getBoard() 기능 처리");
		/* updateBoardCount(vo); */
		return entityManager.find(BoardVO.class, vo.getSeq());
	}

	public List<BoardVO> getBoardList(BoardVO vo) {
		System.out.println("==> JPA로 getBoardList() 기능 처리");
		String spql = "FROM BoardVO b ORDER BY b.seq DESC";
		return entityManager.createQuery(spql).getResultList();
	}

	public void updateBoardCount(BoardVO vo) {
		System.out.println("==> JPA로 updateBoardCount() 기능 처리");
		BoardVO updateBoardCount = entityManager.find(BoardVO.class, vo.getSeq());
		updateBoardCount.setCnt(updateBoardCount.getCnt() + 1);
		entityManager.merge(updateBoardCount);
		entityManager.flush();
	}

}