package egovframework.itman.employee.service.impl;

import java.util.List;
import java.util.Map;

import org.egovframe.rte.psl.dataaccess.EgovAbstractMapper;
import org.springframework.stereotype.Repository;

import egovframework.itman.division.service.EgovItmanDivisionVO;
import egovframework.itman.employee.service.EgovItmanEmployeePositionVO;
import egovframework.itman.employee.service.EgovItmanEmployeeSearchVO;
import egovframework.itman.employee.service.EgovItmanEmployeeStateVO;
import egovframework.itman.employee.service.EgovItmanEmployeeVO;
import egovframework.itman.group.service.EgovItmanGroupVO;

@Repository("egovItmanEmployeeDAO")
public class EgovItmanEmployeeDAO extends EgovAbstractMapper {

    public List<?> selectEmploList(EgovItmanEmployeeSearchVO searchVO) {
        return selectList("egovItmanEmployeeDAO.selectEmploList", searchVO);
    }

    public int selectEmploListTotCnt(EgovItmanEmployeeSearchVO searchVO) {
        return (Integer) selectOne("egovItmanEmployeeDAO.selectEmploListTotCnt", searchVO);
    }

    // ▼▼▼ 이 메서드의 내용을 수정합니다 ▼▼▼
    public int countEmployeeInGroup(EgovItmanGroupVO groupVO) {
        // "egovItmanEmployeeDAO.countEmployeeInGroup" 라는 ID를 가진 SQL을 실행합니다.
        return (Integer) selectOne("egovItmanEmployeeDAO.countEmployeeInGroup", groupVO);
    }

    public void insertDivision(EgovItmanDivisionVO divisionVO) {
        insert("egovItmanEmployeeDAO.insertDivision", divisionVO);
    }

    public List<?> selectDivisionList(String groIdx) {
        return selectList("egovItmanEmployeeDAO.selectDivisionList", groIdx);
    }

    public List<?> selectPositionList(String groIdx) {
        return selectList("egovItmanEmployeeDAO.selectPositionList", groIdx);
    }

    public List<?> selectStateList(String groIdx) {
        return selectList("egovItmanEmployeeDAO.selectStateList", groIdx);
    }
    public void insertEmplo(EgovItmanEmployeeVO emploVO) {
        insert("egovItmanEmployeeDAO.insertEmplo", emploVO);
    }
 // 직원 상세 정보 조회
    public EgovItmanEmployeeVO selectEmploView(String empIdx) {
        return (EgovItmanEmployeeVO) selectOne("egovItmanEmployeeDAO.selectEmploView", empIdx);
    }
    
    // 직원 소유 자산 목록 조회
    public List<?> selectEmploAssetList(String empIdx) {
        return selectList("egovItmanEmployeeDAO.selectEmploAssetList", empIdx);
    }

    public void insertPosition(EgovItmanEmployeePositionVO positionVO) {
	    insert("egovItmanEmployeeDAO.insertPosition", positionVO);
	}
	public void insertState(EgovItmanEmployeeStateVO stateVO) {
	    insert("egovItmanEmployeeDAO.insertState", stateVO);
	}

	public List<EgovItmanEmployeeVO> selectEmployeeList(EgovItmanGroupVO egovItmanGroupVO) {
		return selectList("egovItmanEmployeeDAO.selectEmployeeList", egovItmanGroupVO);
	}
	public void deleteEmplo(EgovItmanEmployeeVO emploVO) {
        delete("egovItmanEmployeeDAO.deleteEmplo", emploVO);
    }

    public EgovItmanEmployeeVO selectEmploBasicByGroup(Map<String, Object> params) {
        return selectOne("egovItmanEmployeeDAO.selectEmploBasicByGroup", params);
    }
}
