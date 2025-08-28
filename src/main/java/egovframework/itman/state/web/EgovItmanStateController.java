package egovframework.itman.state.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import egovframework.itman.group.service.EgovItmanGroupVO;
import egovframework.itman.state.service.EgovItmanStateService;
import egovframework.itman.state.service.EgovItmanStateVO;

@Controller
public class EgovItmanStateController {
	
	@Resource(name = "egovItmanStateService")
	EgovItmanStateService egovItmanStateService;

	//조회
	@RequestMapping("ingroup/aStatList.do")
	public String aStatList(
			EgovItmanGroupVO egovItmanGroupVO,
			EgovItmanStateVO egovItmanStateVO,
			HttpSession session,
			Model model,
			@RequestParam(value="search", required=false, defaultValue="all") String search,
			@RequestParam(value="like", required=false, defaultValue="") String like,
			@RequestParam(value="page", required=false, defaultValue="1") int page
			) {
	
		String groIdx = (String) session.getAttribute("groIdx");
		String memIdx = (String) session.getAttribute("memIdx");
		if (memIdx != null) {
			
			//페이징
			int pageResult = 10;                       
			int start = (page - 1) * pageResult;
			
			Map<String, Object> paramMap = new HashMap<>();
			paramMap.put("groIdx", groIdx);
			paramMap.put("search", search);
			paramMap.put("like", like);
			paramMap.put("start", start);
			paramMap.put("pageResult", pageResult);
			
			int count = egovItmanStateService.selectStateCount(paramMap);
			int totalPage = (int) Math.ceil((double) count / pageResult);
			
		//	리스트 조회 검색
			List<EgovItmanStateVO> stateList = egovItmanStateService.selectStateMap(paramMap);
			
			model.addAttribute("stateList", stateList);
			model.addAttribute("count", count);
			model.addAttribute("page", page);
			model.addAttribute("totalPage", totalPage);
			
// STA_YN가 뭐임???
			//count해서 result > 1 == ('Y' => 'N' 아님 'N' => 'Y') ???
			
			return "itman/ingroup/aStatList";
		} else {
			model.addAttribute("msg", "로그인 후 이용해주세요.");
			return "itman/user/login";
		}		
	}
	
	//추가
	@RequestMapping("/popup/contWriteItmState.do")
	public String contWriteItmState(
			HttpSession session,
			Model model
			) {
		
		String groIdx = (String) session.getAttribute("groIdx");
		String memIdx = (String) session.getAttribute("memIdx");
		if (memIdx != null) {
			model.addAttribute("groIdx", groIdx);
			return "itman/popup/contWriteItmState";
		}else {
			model.addAttribute("msg", "로그인 후 이용해주세요.");
			return "itman/common/msgAlertLogin";
		}
	}
	
	//추가Proc
	@RequestMapping("/popup/ppProcess/writeItmStateProc.do")
	public String writeItmStateProc(
			EgovItmanStateVO egovItmanStateVO,
			Model model,
			HttpSession session,
			@RequestParam("groIdx") String groIdx
			) {
		
		String memIdx = (String) session.getAttribute("memIdx");
		egovItmanStateVO.setRegIdx(memIdx);
		egovItmanStateVO.setGroIdx(groIdx);
		
		int result = egovItmanStateService.insertState(egovItmanStateVO);
		
		if (result == 1) {
			model.addAttribute("msg", "추가되었습니다.");
		} else if (result == 0){
			model.addAttribute("msg", "취소되었습니다.");
		}
		
		return "itman/common/msgAlertReload";
	}
	
	//수정
	@RequestMapping("popup/contWrite.do")
	public String contEditItmLocation(
			@RequestParam(name="idx") String idx,
			HttpSession session,
			Model model) {
		String groIdx = (String) session.getAttribute("groIdx");
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("groIdx", groIdx);
		paramMap.put("staIdx", idx);
		
	    EgovItmanStateVO stateOne = egovItmanStateService.selectStateOne(paramMap);
	    model.addAttribute("row", stateOne);
		return "itman/popup/contWrite";
	}
	
	//수정Proc
	@RequestMapping("/popup/pp_process/updateStatProc.do")
	public String updateStatProc(
			EgovItmanStateVO egovItmanStateVO,
			Model model,
			HttpSession session
			) {
		
		String memIdx = (String) session.getAttribute("memIdx");
		String groIdx = (String) session.getAttribute("groIdx");
		egovItmanStateVO.setModIdx(memIdx);
		egovItmanStateVO.setGroIdx(groIdx);
		
		int result = egovItmanStateService.updateState(egovItmanStateVO);
		
		if (result == 1) {
			model.addAttribute("msg", "자산 상태가 성공적으로 수정되었습니다!");
		} else if (result == 0){
			model.addAttribute("msg", "실패");
		}
		
		return "itman/common/msgAlertReload";
	}
	
	//삭제
	@RequestMapping(value="popup/contDel.do", params="id=staDel")
	public String staListDelete(
			@RequestParam(value="id", required=false) String id,
			@RequestParam("idx") String idx,
			HttpSession session,
			Model model) {
		String groIdx = (String) session.getAttribute("groIdx");
		model.addAttribute("groIdx", groIdx);
		model.addAttribute("idx", idx);
		model.addAttribute("id", id);
		return "itman/popup/listDelete";
		
	}
	
	//삭ㅈ제Proc
	@RequestMapping(value="pp_process/listDeleteProc.do", params="id=staDel")
	public String staListDeleteProc(
			@RequestParam("id") String id,
			@RequestParam("idx") String idx,
			@RequestParam(value="memo", required=false) String memo, 
			HttpSession session,
			Model model,
			EgovItmanStateVO egovItmanStateVO) {
		String groIdx = (String) session.getAttribute("groIdx");
		String memIdx = (String) session.getAttribute("memIdx");
		
		if("staDel".equals(id)){
			egovItmanStateVO.setDelIdx(memIdx);
			egovItmanStateVO.setGroIdx(groIdx);
			egovItmanStateVO.setStaIdx(idx);
			egovItmanStateVO.setStaNote(memo);
	    
			int result = egovItmanStateService.deleteState(egovItmanStateVO);
		
			if (result == 1) {
				model.addAttribute("msg", "삭제되었습니다.");
			} else if (result == 0){
				model.addAttribute("msg", "실패");
			}
			
			
			model.addAttribute("supIdx", idx);
			model.addAttribute("groIdx", groIdx);
			return "itman/common/msgAlertReload";
			} else {
				model.addAttribute("msg", "id값이 맞지 않습니다.");
				return "itman/common/msgAlertReload";
		}
	}
}
