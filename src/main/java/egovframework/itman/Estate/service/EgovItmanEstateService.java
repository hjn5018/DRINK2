package egovframework.itman.Estate.service;

import java.util.List;

public interface EgovItmanEstateService {
    List<EgovItmanEstateVO> selectEstateList(EgovItmanEstateSearchVO searchVO) throws Exception;
    int selectStateListTotCnt(EgovItmanEstateSearchVO searchVO) throws Exception;
    EgovItmanEstateVO selectStateDetail(String stIdx) throws Exception;
    void insertEstate(EgovItmanEstateVO stateVO) throws Exception;
    void updateEstate(EgovItmanEstateVO stateVO) throws Exception;
    int deleteEstate(EgovItmanEstateVO stateVO) throws Exception;
}