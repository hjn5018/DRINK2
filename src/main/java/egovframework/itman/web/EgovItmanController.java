package egovframework.itman.web;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class EgovItmanController {

	@RequestMapping(value = "/index.do")
    public String index(HttpSession hsession, Model model) {
    	
		// 로그인 시 저장한 memMail을 토대로 isLogin을 확인한다.
    	boolean isLogin = hsession.getAttribute("memIdx") != null;
    	model.addAttribute("isLogin", isLogin);
    	
    	// isLogin이면 memName을 반환한다.
    	if (isLogin) {
            String memName = (String) hsession.getAttribute("memName");
            model.addAttribute("memName", memName);
        }
    	
        return "itman/index";
    }
}
