package egovframework.itman.asset.service;

import java.util.List;
import java.util.Map;

public interface EgovItmanAssetService {

	List<EgovItmanAssetVO> selectAssetList(EgovItmanAssetVO egovItmanAssetVO);

	int selectAssetListTotCnt(EgovItmanAssetVO egovItmanAssetVO);

	int insertAsset(EgovItmanAssetVO egovItmanAssetVO);

	EgovItmanAssetVO selectAsset(String assIdx);

	int deleteAsset(Map<String, Object> params);

	String selectNextUlid(Map<String, Object> params);
	
	EgovItmanAssetVO selectAssetByUlid(String assUlid);
	
	int updateAssetName(EgovItmanAssetVO egovItmanAssetVO);
	int updateAssetCategory(EgovItmanAssetVO egovItmanAssetVO);
	int updateAssetState(EgovItmanAssetVO egovItmanAssetVO);
	int updateAssetLocation(EgovItmanAssetVO egovItmanAssetVO);
	int updateAssetEmployee(EgovItmanAssetVO egovItmanAssetVO);
	int updateAssetSupplier(EgovItmanAssetVO egovItmanAssetVO);
	int updateAssetBuyDate(EgovItmanAssetVO egovItmanAssetVO);
	int updateAssetPrice(EgovItmanAssetVO egovItmanAssetVO);
	int updateAssetImage(EgovItmanAssetVO egovItmanAssetVO);
}
