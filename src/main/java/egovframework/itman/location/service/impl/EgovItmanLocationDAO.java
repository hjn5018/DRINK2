package egovframework.itman.location.service.impl;

import java.util.List;
import java.util.Map;

import org.egovframe.rte.psl.dataaccess.EgovAbstractMapper;
import org.springframework.stereotype.Repository;

// import egovframework.itman.group.service.EgovItmanGroupVO;
import egovframework.itman.location.service.EgovItmanLocationVO;

@Repository("egovItmanLocationDAO")
public class EgovItmanLocationDAO extends EgovAbstractMapper{

	public List<EgovItmanLocationVO> selectLocationList(EgovItmanLocationVO searchVO) {
		return selectList("egovItmanLocationDAO.selectLocationList", searchVO);
	}

	public int selectLocationListTotCnt(EgovItmanLocationVO searchVO) {
		return selectOne("egovItmanLocationDAO.selectLocationListTotCnt", searchVO);
	}

	public int insertLocation(EgovItmanLocationVO egovItmanLocationVO) {
		return insert("egovItmanLocationDAO.insertLocation", egovItmanLocationVO);
	}

	public int selectLocationCount(Map<String, Object> paramMap) {
		return selectOne("selectLocationCount", paramMap);
	}

	public List<EgovItmanLocationVO> selectLocationMap(Map<String, Object> paramMap) {
		return selectList("selectLocationMap", paramMap);
	}

	public EgovItmanLocationVO selectLocationOne(Map<String, Object> paramMap) {
		return selectOne("selectLocationOne", paramMap);
	}

	public int updateLocation(EgovItmanLocationVO egovItmanLocationVO) {
		return update("updateLocation", egovItmanLocationVO);
	}

	public int deleteLocation(EgovItmanLocationVO egovItmanLocatsionVO) {
		return delete("deleteLocation", egovItmanLocatsionVO);
	}

	
}
