package egovframework.itman.Estate.service.impl;

import java.util.List;

import org.egovframe.rte.psl.dataaccess.EgovAbstractMapper;
import org.springframework.stereotype.Repository;

import egovframework.itman.Estate.service.EgovItmanEstateSearchVO;
import egovframework.itman.Estate.service.EgovItmanEstateVO;

@Repository("egovItmanEstateDAO")
public class EgovItmanEstateDAO extends EgovAbstractMapper {
	public List selectEstateList(EgovItmanEstateSearchVO searchVO) {
	    return selectList("egovItmanEstateDAO.selectEstateList", searchVO);
	}
    
    public int selectStateListTotCnt(EgovItmanEstateSearchVO searchVO) {
        return (Integer) selectOne("egovItmanEstateDAO.selectStateListTotCnt", searchVO);
    }
    
    public EgovItmanEstateVO selectStateDetail(String stIdx) {
        return (EgovItmanEstateVO) selectOne("egovItmanEstateDAO.selectStateDetail", stIdx);
    }
    
    public void insertEstate(EgovItmanEstateVO stateVO) {
        insert("egovItmanEstateDAO.insertEstate", stateVO);
    }
    
    public void updateEstate(EgovItmanEstateVO stateVO) {
        update("egovItmanEstateDAO.updateEstate", stateVO);
    }    
    public int checkDuplicateStateCode(EgovItmanEstateVO vo) throws Exception {
        return (Integer) selectOne("egovItmanEstateDAO.checkDuplicateStateCode", vo);
    }

	public int deleteEstate(EgovItmanEstateVO stateVO) {
		return update("deleteEstate", stateVO);
	}
}