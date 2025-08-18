package egovframework.itman.Estate.service;

import egovframework.com.cmm.ComDefaultVO;

public class EgovItmanEstateVO extends ComDefaultVO {
    private static final long serialVersionUID = 1L;
    
    private String groIdx;
    private String stIdx;
    private String stName;
    private String slNote;
    private String delYn;
    private String regIdx;
    private String regDt;
    private String updIdx;
    private String updDt;
    private String delIdx;
    private String delReason;
    private String delDate;
    private int empCnt; // 직원 수를 저장하는 필드
    private String stCode;
    private String stYn;
    
    // Getter and Setter
    public String getGroIdx() {
        return groIdx;
    }

    public void setGroIdx(String groIdx) {
        this.groIdx = groIdx;
    }

    public String getStIdx() {
        return stIdx;
    }

    public void setStIdx(String stIdx) {
        this.stIdx = stIdx;
    }

    public String getStName() {
        return stName;
    }

    public void setStName(String stName) {
        this.stName = stName;
    }

    public String getSlNote() {
        return slNote;
    }

    public void setSlNote(String slNote) {
        this.slNote = slNote;
    }

    public String getDelYn() {
        return delYn;
    }

    public void setDelYn(String delYn) {
        this.delYn = delYn;
    }

    public String getRegIdx() {
        return regIdx;
    }

    public void setRegIdx(String regIdx) {
        this.regIdx = regIdx;
    }

    public String getRegDt() {
        return regDt;
    }

    public void setRegDt(String regDt) {
        this.regDt = regDt;
    }

    public String getUpdIdx() {
        return updIdx;
    }

    public void setUpdIdx(String updIdx) {
        this.updIdx = updIdx;
    }

    public String getUpdDt() {
        return updDt;
    }

    public void setUpdDt(String updDt) {
        this.updDt = updDt;
    }

    public String getDelIdx() {
        return delIdx;
    }

    public void setDelIdx(String delIdx) {
        this.delIdx = delIdx;
    }

    public String getDelReason() {
        return delReason;
    }

    public void setDelReason(String delReason) {
        this.delReason = delReason;
    }

    public String getDelDate() {
        return delDate;
    }

    public void setDelDate(String delDate) {
        this.delDate = delDate;
    }

    public int getEmpCnt() {
        return empCnt;
    }

    public void setEmpCnt(int empCnt) {
        this.empCnt = empCnt;
    }
    public String getStCode() {
        return stCode;
    }

    public void setStCode(String stCode) {
        this.stCode = stCode;
    }
    public String getStYn() {
        return stYn;
    }
    public void setStYn(String stYn) {
        this.stYn = stYn;
    }
}
