package egovframework.itman.division.service;

public class EgovItmanDivisionSearchVO {

    // 그룹 ID
    private String groIdx;
    
    // 검색 조건 (all, name, code)
    private String searchCondition;
    
    // 검색어
    private String searchKeyword;
    
    // 현재 페이지 번호
    private int pageIndex = 1;
    
    // 한 페이지당 출력할 게시물 수
    private int pageUnit = 10;
    
    // 페이지 목록에 출력할 페이지 개수
    private int pageSize = 10;
    
    // 첫 번째 레코드 번호
    private int firstIndex;
    
    // 마지막 레코드 번호
    private int lastIndex;
    
    // 한 페이지당 레코드 수
    private int recordCountPerPage;

    // --- Getters and Setters ---
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

    public int getPageUnit() {
        return pageUnit;
    }

    public void setPageUnit(int pageUnit) {
        this.pageUnit = pageUnit;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
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
}