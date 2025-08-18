package egovframework.itman.asset.category.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.itman.asset.category.service.EgovItmanAssetCategoryService;
import egovframework.itman.asset.category.service.EgovItmanAssetCategoryVO;

@Service("egovItmanAssetCategoryService")
public class EgovItmanAssetCategoryServiceImpl implements EgovItmanAssetCategoryService{

	@Resource(name = "egovItmanAssetCategoryDAO")
	EgovItmanAssetCategoryDAO egovItmanAssetCategoryDAO;

	@Override
	public List<EgovItmanAssetCategoryVO> selectAssetCategoryList(Map<String, Object> paramMap) {
		return egovItmanAssetCategoryDAO.selectAssetCategoryList(paramMap);
	}

	@Override
	public int insertAssetCategory(EgovItmanAssetCategoryVO egovItmanAssetCategoryVO) {
		return egovItmanAssetCategoryDAO.insertAssetCategory(egovItmanAssetCategoryVO);
	}
	
	@Override
	public String selectAssetCategoryCode(Map<String, Object> params) {
		return egovItmanAssetCategoryDAO.selectAssetCategoryCode(params);
	}

	@Override
	public EgovItmanAssetCategoryVO selectAssetCategory(Map<String, Object> paramMap) {
		return egovItmanAssetCategoryDAO.selectAssetCategory(paramMap);
	}

	@Override
	public int selectAssetCategoryCount(Map<String, Object> paramMap) {
		return egovItmanAssetCategoryDAO.selectAssetCategoryCount(paramMap);
	}

	@Override
	public int updateAssetCategory(EgovItmanAssetCategoryVO egovItmanAssetCategoryVO) {
		return egovItmanAssetCategoryDAO.updateAssetCategory(egovItmanAssetCategoryVO);
	}

	@Override
	public int deleteAssetCategory(EgovItmanAssetCategoryVO egovItmanAssetCategoryVO) {
		int result = egovItmanAssetCategoryDAO.deleteAssetCategory(egovItmanAssetCategoryVO);
		return result;
	}

}
