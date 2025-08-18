package egovframework.itman.division.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.egovframe.rte.fdl.cmmn.EgovAbstractServiceImpl;
import org.springframework.stereotype.Service;

import egovframework.itman.division.service.EgovItmanDivisionSearchVO;
import egovframework.itman.division.service.EgovItmanDivisionService;
import egovframework.itman.division.service.EgovItmanDivisionVO;

@Service("egovItmanDivisionService")
public class EgovItmanDivisionServiceImpl extends EgovAbstractServiceImpl implements EgovItmanDivisionService {

    @Resource(name = "egovItmanDivisionDAO")
    private EgovItmanDivisionDAO egovItmanDivisionDAO;

    @Override
    public List<EgovItmanDivisionVO> selectDepartList(EgovItmanDivisionSearchVO searchVO) throws Exception {
        return (List<EgovItmanDivisionVO>) egovItmanDivisionDAO.selectDepartList(searchVO);
    }

    @Override
    public int selectDepartListTotCnt(EgovItmanDivisionSearchVO searchVO) throws Exception {
        return egovItmanDivisionDAO.selectDepartListTotCnt(searchVO);
    }

    @Override
    public void insertDivision(EgovItmanDivisionVO divisionVO) throws Exception {
        egovItmanDivisionDAO.insertDivision(divisionVO);
    }

    @Override
    public List<EgovItmanDivisionVO> selectDivisionList(String groIdx) throws Exception {
        return (List<EgovItmanDivisionVO>) egovItmanDivisionDAO.selectDivisionList(groIdx);
    }

    @Override
    public EgovItmanDivisionVO selectDivisionDetail(String divIdx) throws Exception {
        return egovItmanDivisionDAO.selectDivisionDetail(divIdx);
    }
    
    @Override
    public void updateDivision(EgovItmanDivisionVO divisionVO) throws Exception {
        egovItmanDivisionDAO.updateDivision(divisionVO);
    }
    
    @Override
    public int deleteDivision(EgovItmanDivisionVO divisionVO) throws Exception {
        return egovItmanDivisionDAO.deleteDivision(divisionVO);
    }
	@Override
	public String selectDivisionCode(Map<String, Object> params) {
		return egovItmanDivisionDAO.selectDivisionCode(params);
	}

}
