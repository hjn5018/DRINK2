package egovframework.itman.supplier.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.itman.supplier.service.EgovItmanSupplierService;
import egovframework.itman.supplier.service.EgovItmanSupplierVO;

@Service("egovItmanSupplierService")
public class EgovItmanSupplierServiceImpl implements EgovItmanSupplierService{

	@Resource(name = "egovItmanSupplierDAO")
	EgovItmanSupplierDAO egovItmanSupplierDAO;

	@Override
	public List<EgovItmanSupplierVO> selectSupplierList(Map<String, Object> paramMap) {
		return egovItmanSupplierDAO.selectSupplierList(paramMap);
	}

	@Override
	public int insertSupplier(EgovItmanSupplierVO egovItmanSupplierVO) {
		return egovItmanSupplierDAO.insertSupplier(egovItmanSupplierVO);
	}

	@Override
	public int selectSupplierCount(Map<String, Object> paramMap) {
		return egovItmanSupplierDAO.selectSupplierCount(paramMap);
	}

	@Override
	public int updateSupplier(EgovItmanSupplierVO egovItmanSupplierVO) {
		return egovItmanSupplierDAO.updateSupplier(egovItmanSupplierVO);
	}

	@Override
	public EgovItmanSupplierVO selectSupplierOne(Map<String, Object> paramMap) {
		return egovItmanSupplierDAO.selectSupplierOne(paramMap);
	}

	@Override
	public int deleteSupplier(EgovItmanSupplierVO egovItmanSupplierVO) {
		return egovItmanSupplierDAO.deleteSupplier(egovItmanSupplierVO);
	}
	
}
