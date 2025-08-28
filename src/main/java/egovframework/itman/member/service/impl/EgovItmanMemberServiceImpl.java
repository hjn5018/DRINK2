package egovframework.itman.member.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.itman.group.service.EgovItmanGroupVO;
import egovframework.itman.member.service.EgovItmanMemberService;
import egovframework.itman.member.service.EgovItmanMemberVO;

@Service("egovItmanMemberService")
public class EgovItmanMemberServiceImpl implements EgovItmanMemberService {

	@Resource(name = "egovItmanMemberDAO")
	EgovItmanMemberDAO egovItmanMemberDAO;
	
	@Override
	public EgovItmanMemberVO selectMember(EgovItmanMemberVO egovItmanMemberVO) {
		return egovItmanMemberDAO.selectMember(egovItmanMemberVO);
	}

	@Override
	public EgovItmanMemberVO selectPhoneEdit(String memIdx) {
		return egovItmanMemberDAO.selectPhoneNumber(memIdx);
	}

	@Override
	public void updateMember(EgovItmanMemberVO memberVO) {
		egovItmanMemberDAO.updateMemTel(memberVO);
	}

	@Override
	public int updateDelYn(String memIdx) {
		return egovItmanMemberDAO.updateDelYn(memIdx);
	}

	@Override
	public EgovItmanGroupVO selectGroupByIdx(String groIdx) {
		return egovItmanMemberDAO.selectGroupByIdx(groIdx);
	}

	@Override
	public int updateGroup(EgovItmanGroupVO egovItmanGroupVO) {
		return egovItmanMemberDAO.updateGroup(egovItmanGroupVO);
	}

	@Override
	public int updateGroupDelYn(EgovItmanGroupVO groupVO) {
		return egovItmanMemberDAO.updateGroupDelYn(groupVO);
	}

}
