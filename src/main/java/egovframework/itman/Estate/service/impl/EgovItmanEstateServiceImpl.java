package egovframework.itman.Estate.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.egovframe.rte.fdl.cmmn.EgovAbstractServiceImpl;
import org.egovframe.rte.fdl.cmmn.exception.EgovBizException;
import org.springframework.stereotype.Service;

import egovframework.itman.Estate.service.EgovItmanEstateSearchVO;
import egovframework.itman.Estate.service.EgovItmanEstateService;
import egovframework.itman.Estate.service.EgovItmanEstateVO;

@Service("egovItmanEstateService")
public class EgovItmanEstateServiceImpl extends EgovAbstractServiceImpl implements EgovItmanEstateService {
    
    @Resource(name = "egovItmanEstateDAO")
    private EgovItmanEstateDAO egovItmanEstateDAO;

    @Override
    @SuppressWarnings("unchecked")
    public List<EgovItmanEstateVO> selectEstateList(EgovItmanEstateSearchVO searchVO) throws Exception {
        List rawList = egovItmanEstateDAO.selectEstateList(searchVO);
        return (List<EgovItmanEstateVO>) rawList;
    }
    
    @Override
    public int selectStateListTotCnt(EgovItmanEstateSearchVO searchVO) throws Exception {
        return egovItmanEstateDAO.selectStateListTotCnt(searchVO);
    }
    
    @Override
    public EgovItmanEstateVO selectStateDetail(String stIdx) throws Exception {
        return egovItmanEstateDAO.selectStateDetail(stIdx);
    }
    
    @Override
    public void insertEstate(EgovItmanEstateVO stateVO) throws Exception {
        egovItmanEstateDAO.insertEstate(stateVO);
    }
    
    @Override
    public void updateEstate(EgovItmanEstateVO vo) throws Exception {
        // 1. 상태 코드가 중복되는지 먼저 확인합니다.
        int duplicateCount = egovItmanEstateDAO.checkDuplicateStateCode(vo);
        
        // 2. 중복된 코드가 있다면, EgovBizException을 발생시켜 Controller에 알립니다.
        if (duplicateCount > 0) {
            throw new EgovBizException("직원 상태 코드가 중복입니다. 다시 입력해 주세요.");
        }
        
        // 3. 중복이 없으면, 업데이트를 수행합니다.
        egovItmanEstateDAO.updateEstate(vo);
    }

	@Override
	public int deleteEstate(EgovItmanEstateVO stateVO) throws Exception {
		return egovItmanEstateDAO.deleteEstate(stateVO);
	}
    
    
}