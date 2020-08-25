package com.springbook.biz.user.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.springbook.biz.user.RoleVO;
import com.springbook.biz.user.UserVO;

@Repository("userDAO")
public class UserDAOMybatis {

	@Autowired
	private SqlSessionTemplate mybatis;

	public UserVO getUser(UserVO vo) {
		System.out.println("==> Mybatis로 getUser() 기능 처리");
		/*
		 * UserVO user = new UserVO(); user = (UserVO)
		 * mybatis.selectOne("UserDAO.getUser",vo); return user;
		 */
		return mybatis.selectOne("UserDAO.getUser", vo);
	}

	public void register(UserVO vo) {
		System.out.println("==> Mybatis로 register() 기능 처리");
		mybatis.insert("UserDAO.registerUser", vo);
	}

	public Map<String, String> getRoles() {
		System.out.println("==> Mybatis로 getRoles() 기능 처리");
		Map<String, String> map = new HashMap<String, String>();
		List<RoleVO> list = mybatis.selectList("UserDAO.getRoles");
		for (RoleVO vo : list) {
			System.out.printf("%s: %s\n", vo.getRole(), vo.getRoleName());
		}
		for (RoleVO vo : list) {
			map.put(vo.getRole(), vo.getRoleName());
		}
		return map;
	}

	public void updateUser(UserVO vo) {
		System.out.println("==> Mybatis로 updateUser() 기능 처리");
		mybatis.update("UserDAO.updateUser", vo);
	}

	public void deleteUser(String id) {
		System.out.println("==> Mybatis로 deleteUser() 기능 처리");
		mybatis.delete("UserDAO.deleteUser", id);
	}

}