package egovframework.itman.employee.service;

import java.util.List;
import java.util.Map;

import egovframework.itman.division.service.EgovItmanDivisionVO;
import egovframework.itman.group.service.EgovItmanGroupVO; // import 추가

public interface EgovItmanEmployeeService {

    /** 직원 목록 조회 */
    List<?> selectEmploList(EgovItmanEmployeeSearchVO searchVO) throws Exception;

    /** 직원 목록 총 개수 조회 */
    int selectEmploListTotCnt(EgovItmanEmployeeSearchVO searchVO) throws Exception;

    /** 그룹 내 직원 수 조회 */
    int countEmployeeInGroup(EgovItmanGroupVO groupVO);
    
    void insertDivision(EgovItmanDivisionVO divisionVO) throws Exception;
    void insertPosition(EgovItmanEmployeePositionVO positionVO) throws Exception;
    void insertState(EgovItmanEmployeeStateVO stateVO) throws Exception;
    List<?> selectDivisionList(String groIdx) throws Exception;

    List<?> selectPositionList(String groIdx) throws Exception;

    List<?> selectStateList(String groIdx) throws Exception;
    void insertEmplo(EgovItmanEmployeeVO emploVO) throws Exception;

	List<EgovItmanEmployeeVO> selectEmployeeList(EgovItmanGroupVO egovItmanGroupVO);

    EgovItmanEmployeeVO selectEmploView(String empIdx) throws Exception;
    
    List<?> selectEmploAssetList(String empIdx) throws Exception;
    void updateEmploNum(EgovItmanEmployeeVO emploVO) throws Exception;
    void updateEmploName(EgovItmanEmployeeVO emploVO) throws Exception;
    void updateEmploMail(EgovItmanEmployeeVO emploVO) throws Exception;
    void updateEmploTel(EgovItmanEmployeeVO emploVO) throws Exception;
    void updateEmploDivision(EgovItmanEmployeeVO emploVO) throws Exception;
    void updateEmploPos(EgovItmanEmployeeVO emploVO) throws Exception;
    void updateEmploState(EgovItmanEmployeeVO emploVO) throws Exception;
    void deleteEmplo(EgovItmanEmployeeVO emploVO) throws Exception;

    EgovItmanEmployeeVO selectEmploBasicByGroup(Map<String, Object> params);
}
