package com.springbook.biz.board;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/*JPA Board_Test*/
public class BoardServiceClient {

	public static void main(String[] args) {
		/* EntityManager 생성 */
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("JPAProject");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		/* Transaction 생성 */
		EntityTransaction transaction = entityManager.getTransaction();
		/* Transaction 시작 */
		try {
			transaction.begin();/* Transaction 시작 */
			Board board = new Board();
			board.setTitle("JPA 제목");
			board.setWriter("관리자");
			board.setContent("JPA 글 등록 잘 되네요.");
			/* 글 등록 */
			entityManager.persist(board);/* Object 저장 */
			/* 글 목록 조회 */
			String jpql = "SELECT b FROM Board b ORDER BY b.seq DESC";
			List<Board> boardList = entityManager.createQuery(jpql, Board.class).getResultList();
			for (Board b : boardList) {
				System.out.printf("==>%s\n", b);
			}
			/* Transaction commit */
			transaction.commit();
		} catch (Exception e) {
			/* Transaction RollBack */
			transaction.rollback();
			e.printStackTrace();
		} finally {
			if (entityManager != null) {
				entityManager.close();/* EntityManager 해제 */
			}
		}
		if (entityManagerFactory != null) {
			entityManagerFactory.close();/* EntityManagerFactory 해제 */
		}
	}

}