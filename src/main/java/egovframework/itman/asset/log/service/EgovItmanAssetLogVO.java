package egovframework.itman.asset.log.service;

import egovframework.itman.asset.service.EgovItmanAssetVO;
import egovframework.itman.member.service.EgovItmanMemberVO;

public class EgovItmanAssetLogVO {

	private String alIdx;
	private String assNameLog;
	private String assIdx;
	private String alType;
	private String alCat;
	private String alCont;
	private String alNote;
	private String regDate;
	private String regIdx;
	private String regIp;
	private String groIdx;

	// Search & Paging
	private String searchCondition = "all";
	private String searchKeyword = "";
	private String orderBy = "regDate_desc"; // 추가할 필드
	private int pageIndex = 1;
	private int pageSize = 10;
	private int recordCountPerPage = 10;
	private int firstIndex = 0;
	private int totalRecordCount = 0;
	
	private EgovItmanMemberVO egovItmanMemberVO;
	private EgovItmanAssetVO egovItmanAssetVO;
	
	public EgovItmanMemberVO getEgovItmanMemberVO() {
		return egovItmanMemberVO;
	}

	public void setEgovItmanMemberVO(EgovItmanMemberVO egovItmanMemberVO) {
		this.egovItmanMemberVO = egovItmanMemberVO;
	}

	public EgovItmanAssetVO getEgovItmanAssetVO() {
		return egovItmanAssetVO;
	}

	public void setEgovItmanAssetVO(EgovItmanAssetVO egovItmanAssetVO) {
		this.egovItmanAssetVO = egovItmanAssetVO;
	}

	public String getAlIdx() {
		return alIdx;
	}

	public void setAlIdx(String alIdx) {
		this.alIdx = alIdx;
	}

	public String getAssNameLog() {
		return assNameLog;
	}

	public void setAssNameLog(String assNameLog) {
		this.assNameLog = assNameLog;
	}

	public String getAssIdx() {
		return assIdx;
	}

	public void setAssIdx(String assIdx) {
		this.assIdx = assIdx;
	}

	public String getAlType() {
		return alType;
	}

	public void setAlType(String alType) {
		this.alType = alType;
	}

	public String getAlCat() {
		return alCat;
	}

	public void setAlCat(String alCat) {
		this.alCat = alCat;
	}

	public String getAlCont() {
		return alCont;
	}

	public void setAlCont(String alCont) {
		this.alCont = alCont;
	}

	public String getAlNote() {
		return alNote;
	}

	public void setAlNote(String alNote) {
		this.alNote = alNote;
	}

	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

	public String getRegIdx() {
		return regIdx;
	}

	public void setRegIdx(String regIdx) {
		this.regIdx = regIdx;
	}

	public String getRegIp() {
		return regIp;
	}

	public void setRegIp(String regIp) {
		this.regIp = regIp;
	}

	public String getGroIdx() {
		return groIdx;
	}

	public void setGroIdx(String groIdx) {
		this.groIdx = groIdx;
	}

	public String getSearchCondition() {
		return searchCondition;
	}

	public void setSearchCondition(String searchCondition) {
		this.searchCondition = searchCondition;
	}

	public String getSearchKeyword() {
		return searchKeyword;
	}

	public void setSearchKeyword(String searchKeyword) {
		this.searchKeyword = searchKeyword;
	}

	public int getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getRecordCountPerPage() {
		return recordCountPerPage;
	}

	public void setRecordCountPerPage(int recordCountPerPage) {
		this.recordCountPerPage = recordCountPerPage;
	}

	public int getFirstIndex() {
		return firstIndex;
	}

	public void setFirstIndex(int firstIndex) {
		this.firstIndex = firstIndex;
	}

		public int getTotalRecordCount() {
		return totalRecordCount;
	}

	public void setTotalRecordCount(int totalRecordCount) {
		this.totalRecordCount = totalRecordCount;
	}

	public String getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}
}
