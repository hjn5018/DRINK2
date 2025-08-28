package egovframework.itman.dashboard.web;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import egovframework.itman.Estate.service.EgovItmanEstateVO;
import egovframework.itman.asset.log.service.EgovItmanAssetLogVO;
import egovframework.itman.dashboard.service.EgovItmanDashboardService;
import egovframework.itman.group.service.EgovItmanGroupService;
import egovframework.itman.group.service.EgovItmanGroupVO;
import egovframework.itman.member.service.EgovItmanMemberVO;

@Controller
public class EgovItmanDashboardController {
	
	@Resource(name = "egovItmanDashboardService")
	EgovItmanDashboardService egovItmanDashboardService;
	
	@Resource(name = "egovItmanGroupService")
	EgovItmanGroupService egovItmanGroupService;
	
	@RequestMapping("ingroup/dashboard.do")
	public String dashboard(
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
		
		egovItmanGroupVO.setGroIdx(groIdx);
		List<EgovItmanEstateVO> assetStateList = egovItmanDashboardService.selectAssetStateList(egovItmanGroupVO);
		List<EgovItmanAssetLogVO> egovItmanAssetLogList = egovItmanDashboardService.selectAssetLogList(egovItmanGroupVO);
		model.addAttribute("assetStateList", assetStateList);
		model.addAttribute("egovItmanAssetLogList", egovItmanAssetLogList);
		return "itman/ingroup/dashboard";		
		} else {
			model.addAttribute("msg", "로그인 후 이용해주세요.");
			return "itman/user/login";
		}
	}
}
