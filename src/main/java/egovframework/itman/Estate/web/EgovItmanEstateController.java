package egovframework.itman.Estate.web;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.egovframe.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import egovframework.itman.Estate.service.EgovItmanEstateSearchVO;
import egovframework.itman.Estate.service.EgovItmanEstateService;
import egovframework.itman.Estate.service.EgovItmanEstateVO;

@Controller
public class EgovItmanEstateController {

    @Resource(name = "egovItmanEstateService")
    private EgovItmanEstateService egovItmanEstateService;

    /**
     * 직원 상태 목록을 조회합니다. (페이징, 검색 포함)
     */
    @GetMapping("/ingroup/eStatList.do")
    public String selectStateList(
            @ModelAttribute("searchVO") EgovItmanEstateSearchVO searchVO,
            HttpSession session,
            Model model) throws Exception {

        String groIdx = (String) session.getAttribute("groIdx");
        if (groIdx == null || groIdx.isEmpty()) {
            return "redirect:/login.do";
        }
        searchVO.setGroIdx(groIdx);

        PaginationInfo paginationInfo = new PaginationInfo();
        paginationInfo.setCurrentPageNo(searchVO.getPageIndex());
        paginationInfo.setRecordCountPerPage(searchVO.getPageUnit());
        paginationInfo.setPageSize(searchVO.getPageSize());

        searchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
        searchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

        int totCnt = egovItmanEstateService.selectStateListTotCnt(searchVO);
        paginationInfo.setTotalRecordCount(totCnt);

        List<EgovItmanEstateVO> resultList = egovItmanEstateService.selectEstateList(searchVO);

        model.addAttribute("resultList", resultList);
        model.addAttribute("paginationInfo", paginationInfo);

        return "itman/ingroup/eStatList";
    }

    /**
     * GET: 직원 상태 추가 팝업 폼을 보여줍니다.
     */
    @GetMapping("/popup/state/contWriteItmState.do")
    public String contWriteStateForm(@RequestParam("groIdx") String groIdx, Model model) throws Exception {
        
        // 그룹 이름 등을 조회하는 로직 (필요시 추가)
        // String groName = groupService.selectGroupName(groIdx);
        // model.addAttribute("groName", groName);

        EgovItmanEstateVO stateVO = new EgovItmanEstateVO();
        stateVO.setGroIdx(groIdx);
        model.addAttribute("stateVO", stateVO);
        
        // 수정된 부분: return 경로를 새 JSP 파일 이름으로 변경
        return "itman/popup/contWriteItmEmpState"; 
    }
    /**
     * POST: 폼에서 전송된 직원 상태 정보를 DB에 저장합니다.
     */
    @PostMapping("/popup/state/writeStateProc.do")
    public String writeStateProc(@ModelAttribute("stateVO") EgovItmanEstateVO stateVO,
                                 HttpSession session,
                                 Model model) throws Exception {
        String regIdx = (String) session.getAttribute("memIdx"); // 세션 Key를 'memIdx'로 통일
        stateVO.setRegIdx(regIdx);

        egovItmanEstateService.insertEstate(stateVO);

        model.addAttribute("msg", "상태가 성공적으로 등록되었습니다!");
        return "itman/common/msgAlertReload"; // 공용 결과 페이지 사용
    }

    /**
     * GET: 직원 상태 수정 팝업 폼을 보여줍니다.
     */
    @GetMapping("/popup/state/contUpdateItmState.do")
    public String contUpdateStateForm(@RequestParam("stIdx") String stIdx, Model model) throws Exception {
        EgovItmanEstateVO stateVO = egovItmanEstateService.selectStateDetail(stIdx);
        model.addAttribute("stateVO", stateVO);
        return "itman/popup/contEditItmState"; // JSP 경로 변경
    }

    /**
     * POST: 폼에서 전송된 직원 상태 정보를 수정합니다.
     */
    @PostMapping("/popup/state/updateStateProc.do")
    public String updateStateProc(@ModelAttribute("stateVO") EgovItmanEstateVO stateVO,
                                  HttpSession session,
                                  Model model) throws Exception {
        String updIdx = (String) session.getAttribute("memIdx"); // 세션 Key를 'memIdx'로 통일
        stateVO.setUpdIdx(updIdx);

        egovItmanEstateService.updateEstate(stateVO);

        model.addAttribute("msg", "상태가 성공적으로 수정되었습니다!");
        return "itman/common/msgAlertReload"; // 공용 결과 페이지 사용
    }

    /**
     * GET: 삭제 확인 팝업을 보여줍니다.
     */
    @RequestMapping(value="popup/listDelete.do", params="id=eStateDel")
    public String contDeleteItmStateForm(
    		@RequestParam(value="id", required=false) String id,
			@RequestParam("idx") String idx,
    		Model model) {
    	model.addAttribute("idx", idx);
		model.addAttribute("id", id);
        return "itman/popup/listDelete"; // 공용 삭제 확인 페이지 사용
    }

    /**
     * POST: 직원 상태 삭제를 처리합니다.
     */
    @RequestMapping(value = "/pp_process/listDeleteProc.do", params="id=eStateDel")
    public String deleteEstateProc(
    		@RequestParam("id") String id,
			@RequestParam("idx") String idx,
			@RequestParam(value="memo", required=false) String memo, 
    		@ModelAttribute("stateVO") EgovItmanEstateVO stateVO,
                                  HttpSession session,
                                  Model model
                                  ) throws Exception {
        String delIdx = (String) session.getAttribute("memIdx"); // 세션 Key를 'memIdx'로 통일
        String groIdx = (String) session.getAttribute("groIdx");
        stateVO.setDelIdx(delIdx);
        stateVO.setGroIdx(groIdx);
        stateVO.setStIdx(idx);
        stateVO.setSlNote(memo);

        int result = egovItmanEstateService.deleteEstate(stateVO);
		if (result == 1) {
			model.addAttribute("msg", "상태가 성공적으로 삭제되었습니다!");
			return "itman/common/msgAlertReload";
		}else {
			model.addAttribute("msg", "id값이 맞지 않습니다.");
			return "itman/common/msgAlertReload";
		}
    }
}