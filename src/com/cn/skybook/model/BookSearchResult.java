package com.cn.skybook.model;

import org.json.*;
import java.util.ArrayList;

public class BookSearchResult {
	
    private boolean isFoot;
    private String errorCorrection;
    private boolean gaiaContent;
    private String isSpecialStock;
    private String adEventId;
    private String showStyleRule;
    private NewerBanner newerBanner;
    private ArrayList<WareList> wareList;
    private boolean hasTerm;
    private double wareCount;
    
    
	public BookSearchResult () {
		
	}	
        
    public BookSearchResult (JSONObject json) {
    
        this.isFoot = json.optBoolean("isFoot");
        this.errorCorrection = json.optString("errorCorrection");
        this.gaiaContent = json.optBoolean("gaiaContent");
        this.isSpecialStock = json.optString("isSpecialStock");
        this.adEventId = json.optString("adEventId");
        this.showStyleRule = json.optString("showStyleRule");
        this.newerBanner = new NewerBanner(json.optJSONObject("newerBanner"));

        this.wareList = new ArrayList<WareList>();
        JSONArray arrayWareList = json.optJSONArray("wareList");
        if (null != arrayWareList) {
            int wareListLength = arrayWareList.length();
            for (int i = 0; i < wareListLength; i++) {
                JSONObject item = arrayWareList.optJSONObject(i);
                if (null != item) {
                    this.wareList.add(new WareList(item));
                }
            }
        }
        else {
            JSONObject item = json.optJSONObject("wareList");
            if (null != item) {
                this.wareList.add(new WareList(item));
            }
        }

        this.hasTerm = json.optBoolean("hasTerm");
        this.wareCount = json.optDouble("wareCount");

    }
    
    public boolean getIsFoot() {
        return this.isFoot;
    }

    public void setIsFoot(boolean isFoot) {
        this.isFoot = isFoot;
    }

    public String getErrorCorrection() {
        return this.errorCorrection;
    }

    public void setErrorCorrection(String errorCorrection) {
        this.errorCorrection = errorCorrection;
    }

    public boolean getGaiaContent() {
        return this.gaiaContent;
    }

    public void setGaiaContent(boolean gaiaContent) {
        this.gaiaContent = gaiaContent;
    }

    public String getIsSpecialStock() {
        return this.isSpecialStock;
    }

    public void setIsSpecialStock(String isSpecialStock) {
        this.isSpecialStock = isSpecialStock;
    }

    public String getAdEventId() {
        return this.adEventId;
    }

    public void setAdEventId(String adEventId) {
        this.adEventId = adEventId;
    }

    public String getShowStyleRule() {
        return this.showStyleRule;
    }

    public void setShowStyleRule(String showStyleRule) {
        this.showStyleRule = showStyleRule;
    }

    public NewerBanner getNewerBanner() {
        return this.newerBanner;
    }

    public void setNewerBanner(NewerBanner newerBanner) {
        this.newerBanner = newerBanner;
    }

    public ArrayList<WareList> getWareList() {
        return this.wareList;
    }

    public void setWareList(ArrayList<WareList> wareList) {
        this.wareList = wareList;
    }

    public boolean getHasTerm() {
        return this.hasTerm;
    }

    public void setHasTerm(boolean hasTerm) {
        this.hasTerm = hasTerm;
    }

    public double getWareCount() {
        return this.wareCount;
    }

    public void setWareCount(double wareCount) {
        this.wareCount = wareCount;
    }


    
}
