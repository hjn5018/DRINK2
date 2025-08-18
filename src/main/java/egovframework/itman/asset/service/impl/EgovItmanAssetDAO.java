package egovframework.itman.asset.service.impl;

import java.util.List;
import java.util.Map;

import org.egovframe.rte.psl.dataaccess.EgovAbstractMapper;
import org.springframework.stereotype.Repository;

import egovframework.itman.asset.service.EgovItmanAssetVO;

@Repository("egovItmanAssetDAO")
public class EgovItmanAssetDAO extends EgovAbstractMapper{

	public List<EgovItmanAssetVO> selectAssetList (EgovItmanAssetVO egovItmanAssetVO) {
		return selectList("egovItmanAssetDAO.selectAssetList", egovItmanAssetVO);
	}

	public int selectAssetListTotCnt(EgovItmanAssetVO egovItmanAssetVO) {
		return selectOne("egovItmanAssetDAO.selectAssetListTotCnt", egovItmanAssetVO);
	}

	public int insertAsset(EgovItmanAssetVO egovItmanAssetVO) {
		return insert("egovItmanAssetDAO.insertAsset", egovItmanAssetVO);
	}

	public EgovItmanAssetVO selectAsset(String assIdx) {
		return selectOne("egovItmanAssetDAO.selectAsset", assIdx);
	}

	public int deleteAsset(Map<String, Object> params) {
		return update("egovItmanAssetDAO.deleteAsset", params);
	}

	public String selectNextUlid(Map<String, Object> params) {
		return selectOne("egovItmanAssetDAO.selectNextUlid", params);
	}
	
	public EgovItmanAssetVO selectAssetByUlid(String assUlid) {
		return selectOne("egovItmanAssetDAO.selectAssetByUlid", assUlid);
	}
	
	public int updateAssetName(EgovItmanAssetVO egovItmanAssetVO) {
		return update("egovItmanAssetDAO.updateAssetName", egovItmanAssetVO);
	}
	
	public int updateAssetCategory(EgovItmanAssetVO egovItmanAssetVO) {
		return update("egovItmanAssetDAO.updateAssetCategory", egovItmanAssetVO);
	}
	
	public int updateAssetState(EgovItmanAssetVO egovItmanAssetVO) {
		return update("egovItmanAssetDAO.updateAssetState", egovItmanAssetVO);
	}
	
	public int updateAssetLocation(EgovItmanAssetVO egovItmanAssetVO) {
		return update("egovItmanAssetDAO.updateAssetLocation", egovItmanAssetVO);
	}
	
	public int updateAssetEmployee(EgovItmanAssetVO egovItmanAssetVO) {
		return update("egovItmanAssetDAO.updateAssetEmployee", egovItmanAssetVO);
	}
	
	public int updateAssetSupplier(EgovItmanAssetVO egovItmanAssetVO) {
		return update("egovItmanAssetDAO.updateAssetSupplier", egovItmanAssetVO);
	}
	
	public int updateAssetBuyDate(EgovItmanAssetVO egovItmanAssetVO) {
		return update("egovItmanAssetDAO.updateAssetBuyDate", egovItmanAssetVO);
	}
	
	public int updateAssetPrice(EgovItmanAssetVO egovItmanAssetVO) {
		return update("egovItmanAssetDAO.updateAssetPrice", egovItmanAssetVO);
	}
	
	public int updateAssetImage(EgovItmanAssetVO egovItmanAssetVO) {
		return update("egovItmanAssetDAO.updateAssetImage", egovItmanAssetVO);
	}
}