package egovframework.itman.location.service;

public class EgovItmanLocationVO {

	private String locIdx;
	private String groIdx;
	private String locCode;
	private String locName;
	private String regDate;
	private String regIdx;
	private String modDate;
	private String modIdx;
	private String delYn;
	private String delIdx;
	private String delDate;
	private String slNote;

	public String getLocIdx() {
		return locIdx;
	}

	public void setLocIdx(String locIdx) {
		this.locIdx = locIdx;
	}

	public String getGroIdx() {
		return groIdx;
	}

	public void setGroIdx(String groIdx) {
		this.groIdx = groIdx;
	}

	public String getLocCode() {
		return locCode;
	}

	public void setLocCode(String locCode) {
		this.locCode = locCode;
	}

	public String getLocName() {
		return locName;
	}

	public void setLocName(String locName) {
		this.locName = locName;
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

	public String getModDate() {
		return modDate;
	}

	public void setModDate(String modDate) {
		this.modDate = modDate;
	}

	public String getModIdx() {
		return modIdx;
	}

	public void setModIdx(String modeIdx) {
		this.modIdx = modeIdx;
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

	public String getDelDate() {
		return delDate;
	}

	public void setDelDate(String delDate) {
		this.delDate = delDate;
	}

	public String getSlNote() {
		return slNote;
	}

	public void setSlNote(String slNote) {
		this.slNote = slNote;
	}

	// 페이징 관련 필드
	private int pageIndex = 1;
	private int firstIndex;
	private int lastIndex;
		private int recordCountPerPage = 10;
	private int pageSize = 10;
	private int pageUnit = 10;

	public int getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}

	public int getFirstIndex() {
		return firstIndex;
	}

	public void setFirstIndex(int firstIndex) {
		this.firstIndex = firstIndex;
	}

	public int getLastIndex() {
		return lastIndex;
	}

	public void setLastIndex(int lastIndex) {
		this.lastIndex = lastIndex;
	}

	public int getRecordCountPerPage() {
		return recordCountPerPage;
	}

	public void setRecordCountPerPage(int recordCountPerPage) {
		this.recordCountPerPage = recordCountPerPage;
	}

		public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPageUnit() {
		return pageUnit;
	}

	public void setPageUnit(int pageUnit) {
		this.pageUnit = pageUnit;
	}

}
