package egovframework.itman.dashboard.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.itman.Estate.service.EgovItmanEstateVO;
import egovframework.itman.asset.log.service.EgovItmanAssetLogVO;
import egovframework.itman.dashboard.service.EgovItmanDashboardService;
import egovframework.itman.group.service.EgovItmanGroupVO;

@Service("egovItmanDashboardService")
public class EgovItmanDashboardServiceImpl implements EgovItmanDashboardService{

	@Resource(name="egovItmanDashboardDAO")
	EgovItmanDashboardDAO egovItmanDashboardDAO;
	
	@Override
	public List<EgovItmanEstateVO> selectAssetStateList(EgovItmanGroupVO egovItmanGroupVO) {
		return egovItmanDashboardDAO.selectAssetStateList(egovItmanGroupVO);
	}

	@Override
	public List<EgovItmanAssetLogVO> selectAssetLogList(EgovItmanGroupVO egovItmanGroupVO) {
		return egovItmanDashboardDAO.selectAssetLogList(egovItmanGroupVO);
	}


}
