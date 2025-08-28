package egovframework.itman.division.service;

import java.util.List;
import java.util.Map;

public interface EgovItmanDivisionService {

    List<EgovItmanDivisionVO> selectDepartList(EgovItmanDivisionSearchVO searchVO) throws Exception;

    int selectDepartListTotCnt(EgovItmanDivisionSearchVO searchVO) throws Exception;

    void insertDivision(EgovItmanDivisionVO divisionVO) throws Exception;

    List<EgovItmanDivisionVO> selectDivisionList(String groIdx) throws Exception;

    EgovItmanDivisionVO selectDivisionDetail(String divIdx) throws Exception;

    void updateDivision(EgovItmanDivisionVO divisionVO) throws Exception;

    int deleteDivision(EgovItmanDivisionVO divisionVO) throws Exception;
	String selectDivisionCode(Map<String, Object> params);

}
