package egovframework.itman.asset.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.itman.asset.service.EgovItmanAssetService;
import egovframework.itman.asset.service.EgovItmanAssetVO;

@Service("egovItmanAssetService")
public class EgovItmanAssetServiceImpl implements EgovItmanAssetService{
	
	@Resource(name="egovItmanAssetDAO")
	EgovItmanAssetDAO egovItmanAssetDAO;

	@Override
	public List<EgovItmanAssetVO> selectAssetList(EgovItmanAssetVO egovItmanAssetVO) {
		
		return egovItmanAssetDAO.selectAssetList(egovItmanAssetVO);
	}

	@Override
	public int selectAssetListTotCnt(EgovItmanAssetVO egovItmanAssetVO) {
		return egovItmanAssetDAO.selectAssetListTotCnt(egovItmanAssetVO);
	}

	@Override
	public int insertAsset(EgovItmanAssetVO egovItmanAssetVO) {
		return egovItmanAssetDAO.insertAsset(egovItmanAssetVO);
	}

	@Override
	public EgovItmanAssetVO selectAsset(String assIdx) {
		return egovItmanAssetDAO.selectAsset(assIdx);
	}

	@Override
	public int deleteAsset(Map<String, Object> params) {
		return egovItmanAssetDAO.deleteAsset(params);
	}

	@Override
	public String selectNextUlid(Map<String, Object> params) {
		return egovItmanAssetDAO.selectNextUlid(params);
	}
	
	@Override
	public EgovItmanAssetVO selectAssetByUlid(String assUlid) {
		return egovItmanAssetDAO.selectAssetByUlid(assUlid);
	}
	
	@Override
	public int updateAssetName(EgovItmanAssetVO egovItmanAssetVO) {
		return egovItmanAssetDAO.updateAssetName(egovItmanAssetVO);
	}
	
	@Override
	public int updateAssetCategory(EgovItmanAssetVO egovItmanAssetVO) {
		return egovItmanAssetDAO.updateAssetCategory(egovItmanAssetVO);
	}
	
	@Override
	public int updateAssetState(EgovItmanAssetVO egovItmanAssetVO) {
		return egovItmanAssetDAO.updateAssetState(egovItmanAssetVO);
	}
	
	@Override
	public int updateAssetLocation(EgovItmanAssetVO egovItmanAssetVO) {
		return egovItmanAssetDAO.updateAssetLocation(egovItmanAssetVO);
	}
	
	@Override
	public int updateAssetEmployee(EgovItmanAssetVO egovItmanAssetVO) {
		return egovItmanAssetDAO.updateAssetEmployee(egovItmanAssetVO);
	}
	
	@Override
	public int updateAssetSupplier(EgovItmanAssetVO egovItmanAssetVO) {
		return egovItmanAssetDAO.updateAssetSupplier(egovItmanAssetVO);
	}
	
	@Override
	public int updateAssetBuyDate(EgovItmanAssetVO egovItmanAssetVO) {
		return egovItmanAssetDAO.updateAssetBuyDate(egovItmanAssetVO);
	}
	
	@Override
	public int updateAssetPrice(EgovItmanAssetVO egovItmanAssetVO) {
		return egovItmanAssetDAO.updateAssetPrice(egovItmanAssetVO);
	}
	
	@Override
	public int updateAssetImage(EgovItmanAssetVO egovItmanAssetVO) {
		return egovItmanAssetDAO.updateAssetImage(egovItmanAssetVO);
	}
}

