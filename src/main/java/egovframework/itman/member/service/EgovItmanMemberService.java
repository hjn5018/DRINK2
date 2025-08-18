package egovframework.itman.member.service;

import egovframework.itman.group.service.EgovItmanGroupVO;

public interface EgovItmanMemberService {

	EgovItmanMemberVO selectMember(EgovItmanMemberVO egovItmanMemberVO);

	EgovItmanMemberVO selectPhoneEdit(String memIdx);

	void updateMember(EgovItmanMemberVO memberVO);

	int updateDelYn(String memIdx);

	EgovItmanGroupVO selectGroupByIdx(String groIdx);

	int updateGroup(EgovItmanGroupVO egovItmanGroupVO);

	int updateGroupDelYn(EgovItmanGroupVO groupVO);
}
