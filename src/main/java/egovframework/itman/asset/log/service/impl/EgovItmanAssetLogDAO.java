package egovframework.itman.asset.log.service.impl;

import java.util.List;

import org.egovframe.rte.psl.dataaccess.EgovAbstractMapper;
import org.springframework.stereotype.Repository;

import egovframework.itman.asset.log.service.EgovItmanAssetLogVO;

@Repository("egovItmanAssetLogDAO")
public class EgovItmanAssetLogDAO extends EgovAbstractMapper{

	public int insertLog(EgovItmanAssetLogVO egovItmanAssetLogVO) {
		return insert("egovItmanAssetLogDAO.insertAssetLog", egovItmanAssetLogVO);
	}
	
	public List<EgovItmanAssetLogVO> selectAssetLogList(EgovItmanAssetLogVO egovItmanAssetLogVO) {
		return selectList("egovItmanAssetLogDAO.selectAssetLogList", egovItmanAssetLogVO);
	}
	
		public int selectAssetLogListTotCnt(EgovItmanAssetLogVO egovItmanAssetLogVO) {
		return selectOne("egovItmanAssetLogDAO.selectAssetLogListTotCnt", egovItmanAssetLogVO);
	}

	public List<EgovItmanAssetLogVO> selectAssetLogListByAssIdx(String assIdx) {
		return selectList("egovItmanAssetLogDAO.selectAssetLogListByAssIdx", assIdx);
	}
}
