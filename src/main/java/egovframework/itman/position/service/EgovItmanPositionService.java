// egovframework.itman.position.service.EgovItmanPositionService.java

package egovframework.itman.position.service;

import java.util.List;

public interface EgovItmanPositionService {

    // DAO의 selectList가 List<?>를 반환하므로, 인터페이스도 List<?>로 변경
    List<?> selectPositionList(EgovItmanPositionSearchVO searchVO) throws Exception;
    
    // DAO의 selectPositionListTotCnt와 메서드 이름 통일
    int selectPositionListTotCnt(EgovItmanPositionSearchVO searchVO) throws Exception;

    void insertPosition(EgovItmanPositionVO positionVO) throws Exception;

    EgovItmanPositionVO selectPositionDetail(String posIdx) throws Exception;

    void updatePosition(EgovItmanPositionVO positionVO) throws Exception;
    
    int deletePosition(EgovItmanPositionVO positionVO) throws Exception;
}