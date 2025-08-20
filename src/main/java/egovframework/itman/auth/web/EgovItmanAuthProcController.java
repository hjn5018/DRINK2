package egovframework.itman.auth.web;

import static egovframework.itman.cmm.util.EgovItmanCmsCommonUtil.alertMove;

import java.text.SimpleDateFormat;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import egovframework.itman.auth.service.EgovItmanAuthProcService;
import egovframework.itman.auth.service.EgovitmanEmailCodeVO;
import egovframework.itman.member.service.EgovItmanMemberVO;

@Controller
public class EgovItmanAuthProcController {
	
	@Autowired
	private EgovItmanAuthProcService egovItmanAuthProcService;
	
	@Autowired
	private PasswordEncoder pEncoder;
	
	@RequestMapping("/user/sendEmailProc.do")
	public String sendEmailProc(
			EgovItmanMemberVO vo, 
			@RequestParam("mode") String mode,  
			@RequestParam(value="userPhone1", required=false) String p1,
            @RequestParam(value="userPhone2", required=false) String p2,
            @RequestParam(value="userPhone3", required=false) String p3,
            RedirectAttributes redirectAttributes,
            Model model,
            HttpSession session
            ) {
		
		// 번호 등록
	    if (vo.getMemTel()==null || vo.getMemTel().isEmpty()) {
            vo.setMemTel(p1 + p2 + p3);
        }

        // 1) 이메일 중복 체크
	    if (mode.equals("회원가입")) {
	        if (egovItmanAuthProcService.isEmailExists(vo.getMemMail())) {
	        	return alertMove(model, "중복된 이메일입니다.", "/user/join02.do");
	        }
	    }

        // 2) 서비스 호출: 인증번호 생성 → DB INSERT → 메일 전송
        EgovitmanEmailCodeVO procVO = egovItmanAuthProcService.sendEmailCode(vo, mode);

        // joinProc.do에서 가입을 위한 정보를 session에 보관한다.
        session.setAttribute("memName", vo.getMemName());
        session.setAttribute("memMail", vo.getMemMail());
    	session.setAttribute("memTel", vo.getMemTel());
        session.setAttribute("mode", mode);
        session.setAttribute("ecNum", procVO.getEcNum());
    	session.setAttribute("regDate", procVO.getRegDate());
        if (mode.equals("회원가입")) {
        	// 암호 bCrypt
        	String hashedMemPw= pEncoder.encode(vo.getMemPw());
        	session.setAttribute("hashedMemPw", hashedMemPw);
        }
        
        // 배포 시 삭제
        System.err.println("ecNum: " + procVO.getEcNum());
        System.err.println("ecNum: " + procVO.getEcNum());
        System.err.println("ecNum: " + procVO.getEcNum());
        System.err.println("ecNum: " + procVO.getEcNum());
        
        // 이메일 인증 코드 확인 페이지
        return "redirect:/user/verifyEmailCode.do";
	}
	
	@PostMapping("/user/idCheck.do")
	@ResponseBody
	public String idCheck(@RequestParam("email") String email) {
		
	    if (email == null || email.trim().isEmpty())
	    	return "2"; // 입력 안 함("이메일 형식에 맞게 입력해주세요.")
	    if (!email.matches("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$"))
	    	return "2"; // 이메일 형식 오류("이메일 형식에 맞게 입력해주세요.")

	    boolean exists = egovItmanAuthProcService.isEmailExists(email);
	    return exists ? "1" : "0"; // 1="이미 존재하는 이메일입니다.", 0="사용가능한 이메일입니다"
	}

	@RequestMapping("/user/joinProc.do")
	public String joinProc(
			EgovItmanMemberVO vo,
			RedirectAttributes rdab,
			@RequestParam("verCode") String verCode,
            Model model,
            HttpSession session
            ) {
		
		String mode = (String) session.getAttribute("mode");
		String ecNum = (String) session.getAttribute("ecNum");
		String regDate = (String) session.getAttribute("regDate");
		
		try {	
			// 인증번호 유효성 검사
			if(!verCode.equals(ecNum)) {
				return alertMove(model, "인증번호가 일치하지 않습니다.", "/user/verifyEmailCode.do");
			}
			
			// 인증 시간 유효성 검사
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
	        long regdate = sdf.parse(regDate).getTime();
	        long now = System.currentTimeMillis();
	        if (now - regdate > 1000 * 60 * 5) { // 5분 초과
	        	return alertMove(model, "인증 유효 시간이 초과되었습니다.", "/user/verifyEmailCode.do");
	        }
	        
			// sendEmailProc.do에서 session에 저장한 회원 정보를 불러온다.
			String memName = (String) session.getAttribute("memName"); 
			String memMail = (String) session.getAttribute("memMail");
			String hashedMemPw = (String) session.getAttribute("hashedMemPw");
			String memTel = (String) session.getAttribute("memTel");
			
			// insertUser service를 위해 vo를 설정한다.
			vo.setMemName(memName);
			vo.setMemMail(memMail);
			vo.setMemPw(hashedMemPw);
			vo.setMemTel(memTel);
			
			// 회원가입
			int result = egovItmanAuthProcService.insertUser(vo);
			
			// 사용한 정보를 초기화한다.
			session.removeAttribute("memName");
			session.removeAttribute("memMail");
			session.removeAttribute("hashedMemPw");
			session.removeAttribute("memTel");
			
			if(result > 0) {
				rdab.addFlashAttribute("memName", vo.getMemName());
				return "redirect:/user/join03.do"; // 가입 성공
			} else {
				return alertMove(model, "가입에 실패했습니다. 잠시 후 다시 시도해주세요.", "/user/join02.do"); // 가입 실패
			}
		} catch (Exception e){
			e.printStackTrace();
			return alertMove(model, "가입에 실패했습니다. 잠시 후 다시 시도해주세요.", "/user/join02.do"); // 오류 발생
		}
	}
	
	@RequestMapping("/user/loginProc.do")
	public String loginProc(
			@RequestParam String memMail,
			@RequestParam String memPw,
			HttpSession session,
			Model model) {
		
		EgovItmanMemberVO vo = egovItmanAuthProcService.loginProc(memMail, memPw);
		if(vo != null) {
			session.setAttribute("memIdx", vo.getMemIdx());
			session.setAttribute("memName", vo.getMemName());
			return "redirect:/index.do";
		} else {
			return alertMove(model, "아이디 또는 패스워드가 일치하지 않습니다.", "/user/login.do");
		}
	}
	
	@RequestMapping("/user/logoutProc.do")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/index.do";
	}
	
	@RequestMapping("/user/findEmailProc.do")
	public String findEmailProc(
			@RequestParam(value="memName") String memName,
			@RequestParam(value="userPhone1") String p1,
            @RequestParam(value="userPhone2") String p2,
            @RequestParam(value="userPhone3") String p3,
            RedirectAttributes rdab,
            Model model
			) {
		
		String memTel = (p1 + p2 + p3);
		EgovItmanMemberVO vo = egovItmanAuthProcService.findEmail(memName, memTel);
		
		if(vo != null) {
			rdab.addFlashAttribute("memMail", vo.getMemMail());
			return "redirect:/user/compEmail.do"; // 회원 조회 성공.
		} else {
			return alertMove(model, "입력한 정보의 회원이 존재하지 않습니다.", "/user/findEmail.do"); // 회원 조회 실패.
		}
	}
	
	@RequestMapping("/user/findPassProc.do")
	public String findPassProc(
			@RequestParam(value="memName") String memName,
			@RequestParam(value="memMail") String memMail,
			@RequestParam("mode") String mode,
			@RequestParam(value="userPhone1") String p1,
            @RequestParam(value="userPhone2") String p2,
            @RequestParam(value="userPhone3") String p3,
            RedirectAttributes rdab,
            Model model,
            HttpSession session) {
		
		String memTel = (p1 + p2 + p3);
		EgovItmanMemberVO vo = egovItmanAuthProcService.findMemIdx(memName, memMail, memTel);
		
		if(vo != null) {
			rdab.addAttribute("memName", memName);
			rdab.addAttribute("memMail", memMail);
			rdab.addAttribute("memTel", memTel);
			rdab.addAttribute("mode", mode);
			
			return "redirect:/user/sendEmailProc.do";
		} else {
			return alertMove(model, "일치하는 회원이 없습니다.", "/user/findPass.do");
		}
	}
	
	@RequestMapping("/user/changePasswordProc.do")
	public String changePassword(
	    @RequestParam("memPw") String memPw,
	    @RequestParam("memPwCh") String memPwCh,
	    HttpSession session,
	    RedirectAttributes rdab,
	    Model model) {

		if(!memPw.equals(memPwCh)) {
			return alertMove(model, "비밀번호가 일치하지 않습니다!", "/user/changePassword.do");
		}
		
		String memName = (String) session.getAttribute("memName");
		String memMail = (String) session.getAttribute("memMail");
		String memTel = (String) session.getAttribute("memTel");
		EgovItmanMemberVO resultVO = egovItmanAuthProcService.findMemIdx(memName, memMail, memTel);
		String memIdx = resultVO.getMemIdx();
		EgovItmanMemberVO vo = egovItmanAuthProcService.selectUserByIdx(memIdx);
		String beforeMemPw = vo.getMemPw();
		if(pEncoder.matches(memPw, beforeMemPw)) {
			return alertMove(model, "기존 비밀번호와 같은 비밀번호는 사용할 수 없습니다.", "/user/changePassword.do");
		}
		
		String hash = pEncoder.encode(memPw);
		int result = egovItmanAuthProcService.updateUserPw(memIdx, hash);
		
		session.removeAttribute("memName");
		session.removeAttribute("memMail");
		session.removeAttribute("memTel");
		session.removeAttribute("mode");
		session.removeAttribute("ecNum");
		session.removeAttribute("regDate");
		
		if(result > 0) {
			return alertMove(model, "비밀번호 변경이 완료되었습니다.", "/user/login.do");
		} else {
			return alertMove(model, "변경에 실패했습니다. 잠시 후 다시 시도해주세요", "/user/changePassword.do");
		}
	}
	
	@RequestMapping("/user/mypage/changePasswordProc.do")
	public String changePasswordProc(
			@RequestParam("beforePw") String beforePw,
			@RequestParam("memPw") String memPw,
			HttpSession session,
			RedirectAttributes rdab
			) {
		
		String memIdx = (String) session.getAttribute("memIdx");
		EgovItmanMemberVO vo = egovItmanAuthProcService.selectUserByIdx(memIdx);
		String MemPwInDB = vo.getMemPw();
		if(pEncoder.matches(beforePw, MemPwInDB)) {
				
			String hash = pEncoder.encode(memPw);
			int result = egovItmanAuthProcService.updateUserPw(memIdx, hash);
			
			if(result > 0) {
				rdab.addFlashAttribute("msg", "비밀번호 변경이 완료되었습니다.");
			}
			return "redirect:/user/login.do";
			
		} else {
			rdab.addFlashAttribute("msg", "현재 비밀번호가 맞지 않습니다.");
			return "redirect:/user/changePass.do";
		}
	}
	 
}
