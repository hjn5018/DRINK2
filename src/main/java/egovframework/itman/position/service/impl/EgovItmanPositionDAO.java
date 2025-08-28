// egovframework.itman.position.service.impl.EgovItmanPositionDAO.java
package egovframework.itman.position.service.impl;

import java.util.List;

import org.egovframe.rte.psl.dataaccess.EgovAbstractMapper;
import org.springframework.stereotype.Repository;

import egovframework.itman.position.service.EgovItmanPositionSearchVO;
import egovframework.itman.position.service.EgovItmanPositionVO;

@Repository("egovItmanPositionDAO")
public class EgovItmanPositionDAO extends EgovAbstractMapper {

    /**
     * 직위 목록을 조회합니다.
     * @param searchVO 직위 검색 조건을 담고 있는 VO
     * @return 직위 목록
     */
    public List<?> selectPositionList(EgovItmanPositionSearchVO searchVO) {
        return selectList("egovItmanPositionDAO.selectPositionList", searchVO);
    }
    
    /**
     * 직위 목록의 총 개수를 조회합니다.
     * @param searchVO 직위 검색 조건을 담고 있는 VO
     * @return 직위 총 개수
     */
    public int selectPositionListTotCnt(EgovItmanPositionSearchVO searchVO) {
        return (Integer) selectOne("egovItmanPositionDAO.selectPositionListTotCnt", searchVO);
    }
    
    /**
     * 직위 정보를 등록합니다.
     * @param positionVO 등록할 직위 정보를 담고 있는 VO
     */
    public void insertPosition(EgovItmanPositionVO positionVO) {
        insert("egovItmanPositionDAO.insertPosition", positionVO);
    }
    
    /**
     * 직위 상세 정보를 조회합니다.
     * @param posIdx 직위 고유번호
     * @return 직위 상세 정보 VO
     */
    public EgovItmanPositionVO selectPositionDetail(String posIdx) {
        return (EgovItmanPositionVO) selectOne("egovItmanPositionDAO.selectPositionDetail", posIdx);
    }
    
    /**
     * 직위 정보를 수정합니다.
     * @param positionVO 수정할 직위 정보를 담고 있는 VO
     */
    public void updatePosition(EgovItmanPositionVO positionVO) {
        update("egovItmanPositionDAO.updatePosition", positionVO);
    }
    
    /**
     * 직위 정보를 삭제(논리적 삭제)합니다.
     * @param positionVO 삭제할 직위 정보를 담고 있는 VO
     */
    public int deletePosition(EgovItmanPositionVO positionVO) {
        return update("egovItmanPositionDAO.deletePosition", positionVO);
    }
}