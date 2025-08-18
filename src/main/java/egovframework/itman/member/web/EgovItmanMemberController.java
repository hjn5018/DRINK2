package egovframework.itman.member.web;

import java.io.File;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import egovframework.itman.auth.service.EgovItmanAuthProcService;
import egovframework.itman.group.service.EgovItmanGroupService;
import egovframework.itman.group.service.EgovItmanGroupVO;
import egovframework.itman.member.service.EgovItmanMemberService;
import egovframework.itman.member.service.EgovItmanMemberVO;

@Controller
public class EgovItmanMemberController {

	@Autowired
	private EgovItmanAuthProcService egovItmanLoginProcService;
	
	@Autowired
	private EgovItmanGroupService egovItmanGroupService;
	
	@Autowired
	private EgovItmanMemberService egovItmanMemberService;
	
	@Autowired
	private PasswordEncoder pEncoder;
	
	@RequestMapping("/user/mypage.do")
 	public String mypage(HttpSession session, Model model) {
	 
     String memIdx = (String) session.getAttribute("memIdx");
        if (memIdx != null) {
            EgovItmanMemberVO vo = egovItmanLoginProcService.mypageSelectUser(memIdx);
            model.addAttribute("memName", vo.getMemName());
            model.addAttribute("memMail", vo.getMemMail());
            model.addAttribute("memTel", vo.getMemTel());
            
    		List<EgovItmanGroupVO> groupList = egovItmanGroupService.selectGroupList(vo);
            model.addAttribute("groupList", groupList);
            return "itman/user/mypage";
        } else {
        	model.addAttribute("msg", "로그인 후 이용해주세요.");
        }return "itman/user/login";
         
    }
	
	@RequestMapping("/popup/phoneEdit.do")
	public String phoneEdit(
			HttpSession hsession,
			Model model,
			RedirectAttributes rdab
			) {
		
		String memIdx = (String) hsession.getAttribute("memIdx");
		if(memIdx != null) {
			EgovItmanMemberVO vo = egovItmanMemberService.selectPhoneEdit(memIdx);
			String memTel = vo.getMemTel();
			String tel = memTel == null ? "" : memTel.replaceAll("-", "");
			
			String p1 = "", p2 = "", p3 = "";
		       if (tel.length() == 11) {
		           p1 = tel.substring(0, 3);
		           p2 = tel.substring(3, 7);
		           p3 = tel.substring(7, 11);
		       } else if (tel.length() == 10) {
		           p1 = tel.substring(0, 3);
		           p2 = tel.substring(3, 6);
		           p3 = tel.substring(6, 10);
		       }
		       model.addAttribute("userPhone1", p1);
		       model.addAttribute("userPhone2", p2);
		       model.addAttribute("userPhone3", p3);
		       return "itman/popup/phoneEdit";
		}	else {
				rdab.addFlashAttribute("msg", "로그인 후 이용해주세요");
		            return "redirect:/user/login.do";
		        
		}
		
	}	
	
	@RequestMapping("/popup/phoneEditProc.do")
	public String phoneEditProc(
			@RequestParam("userPhone1") String p1,
            @RequestParam("userPhone2") String p2,
            @RequestParam("userPhone3") String p3,
            HttpSession hsession,
			Model model,
			RedirectAttributes rdab
			) {
		
	     String memIdx = (String) hsession.getAttribute("memIdx");
	
	        EgovItmanMemberVO vo = new EgovItmanMemberVO();
	        vo.setMemIdx(memIdx);
	        EgovItmanMemberVO memberVO = egovItmanMemberService.selectMember(vo);

	        String memTel = (p1 + p2 + p3); //.replaceAll("-", "")
	        memberVO.setMemTel(memTel);

	        egovItmanMemberService.updateMember(memberVO);
	        
	        rdab.addFlashAttribute("msg", "수정이 완료되었습니다.");
	        return "redirect:/popup/phoneEdit.do";
	        		
	}
	
	@RequestMapping("/user/privacy.do")
	public String privacy(Model model, HttpSession hsession) {
		String memIdx = (String) hsession.getAttribute("memIdx");
        if (memIdx != null) {
            EgovItmanMemberVO vo = egovItmanLoginProcService.mypageSelectUser(memIdx);            
    		List<EgovItmanGroupVO> groupList = egovItmanGroupService.selectGroupList(vo);
            model.addAttribute("groupList", groupList);
        }
		return "itman/user/privacy";
	}
	
	@RequestMapping("/user/accDel.do")
	public String accDel(
			HttpSession session,
			Model model,
			RedirectAttributes rdab) {
		String memIdx = (String) session.getAttribute("memIdx");
		EgovItmanMemberVO vo = egovItmanLoginProcService.selectUserByIdx(memIdx);
		if (memIdx != null) {
		model.addAttribute("memName", vo.getMemName());
		return "itman/user/accDel";
		} else {
			rdab.addFlashAttribute("msg", "로그인 후 이용해주세요.");
			return "redirect:/user/login.do";
		}
	}
	
	@RequestMapping("/user/accDelProc.do")
	public String accDelProc(
			@RequestParam("memPw") String memPw,
			HttpSession session,
			RedirectAttributes rdab) {
		String memIdx = (String) session.getAttribute("memIdx");
		EgovItmanMemberVO vo = egovItmanLoginProcService.selectUserByIdx(memIdx);
		String MemPwInDB = vo.getMemPw();
		if(pEncoder.matches(memPw, MemPwInDB)) {
			
			int result = egovItmanMemberService.updateDelYn(memIdx);//del yn
			
			if(result > 0) {
				rdab.addFlashAttribute("msg", "계정 탈퇴되었습니다.");// 삭제 메세지
			}
			return "redirect:/user/logoutProc.do";
		} else {
			rdab.addFlashAttribute("msg", "패스워드가 일치하지 않습니다.");//패스워드 일치하지 않는다는 메세
			return "redirect:/user/accDel.do";
		} 
	} 
	
	@RequestMapping("/user/myGroup.do")
	public String myGroup(Model model, HttpSession hsession) {
		String memIdx = (String) hsession.getAttribute("memIdx");
        if (memIdx != null) {
            EgovItmanMemberVO vo = egovItmanLoginProcService.mypageSelectUser(memIdx);            
    		List<EgovItmanGroupVO> groupList = egovItmanGroupService.selectGroupList(vo);
            model.addAttribute("groupList", groupList);
        }
		return "itman/user/myGroup";
	}
	
	@RequestMapping("/user/groupWrite.do")
	public String groupWrite(@RequestParam("idx") String groIdx, Model model) throws Exception {
		EgovItmanGroupVO valueRow = egovItmanMemberService.selectGroupByIdx(groIdx);
        model.addAttribute("valueRow", valueRow);
        return "itman/user/groupWrite";
	}
	
	@RequestMapping("/user/updateGroupProc.do")
	public String updateGroupProc(
			EgovItmanGroupVO egovItmanGroupVO,
			HttpSession session,
			Model model,
			@RequestParam("image") MultipartFile file,
            HttpServletRequest request,
            RedirectAttributes rdab) throws Exception{
		
		String savedFileName = "";

		if (!file.isEmpty()) {
			// 이미지 업로드 경로: C:/eGovFrameDev-4.3.1-64bit/workspace-egov/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/DRINK/upload/group
	        String uploadPath = request.getServletContext().getRealPath("/upload/group");
	        File uploadDir = new File(uploadPath);
	        if (!uploadDir.exists()) uploadDir.mkdirs();

	        String originalFilename = file.getOriginalFilename();
	        savedFileName = UUID.randomUUID().toString() + "_" + originalFilename;
	        File target = new File(uploadPath, savedFileName);
	        file.transferTo(target);
	    }
		
		egovItmanGroupVO.setGroImg(savedFileName); // DB에는 파일명만 저장
		
		String groOwnerIDx = (String) session.getAttribute("memIdx");
		egovItmanGroupVO.setGroOwnerIdx(groOwnerIDx);
		egovItmanGroupVO.setRegIdx(groOwnerIDx);
		String ip = EgovItmanIpUtil.getClientIP(request);
		egovItmanGroupVO.setModIp(ip);

		int result = egovItmanMemberService.updateGroup(egovItmanGroupVO);
		model.addAttribute("result", result);
				
		if (result == 1) {
			model.addAttribute("msg", "그룹이 성공적으로 수정되었습니다!");
		} else if (result == 0){
			model.addAttribute("msg", "수정이 실패되었습니다.");
		}
		
		return "itman/common/msgAlertReload";
		
	}
	
	@RequestMapping("/user/groupDel.do")
	public String groupDel(@RequestParam("idx") String groIdx, Model model) {
	    model.addAttribute("groIdx", groIdx);
	    return "itman/user/groupDel";
	}
	
	@RequestMapping("/user/groupDelProc.do")
	public String groupDelProc(
			@RequestParam("memPw") String memPw,
			@RequestParam("groIdx") String groIdx,
			HttpSession session,
			RedirectAttributes rdab,
			Model model,
			HttpServletRequest request) {
		String memIdx = (String) session.getAttribute("memIdx");
		EgovItmanMemberVO vo = egovItmanLoginProcService.selectUserByIdx(memIdx);
		String MemPwInDB = vo.getMemPw();
		if(pEncoder.matches(memPw, MemPwInDB)) {
			EgovItmanGroupVO groupVO = new EgovItmanGroupVO();
		    groupVO.setGroIdx(groIdx);
		    groupVO.setDelIdx(memIdx);
		    
		    String ip = EgovItmanIpUtil.getClientIP(request);
			groupVO.setDelIp(ip);
			
		    int result = egovItmanMemberService.updateGroupDelYn(groupVO);//del yn
		    
			if(result > 0) {
				model.addAttribute("msg", "삭제되었습니다.");
			}
			return "itman/common/msgAlertReload";
		} else {
			rdab.addFlashAttribute("msg", "비밀번호가 일치하지 않습니다!");//패스워드 일치하지 않는다는 메세
			return "redirect:/user/groupDel.do?idx=" + groIdx;
		}  
	}
		
	
}
	
