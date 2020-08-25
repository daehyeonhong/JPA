package com.springbook.biz.user;

import java.util.Map;

public interface UserService {
	public UserVO getUser(UserVO vo);/* 회원 정보 조회 */

	public void register(UserVO vo);/* 회원 등록 */

	public Map<String, String> getRoles();/* 권한 조회 */

	public void updateUser(UserVO vo);/* 회원 정보 업데이트 */

	public void deleteUser(String id);/* 회원 삭제 */
}