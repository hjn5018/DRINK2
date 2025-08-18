package egovframework.itman.employee.web;

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

import egovframework.itman.division.service.EgovItmanDivisionService;
import egovframework.itman.employee.service.EgovItmanEmployeePositionVO;
import egovframework.itman.employee.service.EgovItmanEmployeeSearchVO;
import egovframework.itman.employee.service.EgovItmanEmployeeService;
import egovframework.itman.employee.service.EgovItmanEmployeeStateVO;
import egovframework.itman.employee.service.EgovItmanEmployeeVO;
import egovframework.itman.group.service.EgovItmanGroupService;
import egovframework.itman.group.service.EgovItmanGroupVO;
import egovframework.itman.member.service.EgovItmanMemberVO;

@Controller
public class EgovItmanEmployeeController {
	
	@Resource(name = "egovItmanEmployeeService")
	EgovItmanEmployeeService egovItmanEmployeeService;
	
	@Resource(name = "egovItmanGroupService")
	EgovItmanGroupService egovItmanGroupService;
	
    @Resource(name = "egovItmanDivisionService")
    EgovItmanDivisionService egovItmanDivisionService;
	
    // ========================================
    // 직원 목록
    // ========================================
    @RequestMapping("/ingroup/emploList.do")
    public String selectEmploList(
        @ModelAttribute("searchVO") EgovItmanEmployeeSearchVO searchVO, 
        HttpSession session,
        Model model
    ) throws Exception {
        String groIdx = (String) session.getAttribute("groIdx");
        String memIdx = (String) session.getAttribute("memIdx");

        EgovItmanGroupVO egovItmanGroupVO = new EgovItmanGroupVO();
        egovItmanGroupVO.setGroIdx(groIdx);
        EgovItmanGroupVO groupVO = egovItmanGroupService.selectGroup(egovItmanGroupVO);
        model.addAttribute("groupVO", groupVO);
        
        EgovItmanMemberVO egovItmanMemberVO = new EgovItmanMemberVO();
        egovItmanMemberVO.setMemIdx(memIdx);
        List<EgovItmanGroupVO> groupList = egovItmanGroupService.selectGroupList(egovItmanMemberVO);
        model.addAttribute("groupList", groupList);

        searchVO.setGroIdx(groIdx);
        
        PaginationInfo paginationInfo = new PaginationInfo();
        paginationInfo.setCurrentPageNo(searchVO.getPageIndex());
        paginationInfo.setRecordCountPerPage(searchVO.getPageUnit());
        paginationInfo.setPageSize(searchVO.getPageSize());
        searchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
        searchVO.setLastIndex(paginationInfo.getLastRecordIndex());
        searchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

        List<?> resultList = egovItmanEmployeeService.selectEmploList(searchVO);
        int totCnt = egovItmanEmployeeService.selectEmploListTotCnt(searchVO);
        paginationInfo.setTotalRecordCount(totCnt);
        model.addAttribute("resultList", resultList);
        model.addAttribute("paginationInfo", paginationInfo);
        
        return "itman/ingroup/emploList";
    }

    // ========================================
    // 직원 등록 폼 이동
    // ========================================
    @RequestMapping("/ingroup/emploWrite.do")
    public String emploWriteForm(@RequestParam("groIdx") String groIdx, Model model) throws Exception {
        List<?> divisionList = egovItmanEmployeeService.selectDivisionList(groIdx);
        List<?> positionList = egovItmanEmployeeService.selectPositionList(groIdx);
        List<?> stateList = egovItmanEmployeeService.selectStateList(groIdx);

        EgovItmanEmployeeVO emploVO = new EgovItmanEmployeeVO();
        emploVO.setGroIdx(groIdx);

        model.addAttribute("divisionList", divisionList);
        model.addAttribute("positionList", positionList);
        model.addAttribute("stateList", stateList);
        model.addAttribute("emploVO", emploVO); 
        
        return "itman/ingroup/emploWrite";
    }

    // ========================================
    // 직원 등록 저장 처리 (POST)
    // ========================================
    @RequestMapping(value = "/ingroup/emploWriteProc.do", method = RequestMethod.POST)
    public String emploWriteProc(@ModelAttribute("emploVO") EgovItmanEmployeeVO emploVO,
                                 Model model,
                                 HttpSession session) throws Exception {
        String regIdx = (String) session.getAttribute("memIdx"); 
        if (regIdx == null) {
            regIdx = "tester"; 
        }
        
        emploVO.setRegIdx(regIdx);
        egovItmanEmployeeService.insertEmplo(emploVO); 
        return "redirect:/ingroup/emploList.do?groIdx=" + emploVO.getGroIdx();
    }
    
    // ========================================
    // 직위 등록 팝업 폼 열기 (GET)
    // ========================================
    @RequestMapping(value = "/popup/emploPositionWrite.do", method = RequestMethod.GET)
    public String positionWriteForm(@RequestParam("groIdx") String groIdx, Model model) {
        EgovItmanEmployeePositionVO positionVO = new EgovItmanEmployeePositionVO();
        positionVO.setGroIdx(groIdx);
        model.addAttribute("positionVO", positionVO);
        return "itman/popup/employee/emploPositionWrite";
    }

    // ========================================
    // 직위 등록 저장 처리 (POST)
    // ========================================
    @RequestMapping(value = "/popup/employee/emploPositionWriteProc.do", method = RequestMethod.POST)
    public String positionWriteProc(@ModelAttribute("positionVO") EgovItmanEmployeePositionVO positionVO,
                                    Model model,
                                    HttpSession session) throws Exception {
        String regIdx = (String) session.getAttribute("memIdx");
        if (regIdx == null) {
            regIdx = "tester";
        }
        positionVO.setRegIdx(regIdx);
        egovItmanEmployeeService.insertPosition(positionVO);
        model.addAttribute("msg", "직위가 성공적으로 생성되었습니다!");
        return "itman/common/msgAlertReload";
    }
    
    // ========================================
    // 상태 등록 (팝업)
    // ========================================
    @RequestMapping(value = "/popup/emploStateWrite.do", method = RequestMethod.GET)
    public String stateWriteForm(@RequestParam("groIdx") String groIdx, Model model) {
        EgovItmanEmployeeStateVO stateVO = new EgovItmanEmployeeStateVO();
        stateVO.setGroIdx(groIdx);
        model.addAttribute("stateVO", stateVO);
        return "itman/popup/employee/emploStateWrite";
    }

    @RequestMapping(value = "/popup/employee/emploStateWriteProc.do", method = RequestMethod.POST)
    public String stateWriteProc(@ModelAttribute("stateVO") EgovItmanEmployeeStateVO stateVO,
                                 Model model,
                                 HttpSession session) throws Exception {
        String regIdx = (String) session.getAttribute("memIdx");
        if (regIdx == null) {
            regIdx = "tester";
        }
        stateVO.setRegIdx(regIdx);
        egovItmanEmployeeService.insertState(stateVO);
        model.addAttribute("msg", "상태가 성공적으로 생성되었습니다!");
        return "itman/common/msgAlertReload";
    }
    
	@RequestMapping("popup/searchPop.do")
	public String searchPop(
			@ModelAttribute("searchVO") EgovItmanEmployeeSearchVO searchVO,
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

	    List<?> resultList = egovItmanEmployeeService.selectEmploList(searchVO);
	    int totCnt = egovItmanEmployeeService.selectEmploListTotCnt(searchVO);
	    paginationInfo.setTotalRecordCount(totCnt);

	    model.addAttribute("resultList", resultList);
	    model.addAttribute("paginationInfo", paginationInfo);

		return "itman/popup/searchPop";
	}
	
	@RequestMapping("ingroup/eStatList.do")
	public String eStatList(
			EgovItmanGroupVO egovItmanGroupVO,
			EgovItmanMemberVO egovItmanMemberVO,
			HttpSession session,
			Model model
			) {
		
		String groIdx = (String) session.getAttribute("groIdx");
		egovItmanGroupVO.setGroIdx(groIdx);
		EgovItmanGroupVO groupVO = egovItmanGroupService.selectGroup(egovItmanGroupVO);
		model.addAttribute("groupVO", groupVO);
		
		String memIdx = (String) session.getAttribute("memIdx");
		egovItmanMemberVO.setMemIdx(memIdx);
		List<EgovItmanGroupVO> groupList = egovItmanGroupService.selectGroupList(egovItmanMemberVO);
		model.addAttribute("groupList", groupList);
		
		return "itman/ingroup/eStatList";
	}
	@RequestMapping("/ingroup/emploView.do")
	public String selectEmploView(@RequestParam("empIdx") String empIdx, Model model) throws Exception {
	    EgovItmanEmployeeVO emploVO = egovItmanEmployeeService.selectEmploView(empIdx);
	    List<?> assetList = egovItmanEmployeeService.selectEmploAssetList(empIdx);
	    
	    model.addAttribute("emploVO", emploVO);
	    model.addAttribute("assetList", assetList);
	    
	    return "itman/ingroup/emploView";
	}
	// EgovItmanEmployeeController.java
	// ========================================
	// 직원 사번 변경 팝업 폼 이동 (GET)
	// ========================================
	@RequestMapping(value = "/popup/employee/emploNumInfoEdit.do", method = RequestMethod.GET)
	public String emploNumInfoEditForm(@RequestParam("empIdx") String empIdx, Model model) throws Exception {
	    // ...
	    EgovItmanEmployeeVO emploVO = egovItmanEmployeeService.selectEmploView(empIdx); 
	    model.addAttribute("emploVO", emploVO);
	    return "itman/popup/employee/emploNumInfoEdit";
	}
	// EgovItmanEmployeeController.java
	// ========================================
	// 직원 사번 변경 저장 처리 (POST)
	// ========================================
	@RequestMapping(value = "/popup/employee/emploNumInfoEditProc.do", method = RequestMethod.POST)
	public String emploNumInfoEditProc(@ModelAttribute("emploVO") EgovItmanEmployeeVO emploVO,
	                                   Model model) throws Exception {
	    egovItmanEmployeeService.updateEmploNum(emploVO);
	    
	    model.addAttribute("msg", "사번이 성공적으로 변경되었습니다!");
	    return "itman/common/msgAlertReload"; // 팝업창을 닫고 부모창을 새로고침하는 공통 JSP
	}
	@RequestMapping(value = "/popup/employee/emploNameInfoEdit.do", method = RequestMethod.GET)
	public String emploNameInfoEditForm(@RequestParam("empIdx") String empIdx, Model model) throws Exception {
	    EgovItmanEmployeeVO emploVO = egovItmanEmployeeService.selectEmploView(empIdx); 
	    model.addAttribute("emploVO", emploVO);
	    return "itman/popup/employee/emploNameInfoEdit";
	}
	@RequestMapping(value = "/popup/employee/emploNameInfoEditProc.do", method = RequestMethod.POST)
	public String emploNameInfoEditProc(@ModelAttribute("emploVO") EgovItmanEmployeeVO emploVO,
	                                    Model model) throws Exception {
	    egovItmanEmployeeService.updateEmploName(emploVO);
	    
	    model.addAttribute("msg", "이름이 성공적으로 변경되었습니다!");
	    return "itman/common/msgAlertReload"; 
	    
	}
	@RequestMapping(value = "/popup/employee/emploMailInfoEdit.do", method = RequestMethod.GET)
	public String emploMailInfoEditForm(@RequestParam("empIdx") String empIdx, Model model) throws Exception {
	    EgovItmanEmployeeVO emploVO = egovItmanEmployeeService.selectEmploView(empIdx); 
	    model.addAttribute("emploVO", emploVO);
	    return "itman/popup/employee/emploMailInfoEdit";
	}
	@RequestMapping(value = "/popup/employee/emploMailInfoEditProc.do", method = RequestMethod.POST)
    public String emploMailInfoEditProc(@ModelAttribute("emploVO") EgovItmanEmployeeVO emploVO,
                                        Model model) throws Exception {
        egovItmanEmployeeService.updateEmploMail(emploVO);
        model.addAttribute("msg", "이메일이 성공적으로 변경되었습니다!");
        return "itman/common/msgAlertReload";
    }

    // ========================================
    // 직원 휴대폰 변경 팝업 폼 이동 (GET)
    // ========================================
    @RequestMapping(value = "/popup/employee/emploTelInfoEdit.do", method = RequestMethod.GET)
    public String emploTelInfoEditForm(@RequestParam("empIdx") String empIdx, Model model) throws Exception {
        EgovItmanEmployeeVO emploVO = egovItmanEmployeeService.selectEmploView(empIdx);
        model.addAttribute("emploVO", emploVO);
        return "itman/popup/employee/emploTelInfoEdit";
    }
    
    // ========================================
    // 직원 휴대폰 변경 저장 처리 (POST)
    // ========================================
    @RequestMapping(value = "/popup/employee/emploTelInfoEditProc.do", method = RequestMethod.POST)
    public String emploTelInfoEditProc(@ModelAttribute("emploVO") EgovItmanEmployeeVO emploVO,
                                       Model model) throws Exception {
        egovItmanEmployeeService.updateEmploTel(emploVO);
        model.addAttribute("msg", "휴대폰 번호가 성공적으로 변경되었습니다!");
        return "itman/common/msgAlertReload";
    }
    
    // ========================================
    // 직원 부서 변경 팝업 폼 이동 (GET)
    // ========================================
    @RequestMapping(value = "/popup/employee/emploDivisionInfoEdit.do", method = RequestMethod.GET)
    public String emploDivisionInfoEditForm(@RequestParam("empIdx") String empIdx, HttpSession session, Model model) throws Exception {
        String groIdx = (String) session.getAttribute("groIdx");
        
        List<?> divisionList = egovItmanDivisionService.selectDivisionList(groIdx);
        EgovItmanEmployeeVO emploVO = new EgovItmanEmployeeVO();
        emploVO.setEmpIdx(empIdx);
        
        model.addAttribute("divisionList", divisionList);
        model.addAttribute("emploVO", emploVO);
        return "itman/popup/employee/emploDivisionInfoEdit";
    }

    // ========================================
    // 직원 부서 변경 저장 처리 (POST)
    // ========================================
    @RequestMapping(value = "/popup/employee/emploDivisionInfoEditProc.do", method = RequestMethod.POST)
    public String emploDivisionInfoEditProc(@ModelAttribute("emploVO") EgovItmanEmployeeVO emploVO,
                                           HttpSession session,
                                           Model model) throws Exception {
        String modIdx = (String) session.getAttribute("memIdx");
        if (modIdx == null) {
            modIdx = "0"; 
        }
        emploVO.setModIdx(modIdx);
        
        // 이 부분에서 EgovItmanEmployeeService의 updateEmploDivision을 호출하는 것은
        // 그대로 유지하되, 만약 부서에 관련된 다른 검증 로직 등이 필요하다면
        // EgovItmanDivisionService를 활용할 수 있습니다.
        // 현재 코드는 이미 잘 작동하고 있으므로, 굳이 수정하지 않아도 됩니다.
        // 핵심은 'division'과 관련된 로직을 필요할 때 'division' 서비스를 사용해야 한다는 원칙입니다.
        
        egovItmanEmployeeService.updateEmploDivision(emploVO);
        
        model.addAttribute("msg", "부서가 성공적으로 변경되었습니다!");
        return "itman/common/msgAlertReload";
    }

    // ========================================
    // 직원 직위 변경 팝업 폼 이동 (GET)
    // ========================================
    @RequestMapping(value = "/popup/employee/emploPosInfoEdit.do", method = RequestMethod.GET)
    public String emploPosInfoEditForm(@RequestParam("empIdx") String empIdx, HttpSession session, Model model) throws Exception {
        String groIdx = (String) session.getAttribute("groIdx");
        List<?> positionList = egovItmanEmployeeService.selectPositionList(groIdx);
        EgovItmanEmployeeVO emploVO = new EgovItmanEmployeeVO();
        emploVO.setEmpIdx(empIdx);
        
        model.addAttribute("positionList", positionList);
        model.addAttribute("emploVO", emploVO);
        return "itman/popup/employee/emploPosInfoEdit";
    }
    
    // ========================================
    // 직원 직위 변경 저장 처리 (POST)
    // ========================================
    @RequestMapping(value = "/popup/employee/emploPosInfoEditProc.do", method = RequestMethod.POST)
    public String emploPosInfoEditProc(@ModelAttribute("emploVO") EgovItmanEmployeeVO emploVO,
                                       Model model) throws Exception {
        egovItmanEmployeeService.updateEmploPos(emploVO);
        model.addAttribute("msg", "직위가 성공적으로 변경되었습니다!");
        return "itman/common/msgAlertReload";
    }

    // ========================================
    // 직원 상태 변경 팝업 폼 이동 (GET)
    // ========================================
    @RequestMapping(value = "/popup/employee/emploStateInfoEdit.do", method = RequestMethod.GET)
    public String emploStateInfoEditForm(@RequestParam("empIdx") String empIdx, HttpSession session, Model model) throws Exception {
        String groIdx = (String) session.getAttribute("groIdx");
        List<?> stateList = egovItmanEmployeeService.selectStateList(groIdx);
        EgovItmanEmployeeVO emploVO = new EgovItmanEmployeeVO();
        emploVO.setEmpIdx(empIdx);
        
        model.addAttribute("stateList", stateList);
        model.addAttribute("emploVO", emploVO);
        return "itman/popup/employee/emploStateInfoEdit";
    }
    
    // ========================================
    // 직원 상태 변경 저장 처리 (POST)
    // ========================================
    @RequestMapping(value = "/popup/employee/emploStateInfoEditProc.do", method = RequestMethod.POST)
    public String emploStateInfoEditProc(@ModelAttribute("emploVO") EgovItmanEmployeeVO emploVO,
                                           Model model) throws Exception {
        egovItmanEmployeeService.updateEmploState(emploVO);
        model.addAttribute("msg", "상태가 성공적으로 변경되었습니다!");
        return "itman/common/msgAlertReload";
    }

    // ========================================
    // 직원 삭제
    // ========================================
    @RequestMapping(value = "/popup/employee/emploDel.do", method = RequestMethod.GET)
    public String emploDeleteForm(@RequestParam("empIdx") String empIdx, Model model) throws Exception {
        EgovItmanEmployeeVO emploVO = new EgovItmanEmployeeVO();
        emploVO.setEmpIdx(empIdx);
        model.addAttribute("emploVO", emploVO);
        return "itman/popup/employee/emploDel";
    }

    // ========================================
    // 직원 삭제 처리
    // ========================================
    @RequestMapping(value = "/popup/employee/emploDelProc.do", method = RequestMethod.POST)
    public String emploDeleteProc(@ModelAttribute("emploVO") EgovItmanEmployeeVO emploVO, HttpSession session,
    		Model model) throws Exception {
    	
        String delIdx = (String) session.getAttribute("memIdx");
        String groIdx = (String) session.getAttribute("groIdx");
        
        if (delIdx == null) {
            delIdx = "tester";
        }
        emploVO.setDelIdx(delIdx);
        egovItmanEmployeeService.deleteEmplo(emploVO);
        model.addAttribute("msg", "삭제되었습니다.");
        model.addAttribute("groIdx", groIdx);
        
        return "itman/common/msgAlertGoEmploList";
    }
    
    
}