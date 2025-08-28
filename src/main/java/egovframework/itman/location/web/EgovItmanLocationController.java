package egovframework.itman.location.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.egovframe.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import egovframework.itman.group.service.EgovItmanGroupService;
import egovframework.itman.group.service.EgovItmanGroupVO;
import egovframework.itman.location.service.EgovItmanLocationService;
import egovframework.itman.location.service.EgovItmanLocationVO;
import egovframework.itman.member.service.EgovItmanMemberVO;

@Controller
public class EgovItmanLocationController {
	
	@Resource(name = "egovItmanLocationService")
	EgovItmanLocationService egovItmanLocationService;
	
	@Resource(name = "egovItmanGroupService")
	EgovItmanGroupService egovItmanGroupService;
	
	//조회
	@RequestMapping("ingroup/locList.do")
	public String locList(EgovItmanGroupVO egovItmanGroupVO,
			EgovItmanMemberVO egovItmanMemberVO,
			HttpSession session,
			Model model,
			@RequestParam(value="search", required=false, defaultValue="all") String search,
			@RequestParam(value="like", required=false, defaultValue="") String like,
			@RequestParam(value="page", required=false, defaultValue="1") int page
			) {
		
		String memIdx = (String) session.getAttribute("memIdx");
		String groIdx = (String) session.getAttribute("groIdx");
		if (memIdx != null) {
			egovItmanGroupVO.setGroIdx(groIdx);
			EgovItmanGroupVO groupVO = egovItmanGroupService.selectGroup(egovItmanGroupVO);
			model.addAttribute("groupVO", groupVO);
			
		
			egovItmanMemberVO.setMemIdx(memIdx);
			List<EgovItmanGroupVO> groupList = egovItmanGroupService.selectGroupList(egovItmanMemberVO);
			model.addAttribute("groupList", groupList);
		
		//페이징
			int pageResult = 10;                       
			int start = (page - 1) * pageResult;
			
			Map<String, Object> paramMap = new HashMap<>();
			paramMap.put("groIdx", groIdx);
			paramMap.put("search", search);
			paramMap.put("like", like);
			paramMap.put("start", start);
			paramMap.put("pageResult", pageResult);
			
			int count = egovItmanLocationService.selectLocationCount(paramMap);
			int totalPage = (int) Math.ceil((double) count / pageResult);
			
		//	리스트 조회 검색
			List<EgovItmanLocationVO> locationList = egovItmanLocationService.selectLocationMap(paramMap);
			
			model.addAttribute("locationList", locationList);
			model.addAttribute("count", count);
			model.addAttribute("page", page);
			model.addAttribute("pageResult", pageResult);
			model.addAttribute("totalPage", totalPage);
			model.addAttribute("search", search);
			model.addAttribute("like", like);
			
			
			return "itman/ingroup/locList";
		} else {
			model.addAttribute("msg", "로그인 후 이용해주세요.");
			return "itman/user/login";
		}
	}
	
	//추가
	@RequestMapping("/popup/contWriteItmLocation.do")
	public String contWriteItmLocation(
			HttpSession session,
			Model model
			) {
		
		String groIdx = (String) session.getAttribute("groIdx");
		String memIdx = (String) session.getAttribute("memIdx"); 
		if (memIdx != null) {
			model.addAttribute("groIdx", groIdx);
			return "itman/popup/contWriteItmLocation";
		} else {
			model.addAttribute("msg", "로그인 후 이용해주세요.");
			return "itman/common/msgAlertLogin";
		}
		
	}
	
	//추가Proc
	@RequestMapping("/popup/ppProcess/writeItmLocationProc.do")
	public String writeItmLocationProc(
			EgovItmanLocationVO egovItmanLocationVO,
			Model model,
			HttpSession session,
			@RequestParam("groIdx") String groIdx
			) {
		
		String memIdx = (String) session.getAttribute("memIdx");
		egovItmanLocationVO.setRegIdx(memIdx);
		egovItmanLocationVO.setGroIdx(groIdx);
		
		int result = egovItmanLocationService.insertLocation(egovItmanLocationVO);
		
		if (result == 1) {
			model.addAttribute("msg", "추가되었습니다.");
		} else if (result == 0){
			model.addAttribute("msg", "취소되었습니다.");
		}
		
		return "itman/common/msgAlertReload";
	}
	
	//수정
	@RequestMapping("popup/contEditItmLocation.do")
	public String contEditItmLocation(
			@RequestParam(name="idx") String idx,
			HttpSession session,
			Model model) {
		String groIdx = (String) session.getAttribute("groIdx");
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("groIdx", groIdx);
		paramMap.put("locIdx", idx);
		
	    EgovItmanLocationVO LocationOne = egovItmanLocationService.selectLocationOne(paramMap);
	    model.addAttribute("row", LocationOne);
		return "itman/popup/contEditItmLocation";
	}
	
	//수정Proc
	@RequestMapping("/popup/pp_process/updateItmLocationProc.do")
	public String updateItmLocationProc(
			EgovItmanLocationVO egovItmanLocationVO,
			Model model,
			HttpSession session
			) {
		
		String memIdx = (String) session.getAttribute("memIdx");
		String groIdx = (String) session.getAttribute("groIdx");
		egovItmanLocationVO.setModIdx(memIdx);
		egovItmanLocationVO.setGroIdx(groIdx);
		
		int result = egovItmanLocationService.updateLocation(egovItmanLocationVO);
		
		if (result == 1) {
			model.addAttribute("msg", "위치가 성공적으로 수정되었습니다!");
		} else if (result == 0){
			model.addAttribute("msg", "실패");
		}
		
		return "itman/common/msgAlertReload";
	}
	
	//삭제
	@RequestMapping(value="popup/listDelete.do", params="id=locDel")
	public String locListDelete(
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
	
	//삭제Proc
	@RequestMapping(value="pp_process/listDeleteProc.do", params="id=locDel")
	public String locListDeleteProc(
			@RequestParam("id") String id,
			@RequestParam("idx") String idx,
			@RequestParam(value="memo", required=false) String memo, 
			HttpSession session,
			Model model,
			EgovItmanLocationVO egovItmanLocatsionVO) {
		String groIdx = (String) session.getAttribute("groIdx");
		String memIdx = (String) session.getAttribute("memIdx");
		
		if("locDel".equals(id)){
			egovItmanLocatsionVO.setDelIdx(memIdx);
			egovItmanLocatsionVO.setGroIdx(groIdx);
			egovItmanLocatsionVO.setLocIdx(idx);
			egovItmanLocatsionVO.setSlNote(memo);
	    
			int result = egovItmanLocationService.deleteLocation(egovItmanLocatsionVO);
		
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
	
	@RequestMapping("/popup/locationPop.do")
	public String locationPop(
			EgovItmanLocationVO searchVO,
			Model model,
			HttpSession session,
			@RequestParam(value="searchKeyword", required=false) String searchKeyword
			) throws Exception {
		
		/** paging setting */
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(searchVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(searchVO.getPageUnit());
		paginationInfo.setPageSize(searchVO.getPageSize());

		searchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		searchVO.setLastIndex(paginationInfo.getLastRecordIndex());
		searchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		String groIdx = (String) session.getAttribute("groIdx");
		searchVO.setGroIdx(groIdx);
		searchVO.setLocName(searchKeyword);

		List<EgovItmanLocationVO> locationList = egovItmanLocationService.selectLocationList(searchVO);
		model.addAttribute("locationList", locationList);

		int totCnt = egovItmanLocationService.selectLocationListTotCnt(searchVO);
		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);
		
		return "itman/popup/locationPop";
	}
	
}
