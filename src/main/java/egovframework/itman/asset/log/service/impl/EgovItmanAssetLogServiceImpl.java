package egovframework.itman.asset.log.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.itman.asset.log.service.EgovItmanAssetLogService;
import egovframework.itman.asset.log.service.EgovItmanAssetLogVO;

@Service("egovItmanAssetLogService")
public class EgovItmanAssetLogServiceImpl implements EgovItmanAssetLogService{
	
	@Resource(name = "egovItmanAssetLogDAO")
	EgovItmanAssetLogDAO egovItmanAssetLogDAO;
	
	@Override
	public int insertLog(EgovItmanAssetLogVO egovItmanAssetLogVO) {
		return egovItmanAssetLogDAO.insertLog(egovItmanAssetLogVO);
	}
	
	@Override
	public List<EgovItmanAssetLogVO> selectAssetLogList(EgovItmanAssetLogVO egovItmanAssetLogVO) {
		return egovItmanAssetLogDAO.selectAssetLogList(egovItmanAssetLogVO);
	}
	
		@Override
	public int selectAssetLogListTotCnt(EgovItmanAssetLogVO egovItmanAssetLogVO) {
		return egovItmanAssetLogDAO.selectAssetLogListTotCnt(egovItmanAssetLogVO);
	}

	@Override
	public List<EgovItmanAssetLogVO> selectAssetLogListByAssIdx(String assIdx) {
		return egovItmanAssetLogDAO.selectAssetLogListByAssIdx(assIdx);
	}
}
