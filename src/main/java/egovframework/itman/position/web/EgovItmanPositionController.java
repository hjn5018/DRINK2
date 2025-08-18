package egovframework.itman.position.web;

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

import egovframework.itman.position.service.EgovItmanPositionSearchVO;
import egovframework.itman.position.service.EgovItmanPositionService;
import egovframework.itman.position.service.EgovItmanPositionVO;

@Controller
public class EgovItmanPositionController {

    @Resource(name = "egovItmanPositionService")
    private EgovItmanPositionService egovItmanPositionService;

    /**
     * 직위 목록을 조회하고 페이징 처리합니다.
     * @param searchVO 검색 조건을 담고 있는 VO
     * @param session 세션 정보
     * @param model 모델 객체
     * @return 직위 목록 화면
     * @throws Exception
     */
    @RequestMapping("/ingroup/spotList.do")
    public String selectPositionList(
        @ModelAttribute("searchVO") EgovItmanPositionSearchVO searchVO,
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

        // 메서드 이름을 selectPositionList로 수정하고 반환 타입 캐스팅
        List<EgovItmanPositionVO> resultList = (List<EgovItmanPositionVO>) egovItmanPositionService.selectPositionList(searchVO);
        
        // 메서드 이름을 selectPositionListTotCnt로 수정
        int totCnt = egovItmanPositionService.selectPositionListTotCnt(searchVO);

        paginationInfo.setTotalRecordCount(totCnt);
        model.addAttribute("resultList", resultList);
        model.addAttribute("paginationInfo", paginationInfo);
        model.addAttribute("searchVO", searchVO);

        return "itman/ingroup/spotList";
    }

    /**
     * GET: 직위 추가 팝업 폼을 보여줍니다.
     * @param groIdx 그룹 고유번호
     * @param model 모델 객체
     * @return 직위 추가 팝업 화면
     */
    @GetMapping("/popup/position/contWriteItmPosition.do")
    public String contWritePositionForm(@RequestParam("groIdx") String groIdx, Model model) {
        EgovItmanPositionVO positionVO = new EgovItmanPositionVO();
        positionVO.setGroIdx(groIdx);
        model.addAttribute("positionVO", positionVO);
        return "itman/popup/contWriteItmPosition";
    }

    /**
     * POST: 폼에서 전송된 직위 정보를 DB에 저장합니다.
     * @param positionVO 직위 정보를 담고 있는 VO
     * @param session 세션 정보
     * @param model 모델 객체
     * @return 알림 메시지 후 페이지 새로고침
     * @throws Exception
     */
    @PostMapping("/popup/position/writePositionProc.do")
    public String writePositionProc(@ModelAttribute("positionVO") EgovItmanPositionVO positionVO,
                                    HttpSession session,
                                    Model model) throws Exception {
        String regIdx = (String) session.getAttribute("memIdx");
        if (regIdx == null) {
            regIdx = "0"; // 로그인하지 않은 경우 처리
        }
        positionVO.setRegIdx(regIdx);
        egovItmanPositionService.insertPosition(positionVO);
        model.addAttribute("msg", "직위가 성공적으로 등록되었습니다!");
        return "itman/common/msgAlertReload";
    }

    /**
     * GET: 직위 수정 팝업 폼을 보여줍니다.
     * @param posIdx 직위 고유번호
     * @param model 모델 객체
     * @return 직위 수정 팝업 화면
     * @throws Exception
     */
    @GetMapping("/popup/position/contUpdateItmPosition.do")
    public String contUpdatePositionForm(@RequestParam("posIdx") String posIdx, Model model) throws Exception {
        EgovItmanPositionVO positionVO = egovItmanPositionService.selectPositionDetail(posIdx);
        model.addAttribute("positionVO", positionVO);
        return "itman/popup/contEditItmPosition";
    }
    
    /**
     * POST: 폼에서 전송된 직위 정보를 수정합니다.
     * @param positionVO 수정할 직위 정보를 담고 있는 VO
     * @param session 세션 정보
     * @param model 모델 객체
     * @return 알림 메시지 후 페이지 새로고침
     * @throws Exception
     */
    @PostMapping("/popup/position/updatePositionProc.do")
    public String updatePositionProc(@ModelAttribute("positionVO") EgovItmanPositionVO positionVO,
                                    HttpSession session,
                                    Model model) throws Exception {
        String updIdx = (String) session.getAttribute("memIdx");
        if (updIdx == null) {
            updIdx = "0"; // 로그인하지 않은 경우 처리
        }
        positionVO.setUpdIdx(updIdx);
        egovItmanPositionService.updatePosition(positionVO);
        model.addAttribute("msg", "직위가 성공적으로 수정되었습니다!");
        return "itman/common/msgAlertReload";
    }
    /**
     * GET: 삭제 확인 팝업을 보여줍니다.
     * @param posIdx 삭제할 직위 고유번호
     * @param model 모델 객체
     * @return 삭제 팝업 화면
     */
    /*@GetMapping("/popup/position/contDeleteItmPosition.do")
    public String contDeleteItmPositionForm(@RequestParam("posIdx") String posIdx, Model model) {
        model.addAttribute("posIdx", posIdx);
        model.addAttribute("idx", posIdx);
//        model.addAttribute("id", 1);
        return "itman/popup/listDelete"; // listDelete.jsp 사용
    }*/

    /**
     * POST: 직위 삭제를 처리합니다.
     * @param positionVO 삭제 정보를 담고 있는 VO
     * @param session 세션 정보
     * @param model 모델 객체
     * @return 알림 메시지 후 페이지 새로고침
     * @throws Exception
     */
    /*@PostMapping("/popup/position/deletePositionProc.do")
    public String deletePositionProc(@ModelAttribute("positionVO") EgovItmanPositionVO positionVO,
                                    HttpSession session,
                                    Model model) throws Exception {
        String delIdx = (String) session.getAttribute("memIdx");
        if (delIdx == null) {
            delIdx = "0"; // 로그인하지 않은 경우 처리
        }
        positionVO.setDelIdx(delIdx);
        egovItmanPositionService.deletePosition(positionVO);
        model.addAttribute("msg", "직위가 성공적으로 삭제되었습니다!");
        return "itman/common/msgAlertReload";
    }*/
  //삭제
  	@RequestMapping(value="popup/listDelete.do", params="id=posDel")
  	public String assetCategoryListDelete(
  			@RequestParam(value="id", required=false) String id,
  			@RequestParam("posIdx") String posIdx,
  			HttpSession session,
  			Model model) {
  		String groIdx = (String) session.getAttribute("groIdx");
  		model.addAttribute("groIdx", groIdx);
  		model.addAttribute("idx", posIdx);
  		model.addAttribute("id", id);
  		return "itman/popup/listDelete";
  		
  	}
  	
  	//삭제Proc
  	@RequestMapping(value="pp_process/listDeleteProc.do", params="id=posDel")
  	public String assetCategoryListDeleteProc(
  			@RequestParam("id") String id,
  			@RequestParam("idx") String posIdx,
  			@RequestParam(value="memo", required=false) String memo, 
  			HttpSession session,
  			Model model,
  			EgovItmanPositionVO egovItmanPositionVO) throws Exception {
  		String groIdx = (String) session.getAttribute("groIdx");
  		String memIdx = (String) session.getAttribute("memIdx");
  		
  		if("posDel".equals(id)){
  			egovItmanPositionVO.setDelIdx(memIdx);
  			egovItmanPositionVO.setGroIdx(groIdx);
  			egovItmanPositionVO.setPosIdx(posIdx);
  	    
  		int result = egovItmanPositionService.deletePosition(egovItmanPositionVO);

  		if (result == 1) {
  			model.addAttribute("msg", "삭제되었습니다.");
  		} else if (result == 0){
  			model.addAttribute("msg", "실패");
  		}
  		
  		
  		model.addAttribute("divIdx", posIdx);
  		model.addAttribute("groIdx", groIdx);
  		return "itman/common/msgAlertReload";
  		} else {
  			model.addAttribute("msg", "id값이 맞지 않습니다.");
  			return "itman/common/msgAlertReload";
  		}
  	}
}