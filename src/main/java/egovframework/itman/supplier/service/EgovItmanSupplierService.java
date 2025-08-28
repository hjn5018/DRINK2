package egovframework.itman.supplier.service;

import java.util.List;
import java.util.Map;

public interface EgovItmanSupplierService {

	List<EgovItmanSupplierVO> selectSupplierList(Map<String, Object> paramMap);

	int insertSupplier(EgovItmanSupplierVO egovItmanSupplierVO);

	int selectSupplierCount(Map<String, Object> paramMap);

	int updateSupplier(EgovItmanSupplierVO egovItmanSupplierVO);

	EgovItmanSupplierVO selectSupplierOne(Map<String, Object> paramMap);

	int deleteSupplier(EgovItmanSupplierVO egovItmanSupplierVO);

}
