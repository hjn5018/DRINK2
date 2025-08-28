package egovframework.itman.employee.service;

import java.util.Date;

public class EgovItmanEmployeeStateVO {

    private String empStIdx;
    private String groIdx;
    private String empStName;
    private String empStCode;
    private String delYn;
    private String regIdx;
    private Date regDate;

    // --- 모든 필드에 대한 Getters and Setters ---
    public String getEmpStIdx() { return empStIdx; }
    public void setEmpStIdx(String empStIdx) { this.empStIdx = empStIdx; }
    public String getGroIdx() { return groIdx; }
    public void setGroIdx(String groIdx) { this.groIdx = groIdx; }
    public String getEmpStName() { return empStName; }
    public void setEmpStName(String empStName) { this.empStName = empStName; }
    public String getEmpStCode() { return empStCode; }
    public void setEmpStCode(String empStCode) { this.empStCode = empStCode; }
    public String getDelYn() { return delYn; }
    public void setDelYn(String delYn) { this.delYn = delYn; }
    public String getRegIdx() { return regIdx; }
    public void setRegIdx(String regIdx) { this.regIdx = regIdx; }
    public Date getRegDate() { return regDate; }
    public void setRegDate(Date regDate) { this.regDate = regDate; }
}