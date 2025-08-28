package egovframework.itman.group.service;

import java.util.List;

import egovframework.itman.member.service.EgovItmanMemberVO;

public interface EgovItmanGroupService {

	List<EgovItmanGroupVO> selectGroupList(EgovItmanMemberVO egovItmanMemberVO);

	int insertGroup(EgovItmanGroupVO egovItmanGroupVO);

	EgovItmanGroupVO selectGroup(EgovItmanGroupVO egovItmanGroupVO);

}
