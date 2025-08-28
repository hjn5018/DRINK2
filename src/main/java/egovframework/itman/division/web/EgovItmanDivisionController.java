package egovframework.itman.division.web;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.egovframe.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import egovframework.itman.division.service.EgovItmanDivisionSearchVO;
import egovframework.itman.division.service.EgovItmanDivisionService;
import egovframework.itman.division.service.EgovItmanDivisionVO;
import egovframework.itman.group.service.EgovItmanGroupService;
@Controller
public class EgovItmanDivisionController {
    @Resource(name = "egovItmanGroupService")
    EgovItmanGroupService egovItmanGroupService;
    @Resource(name = "egovItmanDivisionService")
    EgovItmanDivisionService egovItmanDivisionService;
    
    @RequestMapping("/ingroup/departList.do")
    public String selectDepartList(
        @ModelAttribute("searchVO") EgovItmanDivisionSearchVO searchVO,
        HttpSession session,
        Model model
    ) throws Exception {
        String groIdx = (String) session.getAttribute("groIdx");
        searchVO.setGroIdx(groIdx);
        PaginationInfo paginationInfo = new PaginationInfo();
        paginationInfo.setCurrentPageNo(searchVO.getPageIndex());
        paginationInfo.setRecordCountPerPage(searchVO.getPageUnit());
        paginationInfo.setPageSize(searchVO.getPageSize());
        searchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
        searchVO.setLastIndex(paginationInfo.getLastRecordIndex());
        searchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
        List<?> resultList = egovItmanDivisionService.selectDepartList(searchVO);
        int totCnt = egovItmanDivisionService.selectDepartListTotCnt(searchVO);
        paginationInfo.setTotalRecordCount(totCnt);
        model.addAttribute("resultList", resultList);
        model.addAttribute("paginationInfo", paginationInfo);
        model.addAttribute("searchVO", searchVO);
        return "itman/ingroup/departList";
    }
    @RequestMapping(value = "/popup/emploDivisionWrite.do", method = RequestMethod.GET)
    public String divisionWriteForm(@RequestParam("groIdx") String groIdx, Model model) {
        EgovItmanDivisionVO divisionVO = new EgovItmanDivisionVO();
        divisionVO.setGroIdx(groIdx);
        model.addAttribute("divisionVO", divisionVO);
        return "itman/popup/employee/emploDivisionWrite";
    }
    @RequestMapping(value = "/popup/employee/emploDivisionWriteProc.do", method = RequestMethod.POST)
    public String divisionWriteProc(
    		@ModelAttribute("divisionVO") EgovItmanDivisionVO divisionVO,
                                    Model model,
                                    HttpSession session,
                                    RedirectAttributes rdab) throws Exception {
        String regIdx = (String) session.getAttribute("memIdx");
        String groIdx = (String) session.getAttribute("groIdx");
        if (regIdx == null) {
            model.addAttribute("msg", "로그인 후 이용해주세요.");
            return "itman/common/msgAlertLogin";
        }
        if(divisionVO.getDivYn() == null) {
        	rdab.addFlashAttribute("msg", "사용여부를 선택해주세요.");
        	return "redirect:/popup/emploDivisionWrite.do?groIdx=" + groIdx;
        }
        divisionVO.setRegIdx(regIdx);
        egovItmanDivisionService.insertDivision(divisionVO);
        model.addAttribute("msg", "부서가 성공적으로 생성되었습니다!");
        return "itman/common/msgAlertReload";
    }
    // ========================================
    // 부서 추가 팝업 폼 이동 (GET)
    // ========================================
    @RequestMapping(value = "/popup/division/contWriteItmDivision.do", method = RequestMethod.GET)
    public String contWriteItmDivisionForm(HttpSession session, Model model) {
        String groIdx = (String) session.getAttribute("groIdx");
        EgovItmanDivisionVO divisionVO = new EgovItmanDivisionVO();
        divisionVO.setGroIdx(groIdx);
        model.addAttribute("divisionVO", divisionVO);
        return "itman/popup/contWriteItmDivision";
    }
    // ========================================
    // 부서 추가 저장 처리 (POST)
    // ========================================
    @RequestMapping(value = "/popup/division/writeItmDivisionProc.do", method = RequestMethod.POST)
    public String writeItmDivisionProc(@ModelAttribute("divisionVO") EgovItmanDivisionVO divisionVO,
                                       HttpSession session,
                                       Model model) throws Exception {
        String regIdx = (String) session.getAttribute("memIdx");
        if (regIdx == null) {
            regIdx = "tester";
        }
        divisionVO.setRegIdx(regIdx);
        egovItmanDivisionService.insertDivision(divisionVO);
        model.addAttribute("msg", "부서가 성공적으로 추가되었습니다!");
        return "itman/common/msgAlertReload";
    }
    // ========================================
    // 부서 수정 팝업 폼 이동 (GET)
    // ========================================
    @RequestMapping(value = "/popup/division/contWriteDivision.do", method = RequestMethod.GET)
    public String contWriteDivisionForm(@RequestParam("idx") String divIdx, HttpSession session, Model model) throws Exception {
        EgovItmanDivisionVO divisionVO = egovItmanDivisionService.selectDivisionDetail(divIdx);
        model.addAttribute("divisionVO", divisionVO);
        return "itman/popup/contWriteDivision";
    }
    // ========================================
    // 부서 수정 저장 처리 (POST)
    // ========================================
    @RequestMapping(value = "/popup/division/updateDivisionProc.do", method = RequestMethod.POST)
    public String updateDivisionProc(@ModelAttribute("divisionVO") EgovItmanDivisionVO divisionVO,
                                     HttpSession session,
                                     Model model) throws Exception {
        String modIdx = (String) session.getAttribute("memIdx");
        if (modIdx == null) {
            modIdx = "0";
        }
        divisionVO.setModIdx(modIdx);
        egovItmanDivisionService.updateDivision(divisionVO);
        model.addAttribute("msg", "부서가 성공적으로 수정되었습니다!");
        return "itman/common/msgAlertReload";
    }
    
  //삭제
  	@RequestMapping(value="popup/listDelete.do", params="id=divDel")
  	public String assetCategoryListDelete(
  			@RequestParam(value="id", required=false) String id,
  			@RequestParam("divIdx") String divIdx,
  			HttpSession session,
  			Model model) {
  		String groIdx = (String) session.getAttribute("groIdx");
  		model.addAttribute("groIdx", groIdx);
  		model.addAttribute("idx", divIdx);
  		model.addAttribute("id", id);
  		return "itman/popup/listDelete";
  		
  	}
  	
  	//삭제Proc
  	@RequestMapping(value="pp_process/listDeleteProc.do", params="id=divDel")
  	public String assetCategoryListDeleteProc(
  			@RequestParam("id") String id,
  			@RequestParam("idx") String divIdx,
  			@RequestParam(value="memo", required=false) String memo, 
  			HttpSession session,
  			Model model,
  			EgovItmanDivisionVO egovItmanDivisionVO) throws Exception {
  		String groIdx = (String) session.getAttribute("groIdx");
  		String memIdx = (String) session.getAttribute("memIdx");
  		
  		if("divDel".equals(id)){
  			egovItmanDivisionVO.setDelIdx(memIdx);
  			egovItmanDivisionVO.setGroIdx(groIdx);
  			egovItmanDivisionVO.setDivIdx(divIdx);
  	    
  		int result = egovItmanDivisionService.deleteDivision(egovItmanDivisionVO);

  		if (result == 1) {
  			model.addAttribute("msg", "삭제되었습니다.");
  		} else if (result == 0){
  			model.addAttribute("msg", "실패");
  		}
  		
  		
  		model.addAttribute("divIdx", divIdx);
  		model.addAttribute("groIdx", groIdx);
  		return "itman/common/msgAlertReload";
  		} else {
  			model.addAttribute("msg", "id값이 맞지 않습니다.");
  			return "itman/common/msgAlertReload";
  		}
  	}
}