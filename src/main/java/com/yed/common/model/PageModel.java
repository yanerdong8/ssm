package com.yed.common.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class PageModel<T> {
    //-- 分页参数 --//
    protected int pageNo = 1;
    protected int pageSize = -1;

    //-- 返回结果 --//
    protected List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
    protected long totalCount = -1;

    protected int first;
    protected T t;

    public T getT() {
        return t;
    }

    public void setT(T t) {
        this.t = t;
    }

    public PageModel(int pageNo, int pageSize) {
        this.pageNo = pageNo;
        this.pageSize = pageSize;
        this.first = (pageNo - 1) * pageSize;
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public List<Map<String, Object>> getResult() {
        return result;
    }

    public void setResult(List<Map<String, Object>> result) {
        this.result = result;
    }

    public long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(long totalCount) {
        this.totalCount = totalCount;
    }

    public int getFirst() {
        return first;
    }

    public void setFirst(int first) {
        this.first = first;
    }

}
