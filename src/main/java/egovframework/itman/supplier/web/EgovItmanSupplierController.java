package egovframework.itman.supplier.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import egovframework.itman.group.service.EgovItmanGroupService;
import egovframework.itman.group.service.EgovItmanGroupVO;
import egovframework.itman.member.service.EgovItmanMemberVO;
import egovframework.itman.supplier.service.EgovItmanSupplierService;
import egovframework.itman.supplier.service.EgovItmanSupplierVO;

@Controller
public class EgovItmanSupplierController {
	
	@Resource(name = "egovItmanSupplierService")
	EgovItmanSupplierService egovItmanSupplierService;
	
	//조회

	@Resource(name = "egovItmanGroupService")
	EgovItmanGroupService egovItmanGroupService;

	@RequestMapping("ingroup/buyList.do")
	public String buyList(EgovItmanGroupVO egovItmanGroupVO,
			EgovItmanMemberVO egovItmanMemberVO,
			HttpSession session,
			Model model,
			@RequestParam(value="search", required=false, defaultValue="all") String search,
			@RequestParam(value="like", required=false, defaultValue="") String like,
			@RequestParam(value="page", required=false, defaultValue="1") int page) {
		
		String groIdx = (String) session.getAttribute("groIdx");
		String memIdx = (String) session.getAttribute("memIdx");
		
		if(memIdx != null) {
			egovItmanGroupVO.setGroIdx(groIdx);
			EgovItmanGroupVO groupVO = egovItmanGroupService.selectGroup(egovItmanGroupVO);
			model.addAttribute("groupVO", groupVO);
		
		
		//그룹조회
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
			
			int count = egovItmanSupplierService.selectSupplierCount(paramMap);
			int totalPage = (int) Math.ceil((double) count / pageResult);
		
		//구매처 리스트 조회 및 검색
			List<EgovItmanSupplierVO> supplierList = egovItmanSupplierService.selectSupplierList(paramMap);
			model.addAttribute("supplierList", supplierList);
			
			
			model.addAttribute("count", count);
			model.addAttribute("page", page);
	    	model.addAttribute("pageResult", pageResult);
	    	model.addAttribute("totalPage", totalPage);
	    	model.addAttribute("search", search);
	    	model.addAttribute("like", like);
	    	
	    	return "itman/ingroup/buyList";
		} else {
			model.addAttribute("msg", "로그인 후 이용해주세요.");
			return "itman/user/login";
		}
	}
	
	//추가
	@RequestMapping("popup/contWriteItmSupplier.do")
	public String contWiteItmSupplier(
			HttpSession session,
			Model model
			) {
		
		String groIdx = (String) session.getAttribute("groIdx");
		String memIdx = (String) session.getAttribute("memIdx");
		
		if(memIdx != null) {
			model.addAttribute("groIdx", groIdx);
		
			return "itman/popup/contWriteItmSupplier";
		} else {
			model.addAttribute("msg", "로그인 후 이용해주세요.");
			return "itman/common/msgAlertLogin";
		}
	}
	//추가Proc
	@RequestMapping("popup/ppProcess/writeItmSupplierProc.do")
	public String writeItmSupplierProc(
			EgovItmanSupplierVO egovItmanSupplierVO,
			Model model,
			HttpSession session,
			@RequestParam("groIdx") String groIdx
			) {
		
		String memIdx = (String) session.getAttribute("memIdx");
		egovItmanSupplierVO.setRegIdx(memIdx);
		egovItmanSupplierVO.setGroIdx(groIdx);
		
		int result = egovItmanSupplierService.insertSupplier(egovItmanSupplierVO);
		
		if (result == 1) {
			model.addAttribute("msg", "추가되었습니다.");
		} else if (result == 0){
			model.addAttribute("msg", "취소되었습니다.");
		}
		
		return "itman/common/msgAlertReload";
	}
	
	//수정
	@RequestMapping("popup/contEditItmSupplier.do")
	public String contEditItmSupplier(
			@RequestParam(name="idx") String idx,
			HttpSession session,
			Model model) {
		String groIdx = (String) session.getAttribute("groIdx");
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("groIdx", groIdx);
		paramMap.put("supIdx", idx);
		
	    EgovItmanSupplierVO supplierList = egovItmanSupplierService.selectSupplierOne(paramMap);
	    model.addAttribute("row", supplierList);
		return "itman/popup/contEditItmSupplier";
	}
	
	//수정proc
	@RequestMapping("/popup/pp_process/updateSupplierProc.do")
	public String updateGroupProc(
			EgovItmanSupplierVO egovItmanSupplierVO,
			Model model,
			HttpSession session,
			@RequestParam("groIdx") String groIdx
			) {
		
		String memIdx = (String) session.getAttribute("memIdx");
		egovItmanSupplierVO.setRegIdx(memIdx);
		egovItmanSupplierVO.setGroIdx(groIdx);
		
		int result = egovItmanSupplierService.updateSupplier(egovItmanSupplierVO);
		
		if (result == 1) {
			model.addAttribute("msg", "구매처가 성공적으로 수정되었습니다!");
		} else if (result == 0){
			model.addAttribute("msg", "실패");
		}
		
		return "itman/common/msgAlertReload";
	}
	
	//삭제
	@RequestMapping(value="popup/listDelete.do", params="id=buyDel")
	public String buyListDelete(
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
	@RequestMapping(value="pp_process/listDeleteProc.do", params="id=buyDel")
	public String listDeleteProc(
			@RequestParam("id") String id,
			@RequestParam("idx") String idx,
			@RequestParam(value="memo", required=false) String memo, 
			HttpSession session,
			Model model,
			EgovItmanSupplierVO egovItmanSupplierVO) {
		String groIdx = (String) session.getAttribute("groIdx");
		String memIdx = (String) session.getAttribute("memIdx");
		
		if("buyDel".equals(id)){
		egovItmanSupplierVO.setDelIdx(memIdx);
		egovItmanSupplierVO.setGroIdx(groIdx);
		egovItmanSupplierVO.setSupIdx(idx);
		egovItmanSupplierVO.setSupMemo(memo);
	    
		int result = egovItmanSupplierService.deleteSupplier(egovItmanSupplierVO);
		
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
	
	//구매처 찾기
	@RequestMapping("popup/supplierPop.do")
	public String supplierPop(
			EgovItmanGroupVO egovItmanGroupVO,
			Model model,
			HttpSession session
			) {
		
		String groIdx = (String) session.getAttribute("groIdx");
		
		Map<String, Object> paramMap = new HashMap<>();
		
		paramMap.put("groIdx", groIdx);
		
		List<EgovItmanSupplierVO> supplierList = egovItmanSupplierService.selectSupplierList(paramMap);
		model.addAttribute("supplierList", supplierList);
		
		return "itman/popup/supplierPop";
	}
	
}
