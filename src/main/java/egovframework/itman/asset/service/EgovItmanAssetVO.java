package egovframework.itman.asset.service;

public class EgovItmanAssetVO {

	private String assIdx;
	private String groIdx;
	private String assUlid;
	private String assName;
	private String assCatIdx;
	private String staIdx;
	private String locIdx;
	private String empIdx;
	private String supIdx;
	private String buyDate;
	private String price;
	private String regDate;
	private String regIdx;
	private String modDate;
	private String modIdx;
	private String image;
	private String delYn;
	private String uuid;
	
	private String assCatName;
	private String staName;
	private String empName;
	private String locName;
	private String supName;
	
	// 검색 조건
	private String searchCondition = "";
	private String searchKeyword = "";
	
	// 페이징
	private int pageIndex = 1; // 현재 페이지 번호
	private int pageSize = 10; // JSP에서 보여줄 페이지 갯수
	private int recordCountPerPage = 10; // 한 페이지당 게시되는 게시물 건 수
	private int firstIndex = 1; // 현재 페이지 시작 인덱스 (DB 쿼리 OFFSET)
	private int totalRecordCount = 0; // 전체 레코드 수 (DB 쿼리 LIMIT)
	
	public String getAssCatName() {
		return assCatName;
	}

	public void setAssCatName(String assCatName) {
		this.assCatName = assCatName;
	}

	public String getStaName() {
		return staName;
	}

	public void setStaName(String staName) {
		this.staName = staName;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getLocName() {
		return locName;
	}

	public void setLocName(String locName) {
		this.locName = locName;
	}

	public String getSupName() {
		return supName;
	}

	public void setSupName(String supName) {
		this.supName = supName;
	}

	public String getAssIdx() {
		return assIdx;
	}

	public void setAssIdx(String assIdx) {
		this.assIdx = assIdx;
	}

	public String getGroIdx() {
		return groIdx;
	}

	public void setGroIdx(String groIdx) {
		this.groIdx = groIdx;
	}

	public String getAssUlid() {
		return assUlid;
	}

	public void setAssUlid(String assUlid) {
		this.assUlid = assUlid;
	}

	public String getAssName() {
		return assName;
	}

	public void setAssName(String assName) {
		this.assName = assName;
	}

	public String getAssCatIdx() {
		return assCatIdx;
	}

	public void setAssCatIdx(String assCatIdx) {
		this.assCatIdx = assCatIdx;
	}

	public String getStaIdx() {
		return staIdx;
	}

	public void setStaIdx(String staIdx) {
		this.staIdx = staIdx;
	}

	public String getLocIdx() {
		return locIdx;
	}

	public void setLocIdx(String locIdx) {
		this.locIdx = locIdx;
	}

	public String getEmpIdx() {
		return empIdx;
	}

	public void setEmpIdx(String empIdx) {
		this.empIdx = empIdx;
	}

	public String getSupIdx() {
		return supIdx;
	}

	public void setSupIdx(String supIdx) {
		this.supIdx = supIdx;
	}

	public String getBuyDate() {
		return buyDate;
	}

	public void setBuyDate(String buyDate) {
		this.buyDate = buyDate;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
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

	public void setModIdx(String modIdx) {
		this.modIdx = modIdx;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getDelYn() {
		return delYn;
	}

	public void setDelYn(String delYn) {
		this.delYn = delYn;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
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
}

