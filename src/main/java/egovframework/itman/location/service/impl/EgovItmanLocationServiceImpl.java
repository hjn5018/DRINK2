package egovframework.itman.location.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.egovframe.rte.fdl.cmmn.EgovAbstractServiceImpl;
import org.springframework.stereotype.Service;

// import egovframework.itman.group.service.EgovItmanGroupVO;
import egovframework.itman.location.service.EgovItmanLocationService;
import egovframework.itman.location.service.EgovItmanLocationVO;

@Service("egovItmanLocationService")
public class EgovItmanLocationServiceImpl extends EgovAbstractServiceImpl implements EgovItmanLocationService{

	@Resource(name = "egovItmanLocationDAO")
	EgovItmanLocationDAO egovItmanLocationDAO;

	@Override
	public List<EgovItmanLocationVO> selectLocationList(EgovItmanLocationVO searchVO) {
		return egovItmanLocationDAO.selectLocationList(searchVO);
	}

	@Override
	public int selectLocationListTotCnt(EgovItmanLocationVO searchVO) {
		return egovItmanLocationDAO.selectLocationListTotCnt(searchVO);
	}

	@Override
	public int insertLocation(EgovItmanLocationVO egovItmanLocationVO) {
		return egovItmanLocationDAO.insertLocation(egovItmanLocationVO);
	}

	@Override
	public int selectLocationCount(Map<String, Object> paramMap) {
		return egovItmanLocationDAO.selectLocationCount(paramMap);
	}

	@Override
	public List<EgovItmanLocationVO> selectLocationMap(Map<String, Object> paramMap) {
		return egovItmanLocationDAO.selectLocationMap(paramMap);
	}

	@Override
	public EgovItmanLocationVO selectLocationOne(Map<String, Object> paramMap) {
		return egovItmanLocationDAO.selectLocationOne(paramMap);
	}

	@Override
	public int updateLocation(EgovItmanLocationVO egovItmanLocationVO) {
		return egovItmanLocationDAO.updateLocation(egovItmanLocationVO);
	}

	@Override
	public int deleteLocation(EgovItmanLocationVO egovItmanLocatsionVO) {
		return egovItmanLocationDAO.deleteLocation(egovItmanLocatsionVO);
	}
	
}
