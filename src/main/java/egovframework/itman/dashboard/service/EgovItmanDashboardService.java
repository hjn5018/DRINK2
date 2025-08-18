package egovframework.itman.dashboard.service;

import java.util.List;

import egovframework.itman.Estate.service.EgovItmanEstateVO;
import egovframework.itman.asset.log.service.EgovItmanAssetLogVO;
import egovframework.itman.group.service.EgovItmanGroupVO;

public interface EgovItmanDashboardService {

	List<EgovItmanEstateVO> selectAssetStateList(EgovItmanGroupVO egovItmanGroupVO);

	List<EgovItmanAssetLogVO> selectAssetLogList(EgovItmanGroupVO egovItmanGroupVO);


	
}
