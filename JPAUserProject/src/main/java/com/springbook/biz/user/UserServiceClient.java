package com.springbook.biz.user;

import java.util.List;
import javax.persistence.*;

public class UserServiceClient {

	public static void main(String[] args) {
		/* Create JPAEntityManager */
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("JPAUserProject");
		EntityManager entityManager = entityManagerFactory.createEntityManager();

		EntityTransaction entityTransaction = entityManager.getTransaction();

		try {
			/* Transaction 시작 */
			entityTransaction.begin();

			/* 회원 등록 */
			User user = new User();
			user.setId("il");
			user.setName("일지매");
			user.setPassword("1234");
			user.setRole("user");
			entityManager.persist(user);

			/* 회원 조회 */
			User searchUser = new User();
			searchUser = entityManager.find(User.class, "lim");

			System.out.printf("회원 정보: %s\n", searchUser);

			/* 회원 리스트 출력 */
			String jpql = "SELECT u FROM User u ORDER BY u.id";
			List<User> userList = entityManager.createQuery(jpql, User.class).getResultList();
			for (User u : userList) {
				System.out.printf("==> %s\n", u);
			}

			/* 회원 수정 */
			User updateUser = entityManager.find(User.class, "hong");

			/* updateUser.setId("hong"); */
			/* updateUser.setName("홍길순"); */

			updateUser.setName("홍길뿡");

			entityManager.merge(updateUser);

			/* 회원 정보 삭제 */
			User removeUser = new User();
			removeUser.setId("kim");

			/* 회원 id로 조회 후 구해진 객체를 remove()_Method로 삭제 */
			entityManager.remove(entityManager.find(User.class, removeUser.getId()));

			entityTransaction.commit();
		} catch (Exception e) {
			e.getStackTrace();
			entityTransaction.rollback();/* 이전 상태로 되돌리기 */
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