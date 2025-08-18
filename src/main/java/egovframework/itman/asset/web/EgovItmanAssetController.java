package egovframework.itman.asset.web;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.egovframe.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import egovframework.itman.Estate.service.EgovItmanEstateService;
import egovframework.itman.asset.category.service.EgovItmanAssetCategoryService;
import egovframework.itman.asset.category.service.EgovItmanAssetCategoryVO;
import egovframework.itman.asset.log.service.EgovItmanAssetLogService;
import egovframework.itman.asset.log.service.EgovItmanAssetLogVO;
import egovframework.itman.asset.service.EgovItmanAssetService;
import egovframework.itman.asset.service.EgovItmanAssetVO;
import egovframework.itman.division.service.EgovItmanDivisionService;
import egovframework.itman.employee.service.EgovItmanEmployeeSearchVO;
import egovframework.itman.employee.service.EgovItmanEmployeeService;
import egovframework.itman.employee.service.EgovItmanEmployeeVO;
import egovframework.itman.group.service.EgovItmanGroupService;
import egovframework.itman.group.service.EgovItmanGroupVO;
import egovframework.itman.location.service.EgovItmanLocationService;
import egovframework.itman.location.service.EgovItmanLocationVO;
import egovframework.itman.member.service.EgovItmanMemberVO;
import egovframework.itman.state.service.EgovItmanStateService;
import egovframework.itman.state.service.EgovItmanStateVO;
import egovframework.itman.supplier.service.EgovItmanSupplierService;
import egovframework.itman.supplier.service.EgovItmanSupplierVO;

@Controller
public class EgovItmanAssetController {

	@Resource(name = "egovItmanAssetService")
	EgovItmanAssetService egovItmanAssetService;
	
	@Resource(name = "egovItmanGroupService")
	EgovItmanGroupService egovItmanGroupService;
	
	@Resource(name = "egovItmanAssetCategoryService")
	EgovItmanAssetCategoryService egovItmanAssetCategoryService;
	
	@Resource(name = "egovItmanEstateService")
	EgovItmanEstateService egovItmanEstateService;
	
	@Resource(name = "egovItmanStateService")
	EgovItmanStateService egovItmanStateService;
	
	@Resource(name = "egovItmanSupplierService")
	EgovItmanSupplierService egovItmanSupplierService;

	@Resource(name = "egovItmanEmployeeService")
	EgovItmanEmployeeService egovItmanEmployeeService;

	@Resource(name = "egovItmanDivisionService")
	EgovItmanDivisionService egovItmanDivisionService;
	
	@Resource(name = "egovItmanAssetLogService")
	EgovItmanAssetLogService egovItmanAssetLogService;
	
	@Resource(name = "egovItmanLocationService")
	EgovItmanLocationService egovItmanLocationService;
	
	@RequestMapping("ingroup/assetList.do")
	public String assetList(
			EgovItmanAssetVO egovItmanAssetVO,
			EgovItmanGroupVO egovItmanGroupVO, 
			EgovItmanMemberVO egovItmanMemberVO,
			HttpSession session,
			Model model
			) {
		
		String memIdx = (String) session.getAttribute("memIdx");
		String groIdx = (String) session.getAttribute("groIdx");
		
		if(memIdx != null) {
		egovItmanGroupVO.setGroIdx(groIdx);
		EgovItmanGroupVO groupVO = egovItmanGroupService.selectGroup(egovItmanGroupVO);
		model.addAttribute("groupVO", groupVO);
		
		egovItmanMemberVO.setMemIdx(memIdx);
		List<EgovItmanGroupVO> groupList = egovItmanGroupService.selectGroupList(egovItmanMemberVO);
		model.addAttribute("groupList", groupList);
		
		// 페이징
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(egovItmanAssetVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(egovItmanAssetVO.getRecordCountPerPage());
		paginationInfo.setPageSize(egovItmanAssetVO.getPageSize());

		egovItmanAssetVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		egovItmanAssetVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
		
		egovItmanAssetVO.setGroIdx(groIdx);
		List<EgovItmanAssetVO> assetList = egovItmanAssetService.selectAssetList(egovItmanAssetVO);
		model.addAttribute("assetList", assetList);
		
		int totCnt = egovItmanAssetService.selectAssetListTotCnt(egovItmanAssetVO);
		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);
		
		return "itman/ingroup/assetList";
		}else {
			model.addAttribute("msg", "로그인 후 이용해주세요.");
			return "itman/user/login";
		}
	}
	
	@RequestMapping("ingroup/assetWrite.do")
	public String assetsWrite(
			Model model,
			HttpSession session
			) {
		
		String groIdx = (String) session.getAttribute("groIdx");
		String memIdx = (String) session.getAttribute("memIdx");
		
		if(memIdx != null) {
		EgovItmanGroupVO egovItmanGroupVO = new EgovItmanGroupVO();
		egovItmanGroupVO.setGroIdx(groIdx);
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("groIdx", groIdx);
		List<EgovItmanAssetCategoryVO> assetCategoryList = egovItmanAssetCategoryService.selectAssetCategoryList(paramMap);
		model.addAttribute("assetCategoryList", assetCategoryList);
		
		List<EgovItmanStateVO> stateList = egovItmanStateService.selectStateList(egovItmanGroupVO);
		model.addAttribute("stateList", stateList);
		
		List<EgovItmanSupplierVO> supplierList = egovItmanSupplierService.selectSupplierList(paramMap);
		model.addAttribute("supplierList", supplierList);
		
		return "itman/ingroup/assetWrite";
		}else {
			model.addAttribute("msg", "로그인 후 이용해주세요.");
			return "itman/user/login";
		}
	}
	
	@RequestMapping("ingroup/igProcess/assetWriteProc.do")
	public String assetWriteProc(
			EgovItmanAssetVO egovItmanAssetVO,
			@RequestParam("groIdx") String groIdx,
			HttpSession session,
			RedirectAttributes redirectAttributes,
			@RequestParam("uploadFile") MultipartFile file,
            HttpServletRequest request,
            EgovItmanAssetLogVO egovItmanAssetLogVO
            ) throws Exception{
		
		String savedFileName = "";

		if (!file.isEmpty()) {
			// 이미지 업로드 경로: C:/eGovFrameDev-4.3.1-64bit/workspace-egov/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/DRINK/upload/group
	        String uploadPath = request.getServletContext().getRealPath("/upload/asset");
	        File uploadDir = new File(uploadPath);
	        if (!uploadDir.exists()) uploadDir.mkdirs();

	        String originalFilename = file.getOriginalFilename();
	        savedFileName = UUID.randomUUID().toString() + "_" + originalFilename;
	        File target = new File(uploadPath, savedFileName);
	        file.transferTo(target);
	    }
		
		egovItmanAssetVO.setImage(savedFileName); // DB에는 파일명만 저장
		
		String regIdx = (String) session.getAttribute("memIdx");
		egovItmanAssetVO.setRegIdx(regIdx);
		egovItmanAssetVO.setGroIdx(groIdx);
		
		int result = egovItmanAssetService.insertAsset(egovItmanAssetVO);
		
		if (result == 1) {
			// Insert asset log: 생성/자산
			EgovItmanAssetVO inserted = egovItmanAssetService.selectAssetByUlid(egovItmanAssetVO.getAssUlid());
			if (inserted != null) {
				egovItmanAssetLogVO.setAssIdx(inserted.getAssIdx());
				egovItmanAssetLogVO.setAssNameLog(inserted.getAssName());
				egovItmanAssetLogVO.setAlType("생성");
				egovItmanAssetLogVO.setAlCat("자산");
				egovItmanAssetLogVO.setRegIdx(regIdx);
				egovItmanAssetLogVO.setRegIp(request.getRemoteAddr());
				egovItmanAssetLogService.insertLog(egovItmanAssetLogVO);
			}
			redirectAttributes.addFlashAttribute("msg", "자산이 추가되었습니다.");
		} else if (result == 0) {
			redirectAttributes.addFlashAttribute("msg", "자산 추가가 취소되었습니다.");
		}
		redirectAttributes.addAttribute("groIdx", groIdx);
		
		return "redirect:/ingroup/assetList.do";
	}
	
	@RequestMapping("/ingroup/assetView.do")
	public String assetView(
			@RequestParam("assIdx") String assIdx,
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
		
		EgovItmanAssetVO assetVO = egovItmanAssetService.selectAsset(assIdx);
		model.addAttribute("assetVO", assetVO);
		
		EgovItmanAssetLogVO logVO = new EgovItmanAssetLogVO();
		logVO.setAssIdx(assIdx);
		List<EgovItmanAssetLogVO> assetLogList = egovItmanAssetLogService.selectAssetLogListByAssIdx(assIdx);
		model.addAttribute("assetLogList", assetLogList);
		
		return "itman/ingroup/assetView";
	}
	
	@RequestMapping("/popup/asset/contAssetDel.do")
	public String conAssetDel(
			@RequestParam("assIdx") String assIdx,
			Model model
			) {
		
		EgovItmanAssetVO assetVO = egovItmanAssetService.selectAsset(assIdx);
		model.addAttribute("assetVO", assetVO);
		
		return "itman/popup/asset/contAssetDel";
	}
	
	@RequestMapping(value="/popup/asset/assetNameInfoEdit.do")
	public String assetNameInfoEditForm(
			@RequestParam("assIdx") String assIdx,
			Model model) {
		EgovItmanAssetVO assetVO = egovItmanAssetService.selectAsset(assIdx);
		model.addAttribute("assetVO", assetVO);
		return "itman/popup/asset/assetNameInfoEdit";
	}
	
	@RequestMapping(value="/popup/asset/ppProcess/assetNameInfoEditProc.do", method = RequestMethod.POST)
	public String assetNameInfoEditProc(
			@RequestParam("assIdx") String assIdx,
			@RequestParam("assName") String assName,
			@RequestParam(value="alNote", required=false) String alNote,
			HttpSession session,
			HttpServletRequest request,
			Model model) {
		String modIdx = (String) session.getAttribute("memIdx");
		EgovItmanAssetVO asset = egovItmanAssetService.selectAsset(assIdx);
		if (asset == null) {
			model.addAttribute("msg", "자산이 존재하지 않습니다.");
			return "itman/common/msgAlertReload";
		}
		// update
		EgovItmanAssetVO updateVO = new EgovItmanAssetVO();
		updateVO.setAssIdx(assIdx);
		updateVO.setAssName(assName);
		updateVO.setModIdx(modIdx);
		int upd = egovItmanAssetService.updateAssetName(updateVO);
		
		// log
		if (upd > 0) {
			EgovItmanAssetLogVO logVO = new EgovItmanAssetLogVO();
			logVO.setAssIdx(assIdx);
			logVO.setAssNameLog(assName);
			logVO.setAlType("수정");
			logVO.setAlCat("자산");
			logVO.setAlCont("자산명 변경: " + asset.getAssName() + " → " + assName);
			logVO.setAlNote(alNote);
			logVO.setRegIdx(modIdx);
			logVO.setRegIp(request.getRemoteAddr());
			egovItmanAssetLogService.insertLog(logVO);
			model.addAttribute("msg", "자산명이 변경되었습니다.");
		} else {
			model.addAttribute("msg", "자산명 변경에 실패했습니다.");
		}
		return "itman/common/msgAlertReload";
	}
	
	@RequestMapping(value="/ppProcess/contAssetDelProc.do", produces = "text/html; charset=UTF-8")
	@ResponseBody
	public String contAssetDelProc(
			@RequestParam("assIdx") String assIdx,
			HttpSession session,
			HttpServletRequest request) {
		
		String memIdx = (String) session.getAttribute("memIdx");
		// write log first
		try {
			EgovItmanAssetVO asset = egovItmanAssetService.selectAsset(assIdx);
			if (asset != null) {
				EgovItmanAssetLogVO logVO = new EgovItmanAssetLogVO();
				logVO.setAssIdx(assIdx);
				logVO.setAssNameLog(asset.getAssName());
				logVO.setAlType("삭제");
				logVO.setAlCat("자산");
				logVO.setAlCont("자산 삭제");
				logVO.setRegIdx(memIdx);
				logVO.setRegIp(request.getRemoteAddr());
				egovItmanAssetLogService.insertLog(logVO);
			}
		} catch (Exception ignore) {}
		
		Map<String, Object> params = new HashMap<>();
		params.put("assIdx", assIdx);
		params.put("memIdx", memIdx);
		int result = egovItmanAssetService.deleteAsset(params);
		
		if (result == 1) {
			String contextPath = request.getContextPath();
			return "<script>"
					+ "alert('자산을 성공적으로 삭제하였습니다.');"
					+ "opener.parent.location.href='" + contextPath + "/ingroup/assetList.do';"
					+ "window.close();"
					+ "</script>";
		} else if (result == 0) {
			
			return "<script>"
					+ "alert('자산 삭제에 실패하였습니다.');"
					+ "history.go(-1);"
					+ "</script>";
		}
		return "";
	}
	
	@RequestMapping(value="ingroup/assetAutoUlidProc.do", produces = "application/json; charset=UTF-8")
	@ResponseBody
	public String assetAutoUlidProc(
			@RequestParam("groIdx") String groIdx,
			@RequestParam("assCatIdx") String assCatIdx,
			@RequestParam("empIdx") String empIdx
			) {
		try {
			if (groIdx == null || groIdx.isEmpty() || assCatIdx == null || assCatIdx.isEmpty() || empIdx == null || empIdx.isEmpty()) {
				return "{\"status\":\"error\",\"message\":\"필수 파라미터 누락\"}";
			}

			// 1) Category code
			Map<String, Object> param = new HashMap<>();
			param.put("groIdx", groIdx);
			param.put("assCatIdx", assCatIdx);
			String catCode = egovItmanAssetCategoryService.selectAssetCategoryCode(param);

			// 2) Employee num and division idx
			Map<String, Object> empParam = new HashMap<>();
			empParam.put("groIdx", groIdx);
			empParam.put("empIdx", empIdx);
			EgovItmanEmployeeVO empVO = egovItmanEmployeeService.selectEmploBasicByGroup(empParam);
			String empNum = empVO != null ? empVO.getEmpNum() : null;
			String divIdx = empVO != null ? empVO.getDivIdx() : null;

			// 3) Division code
			Map<String, Object> divParam = new HashMap<>();
			divParam.put("groIdx", groIdx);
			divParam.put("divIdx", divIdx);
			String divCode = egovItmanDivisionService.selectDivisionCode(divParam);

			if (catCode == null || divCode == null || empNum == null) {
				return "{\"status\":\"error\",\"message\":\"코드 조회 실패\"}";
			}

			String baseUlid = catCode + "-" + divCode + "-" + empNum;

			Map<String, Object> ulidParam = new HashMap<>();
			ulidParam.put("groIdx", groIdx);
			ulidParam.put("baseUlid", baseUlid);
			String nextUlid = egovItmanAssetService.selectNextUlid(ulidParam);

			if (nextUlid == null || nextUlid.isEmpty()) {
				return "{\"status\":\"error\",\"message\":\"ULID 생성 실패\"}";
			}

			return "{\"status\":\"success\",\"assUlid\":\"" + nextUlid + "\"}";
		} catch (Exception e) {
			return "{\"status\":\"error\",\"message\":\"서버 오류\"}";
		}
	}
	
	// Category edit popup
	@RequestMapping(value="/popup/asset/assetCategoryInfoEdit.do")
	public String assetCategoryInfoEditForm(@RequestParam("assIdx") String assIdx, HttpSession session, Model model) {
		EgovItmanAssetVO assetVO = egovItmanAssetService.selectAsset(assIdx);
		model.addAttribute("assetVO", assetVO);
		
		String groIdx = (String) session.getAttribute("groIdx");
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("groIdx", groIdx);
		List<EgovItmanAssetCategoryVO> assetCategoryList = egovItmanAssetCategoryService.selectAssetCategoryList(paramMap);
		model.addAttribute("assetCategoryList", assetCategoryList);
		
		return "itman/popup/asset/assetCategoryInfoEdit";
	}
	@RequestMapping(value="/popup/asset/ppProcess/assetCategoryInfoEditProc.do", method = RequestMethod.POST)
	public String assetCategoryInfoEditProc(@RequestParam("assIdx") String assIdx,
			@RequestParam("assCatIdx") String assCatIdx,
			@RequestParam(value="alNote", required=false) String alNote,
			HttpSession session, HttpServletRequest request, Model model) {
		String modIdx = (String) session.getAttribute("memIdx");
		EgovItmanAssetVO before = egovItmanAssetService.selectAsset(assIdx);
		EgovItmanAssetVO vo = new EgovItmanAssetVO(); vo.setAssIdx(assIdx); vo.setAssCatIdx(assCatIdx); vo.setModIdx(modIdx);
		int upd = egovItmanAssetService.updateAssetCategory(vo);
		if (upd > 0) { writeLog(assIdx, before.getAssName(), "수정", "자산", "분류 변경: " + before.getAssCatName() + " → " + assCatIdx, alNote, modIdx, request.getRemoteAddr()); model.addAttribute("msg","분류가 변경되었습니다."); }
		else { model.addAttribute("msg","분류 변경에 실패했습니다."); }
		return "itman/common/msgAlertReload";
	}
	
	// State edit popup
	@RequestMapping(value="/popup/asset/assetStateInfoEdit.do")
	public String assetStateInfoEditForm(@RequestParam("assIdx") String assIdx, HttpSession session, Model model) {
		EgovItmanAssetVO assetVO = egovItmanAssetService.selectAsset(assIdx);
		model.addAttribute("assetVO", assetVO);
		
		String groIdx = (String) session.getAttribute("groIdx");
		EgovItmanGroupVO egovItmanGroupVO = new EgovItmanGroupVO();
		egovItmanGroupVO.setGroIdx(groIdx);
		List<EgovItmanStateVO> stateList = egovItmanStateService.selectStateList(egovItmanGroupVO);
		model.addAttribute("stateList", stateList);
		
		return "itman/popup/asset/assetStateInfoEdit";
	}
	@RequestMapping(value="/popup/asset/ppProcess/assetStateInfoEditProc.do", method = RequestMethod.POST)
	public String assetStateInfoEditProc(@RequestParam("assIdx") String assIdx,
			@RequestParam("staIdx") String staIdx,
			@RequestParam(value="alNote", required=false) String alNote,
			HttpSession session, HttpServletRequest request, Model model) {
		String modIdx = (String) session.getAttribute("memIdx");
		EgovItmanAssetVO before = egovItmanAssetService.selectAsset(assIdx);
		EgovItmanAssetVO vo = new EgovItmanAssetVO(); vo.setAssIdx(assIdx); vo.setStaIdx(staIdx); vo.setModIdx(modIdx);
		int upd = egovItmanAssetService.updateAssetState(vo);
		if (upd > 0) { writeLog(assIdx, before.getAssName(), "수정", "자산", "상태 변경: " + before.getStaName() + " → " + staIdx, alNote, modIdx, request.getRemoteAddr()); model.addAttribute("msg","상태가 변경되었습니다."); }
		else { model.addAttribute("msg","상태 변경에 실패했습니다."); }
		return "itman/common/msgAlertReload";
	}
	
	// Location edit popup
	@RequestMapping(value="/popup/asset/assetLocationInfoEdit.do")
	public String assetLocationInfoEditForm(@RequestParam("assIdx") String assIdx, HttpSession session, Model model) {
		EgovItmanAssetVO assetVO = egovItmanAssetService.selectAsset(assIdx);
		model.addAttribute("assetVO", assetVO);
		
		String groIdx = (String) session.getAttribute("groIdx");
		EgovItmanLocationVO egovItmanLocationVO = new EgovItmanLocationVO();
		egovItmanLocationVO.setGroIdx(groIdx);
		List<EgovItmanLocationVO> locationList = egovItmanLocationService.selectLocationList(egovItmanLocationVO);
		model.addAttribute("locationList", locationList);
		
		return "itman/popup/asset/assetLocationInfoEdit";
	}
	@RequestMapping(value="/popup/asset/ppProcess/assetLocationInfoEditProc.do", method = RequestMethod.POST)
	public String assetLocationInfoEditProc(@RequestParam("assIdx") String assIdx,
			@RequestParam("locIdx") String locIdx,
			@RequestParam(value="alNote", required=false) String alNote,
			HttpSession session, HttpServletRequest request, Model model) {
		String modIdx = (String) session.getAttribute("memIdx");
		EgovItmanAssetVO before = egovItmanAssetService.selectAsset(assIdx);
		EgovItmanAssetVO vo = new EgovItmanAssetVO(); vo.setAssIdx(assIdx); vo.setLocIdx(locIdx); vo.setModIdx(modIdx);
		int upd = egovItmanAssetService.updateAssetLocation(vo);
		if (upd > 0) { writeLog(assIdx, before.getAssName(), "수정", "자산", "위치 변경: " + before.getLocName() + " → " + locIdx, alNote, modIdx, request.getRemoteAddr()); model.addAttribute("msg","위치가 변경되었습니다."); }
		else { model.addAttribute("msg","위치 변경에 실패했습니다."); }
		return "itman/common/msgAlertReload";
	}
	
	// Employee edit popup
	@RequestMapping(value="/popup/asset/assetEmployeeInfoEdit.do")
	public String assetEmployeeInfoEditForm(@RequestParam("assIdx") String assIdx, EgovItmanEmployeeSearchVO searchVO, HttpSession session, Model model) throws Exception {
		EgovItmanAssetVO assetVO = egovItmanAssetService.selectAsset(assIdx);
		model.addAttribute("assetVO", assetVO);
		
		String groIdx = (String) session.getAttribute("groIdx");
		searchVO.setGroIdx(groIdx);
		
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(searchVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(searchVO.getRecordCountPerPage());
		paginationInfo.setPageSize(searchVO.getPageSize());

		searchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		searchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
		
		List<?> employeeList = egovItmanEmployeeService.selectEmploList(searchVO);
		model.addAttribute("employeeList", employeeList);
		
		int totCnt = egovItmanEmployeeService.selectEmploListTotCnt(searchVO);
		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);
		model.addAttribute("searchVO", searchVO);
		
		return "itman/popup/asset/assetEmployeeInfoEdit";
	}
	@RequestMapping(value="/popup/asset/ppProcess/assetEmployeeInfoEditProc.do", method = RequestMethod.POST)
	public String assetEmployeeInfoEditProc(@RequestParam("assIdx") String assIdx,
			@RequestParam("empIdx") String empIdx,
			@RequestParam(value="alNote", required=false) String alNote,
			HttpSession session, HttpServletRequest request, Model model) {
		String modIdx = (String) session.getAttribute("memIdx");
		EgovItmanAssetVO before = egovItmanAssetService.selectAsset(assIdx);
		EgovItmanAssetVO vo = new EgovItmanAssetVO(); vo.setAssIdx(assIdx); vo.setEmpIdx(empIdx); vo.setModIdx(modIdx);
		int upd = egovItmanAssetService.updateAssetEmployee(vo);
		if (upd > 0) { writeLog(assIdx, before.getAssName(), "수정", "자산", "사용직원 변경: " + before.getEmpName() + " → " + empIdx, alNote, modIdx, request.getRemoteAddr()); model.addAttribute("msg","사용직원이 변경되었습니다."); }
		else { model.addAttribute("msg","사용직원 변경에 실패했습니다."); }
		return "itman/common/msgAlertReload";
	}
	
	// Supplier edit popup
	@RequestMapping(value="/popup/asset/assetSupplyInfoEdit.do")
	public String assetSupplyInfoEditForm(@RequestParam("assIdx") String assIdx, HttpSession session, Model model) {
		EgovItmanAssetVO assetVO = egovItmanAssetService.selectAsset(assIdx);
		model.addAttribute("assetVO", assetVO);
		
		String groIdx = (String) session.getAttribute("groIdx");
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("groIdx",groIdx);
		List<EgovItmanSupplierVO> supplierList = egovItmanSupplierService.selectSupplierList(paramMap);
		model.addAttribute("supplierList", supplierList);
		
		return "itman/popup/asset/assetSupplyInfoEdit";
	}
	@RequestMapping(value="/popup/asset/ppProcess/assetSupplyInfoEditProc.do", method = RequestMethod.POST)
	public String assetSupplyInfoEditProc(@RequestParam("assIdx") String assIdx,
			@RequestParam("supIdx") String supIdx,
			@RequestParam(value="alNote", required=false) String alNote,
			HttpSession session, HttpServletRequest request, Model model) {
		String modIdx = (String) session.getAttribute("memIdx");
		EgovItmanAssetVO before = egovItmanAssetService.selectAsset(assIdx);
		EgovItmanAssetVO vo = new EgovItmanAssetVO(); vo.setAssIdx(assIdx); vo.setSupIdx(supIdx); vo.setModIdx(modIdx);
		int upd = egovItmanAssetService.updateAssetSupplier(vo);
		if (upd > 0) { writeLog(assIdx, before.getAssName(), "수정", "자산", "구매처 변경: " + before.getSupName() + " → " + supIdx, alNote, modIdx, request.getRemoteAddr()); model.addAttribute("msg","구매처가 변경되었습니다."); }
		else { model.addAttribute("msg","구매처 변경에 실패했습니다."); }
		return "itman/common/msgAlertReload";
	}
	
	// BuyDate edit popup
	@RequestMapping(value="/popup/asset/assetBuyDateInfoEdit.do")
	public String assetBuyDateInfoEditForm(@RequestParam("assIdx") String assIdx, Model model) {
		EgovItmanAssetVO assetVO = egovItmanAssetService.selectAsset(assIdx);
		model.addAttribute("assetVO", assetVO);
		return "itman/popup/asset/assetBuyDateInfoEdit";
	}
	@RequestMapping(value="/popup/asset/ppProcess/assetBuyDateInfoEditProc.do", method = RequestMethod.POST)
	public String assetBuyDateInfoEditProc(@RequestParam("assIdx") String assIdx,
			@RequestParam("buyDate") String buyDate,
			@RequestParam(value="alNote", required=false) String alNote,
			HttpSession session, HttpServletRequest request, Model model) {
		String modIdx = (String) session.getAttribute("memIdx");
		EgovItmanAssetVO before = egovItmanAssetService.selectAsset(assIdx);
		EgovItmanAssetVO vo = new EgovItmanAssetVO(); vo.setAssIdx(assIdx); vo.setBuyDate(buyDate); vo.setModIdx(modIdx);
		int upd = egovItmanAssetService.updateAssetBuyDate(vo);
		if (upd > 0) { writeLog(assIdx, before.getAssName(), "수정", "자산", "구매일 변경: " + before.getBuyDate() + " → " + buyDate, alNote, modIdx, request.getRemoteAddr()); model.addAttribute("msg","구매일이 변경되었습니다."); }
		else { model.addAttribute("msg","구매일 변경에 실패했습니다."); }
		return "itman/common/msgAlertReload";
	}
	
	// Price edit popup
	@RequestMapping(value="/popup/asset/assetPriceInfoEdit.do")
	public String assetPriceInfoEditForm(@RequestParam("assIdx") String assIdx, Model model) {
		EgovItmanAssetVO assetVO = egovItmanAssetService.selectAsset(assIdx);
		model.addAttribute("assetVO", assetVO);
		return "itman/popup/asset/assetPriceInfoEdit";
	}
	@RequestMapping(value="/popup/asset/ppProcess/assetPriceInfoEditProc.do", method = RequestMethod.POST)
	public String assetPriceInfoEditProc(@RequestParam("assIdx") String assIdx,
			@RequestParam("price") String price,
			@RequestParam(value="alNote", required=false) String alNote,
			HttpSession session, HttpServletRequest request, Model model) {
		String modIdx = (String) session.getAttribute("memIdx");
		EgovItmanAssetVO before = egovItmanAssetService.selectAsset(assIdx);
		EgovItmanAssetVO vo = new EgovItmanAssetVO(); vo.setAssIdx(assIdx); vo.setPrice(price); vo.setModIdx(modIdx);
		int upd = egovItmanAssetService.updateAssetPrice(vo);
		if (upd > 0) { writeLog(assIdx, before.getAssName(), "수정", "자산", "가격 변경: " + before.getPrice() + " → " + price, alNote, modIdx, request.getRemoteAddr()); model.addAttribute("msg","가격이 변경되었습니다."); }
		else { model.addAttribute("msg","가격 변경에 실패했습니다."); }
		return "itman/common/msgAlertReload";
	}
	
	@RequestMapping(value="/popup/asset/ppProcess/assetImageEditProc.do", method = RequestMethod.POST)
	public String assetImageInfoEditProc(@RequestParam("assIdx") String assIdx,
			@RequestParam("uploadFile") MultipartFile file,
			HttpSession session, HttpServletRequest request, Model model) throws Exception{
		
		String savedFileName = "";

		if (!file.isEmpty()) {
			// 이미지 업로드 경로: C:/eGovFrameDev-4.3.1-64bit/workspace-egov/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/DRINK/upload/group
	        String uploadPath = request.getServletContext().getRealPath("/upload/asset");
	        File uploadDir = new File(uploadPath);
	        if (!uploadDir.exists()) uploadDir.mkdirs();

	        String originalFilename = file.getOriginalFilename();
	        savedFileName = UUID.randomUUID().toString() + "_" + originalFilename;
	        File target = new File(uploadPath, savedFileName);
	        file.transferTo(target);
	    }
		
		String modIdx = (String) session.getAttribute("memIdx");
		EgovItmanAssetVO before = egovItmanAssetService.selectAsset(assIdx);
		EgovItmanAssetVO vo = new EgovItmanAssetVO();
		vo.setAssIdx(assIdx);
		vo.setImage(savedFileName); vo.setModIdx(modIdx);
		int upd = egovItmanAssetService.updateAssetImage(vo);
		if (upd > 0) {writeLog(assIdx, before.getAssName(), "수정", "자산", "이미지 변경: " + before.getImage() + " → " + savedFileName, "", modIdx, request.getRemoteAddr());}
		return "redirect:/ingroup/assetView.do?assIdx=" + assIdx;
	}
	
	private void writeLog(String assIdx, String assNameLog, String alType, String alCat, String alCont, String alNote, String regIdx, String regIp) {
		EgovItmanAssetLogVO logVO = new EgovItmanAssetLogVO();
		logVO.setAssIdx(assIdx);
		logVO.setAssNameLog(assNameLog);
		logVO.setAlType(alType);
		logVO.setAlCat(alCat);
		logVO.setAlCont(alCont);
		logVO.setAlNote(alNote);
		logVO.setRegIdx(regIdx);
		logVO.setRegIp(regIp);
		egovItmanAssetLogService.insertLog(logVO);
	}
}