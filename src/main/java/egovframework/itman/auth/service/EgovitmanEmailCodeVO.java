package egovframework.itman.auth.service;

public class EgovitmanEmailCodeVO {
	private String exIdx;
	private String email; 
	private String memName;
	private String ecNum;
	private String ecMode;
	private String regDate;
	
	public String getEx_Idx() {
		return exIdx;
	}
	public void setEx_Idx(String ex_Idx) {
		this.exIdx = ex_Idx;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMemName() {
		return memName;
	}
	public void setMemName(String memName) {
		this.memName = memName;
	}
	public String getEcNum() {
		return ecNum;
	}
	public void setEcNum(String ecNum) {
		this.ecNum = ecNum;
	}
	public String getEcMode() {
		return ecMode;
	}
	public void setEcMode(String ecMode) {
		this.ecMode = ecMode;
	}
	public String getRegDate() {
		return regDate;
	}
	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}
	
	
}
