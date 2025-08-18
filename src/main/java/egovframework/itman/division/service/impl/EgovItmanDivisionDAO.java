// egovframework.itman.division.service.impl.EgovItmanDivisionDAO.java
package egovframework.itman.division.service.impl;

import java.util.List;
import java.util.Map;

import org.egovframe.rte.psl.dataaccess.EgovAbstractMapper;
import org.springframework.stereotype.Repository;

import egovframework.itman.division.service.EgovItmanDivisionSearchVO;
import egovframework.itman.division.service.EgovItmanDivisionVO;
import egovframework.itman.employee.service.EgovItmanEmployeeVO;

@Repository("egovItmanDivisionDAO")
public class EgovItmanDivisionDAO extends EgovAbstractMapper {
    public List<?> selectDepartList(EgovItmanDivisionSearchVO searchVO) {
        return selectList("egovItmanDivisionDAO.selectDepartList", searchVO);
    }
    public int selectDepartListTotCnt(EgovItmanDivisionSearchVO searchVO) {
        return (Integer) selectOne("egovItmanDivisionDAO.selectDepartListTotCnt", searchVO);
    }
    public List<?> selectDivisionList(String groIdx) {
        return selectList("egovItmanDivisionDAO.selectDivisionList", groIdx);
    }
    public void insertDivision(EgovItmanDivisionVO divisionVO) {
        // 'egovItmanDivisionDAO.insertDivision'은 XML 파일의 <insert> 태그 ID와 네임스페이스입니다.
        insert("egovItmanDivisionDAO.insertDivision", divisionVO);
    }
    // 부서 상세 정보 조회 메소드
    public EgovItmanDivisionVO selectDivisionDetail(String divIdx) {
        return (EgovItmanDivisionVO) selectOne("egovItmanDivisionDAO.selectDivisionDetail", divIdx);
    }
    
    // 부서 정보 수정 메소드
    public void updateDivision(EgovItmanDivisionVO divisionVO) {
        update("egovItmanDivisionDAO.updateDivision", divisionVO);
    }
    public int deleteDivision(EgovItmanDivisionVO divisionVO) {
        return update("egovItmanDivisionDAO.deleteDivision", divisionVO);
    }
    
	public String selectDivision(EgovItmanEmployeeVO egovItmanEmployeeVO) {
		return selectOne("egovItmanDivisionDAO.selectDivision", egovItmanEmployeeVO);
	}

	public String selectDivisionCode(Map<String, Object> params) {
		return selectOne("egovItmanDivisionDAO.selectDivisionCode", params);
	}

}