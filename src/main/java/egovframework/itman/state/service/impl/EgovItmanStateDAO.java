package egovframework.itman.state.service.impl;

import java.util.List;
import java.util.Map;

import org.egovframe.rte.psl.dataaccess.EgovAbstractMapper;
import org.springframework.stereotype.Repository;

import egovframework.itman.group.service.EgovItmanGroupVO;
import egovframework.itman.state.service.EgovItmanStateVO;

@Repository("egovItmanStateDAO")
public class EgovItmanStateDAO extends EgovAbstractMapper{

	public List<EgovItmanStateVO> selectStateList(EgovItmanGroupVO egovItmanGroupVO) {
		return selectList("egovItmanStateDAO.selectStateList", egovItmanGroupVO);
	}

	public int insertState(EgovItmanStateVO egovItmanStateVO) {
		return insert("egovItmanStateDAO.insertState", egovItmanStateVO);
	}

	public int selectStateCount(Map<String, Object> paramMap) {
		return selectOne("selectStateCount",paramMap);
	}

	public List<EgovItmanStateVO> selectStateMap(Map<String, Object> paramMap) {
		return selectList("selectStateMap", paramMap);
	}

	public EgovItmanStateVO selectStateOne(Map<String, Object> paramMap) {
		return selectOne("selectStateOne", paramMap);
	}

	public int updateState(EgovItmanStateVO egovItmanStateVO) {
		return update("updateState", egovItmanStateVO);
	}

	public int deleteState(EgovItmanStateVO egovItmanStateVO) {
		return delete("deleteState", egovItmanStateVO);
	}

	
}
