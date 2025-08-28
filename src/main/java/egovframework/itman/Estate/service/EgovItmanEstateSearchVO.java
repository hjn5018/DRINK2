package egovframework.itman.Estate.service;

import egovframework.com.cmm.ComDefaultVO;

public class EgovItmanEstateSearchVO extends ComDefaultVO {
    private static final long serialVersionUID = 1L;
    
    private String groIdx; // This field was missing
    private String searchCondition;
    private String searchKeyword;

    // Getter and setter for the new field
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
}