package egovframework.itman.state.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.itman.group.service.EgovItmanGroupVO;
import egovframework.itman.state.service.EgovItmanStateService;
import egovframework.itman.state.service.EgovItmanStateVO;

@Service("egovItmanStateService")
public class EgovItmanStateServiceImpl implements EgovItmanStateService{

	@Resource(name = "egovItmanStateDAO")
	EgovItmanStateDAO egovItmanStateDAO;
	
	@Override
	public List<EgovItmanStateVO> selectStateList(EgovItmanGroupVO egovItmanGroupVO) {
		return egovItmanStateDAO.selectStateList(egovItmanGroupVO);
	}

	@Override
	public int insertState(EgovItmanStateVO egovItmanStateVO) {
		return egovItmanStateDAO.insertState(egovItmanStateVO);
	}

	@Override
	public int selectStateCount(Map<String, Object> paramMap) {
		return egovItmanStateDAO.selectStateCount(paramMap);
	}

	@Override
	public List<EgovItmanStateVO> selectStateMap(Map<String, Object> paramMap) {
		return egovItmanStateDAO.selectStateMap(paramMap);
	}

	@Override
	public EgovItmanStateVO selectStateOne(Map<String, Object> paramMap) {
		return egovItmanStateDAO.selectStateOne(paramMap);
	}

	@Override
	public int updateState(EgovItmanStateVO egovItmanStateVO) {
		return egovItmanStateDAO.updateState(egovItmanStateVO);
	}

	@Override
	public int deleteState(EgovItmanStateVO egovItmanStateVO) {
		return egovItmanStateDAO.deleteState(egovItmanStateVO);
	}

	
}
