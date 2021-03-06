package com.springbook.biz;

import javax.persistence.*;

public class User02ServiceClient {

	public static void main(String[] args) {
		/* EntityManagerFactory 생성 */
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("JPAUser02Project");

		/* EntityManager 생성 */
		EntityManager entityManager = entityManagerFactory.createEntityManager();

		/* Transaction 처리 */
		EntityTransaction entityTransaction = entityManager.getTransaction();

		try {
			/* Transaction 시작 */
			entityTransaction.begin();

			UserVO user = new UserVO();
			user.setId("hong");
			/* 회원 존재 여부 확인 */
			/* find(class, key) */
			user = entityManager.find(UserVO.class, user.getId());
			if (user == null) {
				System.out.println("사용 가능합니다.");
				user = new UserVO();
				user.setId("hong");
				user.setName("홍길동");
				user.setPassword("1234");
				user.setAddress("서울시");
				user.setRole("user");
				/* 저장 */
				entityManager.persist(user);
			} else {
				System.out.println("이미 존재하는 ID입니다.");
				user = entityManager.find(UserVO.class, user.getId());
				user.setPassword("3456");
				user.setAddress("수원시");

				/* update */
				entityManager.merge(user);
			}

			System.out.println(entityManager.find(UserVO.class, user.getId()));
			/* DB 반영 처리 */
			entityTransaction.commit();

		} catch (Exception e) {
			e.getStackTrace();
			/* RollBack */
			entityTransaction.rollback();
		} finally {
			if (entityManager != null) {
				/* EntityManager 자원 해제 */
				entityManager.close();
			}
		}
		if (entityManagerFactory != null) {
			/* EntityManagerFactory 자원 해제 */
			entityManagerFactory.close();
		}
	}

}