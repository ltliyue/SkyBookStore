package com.cn.skybook.model;

import org.json.*;


public class BookInfo {
	
    private double pageNo;
    private double prev;
    private double pageCount;
    private boolean httpsConfig;
    private String sid;
    private double next;
    private String wdis;
    private String htmlContent;
    
    
	public BookInfo () {
		
	}	
        
    public double getPageNo() {
        return this.pageNo;
    }

    public void setPageNo(double pageNo) {
        this.pageNo = pageNo;
    }

    public double getPrev() {
        return this.prev;
    }

    public void setPrev(double prev) {
        this.prev = prev;
    }

    public double getPageCount() {
        return this.pageCount;
    }

    public void setPageCount(double pageCount) {
        this.pageCount = pageCount;
    }

    public boolean getHttpsConfig() {
        return this.httpsConfig;
    }

    public void setHttpsConfig(boolean httpsConfig) {
        this.httpsConfig = httpsConfig;
    }

    public String getSid() {
        return this.sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public double getNext() {
        return this.next;
    }

    public void setNext(double next) {
        this.next = next;
    }

    public String getWdis() {
        return this.wdis;
    }

    public void setWdis(String wdis) {
        this.wdis = wdis;
    }

    public String getHtmlContent() {
        return this.htmlContent;
    }

    public void setHtmlContent(String htmlContent) {
        this.htmlContent = htmlContent;
    }


    
}
