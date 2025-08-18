package egovframework.itman.asset.category.service;

import java.util.List;
import java.util.Map;

public interface EgovItmanAssetCategoryService {

	List<EgovItmanAssetCategoryVO> selectAssetCategoryList(Map<String, Object> paramMap);

	int insertAssetCategory(EgovItmanAssetCategoryVO egovItmanAssetCategoryVO);

	int updateAssetCategory(EgovItmanAssetCategoryVO egovItmanAssetCategoryVO);

	int deleteAssetCategory(EgovItmanAssetCategoryVO egovItmanAssetCategoryVO);

	EgovItmanAssetCategoryVO selectAssetCategory(Map<String, Object> paramMap);

	String selectAssetCategoryCode(Map<String, Object> params);

	int selectAssetCategoryCount(Map<String, Object> paramMap);

}
