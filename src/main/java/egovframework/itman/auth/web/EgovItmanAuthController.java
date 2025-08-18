package egovframework.itman.auth.web;

import static egovframework.itman.cmm.util.EgovItmanCmsCommonUtil.alertMove;

import java.text.SimpleDateFormat;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import egovframework.itman.member.service.EgovItmanMemberVO;

@Controller
public class EgovItmanAuthController {

    @RequestMapping("/user/login.do")
	public String userLogin() {
    	
		return "itman/user/login";
	}

	@RequestMapping("/user/join01.do")
	public String userJoin01() {
		
		return "itman/user/join01";
	}
	
	@RequestMapping("/user/join02.do")
	public String userJoin02() {
		
		return "itman/user/join02";
	}
	
	@RequestMapping("/user/verifyEmailCode.do")
	public String verifyEmailCode() {
		
		return "itman/user/verifyEmailCode";
	}
	
	@RequestMapping("/user/join03.do")
	public String userJoin03() {
		
		return "itman/user/join03";
	}
	
	@RequestMapping("/user/findEmail.do")
	public String findEmail() {
		
		return "itman/user/findEmail";
	}
	
	@RequestMapping("/user/compEmail.do")
	public String CompEmail() {
		
		return "itman/user/compEmail";
	}
	
	@RequestMapping("/user/findPass.do")
	public String findPass() {
		
		return "itman/user/findPass";
	}
	
	@RequestMapping("/user/changePassword.do")
	public String changePassword(
			EgovItmanMemberVO vo,
			HttpSession session,
			@RequestParam("verCode") String verCode,
            Model model
			) {
		
		String ecNum = (String) session.getAttribute("ecNum");
		String regDate = (String) session.getAttribute("regDate");
		
		try {
			// 인증번호 유효성 검사
			if(!verCode.equals(ecNum)) {
				return alertMove(model, "인증번호가 일치하지 않습니다.", "/user/verifyEmailCode.do");
			}
			
			// 인증시간 유효성 검사
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
		    long regdate = sdf.parse(regDate).getTime();
		    long now = System.currentTimeMillis();
		    if (now - regdate > 1000 * 60 * 5) { // 5분 초과
		    	return alertMove(model, "인증 유효 시간이 초과되었습니다.", "/user/verifyEmailCode.do");
		    }
		    
	    	return "itman/user/changePassword";
	    } catch (Exception e){
			e.printStackTrace();
			return alertMove(model, "인증에 실패했습니다. 잠시 후 다시 시도해주세요.", "/user/findPass.do"); // 오류 발생
		}
	}
	
	@RequestMapping("/user/changePass.do")
	public String changePass(
			RedirectAttributes rdab,
			HttpSession hsession
			) {
		String memIdx = (String) hsession.getAttribute("memIdx");
		if (memIdx == null) {
			rdab.addFlashAttribute("msg", "로그인 후 이용해주세요.");
			return "redirect:/user/login.do";
		} else {
			return "itman/user/changePass";
		}
	}
}
