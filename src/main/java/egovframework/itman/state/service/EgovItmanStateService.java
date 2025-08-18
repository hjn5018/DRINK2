package egovframework.itman.state.service;

import java.util.List;
import java.util.Map;

import egovframework.itman.group.service.EgovItmanGroupVO;

public interface EgovItmanStateService {

	List<EgovItmanStateVO> selectStateList(EgovItmanGroupVO egovItmanGroupVO);

	int insertState(EgovItmanStateVO egovItmanStateVO);

	int selectStateCount(Map<String, Object> paramMap);

	List<EgovItmanStateVO> selectStateMap(Map<String, Object> paramMap);

	EgovItmanStateVO selectStateOne(Map<String, Object> paramMap);

	int updateState(EgovItmanStateVO egovItmanStateVO);

	int deleteState(EgovItmanStateVO egovItmanStateVO);

}
