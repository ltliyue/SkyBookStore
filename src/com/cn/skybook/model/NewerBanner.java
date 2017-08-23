package com.cn.skybook.model;

import org.json.*;


public class NewerBanner {
	
    private boolean show;
    private String abtest;
    private String abtestSingle;
    
    
	public NewerBanner () {
		
	}	
        
    public NewerBanner (JSONObject json) {
    
        this.show = json.optBoolean("show");
        this.abtest = json.optString("abtest");
        this.abtestSingle = json.optString("abtestSingle");

    }
    
    public boolean getShow() {
        return this.show;
    }

    public void setShow(boolean show) {
        this.show = show;
    }

    public String getAbtest() {
        return this.abtest;
    }

    public void setAbtest(String abtest) {
        this.abtest = abtest;
    }

    public String getAbtestSingle() {
        return this.abtestSingle;
    }

    public void setAbtestSingle(String abtestSingle) {
        this.abtestSingle = abtestSingle;
    }


    
}
