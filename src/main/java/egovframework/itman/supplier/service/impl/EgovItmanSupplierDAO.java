package egovframework.itman.supplier.service.impl;

import java.util.List;
import java.util.Map;

import org.egovframe.rte.psl.dataaccess.EgovAbstractMapper;
import org.springframework.stereotype.Repository;

import egovframework.itman.supplier.service.EgovItmanSupplierVO;

@Repository("egovItmanSupplierDAO")
public class EgovItmanSupplierDAO extends EgovAbstractMapper{

	public List<EgovItmanSupplierVO> selectSupplierList(Map<String, Object> paramMap) {
		return selectList("egovItmanSupplierDAO.selectSupplierList", paramMap);
	}

	public int insertSupplier(EgovItmanSupplierVO egovItmanSupplierVO) {
		return insert("egovItmanSupplierDAO.insertSupplier", egovItmanSupplierVO);
	}

	public int selectSupplierCount(Map<String, Object> paramMap) {
		return selectOne("egovItmanSupplierDAO.selectSupplierCount", paramMap);
	}

	public int updateSupplier(EgovItmanSupplierVO egovItmanSupplierVO) {
		return update("updateSupplier",egovItmanSupplierVO);
	}

	public EgovItmanSupplierVO selectSupplierOne(Map<String, Object> paramMap) {
		return selectOne("egovItmanSupplierDAO.selectSupplierOne", paramMap);
	}

	public int deleteSupplier(EgovItmanSupplierVO egovItmanSupplierVO) {
		return delete("deleteSupplier", egovItmanSupplierVO);
	}

}
