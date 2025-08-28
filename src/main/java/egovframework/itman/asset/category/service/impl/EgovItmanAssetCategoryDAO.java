package egovframework.itman.asset.category.service.impl;

import java.util.List;
import java.util.Map;

import org.egovframe.rte.psl.dataaccess.EgovAbstractMapper;
import org.springframework.stereotype.Repository;

import egovframework.itman.asset.category.service.EgovItmanAssetCategoryVO;

@Repository("egovItmanAssetCategoryDAO")
public class EgovItmanAssetCategoryDAO extends EgovAbstractMapper{

	public List<EgovItmanAssetCategoryVO> selectAssetCategoryList(Map<String, Object> paramMap) {
		return selectList("egovItmanAssetCategoryDAO.selectAssetCategoryList", paramMap);
	}

	public int insertAssetCategory(EgovItmanAssetCategoryVO egovItmanAssetCategoryVO) {
		return insert("egovItmanAssetCategoryDAO.insertAssetCategory", egovItmanAssetCategoryVO);
	}
	
	public String selectAssetCategoryCode(Map<String, Object> params) {
		return selectOne("egovItmanAssetCategoryDAO.selectAssetCategoryCode", params);
	}

	public EgovItmanAssetCategoryVO selectAssetCategory(Map<String, Object> paramMap) {
		return selectOne("egovItmanAssetCategoryDAO.selectAssetCategory", paramMap);
	}

	public int selectAssetCategoryCount(Map<String, Object> paramMap) {
		return selectOne("egovItmanAssetCategoryDAO.selectAssetCategoryCount", paramMap);
	}

	public int updateAssetCategory(EgovItmanAssetCategoryVO egovItmanAssetCategoryVO) {
		return update("egovItmanAssetCategoryDAO.updateAssetCategory", egovItmanAssetCategoryVO);
	}

	public int deleteAssetCategory(EgovItmanAssetCategoryVO egovItmanAssetCategoryVO) {
		int result = update("egovItmanAssetCategoryDAO.deleteAssetCategory", egovItmanAssetCategoryVO);
		return result;
	}
	
}
