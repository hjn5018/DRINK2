package egovframework.itman.group.web;

import java.io.File;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import egovframework.itman.asset.service.EgovItmanAssetService;
import egovframework.itman.employee.service.EgovItmanEmployeeService;
import egovframework.itman.group.service.EgovItmanGroupService;
import egovframework.itman.group.service.EgovItmanGroupVO;
import egovframework.itman.member.service.EgovItmanMemberVO;

@Controller
public class EgovItmanGroupController {

	@Resource(name = "egovItmanGroupService")
	EgovItmanGroupService egovItmanGroupService;

	@Resource(name = "egovItmanAssetService")
	EgovItmanAssetService egovItmanAssetService;

	@Resource(name = "egovItmanEmployeeService")
	EgovItmanEmployeeService egovItmanEmployeeService;

	@RequestMapping("/group.do")
	public String group(
			Model model, 
			HttpSession session,
			EgovItmanMemberVO egovItmanMemberVO
			) {

		String memIdx = (String) session.getAttribute("memIdx");
		egovItmanMemberVO.setMemIdx(memIdx);
		List<EgovItmanGroupVO> groupList = egovItmanGroupService.selectGroupList(egovItmanMemberVO);
		model.addAttribute("groupList", groupList);

		return "itman/group";
	}

	@RequestMapping("/groupProc.do")
	public String groupProc(
			@RequestParam("id") String id,
			@RequestParam("groIdx") String groIdx,
			EgovItmanGroupVO egovItmanGroupVO,
			HttpSession session
			) {
		session.setAttribute("groIdx", groIdx);
		session.setAttribute("pageNumDepth01", id);
		
		if ("0".equals(id)) {

			return "redirect:/ingroup/dashboard.do";
		} else if ("1".equals(id)) {

			return "redirect:/ingroup/assetList.do";
		} else if ("2".equals(id)) {

			return "redirect:/ingroup/emploList.do";
		} else if ("3".equals(id)) {

			return "redirect:/ingroup/departList.do";
		} else if ("4".equals(id)) {

			return "redirect:/ingroup/spotList.do";
		} else if ("5".equals(id)) {

			return "redirect:/ingroup/buyList.do";
		} else if ("6".equals(id)) {

			return "redirect:/ingroup/locList.do";
		} else if ("7".equals(id)) {

			return "redirect:/ingroup/assetCategory.do";
		} else if ("8".equals(id)) {

			return "redirect:/ingroup/aStatList.do";
		} else if ("9".equals(id)) {

			return "redirect:/ingroup/eStatList.do";
		} else if ("10".equals(id)) {

			return "redirect:/ingroup/aHistory.do";
		}

		return "";
	}

	@RequestMapping("/popup/addGroup.do")
	public String addGroup(
			HttpSession hsession,
			Model model) {
		String memIdx = (String) hsession.getAttribute("memIdx");
        if (memIdx != null) {
            return "itman/popup/addGroup";
        } else {
        	model.addAttribute("msg", "로그인 후 이용해주세요.");
			return "itman/common/msgAlertLogin";
        }
	}

	@RequestMapping("/popup/ppProcess/addGroupProc.do")
	public String addGroupProc(
			EgovItmanGroupVO egovItmanGroupVO,
			HttpSession session,
			Model model,
			@RequestParam("uploadFile") MultipartFile file,
            HttpServletRequest request
            ) throws Exception{
		
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

		int result = egovItmanGroupService.insertGroup(egovItmanGroupVO);
		model.addAttribute("result", result);

		return "itman/popup/addGroup";
	}
}