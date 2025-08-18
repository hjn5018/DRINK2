package egovframework.itman.group.service.impl;

import java.util.List;

import org.egovframe.rte.psl.dataaccess.EgovAbstractMapper;
import org.springframework.stereotype.Repository;

import egovframework.itman.group.service.EgovItmanGroupVO;
import egovframework.itman.member.service.EgovItmanMemberVO;

@Repository("egovItmanGroupDAO")
public class EgovItmanGroupDAO extends EgovAbstractMapper {

	public List<EgovItmanGroupVO> selectGroupList(EgovItmanMemberVO egovItmanMemberVO) {
		return selectList("egovItmanGroupDAO.selectGroupList", egovItmanMemberVO);
	}

	public int insertGroup(EgovItmanGroupVO egovItmanGroupVO) {
		return insert("egovItmanGroupDAO.insertGroup", egovItmanGroupVO);
	}

	public EgovItmanGroupVO selectGroup(EgovItmanGroupVO egovItmanGroupVO) {
		return selectOne("egovItmanGroupDAO.selectGroup", egovItmanGroupVO);
	}
}
