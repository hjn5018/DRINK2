// egovframework.itman.position.service.impl.EgovItmanPositionServiceImpl.java

package egovframework.itman.position.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.egovframe.rte.fdl.cmmn.EgovAbstractServiceImpl;
import org.springframework.stereotype.Service;

import egovframework.itman.position.service.EgovItmanPositionSearchVO;
import egovframework.itman.position.service.EgovItmanPositionService;
import egovframework.itman.position.service.EgovItmanPositionVO;

@Service("egovItmanPositionService")
public class EgovItmanPositionServiceImpl extends EgovAbstractServiceImpl implements EgovItmanPositionService {

    /** egovItmanPositionDAO */
    @Resource(name = "egovItmanPositionDAO")
    private EgovItmanPositionDAO egovItmanPositionDAO;
    
    /**
     * 직위 목록을 조회합니다.
     * @param searchVO 직위 검색 조건을 담고 있는 VO
     * @return 직위 목록
     * @throws Exception
     */
    @Override
    public List<?> selectPositionList(EgovItmanPositionSearchVO searchVO) throws Exception {
        return egovItmanPositionDAO.selectPositionList(searchVO);
    }
    
    /**
     * 직위 목록의 총 개수를 조회합니다.
     * @param searchVO 직위 검색 조건을 담고 있는 VO
     * @return 직위 총 개수
     * @throws Exception
     */
    @Override
    public int selectPositionListTotCnt(EgovItmanPositionSearchVO searchVO) throws Exception {
        return egovItmanPositionDAO.selectPositionListTotCnt(searchVO);
    }
    
    /**
     * 직위 정보를 등록합니다.
     * @param positionVO 등록할 직위 정보를 담고 있는 VO
     * @throws Exception
     */
    @Override
    public void insertPosition(EgovItmanPositionVO positionVO) throws Exception {
        egovItmanPositionDAO.insertPosition(positionVO);
    }
    
    /**
     * 직위 상세 정보를 조회합니다.
     * @param posIdx 직위 고유번호
     * @return 직위 상세 정보 VO
     * @throws Exception
     */
    @Override
    public EgovItmanPositionVO selectPositionDetail(String posIdx) throws Exception {
        return egovItmanPositionDAO.selectPositionDetail(posIdx);
    }
    
    /**
     * 직위 정보를 수정합니다.
     * @param positionVO 수정할 직위 정보를 담고 있는 VO
     * @throws Exception
     */
    @Override
    public void updatePosition(EgovItmanPositionVO positionVO) throws Exception {
        egovItmanPositionDAO.updatePosition(positionVO);
    }
    
    /**
     * 직위 정보를 삭제(논리적 삭제)합니다.
     * @param positionVO 삭제할 직위 정보를 담고 있는 VO
     * @throws Exception
     */
    @Override
    public int deletePosition(EgovItmanPositionVO positionVO) throws Exception {
        return egovItmanPositionDAO.deletePosition(positionVO);
    }
}