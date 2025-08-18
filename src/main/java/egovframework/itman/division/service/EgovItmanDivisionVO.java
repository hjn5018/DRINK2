package egovframework.itman.division.service;

import java.io.Serializable;
import java.util.Date;

/**
 * 부서 정보 VO (Value Object)
 * * @author
 * @since 2025.08.07
 * @version 1.0
 */
public class EgovItmanDivisionVO implements Serializable {
    private static final long serialVersionUID = 1L;

    /** 부서 식별자(기본키) */
    private String divIdx;
    /** 그룹 식별자 */
    private String groIdx;
    /** 부서 코드번호 */
    private String divCode;
    /** 부서명 */
    private String divName;
    /** 부서 위치 */
    private String divLoc;
    /** 부서 전화번호 */
    private String divTel;
    /** 사용 여부 (Y/N) */
    private String divYn; // 'useYn'과 동일한 용도로 사용
    /** 등록자 ID */
    private String regIdx;
    /** 등록일 */
    private Date regDate;
    /** 수정자 ID */
    private String modIdx;
    /** 수정일 */
    private Date modDate;
    /** 삭제 여부 (Y/N) */
    private String delYn;
    /** 삭제자 ID */
    private String delIdx;
    /** 삭제일 */
    private Date delDate;
    /** 삭제 사유 */
    private String delReason;
    // --- Getter / Setter ---
    public String getDivIdx() {
        return divIdx;
    }
    public void setDivIdx(String divIdx) {
        this.divIdx = divIdx;
    }
    public String getGroIdx() {
        return groIdx;
    }
    public void setGroIdx(String groIdx) {
        this.groIdx = groIdx;
    }
    public String getDivCode() {
        return divCode;
    }
    public void setDivCode(String divCode) {
        this.divCode = divCode;
    }
    public String getDivName() {
        return divName;
    }
    public void setDivName(String divName) {
        this.divName = divName;
    }
    public String getDivLoc() {
        return divLoc;
    }
    public void setDivLoc(String divLoc) {
        this.divLoc = divLoc;
    }
    public String getDivTel() {
        return divTel;
    }
    public void setDivTel(String divTel) {
        this.divTel = divTel;
    }
    public String getDivYn() {
        return divYn;
    }
    public void setDivYn(String divYn) {
        this.divYn = divYn;
    }
    public String getRegIdx() {
        return regIdx;
    }
    public void setRegIdx(String regIdx) {
        this.regIdx = regIdx;
    }
    public Date getRegDate() {
        return regDate;
    }
    public void setRegDate(Date regDate) {
        this.regDate = regDate;
    }
    public String getModIdx() {
        return modIdx;
    }
    public void setModIdx(String modIdx) {
        this.modIdx = modIdx;
    }
    public Date getModDate() {
        return modDate;
    }
    public void setModDate(Date modDate) {
        this.modDate = modDate;
    }
    public String getDelYn() {
        return delYn;
    }
    public void setDelYn(String delYn) {
        this.delYn = delYn;
    }
    public String getDelIdx() {
        return delIdx;
    }
    public void setDelIdx(String delIdx) {
        this.delIdx = delIdx;
    }
    public Date getDelDate() {
        return delDate;
    }
    public void setDelDate(Date delDate) {
        this.delDate = delDate;
    }
    /**
     * @return the delReason
     */
    public String getDelReason() {
        return delReason;
    }

    /**
     * @param delReason the delReason to set
     */
    public void setDelReason(String delReason) {
        this.delReason = delReason;
    }
}