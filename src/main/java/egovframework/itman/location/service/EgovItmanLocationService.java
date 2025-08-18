package egovframework.itman.location.service;

import java.util.List;
import java.util.Map;

public interface EgovItmanLocationService {

	List<EgovItmanLocationVO> selectLocationList(EgovItmanLocationVO searchVO);

	int selectLocationListTotCnt(EgovItmanLocationVO searchVO);

	int insertLocation(EgovItmanLocationVO egovItmanLocationVO);

	int selectLocationCount(Map<String, Object> paramMap);

	List<EgovItmanLocationVO> selectLocationMap(Map<String, Object> paramMap);

	EgovItmanLocationVO selectLocationOne(Map<String, Object> paramMap);

	int updateLocation(EgovItmanLocationVO egovItmanLocationVO);

	int deleteLocation(EgovItmanLocationVO egovItmanLocatsionVO);

}
