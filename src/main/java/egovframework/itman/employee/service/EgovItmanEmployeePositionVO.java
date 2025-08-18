package egovframework.itman.employee.service;

import java.util.Date;

public class EgovItmanEmployeePositionVO {

    private String posIdx;
    private String groIdx;
    private String posName;
    private String posCode;
    private String delYn;
    private String regIdx;
    private Date regDate;

    // --- 모든 필드에 대한 Getters and Setters ---
    public String getPosIdx() { return posIdx; }
    public void setPosIdx(String posIdx) { this.posIdx = posIdx; }
    public String getGroIdx() { return groIdx; }
    public void setGroIdx(String groIdx) { this.groIdx = groIdx; }
    public String getPosName() { return posName; }
    public void setPosName(String posName) { this.posName = posName; }
    public String getPosCode() { return posCode; }
    public void setPosCode(String posCode) { this.posCode = posCode; }
    public String getDelYn() { return delYn; }
    public void setDelYn(String delYn) { this.delYn = delYn; }
    public String getRegIdx() { return regIdx; }
    public void setRegIdx(String regIdx) { this.regIdx = regIdx; }
    public Date getRegDate() { return regDate; }
    public void setRegDate(Date regDate) { this.regDate = regDate; }
}