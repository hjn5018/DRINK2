package egovframework.itman.dashboard.service.impl;

import java.util.List;

import org.egovframe.rte.psl.dataaccess.EgovAbstractMapper;
import org.springframework.stereotype.Repository;

import egovframework.itman.Estate.service.EgovItmanEstateVO;
import egovframework.itman.asset.log.service.EgovItmanAssetLogVO;
import egovframework.itman.group.service.EgovItmanGroupVO;

@Repository("egovItmanDashboardDAO")
public class EgovItmanDashboardDAO extends EgovAbstractMapper{

	public List<EgovItmanEstateVO> selectAssetStateList(EgovItmanGroupVO egovItmanGroupVO) {
		return selectList("egovItmanDashboardDAO.selectAssetStateList", egovItmanGroupVO);
	}

	public List<EgovItmanAssetLogVO> selectAssetLogList(EgovItmanGroupVO egovItmanGroupVO) {
		return selectList("egovItmanDashboardDAO.selectAssetLogList", egovItmanGroupVO);
	}

}
