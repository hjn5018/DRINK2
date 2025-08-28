package egovframework.itman.employee.service;

import egovframework.com.cmm.ComDefaultVO; // DefaultVO 대신 ComDefaultVO를 임포트합니다.

/**
 * 직원 검색 정보를 담는 VO
 * @author 
 * @since 
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 * 수정일      수정자          수정내용
 * -------    --------    ---------------------------
 * 2025.08.06   Gemini      최초 생성
 *
 * </pre>
 */
// ComDefaultVO를 상속받도록 변경합니다.
public class EgovItmanEmployeeSearchVO extends ComDefaultVO {

    private static final long serialVersionUID = 1L;

    /** 그룹 IDX (이 VO에만 필요한 고유 필드) */
    private String groIdx;

    /*
     * ComDefaultVO를 상속받았기 때문에
     * searchCondition, searchKeyword, pageIndex 등의 필드는
     * 여기에 선언하지 않아도 부모 클래스로부터 물려받아 바로 사용할 수 있습니다.
     */

    // --- groIdx 필드에 대한 Getter & Setter ---
    public String getGroIdx() {
        return groIdx;
    }

    public void setGroIdx(String groIdx) {
        this.groIdx = groIdx;
    }
}