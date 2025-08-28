package egovframework.itman.asset.log.service;

import java.util.List;

public interface EgovItmanAssetLogService {

	int insertLog(EgovItmanAssetLogVO egovItmanAssetLogVO);
	
	List<EgovItmanAssetLogVO> selectAssetLogList(EgovItmanAssetLogVO egovItmanAssetLogVO);
	
	int selectAssetLogListTotCnt(EgovItmanAssetLogVO egovItmanAssetLogVO);
	
	List<EgovItmanAssetLogVO> selectAssetLogListByAssIdx(String assIdx);
}
