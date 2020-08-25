package com.springbook.view;

import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import com.springbook.biz.user.UserService;
import com.springbook.biz.user.UserVO;

@Controller
public class UserController {
	@Autowired
	private UserService userService;

	@RequestMapping(value = "/register.do", method = RequestMethod.GET)
	public String register() {
		return "register.jsp";
	}

	/* 회원가입 처리 후 loginPage로 이동 */
	@RequestMapping(value = "/register.do", method = RequestMethod.POST)
	public String registerPro(UserVO vo) {
		System.out.println(vo);
		/* DB저장 */
		vo.setRole("user");
		userService.register(vo);
		return "redirect:login.do";
	}

	/* 회원정보 수정 페이지로 이동 */
	@RequestMapping(value = "/updateInfo.do", method = RequestMethod.GET)
	public String updateInfoForm(UserVO vo, Model model) {
		System.out.printf("수정: %s\n", vo);
		model.addAttribute("user", userService.getUser(vo));
		return "updateInfo.jsp";
	}

	/* 회원정보 수정처리 후 로그인 페이지로 이동 */
	@RequestMapping(value = "/updateInfo.do", method = RequestMethod.POST)
	public String updateInfoPro(UserVO vo, Model model) {
		/* Password가 맞으면 DB수정, 아니면 이전으로 이동 */
		UserVO user = userService.getUser(vo);
		if (user != null) {
			System.out.printf("수정: %s\n", vo);
			/* DB수정 처리 */
			userService.updateUser(vo);
			return "redirect:logout.do";
		}
		model.addAttribute("user", vo);
		return "updateInfo.jsp";
	}

	@ModelAttribute("roleMap")
	public Map<String, String> asd() {
		Map<String, String> roleMap = new HashMap<String, String>();
		roleMap = userService.getRoles();
		return roleMap;
	}

	@RequestMapping(value = "/deleteUser.do", method = RequestMethod.GET)
	public String deleteUser(@RequestParam("id") String id) {
		userService.deleteUser(id);
		return "redirect:logout.do";
	}

}