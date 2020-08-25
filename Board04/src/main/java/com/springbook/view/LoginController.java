package com.springbook.view;

import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.springbook.biz.user.UserService;
import com.springbook.biz.user.UserVO;

@Controller
public class LoginController {

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/login.do", method = RequestMethod.GET) /* HandlerMapping */
	public String loginView(@ModelAttribute("user") UserVO vo) {/* Model.addAttribute("user", vo) */
		System.out.println("==> 로그인 화면으로 이동");
		return "login.jsp";
	}

	/* ID, Password 값이 설정된 UserVO가 넘어 옴 */
	@RequestMapping(value = "/login.do", method = RequestMethod.POST)
	public String login(UserVO vo, /* UserDAO userDAO, */ HttpSession session) {
		System.out.printf("ID: %s\nPassword: %s\n", vo.getId(), vo.getPassword());
		if (vo.getId() == null || vo.getId().equals("")) {
			throw new IllegalArgumentException("아이디는 반드시 입력해야 합니다.");
		}
		/* UserVO user = userDAO.getUser(vo); */
		UserVO user = userService.getUser(vo);
		if (user != null) {
			session.setAttribute("userName", user.getName());
			System.out.printf("userName: %s\n", user.getName());
			return "getBoardList.do";
		}
		return "login.jsp";
	}

	@RequestMapping("logout.do")
	public String logout(HttpSession session) {
		session.invalidate();
		return "login.jsp";
	}
}