package egovframework.itman.web;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import egovframework.itman.group.service.EgovItmanGroupService;
import egovframework.itman.group.service.EgovItmanGroupVO;
import egovframework.itman.member.service.EgovItmanMemberVO;

@ControllerAdvice(basePackages = "egovframework.itman")
public class GlobalModelAttributes {

    @Resource(name = "egovItmanGroupService")
    private EgovItmanGroupService egovItmanGroupService;

    @ModelAttribute
    public void populateCommonHeaderModel(HttpSession session, Model model) {
        String groIdx = (String) session.getAttribute("groIdx");
        String memIdx = (String) session.getAttribute("memIdx");

        if (groIdx != null && !groIdx.isEmpty() && !model.containsAttribute("groupVO")) {
            EgovItmanGroupVO groupParam = new EgovItmanGroupVO();
            groupParam.setGroIdx(groIdx);
            EgovItmanGroupVO groupVO = egovItmanGroupService.selectGroup(groupParam);
            model.addAttribute("groupVO", groupVO);
        }

        if (memIdx != null && !memIdx.isEmpty() && !model.containsAttribute("groupList")) {
            EgovItmanMemberVO memberParam = new EgovItmanMemberVO();
            memberParam.setMemIdx(memIdx);
            List<EgovItmanGroupVO> groupList = egovItmanGroupService.selectGroupList(memberParam);
            model.addAttribute("groupList", groupList);
        }

        if (groIdx != null && !groIdx.isEmpty() && !model.containsAttribute("groIdx")) {
            model.addAttribute("groIdx", groIdx);
        }
    }
} 