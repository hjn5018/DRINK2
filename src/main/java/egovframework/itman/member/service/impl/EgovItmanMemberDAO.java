package egovframework.itman.member.service.impl;

import org.egovframe.rte.psl.dataaccess.EgovAbstractMapper;
import org.springframework.stereotype.Repository;

import egovframework.itman.group.service.EgovItmanGroupVO;
import egovframework.itman.member.service.EgovItmanMemberVO;

@Repository("egovItmanMemberDAO")
public class EgovItmanMemberDAO extends EgovAbstractMapper {

	public EgovItmanMemberVO selectMember(EgovItmanMemberVO egovItmanMemberVO) {
		return selectOne("egovItmanMemberDAO.selectMember", egovItmanMemberVO);
	}

	public EgovItmanMemberVO selectPhoneNumber(String memIdx) {
		return selectOne("selectPhoneNumber", memIdx);
	}

	public void updateMemTel(EgovItmanMemberVO memberVO) {
		update("updateMemTel", memberVO);
	}

	public int updateDelYn(String memIdx) {
		return update("updateDelYn", memIdx);
	}

	public EgovItmanGroupVO selectGroupByIdx(String groIdx) {
		return selectOne("selectGroupByIdx", groIdx);
	}

	public int updateGroup(EgovItmanGroupVO egovItmanGroupVO) {
		return update("updateGroup", egovItmanGroupVO);
	}

	public int updateGroupDelYn(EgovItmanGroupVO groupVO) {
		return update("updateGroupDelYn", groupVO);
	}
}
