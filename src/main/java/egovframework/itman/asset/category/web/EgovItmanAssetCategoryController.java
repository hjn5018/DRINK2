package egovframework.itman.asset.category.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.egovframe.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import egovframework.itman.asset.category.service.EgovItmanAssetCategoryService;
import egovframework.itman.asset.category.service.EgovItmanAssetCategoryVO;
import egovframework.itman.asset.log.service.EgovItmanAssetLogService;
import egovframework.itman.asset.log.service.EgovItmanAssetLogVO;
import egovframework.itman.group.service.EgovItmanGroupService;
import egovframework.itman.group.service.EgovItmanGroupVO;
import egovframework.itman.member.service.EgovItmanMemberVO;

@Controller
public class EgovItmanAssetCategoryController {
	
	@Resource(name = "egovItmanAssetCategoryService")
	EgovItmanAssetCategoryService egovItmanAssetCategoryService;

	@Resource(name = "egovItmanGroupService")
	EgovItmanGroupService egovItmanGroupService;

	@Resource(name = "egovItmanAssetLogService")
	EgovItmanAssetLogService egovItmanAssetLogService;

	@RequestMapping("ingroup/assetCategory.do")
	public String locList(
			HttpSession session,
			EgovItmanGroupVO egovItmanGroupVO,
			Model model,
			@RequestParam(value="search", required=false, defaultValue="all") String search,
			@RequestParam(value="like", required=false, defaultValue="") String like,
			@RequestParam(value="page", required=false, defaultValue="1") int page
			) {
		
		String groIdx = (String) session.getAttribute("groIdx");
		int pageResult = 10;
		int start = (page - 1) * pageResult;
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("groIdx", groIdx);
		paramMap.put("search", search);
		paramMap.put("like", like);
		paramMap.put("page", page);
		paramMap.put("pageResult", pageResult);
		
		int count = egovItmanAssetCategoryService.selectAssetCategoryCount(paramMap);
		int totalPage = (int) Math.ceil((double) count / pageResult);
		
		List<EgovItmanAssetCategoryVO> assetCategoryList = egovItmanAssetCategoryService.selectAssetCategoryList(paramMap);
		model.addAttribute("assetCategoryList", assetCategoryList);
		
		
		model.addAttribute("search", search);
		model.addAttribute("like", like);
		model.addAttribute("page", page);
		model.addAttribute("pageResult", pageResult);
		model.addAttribute("totalPage", totalPage);
		model.addAttribute("count", count);
		
		return "itman/ingroup/assetCategory";
	}
	
	@RequestMapping("ingroup/assetCategoryExcel.do")
	public void assetCategoryExcel(
			HttpServletResponse response,
			HttpSession session,
			@RequestParam(value="search", required=false, defaultValue="all") String search,
			@RequestParam(value="like", required=false, defaultValue="") String like
			) throws Exception {
		
		String groIdx = (String) session.getAttribute("groIdx");
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("groIdx", groIdx);
		paramMap.put("search", search);
		paramMap.put("like", like);
		paramMap.put("page", 1);
		paramMap.put("pageResult", 99999);
		
		List<EgovItmanAssetCategoryVO> assetCategoryList = egovItmanAssetCategoryService.selectAssetCategoryList(paramMap);
		
		// Set response headers
		response.setContentType("application/vnd.ms-excel; charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setHeader("Content-Disposition", "attachment;filename=assetCategory.xls");
		
		// Build HTML string
		StringBuilder sb = new StringBuilder();
		sb.append("<meta charset='utf-8'>");
		sb.append("<table border='1'>");
		sb.append("<thead>");
		sb.append("<tr>");
		sb.append("<th>코드</th>");
		sb.append("<th>자산 분류명</th>");
		sb.append("<th>비고</th>");
		sb.append("</tr>");
		sb.append("</thead>");
		sb.append("<tbody>");
		
		for (EgovItmanAssetCategoryVO vo : assetCategoryList) {
			sb.append("<tr>");
			sb.append("<td>").append(vo.getAssCatCode()).append("</td>");
			sb.append("<td>").append(vo.getAssCatName()).append("</td>");
			sb.append("<td>").append(vo.getSlNote()).append("</td>");
			sb.append("</tr>");
		}
		
		sb.append("</tbody>");
		sb.append("</table>");
		
		// Write HTML to response
		byte[] bytes = sb.toString().getBytes("UTF-8");
		response.getOutputStream().write(bytes);
	}
	
	@RequestMapping("/popup/contWriteAssetCategory.do")
	public String contWriteAssetCategory(
			HttpSession session,
			Model model
			) {
		
		String groIdx = (String) session.getAttribute("groIdx");
		model.addAttribute("groIdx", groIdx);
		
		return "itman/popup/contWriteAssetCategory";
	}
	
	@RequestMapping("/popup/ppProcess/writeAssetCategoryProc.do")
	public String writeAssetCategoryProc(
			@RequestParam("groIdx") String groIdx,
			EgovItmanAssetCategoryVO egovItmanAssetCategoryVO,
			Model model,
			HttpSession session
			) {
		
		String memIdx = (String) session.getAttribute("memIdx");
		egovItmanAssetCategoryVO.setRegIdx(memIdx);
		egovItmanAssetCategoryVO.setGroIdx(groIdx);
		int result = egovItmanAssetCategoryService.insertAssetCategory(egovItmanAssetCategoryVO);
		
		if (result == 1) {
			model.addAttribute("msg", "추가되었습니다.");
		} else if (result == 0){
			model.addAttribute("msg", "취소되었습니다.");
		}
		
		return "itman/common/msgAlertReload";
	}
	
	//수정
	@RequestMapping("popup/contEditAssetCategory.do")
	public String contEditAssetCategory(
			@RequestParam(name="assCatIdx") String assCatIdx,
			HttpSession session,
			Model model) {
		String groIdx = (String) session.getAttribute("groIdx");
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("groIdx", groIdx);
		paramMap.put("assCatIdx", assCatIdx);
		
	    EgovItmanAssetCategoryVO assetCategoryVO = egovItmanAssetCategoryService.selectAssetCategory(paramMap);
	    model.addAttribute("assetCategoryVO", assetCategoryVO);
		return "itman/popup/contEditAssetCategory";
	}
	
	//수정proc
	@RequestMapping("/popup/ppProcess/updateAssetCategoryProc.do")
	public String updateAssetCategoryProc(
			EgovItmanAssetCategoryVO egovItmanAssetCategoryVO,
			Model model,
			HttpSession session,
			@RequestParam("groIdx") String groIdx
			) {
		
		String memIdx = (String) session.getAttribute("memIdx");
		egovItmanAssetCategoryVO.setModIdx(memIdx); // Use ModIdx for modification
		egovItmanAssetCategoryVO.setGroIdx(groIdx);
		
		int result = egovItmanAssetCategoryService.updateAssetCategory(egovItmanAssetCategoryVO);
		
		if (result == 1) {
			model.addAttribute("msg", "자산 분류가 성공적으로 수정되었습니다!");
		} else if (result == 0){
			model.addAttribute("msg", "실패");
		}
		
		return "itman/common/msgAlertReload";
	}
	
	//삭제
	@RequestMapping(value="popup/listDelete.do", params="id=assetCateDel")
	public String assetCategoryListDelete(
			@RequestParam(value="id", required=false) String id,
			@RequestParam("assCatIdx") String assCatIdx,
			HttpSession session,
			Model model) {
		String groIdx = (String) session.getAttribute("groIdx");
		model.addAttribute("groIdx", groIdx);
		model.addAttribute("idx", assCatIdx);
		model.addAttribute("id", id);
		return "itman/popup/listDelete";
		
	}
	
	//삭제Proc
	@RequestMapping(value="pp_process/listDeleteProc.do", params="id=assetCateDel")
	public String assetCategoryListDeleteProc(
			@RequestParam("id") String id,
			@RequestParam("idx") String assCatIdx,
			@RequestParam(value="memo", required=false) String memo, 
			HttpSession session,
			Model model,
			EgovItmanAssetCategoryVO egovItmanAssetCategoryVO) {
		String groIdx = (String) session.getAttribute("groIdx");
		String memIdx = (String) session.getAttribute("memIdx");
		
		if("assetCateDel".equals(id)){
		egovItmanAssetCategoryVO.setDelIdx(memIdx);
		egovItmanAssetCategoryVO.setGroIdx(groIdx);
		egovItmanAssetCategoryVO.setAssCatIdx(assCatIdx);
		egovItmanAssetCategoryVO.setSlNote(memo);
	    
		int result = egovItmanAssetCategoryService.deleteAssetCategory(egovItmanAssetCategoryVO);

		if (result == 1) {
			model.addAttribute("msg", "삭제되었습니다.");
		} else if (result == 0){
			model.addAttribute("msg", "실패");
		}
		
		
		model.addAttribute("assCatIdx", assCatIdx);
		model.addAttribute("groIdx", groIdx);
		return "itman/common/msgAlertReload";
		} else {
			model.addAttribute("msg", "id값이 맞지 않습니다.");
			return "itman/common/msgAlertReload";
		}
	}
	
	@RequestMapping("ingroup/aHistory.do")
	public String aHistory(
			EgovItmanGroupVO egovItmanGroupVO,
			EgovItmanMemberVO egovItmanMemberVO,
			HttpSession session,
			Model model,
			EgovItmanAssetLogVO egovItmanAssetLogVO
			) {
		
		String groIdx = (String) session.getAttribute("groIdx");
		EgovItmanAssetLogVO searchVO = egovItmanAssetLogVO;
		searchVO.setGroIdx(groIdx);
		
		/** 상단 공통 그룹 정보 */
		egovItmanGroupVO.setGroIdx(groIdx);
		EgovItmanGroupVO groupVO = egovItmanGroupService.selectGroup(egovItmanGroupVO);
		model.addAttribute("groupVO", groupVO);
		
		String memIdx = (String) session.getAttribute("memIdx");
		egovItmanMemberVO.setMemIdx(memIdx);
		List<EgovItmanGroupVO> groupList = egovItmanGroupService.selectGroupList(egovItmanMemberVO);
		model.addAttribute("groupList", groupList);
		
		// 페이징 설정 (유효성 검사 포함)
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(searchVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(searchVO.getRecordCountPerPage());
		paginationInfo.setPageSize(searchVO.getPageSize());
		
		// 총 건수 먼저 조회하여 총 페이지 수 산출
		int totCnt = egovItmanAssetLogService.selectAssetLogListTotCnt(searchVO);
		paginationInfo.setTotalRecordCount(totCnt);
		int totalPageCount = paginationInfo.getTotalPageCount();
		
		// 페이지 번호 유효성 검사 및 보정
		int currentPageNo = searchVO.getPageIndex();
		if (currentPageNo < 1) {
			currentPageNo = 1;
		}
		if (totalPageCount > 0 && currentPageNo > totalPageCount) {
			currentPageNo = totalPageCount;
		}
		paginationInfo.setCurrentPageNo(currentPageNo);
		searchVO.setPageIndex(currentPageNo);
		
		// 목록 조회를 위한 OFFSET/LIMIT 재설정
		searchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		searchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
		
		// 데이터 조회
		List<EgovItmanAssetLogVO> assetLogList = egovItmanAssetLogService.selectAssetLogList(searchVO);
		
		model.addAttribute("assetLogList", assetLogList);
		model.addAttribute("paginationInfo", paginationInfo);
		model.addAttribute("egovItmanAssetLogVO", searchVO);
		
		return "itman/ingroup/aHistory";
	}
}
