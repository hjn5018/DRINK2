package egovframework.itman.position.service;

import egovframework.com.cmm.ComDefaultVO;

public class EgovItmanPositionSearchVO extends ComDefaultVO {
    private static final long serialVersionUID = 1L;

    private String groIdx;
    private String searchCondition;
    private String searchKeyword;
    private String orderby;

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

    public String getOrderby() {
        return orderby;
    }

    public void setOrderby(String orderby) {
        this.orderby = orderby;
    }
}