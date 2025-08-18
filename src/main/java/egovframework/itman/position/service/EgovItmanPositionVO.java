package egovframework.itman.position.service;

import egovframework.com.cmm.ComDefaultVO;

public class EgovItmanPositionVO extends ComDefaultVO {
    
    private static final long serialVersionUID = 1L;

    private String groIdx;
    private String posIdx;
    private String posName;
    private String posCode;
    private String slNote;
    private String regIdx;
    private String regDt;
    private String updIdx;
    private String updDt;
    private String delYn;
    private String delIdx; // Add this field
  
    private String delDate; // Add this field
    
    // Getters and Setters for all fields
    
    public String getGroIdx() {
        return groIdx;
    }
    public void setGroIdx(String groIdx) {
        this.groIdx = groIdx;
    }
    
    public String getPosIdx() {
        return posIdx;
    }
    public void setPosIdx(String posIdx) {
        this.posIdx = posIdx;
    }
    
    public String getPosName() {
        return posName;
    }
    public void setPosName(String posName) {
        this.posName = posName;
    }
    
    public String getPosCode() {
        return posCode;
    }
    public void setPosCode(String posCode) {
        this.posCode = posCode;
    }
    
    public String getSlNote() {
        return slNote;
    }
    public void setSlNote(String slNote) {
        this.slNote = slNote;
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
    
    public String getDelYn() {
        return delYn;
    }
    public void setDelYn(String delYn) {
        this.delYn = delYn;
    }

    // Add the missing getter and setter for delIdx
    public String getDelIdx() {
        return delIdx;
    }
    public void setDelIdx(String delIdx) {
        this.delIdx = delIdx;
    }



    // Add the missing getter and setter for delDate
    public String getDelDate() {
        return delDate;
    }
    public void setDelDate(String delDate) {
        this.delDate = delDate;
    }
}